package com.guli.edu.service;

import com.guli.edu.dto.TeacherDto;
import com.guli.edu.entity.EduTeacher;

import java.util.List;
import java.util.Map;

public interface EduTeacherService {

    List<EduTeacher> getTeacherList();

    Map getPageList(String page, String limit, TeacherDto teacherDto);

    Boolean deleteTeacher(String id);

    Boolean addOrUpdateTeacher(EduTeacher eduTeacher);

    EduTeacher getTeacherById(Long id);
}
