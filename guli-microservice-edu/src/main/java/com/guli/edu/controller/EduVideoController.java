package com.guli.edu.controller;

import com.guli.common.vo.R;
import com.guli.edu.dto.VideoInfoDto;
import com.guli.edu.service.EduVideoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Api(description="课时管理")
@CrossOrigin //跨域
@RestController
@RequestMapping("/admin/edu/video")
public class EduVideoController {

    @Autowired
    private EduVideoService videoService;

    @ApiOperation(value = "新增课时")
    @PostMapping("save-video-info")
    public R save(
            @ApiParam(name = "videoForm", value = "课时对象", required = true)
            @RequestBody VideoInfoDto videoInfoDto){
        videoService.saveVideoInfo(videoInfoDto);
        return R.ok();
    }

    @ApiOperation(value = "根据ID查询课时")
    @GetMapping("video-info/{id}")
    public R getVideoInfoById(
            @ApiParam(name = "id", value = "课时ID", required = true)
            @PathVariable Long id){
        VideoInfoDto videoInfoDto = videoService.getVideoInfoFormById(id);
        return R.ok().data("item", videoInfoDto);
    }

    @ApiOperation(value = "根据id修改课时")
    @PutMapping("update-video-info/{id}")
    public R updateCourseInfoById(
            @ApiParam(name = "VideoInfoDto", value = "课时基本信息", required = true)
            @RequestBody VideoInfoDto videoInfoDto,
            @ApiParam(name = "id", value = "课时ID", required = true)
            @PathVariable Long id){
        videoService.updateVideoInfoById(videoInfoDto);
        return R.ok();
    }

    @ApiOperation(value = "根据ID删除课时")
    @DeleteMapping("{id}")
    public R removeById(
            @ApiParam(name = "id", value = "课时ID", required = true)
            @PathVariable Long id){
        boolean result = videoService.removeVideoById(id);
        if(result){
            return R.ok();
        }
        return R.error().message("删除失败");
    }

}
