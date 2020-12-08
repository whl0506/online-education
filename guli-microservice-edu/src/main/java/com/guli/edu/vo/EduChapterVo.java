package com.guli.edu.vo;

import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@ApiModel(value = "章节信息")
@Data
public class EduChapterVo implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;
    private String title;
    private List<EduVideoVo> children = new ArrayList<>();
}
