package com.guli.edu.entity;

import lombok.Data;

import java.util.Date;

@Data
public class EduVideo extends BaseProperties {
    private Long id;

    private Long courseId;

    private Long chapterId;

    private String title;

    private Integer sort;

    private Long playCount;

    private Boolean isFree;

    private String videoSourceId;

    private Float duration;

    private String status;

    private Long size;

    private Long version;

    private Date gmtCreate;

    private Date gmtModified;

}