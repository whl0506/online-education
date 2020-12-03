package com.guli.edu.vo;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class EduSubjectNestedVo {

    private Long id;
    private String title;
    private List<EduSubjectVo> children = new ArrayList<>();
}
