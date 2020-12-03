package com.guli.edu.mapper;

import com.guli.edu.entity.EduSubject;
import com.guli.edu.entity.EduSubjectExample;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface EduSubjectMapper {
    int countByExample(EduSubjectExample example);

    int deleteByExample(EduSubjectExample example);

    int deleteByPrimaryKey(String id);

    int insert(EduSubject record);

    int insertSelective(EduSubject record);

    List<EduSubject> selectByExample(EduSubjectExample example);

    EduSubject selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") EduSubject record, @Param("example") EduSubjectExample example);

    int updateByExample(@Param("record") EduSubject record, @Param("example") EduSubjectExample example);

    int updateByPrimaryKeySelective(EduSubject record);

    int updateByPrimaryKey(EduSubject record);
}