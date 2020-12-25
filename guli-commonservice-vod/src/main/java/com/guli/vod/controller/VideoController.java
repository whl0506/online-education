package com.guli.vod.controller;

import com.guli.common.vo.R;
import com.guli.vod.service.VideoService;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@CrossOrigin //跨域
@RestController
@RequestMapping("/admin/vod/video")
public class VideoController {

    @Autowired
    private VideoService videoService;

    @PostMapping("upload")
    public R uploadVideo(@RequestParam("file") MultipartFile file)  {
        String videoId = videoService.uploadVideo(file);
        return R.ok().message("视频上传成功").data("videoId", videoId);
    }

    @DeleteMapping("{videoId}")
    public R removeVideo(@PathVariable String videoId){
        videoService.removeVideo(videoId);
        return R.ok().message("视频删除成功");
    }

    /**
     * 批量删除视频
     * @param videoIdList
     * @return
     */
    @DeleteMapping("delete-batch")
    public R removeVideoList(
            @ApiParam(name = "videoIdList", value = "云端视频id", required = true)
            @RequestParam("videoIdList") List videoIdList){
        videoService.removeVideoList(videoIdList);
        return R.ok().message("视频删除成功");
    }

}
