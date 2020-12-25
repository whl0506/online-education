package com.guli.edu.service;

import com.guli.edu.dto.VideoInfoDto;
import com.guli.edu.entity.EduVideo;

import java.util.List;

public interface EduVideoService {
    Boolean deleteVideoByCourseId(Long courseId);

    List<EduVideo> getListByCourseId(Long courseId);

    boolean getCountByChapterId(Long id);

    Boolean saveVideoInfo(VideoInfoDto videoInfoDto);

    VideoInfoDto getVideoInfoFormById(Long id);

    Boolean updateVideoInfoById(VideoInfoDto videoInfoDto);

    boolean removeVideoById(Long id);

    boolean removeByCourseId(Long courseId);

}
