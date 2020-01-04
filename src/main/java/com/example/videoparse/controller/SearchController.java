package com.example.videoparse.controller;

import com.example.videoparse.common.BaseController;
import com.example.videoparse.common.Response;
import com.example.videoparse.service.impl.SearchService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

/**
 * @author chen.chao
 * @version 1.0
 * @date 2020/1/3 23:23
 * @description
 */
@RestController
public class SearchController extends BaseController {


    @Autowired
    private SearchService searchService;

    @ApiOperation("通过影片名获取链接")
    @PostMapping("/search/{type}/{key}")
    public Response search(@PathVariable("type") String type,
                           @PathVariable("key") String key,
                           @RequestParam(value = "refresh", defaultValue = "false") Boolean refresh) {
        if (refresh) {
            searchService.clear(key);
        }
        return Response.success(searchService.searchItem(type, key));
    }

    @ApiOperation("搜索历史记录")
    @GetMapping("/search/key/{key}")
    public Response history(@PathVariable("key") String key) {
        return Response.success(searchService.history(key));
    }

}
