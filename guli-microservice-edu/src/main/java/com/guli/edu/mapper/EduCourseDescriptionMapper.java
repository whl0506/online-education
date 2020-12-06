package com.guli.edu.mapper;

import com.guli.edu.entity.EduCourseDescription;
import com.guli.edu.entity.EduCourseDescriptionExample;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface EduCourseDescriptionMapper {
    int countByExample(EduCourseDescriptionExample example);

    int deleteByExample(EduCourseDescriptionExample example);

    int deleteByPrimaryKey(Long id);

    int insert(EduCourseDescription record);

    int insertSelective(EduCourseDescription record);

    List<EduCourseDescription> selectByExampleWithBLOBs(EduCourseDescriptionExample example);

    List<EduCourseDescription> selectByExample(EduCourseDescriptionExample example);

    EduCourseDescription selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") EduCourseDescription record, @Param("example") EduCourseDescriptionExample example);

    int updateByExampleWithBLOBs(@Param("record") EduCourseDescription record, @Param("example") EduCourseDescriptionExample example);

    int updateByExample(@Param("record") EduCourseDescription record, @Param("example") EduCourseDescriptionExample example);

    int updateByPrimaryKeySelective(EduCourseDescription record);

    int updateByPrimaryKeyWithBLOBs(EduCourseDescription record);

    int updateByPrimaryKey(EduCourseDescription record);
}