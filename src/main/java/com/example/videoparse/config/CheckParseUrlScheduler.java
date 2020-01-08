package com.example.videoparse.config;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.videoparse.entity.VideoParseUrl;
import com.example.videoparse.service.VideoParseUrlService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.scheduling.annotation.Scheduled;
import us.codecraft.webmagic.utils.UrlUtils;

import java.io.IOException;
import java.net.*;
import java.util.List;

/**
 * @author chen.chao
 * @version 1.0
 * @date 2020/1/4 22:54
 * @description
 */
@Slf4j
@SpringBootConfiguration
public class CheckParseUrlScheduler {

    @Autowired
    private VideoParseUrlService videoParseUrlService;

    @Scheduled(cron = "0 4 0 * * ? ")
    public void check() {
        List<VideoParseUrl> list = videoParseUrlService.list(new QueryWrapper<VideoParseUrl>().in("status","0","1"));
        list.forEach(s -> {
            try {
                String parseUrl = s.getParseUrl();
                URL url = new URL(parseUrl);
                URLConnection urlConnection = url.openConnection();
                //3 秒 超时
                urlConnection.setConnectTimeout(3000);
                urlConnection.connect();
            } catch (UnknownHostException e) {
                log.info("{}=>{}",s,e);
                s.setStatus("3");
                videoParseUrlService.updateById(s);
            } catch (SocketTimeoutException e) {
                log.info("{}=>{}",s,e);
                s.setStatus("1");
                videoParseUrlService.updateById(s);
            } catch (IOException e) {
                log.info("{}=>{}",s,e);
                s.setStatus("2");
                videoParseUrlService.updateById(s);
            }

        });

    }
}
