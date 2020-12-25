package com.guli.edu.service.impl;

import com.guli.common.constants.ResultCodeEnum;
import com.guli.common.exception.GuliException;
import com.guli.common.vo.R;
import com.guli.edu.client.VodClient;
import com.guli.edu.dto.VideoInfoDto;
import com.guli.edu.entity.EduVideo;
import com.guli.edu.entity.EduVideoExample;
import com.guli.edu.mapper.EduVideoMapper;
import com.guli.edu.service.EduVideoService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

@Service
public class EduVideoServiceImpl implements EduVideoService {

    @Autowired
    private EduVideoMapper eduVideoMapper;

    @Autowired
    private VodClient vodClient;

    @Override
    public Boolean deleteVideoByCourseId(Long courseId) {
        EduVideoExample example = new EduVideoExample();
        example.createCriteria().andCourseIdEqualTo(courseId);
        int result = eduVideoMapper.deleteByExample(example);
        return result > 0;
    }

    @Override
    public List<EduVideo> getListByCourseId(Long courseId) {
        EduVideoExample example = new EduVideoExample();
        example.setOrderByClause("sort,id ASC");
        example.createCriteria().andCourseIdEqualTo(courseId);
        List<EduVideo> eduVideoList = eduVideoMapper.selectByExample(example);
        return eduVideoList;
    }

    @Override
    public boolean getCountByChapterId(Long id) {
        EduVideoExample example = new EduVideoExample();
        example.createCriteria().andChapterIdEqualTo(id);
        int result = eduVideoMapper.deleteByExample(example);
        return result > 0;
    }

    @Override
    public Boolean saveVideoInfo(VideoInfoDto videoInfoDto) {
        EduVideo video = new EduVideo();
        BeanUtils.copyProperties(videoInfoDto,video);
        int result = eduVideoMapper.insert(video);
        return result > 0;
    }

    @Override
    public VideoInfoDto getVideoInfoFormById(Long id) {
        EduVideo video = eduVideoMapper.selectByPrimaryKey(id);
        if (ObjectUtils.isEmpty(video)) {
            throw new GuliException(ResultCodeEnum.NO_EXISTED_DATA);
        }
        //创建videoInfoForm对象
        VideoInfoDto videoInfoDto = new VideoInfoDto();
        BeanUtils.copyProperties(video, videoInfoDto);
        return videoInfoDto;
    }

    @Override
    public Boolean updateVideoInfoById(VideoInfoDto videoInfoDto) {
        EduVideo video = new EduVideo();
        BeanUtils.copyProperties(videoInfoDto,video);
        int result = eduVideoMapper.updateByPrimaryKey(video);
        return result > 0;
    }

    @Override
    public boolean removeVideoById(Long id) {
        EduVideo video = eduVideoMapper.selectByPrimaryKey(id);
        R res = new R();
        if (!ObjectUtils.isEmpty(video)) {
            String sourceId = video.getVideoSourceId();
            if (!StringUtils.isEmpty(sourceId)) {
               res = vodClient.removeVideo(sourceId);
            }
        }
        if (res.getCode() != 20000) {
            return false;
        }
        int result = eduVideoMapper.deleteByPrimaryKey(id);
        return result > 0;
    }

    @Override
    public boolean removeByCourseId(Long courseId) {
        //根据课程id查询所有视频列表
        EduVideoExample example = new EduVideoExample();
        EduVideoExample.Criteria criteria = example.createCriteria();
        criteria.andCourseIdEqualTo(courseId);
        criteria.andVideoSourceIdIsNotNull();
        List<EduVideo> videoList = eduVideoMapper.selectByExample(example);
        //得到所有视频列表的云端原始视频id
        List<String> videoSourceIdList = new ArrayList<>();
        for (int i = 0; i < videoList.size(); i++) {
            EduVideo video = videoList.get(i);
            String videoSourceId = video.getVideoSourceId();
            if(!StringUtils.isEmpty(videoSourceId)){
                videoSourceIdList.add(videoSourceId);
            }
        }
        //调用vod服务删除远程视频
        if(videoSourceIdList.size() > 0){
            vodClient.removeVideoList(videoSourceIdList);
        }
        //删除video表示的记录
        EduVideoExample videoExample = new EduVideoExample();
        EduVideoExample.Criteria videoCriteria = videoExample.createCriteria();
        videoCriteria.andCourseIdEqualTo(courseId);
        int result = eduVideoMapper.deleteByExample(videoExample);
        return result > 0;
    }
}
