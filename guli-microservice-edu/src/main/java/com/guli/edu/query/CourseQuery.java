package com.guli.edu.query;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@ApiModel(value = "Course查询对象", description = "课程查询对象封装")
@Data
public class CourseQuery implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "课程名称")
    private String title;

    @ApiModelProperty(value = "讲师id")
    private Long teacherId;

    @ApiModelProperty(value = "一级类别id")
    private Long subjectParentId;

    @ApiModelProperty(value = "二级类别id")
    private Long subjectId;

}
