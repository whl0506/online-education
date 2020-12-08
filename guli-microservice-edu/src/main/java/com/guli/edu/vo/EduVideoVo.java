package com.guli.edu.vo;

import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.io.Serializable;

@ApiModel(value = "章节信息")
@Data
public class EduVideoVo implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;
    private String title;
    private Boolean isFree;
}
