package com.guli.edu.controller;

import com.guli.common.vo.R;
import com.guli.edu.service.EduSubjectService;
import com.guli.edu.vo.EduSubjectNestedVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/admin/edu/subject")
public class EduSubjectController {

    @Autowired
    private EduSubjectService eduSubjectService;

    @PostMapping("/import")
    public R addSubject(@RequestParam("file") MultipartFile file) {
        List<String> msgList = eduSubjectService.batchImport(file);
        if (msgList.size() == 0) {
            return R.ok().message("批量导入成功");
        }
        return R.error().message("批量导入失败").data("messageList",msgList);
    }

    @GetMapping
    public R nestedList(){
        List<EduSubjectNestedVo> eduSubjectNestedVoList = eduSubjectService.nestedList();
        return R.ok().data("items", eduSubjectNestedVoList);
    }

}
