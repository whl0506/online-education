package com.guli.edu.mapper;

import com.guli.edu.entity.EduVideo;
import com.guli.edu.entity.EduVideoExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface EduVideoMapper {
    int countByExample(EduVideoExample example);

    int deleteByExample(EduVideoExample example);

    int deleteByPrimaryKey(Long id);

    int insert(EduVideo record);

    int insertSelective(EduVideo record);

    List<EduVideo> selectByExample(EduVideoExample example);

    EduVideo selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") EduVideo record, @Param("example") EduVideoExample example);

    int updateByExample(@Param("record") EduVideo record, @Param("example") EduVideoExample example);

    int updateByPrimaryKeySelective(EduVideo record);

    int updateByPrimaryKey(EduVideo record);
}