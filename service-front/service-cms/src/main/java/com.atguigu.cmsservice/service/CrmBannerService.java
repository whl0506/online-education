package com.atguigu.cmsservice.service;

import com.atguigu.cmsservice.entity.CrmBanner;

import java.util.List;

public interface CrmBannerService {
    List<CrmBanner> pageBanner(Integer page, Integer limit);

    CrmBanner getBannerById(Long id);

    Boolean saveBanner(CrmBanner banner);

    Boolean updateBannerById(CrmBanner banner);

    Boolean removeBannerById(Long id);

    List<CrmBanner> selectIndexList();
}
