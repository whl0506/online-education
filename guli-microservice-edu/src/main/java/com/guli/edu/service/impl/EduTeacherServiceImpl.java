package com.guli.edu.service.impl;

import com.guli.edu.entity.EduTeacher;
import com.guli.edu.mapper.EduTeacherMapper;
import com.guli.edu.service.EduTeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EduTeacherServiceImpl implements EduTeacherService {

    @Autowired
    private EduTeacherMapper eduTeacherMapper;

    @Override
    public List<EduTeacher> getTeacherList() {
        List<EduTeacher> eduTeacherList = eduTeacherMapper.selectByExample(null);
        return eduTeacherList;
    }
}
