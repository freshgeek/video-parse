package com.example.videoparse.spider.iqiyi;

import lombok.Data;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.model.ConsolePageModelPipeline;
import us.codecraft.webmagic.model.OOSpider;
import us.codecraft.webmagic.model.annotation.ExtractBy;
import us.codecraft.webmagic.model.annotation.Formatter;
import us.codecraft.webmagic.model.annotation.TargetUrl;
import us.codecraft.webmagic.model.formatter.ObjectFormatter;

import java.util.List;

/**
 * @author chen.chao
 * @version 1.0
 * @date 2020/1/3 19:56
 * @description
 */
@Data
@TargetUrl("https://so.iqiyi.com/so/q_剑王朝")
public class SearchIqiyi {

    @ExtractBy("//*[@id=\"data-widget-searchword\"]/@value")
    private String key;

    @ExtractBy("//*[@id=\"j-body\"]/div[1]/div[3]/div[2]/div[1]/div[2]/text()")
    private String title;

    //           //*[@id="j-body"]/div[1]/div[3]/div[2]/div[1]/div[2]/div[1]/div/div/a/img
    @ExtractBy("//*[@id=\"j-body\"]/div[1]/div[3]/div[2]/div[1]/div[2]/div[1]/div/div/a/img/@src")
    private String coverUrl;
    //           //*[@id="j-body"]/div[1]/div[3]/div[2]/div[1]/div[2]/div[2]/
//               //*[@id="j-body"]/div[1]/div[3]/div[2]/div[1]/div[2]/div[2]/div[4]/span
    @ExtractBy("//*[@id=\"j-body\"]/div[1]/div[3]/div[2]/div[1]/div[2]/div[2]/div[3]/span/text()")
    private String intro;

    @Formatter(value = {"/html/body/div[1]/div[3]/div[2]/div[1]/div[2]/div[2]/div[4]/div/ul[2]/li/a/text()",
            "/html/body/div[1]/div[3]/div[2]/div[1]/div[2]/div[2]/div[4]/div/ul[2]/li/a/@href"}, formatter = IqiyiVideoFormatter.class)
    @ExtractBy(value = "/html/body/div[1]/div[3]/div[2]/div[1]/div[2]/div[2]/div[4]/div/ul[2]/li/a/@href")
    private List<String> iqiyiVideoList;

    @Data
    static class IqiyiVideo {
        private Integer no;
        private String url;
    }

    public static class IqiyiVideoFormatter implements ObjectFormatter<IqiyiVideo> {

        @Override
        public IqiyiVideo format(String raw) throws Exception {
            return null;
        }

        @Override
        public Class<IqiyiVideo> clazz() {
            return IqiyiVideo.class;
        }

        @Override
        public void initParam(String[] extra) {
            System.out.println(extra);
        }
    }

    public static void main(String[] args) {

        OOSpider.create(Site.me(), new ConsolePageModelPipeline(), SearchIqiyi.class)
                .addUrl("https://so.iqiyi.com/so/q_剑王朝").run();
    }

}
