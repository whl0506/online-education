package com.atguigu.cmsservice.mapper;

import com.atguigu.cmsservice.entity.CrmBanner;
import com.atguigu.cmsservice.entity.CrmBannerExample;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface CrmBannerMapper {
    int countByExample(CrmBannerExample example);

    int deleteByExample(CrmBannerExample example);

    int deleteByPrimaryKey(Long id);

    int insert(CrmBanner record);

    int insertSelective(CrmBanner record);

    List<CrmBanner> selectByExample(CrmBannerExample example);

    CrmBanner selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") CrmBanner record, @Param("example") CrmBannerExample example);

    int updateByExample(@Param("record") CrmBanner record, @Param("example") CrmBannerExample example);

    int updateByPrimaryKeySelective(CrmBanner record);

    int updateByPrimaryKey(CrmBanner record);
}