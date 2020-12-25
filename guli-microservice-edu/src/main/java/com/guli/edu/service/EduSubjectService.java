package com.guli.edu.service;

import com.guli.edu.entity.EduSubject;
import com.guli.edu.vo.EduSubjectNestedVo;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface EduSubjectService {

    List<String> batchImport(MultipartFile file);

    List<EduSubjectNestedVo> nestedList();

    Boolean removeSubjectById(Long id);

    String saveSubject(EduSubject eduSubject);

    EduSubject getSubjectById(Long subjectId);
}
