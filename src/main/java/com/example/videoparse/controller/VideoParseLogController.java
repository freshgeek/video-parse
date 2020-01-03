package com.example.videoparse.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;

import org.springframework.web.bind.annotation.*;

import com.example.videoparse.common.BaseController;
import com.example.videoparse.common.Response;
import com.example.videoparse.service.VideoParseLogService;
import com.example.videoparse.entity.VideoParseLog;

import java.io.Serializable;

/**
* <p>
* 日志 前端控制器
* </p>
*
* @author cc 
    qq: 3570632401  
     淘宝链接:[https://m.tb.cn/h.ewA2rg5?sm=60123d]
* @since 2020-01-03
*/

@RestController
@Api(tags = "日志")
@RequestMapping("/admin/api/videoParseLog")
 public class VideoParseLogController extends BaseController {

    @Autowired
    private VideoParseLogService videoParseLogService;


    @GetMapping("/page")
    @ApiOperation("分页")
    public Response getPages(VideoParseLog videoParseLog){
        IPage page = videoParseLogService.page(videoParseLog.convertPage(),new QueryWrapper<VideoParseLog>(videoParseLog));
        return Response.parse(page);
    }

    @PostMapping("/add")
    @ApiOperation("新增")
    public Response add(VideoParseLog videoParseLog){
        videoParseLogService.save(videoParseLog);
        return Response.success();
    }

    @PostMapping("/delete")
    @ApiOperation("删除")
    public Response delete(VideoParseLog  videoParseLog){
        videoParseLogService.removeById(videoParseLog.getId());
        return Response.success();
    }

    @PostMapping("/update")
    @ApiOperation("更新")
    public Response update(VideoParseLog  videoParseLog){
        boolean update = videoParseLogService.updateById(videoParseLog);
        return update ? Response.success() : Response.exception("更新失败");
    }

    @GetMapping("/list")
    @ApiOperation("列表")
    public Response list(VideoParseLog  videoParseLog){
        return Response.success(videoParseLogService.list(new QueryWrapper<VideoParseLog>(videoParseLog)));
    }

    @GetMapping("/find/{id}")
    @ApiOperation("通过id查询")
    public Response find(@PathVariable("id") Serializable id){
        return new Response(videoParseLogService.getById(id));
    }

}
