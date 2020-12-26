package com.guli.edu.controller;

import com.guli.common.vo.R;
import com.guli.edu.service.EduCourseService;
import com.guli.edu.service.EduTeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/eduservice")
@CrossOrigin
public class IndexController {

    @Autowired
    private EduCourseService courseService;
    @Autowired
    private EduTeacherService teacherService;

    //查询前8条热门课程，查询前4条名师
    @GetMapping("/index")
    public R index() {
        //查询前8条热门课程
        Map courseMap = courseService.getPageList(1, 8, null);
        //查询前4条热门讲师
        Map teacherList = teacherService.getPageList("1", "4", null);
        return R.ok().data("courseList",courseMap.get("rows")).data("teacherList",teacherList.get("items"));
    }

}
