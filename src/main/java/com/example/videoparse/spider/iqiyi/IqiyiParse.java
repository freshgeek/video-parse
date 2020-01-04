package com.example.videoparse.spider.iqiyi;

import com.example.videoparse.spider.LoadDataWrapper;
import com.example.videoparse.spider.entity.SearchItem;
import lombok.Builder;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.bag.SynchronizedSortedBag;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.model.ConsolePageModelPipeline;
import us.codecraft.webmagic.model.OOSpider;
import us.codecraft.webmagic.model.annotation.ExtractBy;
import us.codecraft.webmagic.model.annotation.Formatter;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.selector.Selectable;

import java.util.List;

/**
 * @author chen.chao
 * @version 1.0
 * @date 2020/1/3 21:52
 * @description
 */
@Slf4j
public class IqiyiParse extends LoadDataWrapper implements PageProcessor {
    @Override
    public void process(Page page) {
        this.searchItem = new SearchItem();
        Selectable first = page.getHtml().xpath("//*[@id=\"j-body\"]/div[1]/div[3]/div[2]/div[1]/div[2]");
        String key = page.getHtml().xpath("//*[@id=\"j-body\"]/div[1]/div[3]/div[2]/div[1]/div[1]/div[2]/div/text()").toString();
        String title = first.xpath("//*h3/allText()").toString();
        String coverUrl = first.xpath("//*img[@class=\"qy-mod-cover\"]/@src").toString();
        String intro = first.xpath("//*span[@class=\"info-des\"]/text()").all().toString();
        List<Selectable> nodes = first.xpath("//*ul[@data-tvlist-elem=\"list\"]/li/a").nodes();
        searchItem.setKey(key);
        searchItem.setTitle(title);
        searchItem.setIntro(intro);
        searchItem.setCoverUrl(coverUrl);
        searchItem.setSource("爱奇艺");
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

}
