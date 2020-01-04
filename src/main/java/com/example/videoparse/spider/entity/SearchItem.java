package com.example.videoparse.spider.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @author chen.chao
 * @version 1.0
 * @date 2020/1/3 21:53
 * @description
 */
@Data
public class SearchItem {

    private String key;

    private String source;

    private String title;

    private String intro;

    private String coverUrl;

    private List<Video> videos = new ArrayList();

    @Data
    @AllArgsConstructor
    public static class Video{
        String no;
        String url;
    }

}
