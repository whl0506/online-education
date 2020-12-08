package com.guli.edu.service;

import com.guli.edu.dto.CourseInfoForm;
import com.guli.edu.query.CourseQuery;

import java.util.Map;

public interface EduCourseService {

    String saveCourseInfo(CourseInfoForm courseInfoForm);

    CourseInfoForm getCourseInfoFormById(Long id);

    void updateCourseInfoById(CourseInfoForm courseInfoForm);

    Map getPageList(int page, int limit, CourseQuery courseQuery);
}
