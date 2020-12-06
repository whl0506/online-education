package com.guli.edu.service.impl;

import com.guli.common.constants.ResultCodeEnum;
import com.guli.common.exception.GuliException;
import com.guli.edu.dto.CourseInfoForm;
import com.guli.edu.entity.EduCourse;
import com.guli.edu.entity.EduCourseDescription;
import com.guli.edu.mapper.EduCourseDescriptionMapper;
import com.guli.edu.mapper.EduCourseMapper;
import com.guli.edu.service.EduCourseService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class EduCourseServiceImpl implements EduCourseService {

    @Autowired
    private EduCourseMapper eduCourseMapper;

    @Autowired
    private EduCourseDescriptionMapper courseDescriptionMapper;

    @Override
    @Transactional
    public String saveCourseInfo(CourseInfoForm courseInfoForm) {
        EduCourse eduCourse = new EduCourse();
        eduCourse.setStatus(EduCourse.COURSE_DRAFT);
        BeanUtils.copyProperties(courseInfoForm,eduCourse);
        int result = eduCourseMapper.insert(eduCourse);
        if (result <= 0){
            throw new GuliException(ResultCodeEnum.UNKNOWN_REASON);
        }
        EduCourseDescription courseDescription = new EduCourseDescription();
        courseDescription.setId(eduCourse.getId());
        courseDescription.setDescription(courseInfoForm.getDescription());
        int flag = courseDescriptionMapper.insert(courseDescription);
        if (flag <=0 ){
            throw new GuliException(ResultCodeEnum.UNKNOWN_REASON);
        }
        return eduCourse.getId().toString();
    }
}
