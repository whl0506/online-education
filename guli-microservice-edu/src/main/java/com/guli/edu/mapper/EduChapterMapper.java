package com.guli.edu.mapper;

import com.guli.edu.entity.EduChapter;
import com.guli.edu.entity.EduChapterExample;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface EduChapterMapper {
    int countByExample(EduChapterExample example);

    int deleteByExample(EduChapterExample example);

    int deleteByPrimaryKey(Long id);

    int insert(EduChapter record);

    int insertSelective(EduChapter record);

    List<EduChapter> selectByExample(EduChapterExample example);

    EduChapter selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") EduChapter record, @Param("example") EduChapterExample example);

    int updateByExample(@Param("record") EduChapter record, @Param("example") EduChapterExample example);

    int updateByPrimaryKeySelective(EduChapter record);

    int updateByPrimaryKey(EduChapter record);
}