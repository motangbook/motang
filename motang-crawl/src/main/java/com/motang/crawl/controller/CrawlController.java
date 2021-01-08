package com.motang.crawl.controller;

import com.alibaba.fastjson.JSONObject;
import com.motang.crawl.util.HttpClientUtil;
import io.swagger.annotations.Api;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *  @Description 爬虫控制器
 *  @author liuhu
 *  @Date 2021/1/6 20:35
 */
@RestController
@RequestMapping("operation")
@Api(tags = "爬虫接口")
public class CrawlController {


    @GetMapping("open/{sourceid}/{status}")
    public void open(){

    }

    public static void main(String[] args) {
        String baseUrl = "https://www.duquanben.com/";
        String indexHtml = HttpClientUtil.httpGet(baseUrl);
        Document indexDocument = Jsoup.parse(indexHtml);
        Elements categoryElements = indexDocument.select("#subnav > div > div");
        for (Element categoryElement : categoryElements) {
            String categoryHref = categoryElement.select("a").attr("href");
            String categoryName = categoryElement.select("a").text();
            String categoryHtml = HttpClientUtil.httpGet(baseUrl+categoryHref);
            Document categoryDocument = Jsoup.parse(categoryHtml);
        }
    }

    public void ss() {
        String baseUrl = "http://www.jianshengkun.com";
        String s = HttpClientUtil.httpGet(baseUrl);
        Document document = Jsoup.parse(s);
        Elements select = document.select("body > div.navigation > nav > a");
        // 分类
        for (Element element : select) {
            String href = element.attr("href");
            if(!"/".equals(href)){
                String cateGoryName = element.text();
                String s2 = baseUrl  + href;
                String s1 = HttpClientUtil.httpGet(s2);
                Document parse = Jsoup.parse(s1);
                String count = parse.select("#pagelink > a:nth-child(14)").text();
                // 分类分页
                for (int i = 1; i < Integer.parseInt(count)-1; i++) {
                    String substring = s2.substring(0,s2.length()-2);
                    String s3 = HttpClientUtil.httpGet(substring + i);
                    Document parse2 = Jsoup.parse(s3);
                    Elements elements = parse2.select("body > div.store > div.store_left > div > ul > li");
                    // 书籍分页
                    for (Element element1 : elements) {
                        String text = element1.select(" > div.img_span > a").attr("href");
                        String bookName = element1.select(" > div.w100 > a >h2").text();
                        String des = element1.select(" > div.w100 > p").text();
                        String author = element1.select(" > div.w100 >  div > a").attr("href");
                        String update = element1.select(" > div.w100 >  div > em.blue").text();
                        String total = element1.select(" > div.w100 >  div > div > em.orange").text();
                        String contentHref = element1.select(" > div.w100 > a").attr("href");
                        String s4 = HttpClientUtil.httpGet(baseUrl + contentHref);
                        Document parse1 = Jsoup.parse(s4);
                        Elements select2 = parse1.select("#indexselect > option");
                        // 章节分页
                        for (Element element2 : select2) {
                            String text1 = element2.val();
                            String s6 = HttpClientUtil.httpGet(baseUrl + text1);
                            Document parse3 = Jsoup.parse(s6);
                            // 章节的列表
                            Elements select1 = parse3.select("#ul_all_chapters > li");
                            for (Element element3 : select1) {
                                String contentName = element3.text();
                                String contentHref2 = element3.select("a").attr("href");
                                String s5 = HttpClientUtil.httpGet(baseUrl + contentHref2);
                                Element element4 = Jsoup.parse(s5);

                            }
                        }
                        System.out.println(contentHref);
                    }
                }
            }
        }
    }
}
