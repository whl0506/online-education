package com.guli.edu.entity;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class EduCourse extends BaseProperties {

    public static final String COURSE_DRAFT = "Draft"; //课程发布状态值：Draft未发布，Normal已发布
    public static final String COURSE_NORMAL = "Normal";

    private Long id;

    private Long teacherId;

    private Long subjectParentId;

    private Long subjectId;

    private String title;

    private BigDecimal price;

    private Integer lessonNum;

    private String cover;

    private Long buyCount;

    private Long viewCount;

    private Long version;

    private String status;

    private Date gmtCreate;

    private Date gmtModified;

}