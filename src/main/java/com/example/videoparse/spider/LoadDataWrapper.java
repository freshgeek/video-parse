package com.example.videoparse.spider;

import com.example.videoparse.spider.entity.SearchItem;
import lombok.Data;

/**
 * @author chen.chao
 * @version 1.0
 * @date 2020/1/3 21:56
 * @description
 */
public abstract class LoadDataWrapper {

    protected SearchItem searchItem;


    public SearchItem getSearchItem(){
        return searchItem;
    }
}
