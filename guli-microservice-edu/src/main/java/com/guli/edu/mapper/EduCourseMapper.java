package com.guli.edu.mapper;

import com.guli.edu.entity.EduCourse;
import com.guli.edu.entity.EduCourseExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface EduCourseMapper {
    int countByExample(EduCourseExample example);

    int deleteByExample(EduCourseExample example);

    int deleteByPrimaryKey(Long id);

    int insert(EduCourse record);

    int insertSelective(EduCourse record);

    List<EduCourse> selectByExample(EduCourseExample example);

    EduCourse selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") EduCourse record, @Param("example") EduCourseExample example);

    int updateByExample(@Param("record") EduCourse record, @Param("example") EduCourseExample example);

    int updateByPrimaryKeySelective(EduCourse record);

    int updateByPrimaryKey(EduCourse record);
}