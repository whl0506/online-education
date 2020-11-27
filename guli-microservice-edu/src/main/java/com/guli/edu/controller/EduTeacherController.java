package com.guli.edu.controller;

import com.guli.common.vo.R;
import com.guli.edu.dto.TeacherDto;
import com.guli.edu.entity.EduTeacher;
import com.guli.edu.service.EduTeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@CrossOrigin //解决403跨域问题
@RestController
@RequestMapping("/admin/edu/teacher")
public class EduTeacherController {

    @Autowired
    private EduTeacherService eduTeacherService;

    //{"code":20000,"data":{"token":"admin"}}
    //模拟登陆
    @PostMapping("login")
    public R login() {
        return R.ok().data("token","admin");
    }

    //{"code":20000,"data":{"roles":["admin"],"name":"admin",
    // "avatar":"https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif"}}
    @GetMapping("info")
    public R info() {
        return R.ok().data("roles","[admin]").data("name","admin").data("avatar","https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif");
    }

    /**
     * 查询所有讲师
     * @return
     */
    @GetMapping("/list")
    public R getTeacherList() {
        List<EduTeacher> teacherList = eduTeacherService.getTeacherList();
        return R.ok().data("items", teacherList);
    }

    @GetMapping("/{id}")
    public R getById(@PathVariable Long id) {
        EduTeacher eduTeacher = eduTeacherService.getTeacherById(id);
        return R.ok().data("item",eduTeacher);
    }

    /**
     * 根据条件分页查询讲师
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
     * @param id
     * @return
     */
    @DeleteMapping("/delete/{id}")
    public R deleteTeacher(@PathVariable String id) {
        Boolean result = eduTeacherService.deleteTeacher(id);
        if (result) {
            return R.ok();
        }
        return R.error().message("删除失败");
    }

    /**
     * 保存或更新讲师信息
     * @param eduTeacher
     * @return
     */
    @PostMapping("/add_or_update")
    public R addTeacher(@RequestBody EduTeacher eduTeacher ) {
        Boolean result = eduTeacherService.addOrUpdateTeacher(eduTeacher);
        if (result) {
            return R.ok();
        }
        return R.error();
    }

}
