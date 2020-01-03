package com.example.videoparse.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.example.videoparse.common.NetUtils;
import com.example.videoparse.entity.VideoParseLog;
import com.example.videoparse.service.VideoParseLogService;
import com.sun.deploy.association.utility.AppUtility;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;

import org.springframework.web.bind.annotation.*;

import com.example.videoparse.common.BaseController;
import com.example.videoparse.common.Response;
import com.example.videoparse.service.VideoParseUrlService;
import com.example.videoparse.entity.VideoParseUrl;

import java.io.Serializable;
import java.util.*;

/**
 * <p>
 * 基础解析路径 前端控制器
 * </p>
 *
 * @author cc
 * qq: 3570632401
 * 淘宝链接:[https://m.tb.cn/h.ewA2rg5?sm=60123d]
 * @since 2020-01-03
 */

@RestController
@Api(tags = "基础解析路径")
@RequestMapping("/admin/api/videoParseUrl")
public class VideoParseUrlController extends BaseController {

    @Autowired
    private VideoParseUrlService videoParseUrlService;

    @Autowired
    private VideoParseLogService videoParseLogService;

    @GetMapping("/findDouble")
    @ApiOperation("获取有效权重解析地址")
    public Response findDouble(VideoParseUrl videoParseUrl) {

        List<VideoParseUrl> body = new ArrayList();
        //查询两个有效地址
        List<VideoParseUrl> list = videoParseUrlService.list(new QueryWrapper<>(videoParseUrl)
                .eq("status", "0")
                .orderByAsc( "fail_weight","id")
                .last("limit 2")
        );

        // 查询两个过度地址
        List<VideoParseUrl> list1 = videoParseUrlService.list(new QueryWrapper<>(videoParseUrl)
                .eq("status", "1")
                .orderByAsc( "fail_weight","id")
                .last("limit 2")
        );
        body.addAll(list);
        body.addAll(list1);
        return Response.success(body);
    }

    @PostMapping("/fail/{id}")
    public Response fail(@PathVariable("id") Integer id) {
        VideoParseUrl byId = videoParseUrlService.getById(id);
        if (byId == null) {
            return Response.exception("不存在");
        }
        VideoParseLog log = new VideoParseLog();
        log.setContent(byId.toString());
        log.setIp(NetUtils.getIpAddr(getRequest(), true));
        log.setParseId(byId.getId());
        log.setUrl(byId.getParseUrl());
        log.setCreateTime(new Date());
        videoParseLogService.save(log);

        Integer failWeight = byId.getFailWeight();
        if (failWeight < 200 && "0".equals(byId.getStatus())) {
            byId.setFailWeight(failWeight + 1);
        } else if ("1".equals(byId.getStatus())) {
            byId.setStatus("1");
            byId.setFailWeight(0);
        } else {
            byId.setStatus("2");
        }
        videoParseUrlService.updateById(byId);
        return Response.success();
    }


    @GetMapping("/page")
    @ApiOperation("分页")
    public Response getPages(VideoParseUrl videoParseUrl) {
        IPage page = videoParseUrlService.page(videoParseUrl.convertPage(), new QueryWrapper<VideoParseUrl>(videoParseUrl));
        return Response.parse(page);
    }

    @PostMapping("/add")
    @ApiOperation("新增")
    public Response add(VideoParseUrl videoParseUrl) {
        videoParseUrlService.save(videoParseUrl);
        return Response.success();
    }

    @PostMapping("/delete")
    @ApiOperation("删除")
    public Response delete(VideoParseUrl videoParseUrl) {
        videoParseUrlService.removeById(videoParseUrl.getId());
        return Response.success();
    }

    @PostMapping("/update")
    @ApiOperation("更新")
    public Response update(VideoParseUrl videoParseUrl) {
        boolean update = videoParseUrlService.updateById(videoParseUrl);
        return update ? Response.success() : Response.exception("更新失败");
    }

    @GetMapping("/list")
    @ApiOperation("列表")
    public Response list(VideoParseUrl videoParseUrl) {
        return Response.success(videoParseUrlService.list(new QueryWrapper<VideoParseUrl>(videoParseUrl)));
    }

    @GetMapping("/find/{id}")
    @ApiOperation("通过id查询")
    public Response find(@PathVariable("id") Serializable id) {
        return new Response(videoParseUrlService.getById(id));
    }

}
