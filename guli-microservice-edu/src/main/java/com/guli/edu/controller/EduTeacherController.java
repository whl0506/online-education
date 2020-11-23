package com.guli.edu.controller;

import com.guli.common.vo.R;
import com.guli.edu.dto.TeacherDto;
import com.guli.edu.entity.EduTeacher;
import com.guli.edu.service.EduTeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/admin/edu/teacher")
public class EduTeacherController {

    @Autowired
    private EduTeacherService eduTeacherService;

    /**
     * 查询所有讲师
     *
     * @return
     */
    @GetMapping("/list")
    public R getTeacherList() {
        List<EduTeacher> teacherList = eduTeacherService.getTeacherList();
        return R.ok().data("items", teacherList);
    }

    /**
     * 根据条件分页查询讲师
     *
     * @param page
     * @param limit
     * @param teacherDto
     * @return
     */
    @GetMapping("/page/{page}/{limit}")
    public R pageList(@PathVariable String page,
                      @PathVariable String limit,
                      @RequestBody(required = false) TeacherDto teacherDto) {
        Map resultMap = eduTeacherService.getPageList(page, limit, teacherDto);
        return R.ok().data(resultMap);
    }

    /**
     * 根据id逻辑删除讲师
     *
     * @param id
     * @return
     */
    @DeleteMapping("/delete/{id}")
    public R deleteTeacher(@PathVariable String id) {
        Boolean result = eduTeacherService.deleteTeacher(id);
        if (result) {
            return R.ok();
        }
        return R.error();
    }

    @PostMapping("/add_or_update")
    public R addTeacher(@RequestBody EduTeacher eduTeacher ) {
        Boolean result = eduTeacherService.addOrUpdateTeacher(eduTeacher);
        if (result) {
            return R.ok();
        }
        return R.error();
    }

}
