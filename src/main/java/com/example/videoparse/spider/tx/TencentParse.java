package com.example.videoparse.spider.tx;

import com.example.videoparse.spider.LoadDataWrapper;
import com.example.videoparse.spider.entity.SearchItem;
import lombok.extern.slf4j.Slf4j;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.pipeline.ConsolePipeline;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.selector.Html;
import us.codecraft.webmagic.selector.Selectable;

import java.util.List;

/**
 * @author chen.chao
 * @version 1.0
 * @date 2020/1/3 21:52
 * @description
 */
@Slf4j
public class TencentParse extends LoadDataWrapper implements PageProcessor {

    @Override
    public void process(Page page) {
        this.searchItem = new SearchItem();
        Html html = page.getHtml();

        Selectable target = html.xpath("/html/body/div[@class='search_container']" +
                "/div[@class='wrapper']" +
                "/div[contains(@class,'wrapper_main')]" +
                "/div[contains(@class,'result_item_v')]");
        // and not(contains(@class,'none'))
        String title = target.xpath("//*[@class='result_title']/allText()").toString();
        String coverUrl = target.xpath("//*[@class='figure_pic']/@src").toString();
        String intro = target.xpath("//*[@class='desc_text']/text()").toString();

        List<Selectable> nodes =
                target.xpath("//*[@class='result_episode_list']/div[@class='item']/a").nodes();
        searchItem.setTitle(title);
        searchItem.setIntro(intro);
        searchItem.setCoverUrl(coverUrl);
        searchItem.setSource("优酷");
        nodes.forEach(n -> {
            searchItem.getVideos().add(
                    new SearchItem.Video(
                            n.xpath("//a/text()").toString(), n.xpath("//a/@href").toString()
                    ));
        });
        log.info("解析完成{}",searchItem);
    }

    @Override
    public Site getSite() {
        return Site.me();
    }
/*
    public static void main(String[] args) {
        Spider.create(new YouKuParse()).addPipeline(new ConsolePipeline()).addUrl("https://v.qq.com/x/search/?q=庆余年").run();
    }*/

}
