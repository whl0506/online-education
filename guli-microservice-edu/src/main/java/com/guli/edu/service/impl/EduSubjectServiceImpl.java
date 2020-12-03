package com.guli.edu.service.impl;

import com.guli.edu.mapper.EduSubjectMapper;
import com.guli.edu.service.EduSubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
public class EduSubjectServiceImpl implements EduSubjectService {

    @Autowired
    private EduSubjectMapper eduSubjectMapper;

    @Override
    public List<String> batchImport(MultipartFile file) {
        // 1.获取Excel文件输入流
        // 2.循环读取Excel中每row
        // 3.获取每列中数据写入数据库
        return null;
    }
}
