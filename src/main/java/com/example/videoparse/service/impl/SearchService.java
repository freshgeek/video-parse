package com.example.videoparse.service.impl;

import com.example.videoparse.spider.entity.SearchItem;
import com.example.videoparse.spider.iqiyi.IqiyiParse;
import com.example.videoparse.spider.tx.TencentParse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import us.codecraft.webmagic.Spider;

import java.util.List;
import java.util.Set;

/**
 * @author chen.chao
 * @version 1.0
 * @date 2020/1/3 23:25
 * @description
 */
@Service
public class SearchService {

    @Autowired
    private RedisTemplate redisTemplate;

    @Cacheable(cacheNames = "search", key = "#key", unless = "#result==null")
    public SearchItem searchItem(String type, String key) {
        if ("tencent".equals(type)) {
            TencentParse tencentParse = new TencentParse();
            Spider.create(tencentParse).addUrl("https://v.qq.com/x/search/?q=" + key).run();
            SearchItem searchItem = tencentParse.getSearchItem();
            searchItem.setKey(key);
            return searchItem;
        } else if ("youku".equals(type)){
            return null;
        } else {
            IqiyiParse iqiyiParse = new IqiyiParse();
            Spider.create(iqiyiParse).addUrl("https://so.iqiyi.com/so/q_" + key).run();
            SearchItem searchItem = iqiyiParse.getSearchItem();
            searchItem.setKey(key);
            return searchItem;
        }
    }
    @CacheEvict(cacheNames = "search",key = "#key")
    public void clear(String key){

    }

    public Object history(String key) {
        Set keys = redisTemplate.keys("search::*"+key+"*");
        List list = redisTemplate.opsForValue().multiGet(keys);
        return list;
    }
}
