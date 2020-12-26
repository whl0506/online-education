package com.atguigu.cmsservice.service.impl;

import com.atguigu.cmsservice.entity.CrmBanner;
import com.atguigu.cmsservice.entity.CrmBannerExample;
import com.atguigu.cmsservice.mapper.CrmBannerMapper;
import com.atguigu.cmsservice.service.CrmBannerService;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CrmBannerServiceImpl implements CrmBannerService {

    @Autowired
    private CrmBannerMapper crmBannerMapper;

    @Override
    public List<CrmBanner> pageBanner(Integer page, Integer limit) {
        PageHelper.startPage(page,limit);
        List<CrmBanner> crmBanners = crmBannerMapper.selectByExample(null);
        return crmBanners;
    }

    @Override
    public CrmBanner getBannerById(Long id) {
        return crmBannerMapper.selectByPrimaryKey(id);
    }

    @Override
    public Boolean saveBanner(CrmBanner banner) {
        int result = crmBannerMapper.insertSelective(banner);
        return result > 0;
    }

    @Override
    public Boolean updateBannerById(CrmBanner banner) {
        int result = crmBannerMapper.updateByPrimaryKeySelective(banner);
        return result > 0;
    }

    @Override
    public Boolean removeBannerById(Long id) {
        CrmBanner banner = new CrmBanner();
        banner.setId(id);
        banner.setIsDeleted(true);
        int result = crmBannerMapper.updateByPrimaryKeySelective(banner);
        return result > 0;
    }

    @Override
    public List<CrmBanner> selectIndexList() {
        List<CrmBanner> crmBanners = crmBannerMapper.selectByExample(new CrmBannerExample());
        return crmBanners;
    }
}
