package com.guli.edu.controller;

import com.guli.edu.entity.EduTeacher;
import com.guli.edu.service.EduTeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/admin/edu/teacher")
public class EduTeacherController {

    @Autowired
    private EduTeacherService eduTeacherService;

    @GetMapping("/list")
    public List<EduTeacher> getTeacherList(){
        List<EduTeacher> teacherList = eduTeacherService.getTeacherList();
        return teacherList;
    }


}
