package com.guli.edu.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
@ApiModel(value = "EduTeacher对象",description = "讲师")
public class EduTeacher implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "讲师ID")
    private Long id;
    @ApiModelProperty(value = "讲师姓名")
    private String name;
    @ApiModelProperty(value = "讲师资历")
    private String intro;
    private String career;
    private Integer level;
    private String avatar;
    private Integer sort;
    private Integer isDeleted;
    private Date gmtCreate;
    private Date gmtModified;

}
