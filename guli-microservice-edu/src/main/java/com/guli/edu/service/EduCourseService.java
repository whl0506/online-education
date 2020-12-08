package com.guli.edu.service;

import com.guli.edu.dto.CourseInfoDto;
import com.guli.edu.query.CourseQuery;

import java.util.Map;

public interface EduCourseService {

    String saveCourseInfo(CourseInfoDto courseInfoDto);

    CourseInfoDto getCourseInfoFormById(Long id);

    void updateCourseInfoById(CourseInfoDto courseInfoDto);

    Map getPageList(int page, int limit, CourseQuery courseQuery);

    boolean removeCourseById(Long id);
}
