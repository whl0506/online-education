package com.guli.edu.controller;

import com.guli.common.vo.R;
import com.guli.edu.dto.CourseInfoDto;
import com.guli.edu.query.CourseQuery;
import com.guli.edu.service.EduCourseService;
import com.guli.edu.vo.EduCoursePublishVo;
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
    public R saveCourseInfo(@RequestBody CourseInfoDto courseInfoDto) {
        String courseId = eduCourseService.saveCourseInfo(courseInfoDto);
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
        CourseInfoDto courseInfoDto = eduCourseService.getCourseInfoFormById(id);
        return R.ok().data("item", courseInfoDto);
    }

    @ApiOperation(value = "更新课程")
    @PutMapping("update-course-info/{id}")
    public R updateCourseInfoById(
            @ApiParam(name = "CourseInfoDto", value = "课程基本信息", required = true)
            @RequestBody CourseInfoDto courseInfoDto){
        eduCourseService.updateCourseInfoById(courseInfoDto);
        return R.ok();
    }

    @ApiOperation(value = "根据ID删除课程")
    @DeleteMapping("{id}")
    public R removeById(
            @ApiParam(name = "id", value = "课程ID", required = true)
            @PathVariable Long id){
        boolean result = eduCourseService.removeCourseById(id);
        if(result){
            return R.ok();
        }
        return R.error().message("删除失败");
    }

    @ApiOperation(value = "根据课程id获取课程发布信息")
    @GetMapping("course-publish-info/{id}")
    public R getPublishInfoByCourseId(
            @ApiParam(name = "id",value = "课程ID",required = true)
            @PathVariable Long id){
        EduCoursePublishVo publishVo = eduCourseService.getPublishInfoById(id);
        return R.ok().data("item",publishVo);
    }

    @ApiOperation(value = "发布课程")
    @PutMapping("publish-course/{id}")
    public R publishCourse(
            @ApiParam(name = "id",value = "课程ID",required = true)
            @PathVariable Long id){
        Boolean result = eduCourseService.publishCourse(id);
        if (result) {
            return R.ok();
        }
        return R.error().message("发布课程失败");
    }
}
