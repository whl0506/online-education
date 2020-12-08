package com.guli.edu.service;

import com.guli.edu.entity.EduChapter;
import com.guli.edu.vo.EduChapterVo;

import java.util.List;

public interface EduChapterService {
    Boolean removeByCourseId(Long courseId);

    List<EduChapterVo> nestedList(Long courseId);

    Boolean saveChapter(EduChapter chapter);

    EduChapter getById(Long id);

    Boolean updateById(EduChapter chapter);

    boolean removeChapterById(Long id);
}
