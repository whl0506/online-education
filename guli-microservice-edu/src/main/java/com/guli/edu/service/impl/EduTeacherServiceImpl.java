package com.guli.edu.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.guli.common.constants.ResultCodeEnum;
import com.guli.common.exception.GuliException;
import com.guli.edu.dto.TeacherDto;
import com.guli.edu.entity.EduTeacher;
import com.guli.edu.entity.EduTeacherExample;
import com.guli.edu.mapper.EduTeacherMapper;
import com.guli.edu.service.EduTeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class EduTeacherServiceImpl implements EduTeacherService {

    @Autowired
    private EduTeacherMapper eduTeacherMapper;

    @Override
    public List<EduTeacher> getTeacherList() {
        List<EduTeacher> eduTeacherList = eduTeacherMapper.selectByExample(null);
        return eduTeacherList;
    }

    @Override
    public Map getPageList(String page, String limit, TeacherDto teacherDto) {
        if (Integer.valueOf(page) <= 0 || Integer.valueOf(limit) <= 0) {
            throw new GuliException(ResultCodeEnum.PARAM_ERROR);
        }
        Page<EduTeacher> startPage = PageHelper.startPage(Integer.valueOf(page), Integer.valueOf(limit));
        List<EduTeacher> eduTeachers;
        if (ObjectUtils.isEmpty(teacherDto)) {
            eduTeachers = eduTeacherMapper.selectByExample(null);
        } else {
            EduTeacherExample eduTeacherExample = new EduTeacherExample();
            EduTeacherExample.Criteria criteria = eduTeacherExample.createCriteria();
            String teacherDtoName = teacherDto.getName();
            Integer teacherDtoLevel = teacherDto.getLevel();
            String teacherDtoBegin = teacherDto.getBegin();
            String teacherDtoEnd = teacherDto.getEnd();
            if (!StringUtils.isEmpty(teacherDtoName)) {
                criteria.andNameLike(teacherDtoName+"%");
            }
            if (!StringUtils.isEmpty(teacherDtoLevel)) {
                criteria.andLevelEqualTo(teacherDtoLevel);
            }
            Date teacherBegin = parseToDate(teacherDtoBegin);
            if (!ObjectUtils.isEmpty(teacherBegin)) {
                criteria.andGmtCreateGreaterThanOrEqualTo(teacherBegin);
            }
            Date teacherEnd = parseToDate(teacherDtoEnd);
            if (!ObjectUtils.isEmpty(teacherEnd)) {
                criteria.andGmtModifiedLessThanOrEqualTo(teacherEnd);
            }
//          mybatis默认降序
            eduTeacherExample.setOrderByClause("id desc");
            eduTeachers = eduTeacherMapper.selectByExample(eduTeacherExample);
        }
        HashMap pageResult = new HashMap();
        pageResult.put("total",startPage.getTotal());
        pageResult.put("items",eduTeachers);
        return pageResult;
    }

    @Override
    public Boolean deleteTeacher(String id) {
        EduTeacher eduTeacher = new EduTeacher();
        eduTeacher.setId(Long.parseLong(id));
        eduTeacher.setIsDeleted(1);
        int result = eduTeacherMapper.updateByPrimaryKeySelective(eduTeacher);
        if (result == 0){
            return false;
        }
        return true;
    }

    @Override
    public Boolean addOrUpdateTeacher(EduTeacher eduTeacher) {
        int result = 0;
        if (!ObjectUtils.isEmpty(eduTeacher)) {
            Long teacherId = eduTeacher.getId();
            if (ObjectUtils.isEmpty(teacherId)) {
                result = eduTeacherMapper.insert(eduTeacher);
            } else {
                result = eduTeacherMapper.updateByPrimaryKeySelective(eduTeacher);
            }
        }
        if (result == 0) {
            return false;
        }
        return true;
    }

    public Date parseToDate(String time) {
        if (!StringUtils.isEmpty(time)) {
            DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            try {
                return format.parse(time);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

}
