package com.guli.edu.service.impl;

import com.guli.common.constants.ResultCodeEnum;
import com.guli.common.exception.GuliException;
import com.guli.edu.dto.VideoInfoDto;
import com.guli.edu.entity.EduVideo;
import com.guli.edu.entity.EduVideoExample;
import com.guli.edu.mapper.EduVideoMapper;
import com.guli.edu.service.EduVideoService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.List;

@Service
public class EduVideoServiceImpl implements EduVideoService {

    @Autowired
    private EduVideoMapper eduVideoMapper;

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
        int result = eduVideoMapper.deleteByPrimaryKey(id);
        return result > 0;
    }
}
