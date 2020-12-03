package com.guli.edu.entity;

import lombok.Data;

import java.util.Date;

@Data
public class EduSubject extends BaseProperties{
    private Long id;

    private String title;

    private Long parentId;

    private Integer sort;

    private Date gmtCreate;

    private Date gmtModified;

}