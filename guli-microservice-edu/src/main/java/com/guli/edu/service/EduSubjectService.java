package com.guli.edu.service;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface EduSubjectService {

    List<String> batchImport(MultipartFile file);

}
