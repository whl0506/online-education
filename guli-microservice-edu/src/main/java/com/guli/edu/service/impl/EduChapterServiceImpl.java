package com.guli.edu.service.impl;

import com.guli.common.constants.ResultCodeEnum;
import com.guli.common.exception.GuliException;
import com.guli.edu.entity.EduChapter;
import com.guli.edu.entity.EduChapterExample;
import com.guli.edu.entity.EduVideo;
import com.guli.edu.mapper.EduChapterMapper;
import com.guli.edu.service.EduChapterService;
import com.guli.edu.service.EduVideoService;
import com.guli.edu.vo.EduChapterVo;
import com.guli.edu.vo.EduVideoVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.ArrayList;
import java.util.List;

@Service
public class EduChapterServiceImpl implements EduChapterService {

    @Autowired
    private EduChapterMapper eduChapterMapper;

    @Autowired
    private EduVideoService eduVideoService;

    @Override
    public Boolean removeByCourseId(Long courseId) {
        EduChapterExample example = new EduChapterExample();
        example.createCriteria().andCourseIdEqualTo(courseId);
        int result = eduChapterMapper.deleteByExample(example);
        return result > 0;
    }

    @Override
    public List<EduChapterVo> nestedList(Long courseId) {
        //最终要的到的数据列表
        List<EduChapterVo> chapterVoList = new ArrayList<>();
        //获取章节信息
        EduChapterExample example = new EduChapterExample();
        example.setOrderByClause("sort,id ASC");
        example.createCriteria().andCourseIdEqualTo(courseId);
        List<EduChapter> chapters = eduChapterMapper.selectByExample(example);
        //获取视频信息
        List<EduVideo> videoList = eduVideoService.getListByCourseId(courseId);
       //最终数据列表填充
        for (EduChapter chapter : chapters) {
            //填充章节vo数据
            EduChapterVo chapterVo = new EduChapterVo();
            BeanUtils.copyProperties(chapter,chapterVo);
            //填充视频vo信息
            List<EduVideoVo> videoVoList = new ArrayList<>();
            for (EduVideo video : videoList) {
                if (video.getChapterId() == chapter.getId()) {
                    EduVideoVo videoVo = new EduVideoVo();
                    BeanUtils.copyProperties(video,videoVo);
                    videoVoList.add(videoVo);
                }

            }
            chapterVo.setChildren(videoVoList);
            chapterVoList.add(chapterVo);
        }
        return chapterVoList;
    }

    @Override
    public Boolean saveChapter(EduChapter chapter) {
        int insert = eduChapterMapper.insert(chapter);
        return insert > 0;
    }

    @Override
    public EduChapter getById(Long id) {
        EduChapter chapter = eduChapterMapper.selectByPrimaryKey(id);
        if (ObjectUtils.isEmpty(chapter)) {
            throw new GuliException(ResultCodeEnum.NO_EXISTED_DATA);
        }
        return chapter;
    }

    @Override
    public Boolean updateById(EduChapter chapter) {
        int update = eduChapterMapper.updateByPrimaryKey(chapter);
        return update > 0;
    }

    @Override
    public boolean removeChapterById(Long id) {
        //根据id查询是否存在视频，如果有则提示用户尚有子节点
        if(eduVideoService.getCountByChapterId(id)){
            throw new GuliException(20001,"该分章节下存在视频课程，请先删除视频课程");
        }
        Integer result = eduChapterMapper.deleteByPrimaryKey(id);
        return null != result && result > 0;
    }
}
