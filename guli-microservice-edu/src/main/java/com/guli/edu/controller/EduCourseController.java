package com.guli.edu.controller;

import com.guli.common.vo.R;
import com.guli.edu.dto.CourseInfoForm;
import com.guli.edu.query.CourseQuery;
import com.guli.edu.service.EduCourseService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Api(description="课程管理")
@RestController
@CrossOrigin
@RequestMapping("/admin/edu/course")
public class EduCourseController {

    @Autowired
    private EduCourseService eduCourseService;

    @ApiOperation(value = "获取课程信息列表")
    @GetMapping("/{page}/{limit}")
    public R getCoursePageList(@ApiParam(name = "page", value = "当前页码", required = true)
                                   @PathVariable int page,
                               @ApiParam(name = "limit", value = "每页记录数", required = true)
                                   @PathVariable int limit,
                               @ApiParam(name = "courseQuery", value = "查询对象", required = false)
                                       CourseQuery courseQuery ) {
        Map resultMap  =eduCourseService.getPageList(page,limit,courseQuery);
        return R.ok().data(resultMap);
    }

    @ApiOperation(value = "保存课程信息")
    @PostMapping("/save-course-info")
    public R saveCourseInfo(@RequestBody CourseInfoForm courseInfoForm) {
        String courseId = eduCourseService.saveCourseInfo(courseInfoForm);
        if(!StringUtils.isEmpty(courseId)) {
            return R.ok().data("courseId", courseId);
        }
        return R.error().message("保存失败");
    }

    @ApiOperation(value = "根据ID查询课程")
    @GetMapping("course-info/{id}")
    public R getById(
            @ApiParam(name = "id", value = "课程ID", required = true)
            @PathVariable Long id){

        CourseInfoForm courseInfoForm = eduCourseService.getCourseInfoFormById(id);
        return R.ok().data("item", courseInfoForm);
    }

    @ApiOperation(value = "更新课程")
    @PutMapping("update-course-info/{id}")
    public R updateCourseInfoById(
            @ApiParam(name = "CourseInfoForm", value = "课程基本信息", required = true)
            @RequestBody CourseInfoForm courseInfoForm){

        eduCourseService.updateCourseInfoById(courseInfoForm);
        return R.ok();
    }


}
