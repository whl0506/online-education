package com.guli.edu.controller;

import com.guli.common.vo.R;
import com.guli.edu.dto.CourseInfoForm;
import com.guli.edu.service.EduCourseService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

@Api(description="课程管理")
@RestController
@CrossOrigin
@RequestMapping("/admin/edu/course")
public class EduCourseController {

    @Autowired
    private EduCourseService eduCourseService;

    @PostMapping("/save-course-info")
    public R saveCourseInfo(@RequestBody CourseInfoForm courseInfoForm) {
        String courseId = eduCourseService.saveCourseInfo(courseInfoForm);
        if(!StringUtils.isEmpty(courseId)) {
            return R.ok().data("courseId", courseId);
        }
        return R.error().message("保存失败");
    }


}
