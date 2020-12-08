package com.guli.edu.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

@Data
public class EduSubject extends BaseProperties{
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Long id;

    private String title;

    private Long parentId;

    private Integer sort;

    private Date gmtCreate;

    private Date gmtModified;

}