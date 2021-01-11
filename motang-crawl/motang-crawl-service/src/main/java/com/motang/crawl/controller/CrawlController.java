package com.motang.crawl.controller;

import com.motang.crawl.service.ICrawlService;
import com.motang.crawl.util.HttpClientUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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

    @Autowired
    private ICrawlService crawlService;

    @ApiOperation("打开或者关闭爬虫")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "sourceId",value = "爬虫来源Id"),
            @ApiImplicitParam(name = "status",value = "状态 1-开启 2-关闭")
    })
    @GetMapping("open/{sourceId}/{status}")
    public ResponseEntity<Void> open(@PathVariable("sourceId")String sourceId,
                                     @PathVariable("status")String status){
        crawlService.open(sourceId,status);
        return ResponseEntity.ok().build();
    }

    public static void main(String[] args) {
        String baseUrl = "https://www.xiashuwu.com/";
        String indexHtml = HttpClientUtil.httpGet(baseUrl);
        Document indexDocument = Jsoup.parse(indexHtml);
        Elements maleCategoryElements = indexDocument.select("body > header > div.subMenuCon > div.subMenus > ul.male >li");
        Elements femaleCategoryElements = indexDocument.select("body > header > div.subMenuCon > div.subMenus > ul.female >li");
        for (Element maleCategoryElement : maleCategoryElements) {
            String categoryHref = maleCategoryElement.select("a").attr("href");
            if(StringUtils.isNotBlank(categoryHref)){
                String categoryName = maleCategoryElement.select("a").text();
                String categoryBookHtml = HttpClientUtil.httpGet(baseUrl+categoryHref);
                Document categoryBookDocument = Jsoup.parse(categoryBookHtml);
                // 分类下全部小说总页数
                String totalCount = "";
                String[] pageListStr = categoryBookDocument.select("#main > div.footer.pagination > span").text().split(" ");
                for (String s : pageListStr) {
                    if(s.startsWith("共")){
                        totalCount = s.replace("共", "").replace("页", "");
                    }
                }
                // 便利全部分页
                for (int i = 1; i < Integer.parseInt(totalCount)+1; i++) {
                    String bookPageHtml = HttpClientUtil.httpGet("https://www.xiashuwu.com/type/1_0_0_allvisit_" + i+".html");
                    Document bookPageDocument = Jsoup.parse(bookPageHtml);
                    // 当前页全部小说内容
                    Elements categoryBookElement = bookPageDocument.select("#waterfall > div");
                    for (Element bookElement : categoryBookElement) {
                        // 小说详细信息
                        String bookDetailHref = bookElement.select("div.title > h3 > a").attr("href");
                        String bookName = bookElement.select("div.title > h3 > a").text();
                        String author = bookElement.select("div.pic > div").text().replace("/","").replace("著","").trim();
                        String bookIntroduction = bookElement.select("> div.intro").html();
                        String loveCount = bookElement.select("div.num > a.like-num").html();
                        String commentCount = bookElement.select("#div.num > a.cmt-num").html();

                        // 小说具体内容
                        String bookDetailHtml = HttpClientUtil.httpGet(baseUrl+bookDetailHref);
                        Document bookDetailDocument = Jsoup.parse(bookDetailHtml);
                        String collectCount = bookDetailDocument.select("#mainright > div.infonum > ul > li:nth-child(3)").text();
                        String commendCount = bookDetailDocument.select("#mainright > div.infonum > ul > li:nth-child(4)").text();
                        String tag = bookDetailDocument.select("#mainright > div.infonum > ul > li:nth-child(5)").text();

                        Elements select = bookDetailDocument.select("#lastchapter > li");
                        Element lastElement = select.get(select.size() - 1);
                        String countStr = lastElement.select("a").attr("href");
                        String contentCount="";
                        String bokeId = "";
                        String[] list = countStr.split("read_");
                        for (String s : list) {
                            if (s.contains(".html")) {
                                contentCount = s.replace(".html", "");
                            }else {
                                bokeId = s;
                            }
                        }
                        for (int j = 1; j < Integer.parseInt(contentCount)+1; j++) {
                            String contentUrl = baseUrl + bokeId + "read_" + i + ".html";
                            // 小说具体内容
                            String contentDetailHtml = HttpClientUtil.httpGet(contentUrl);
                            Document contentDetailDocument = Jsoup.parse(contentDetailHtml);
                            String contentName = contentDetailDocument.select("body > section > div > article > div.title > h1 > a").text().replace(bookName,"");
                            String contentUpdateTime = contentDetailDocument.select("body > section > div > article > div.title > div > span:nth-child(3)").text().replace("更新时间：","");
                            String content = contentDetailDocument.select("#chaptercontent").html();
                            System.out.println(content);
                        }
                    }
                }
            }
        }
    }


    public void biquge(){
        String baseUrl = "https://www.duquanben.com/";
        String indexHtml = HttpClientUtil.httpGet(baseUrl);
        Document indexDocument = Jsoup.parse(indexHtml);
        Elements categoryElements = indexDocument.select("#subnav > div > div > a");
        for (Element categoryElement : categoryElements) {
            String categoryHref = categoryElement.select("a").attr("href");
            String categoryName = categoryElement.select("a").text();
            String categoryBookHtml = HttpClientUtil.httpGet(baseUrl+categoryHref);
            Document categoryBookDocument = Jsoup.parse(categoryBookHtml);
            // 分类下全部小说总页数
            String totalCount = categoryBookDocument.select("#pagelink > a.last").text();
            // 便利全部分页
            for (int i = 1; i < Integer.parseInt(totalCount)+1; i++) {
                String bookPageHtml = HttpClientUtil.httpGet(baseUrl + "book1/0/" + i);
                Document bookPageDocument = Jsoup.parse(bookPageHtml);
                // 当前页全部小说内容
                Elements categoryBookElement = bookPageDocument.select("#content > div > div.wrap797 > div.clearfix.rec_rboxone > div.clearfix.rec_rullist > ul");
                for (Element bookElement : categoryBookElement) {
                    // 小说详细信息
                    String bookDetailHref = bookElement.select("li.two > a").attr("href");
                    String bookName = bookElement.select("li.two > a").text().replace("全文阅读","");
                    String author = bookElement.select("li.four").text();
                    String bookSize = bookElement.select("li.five").text();
                    String updateTime = bookElement.select("li.six").text();
                    // 小说具体内容
                    String bookDetailHtml = HttpClientUtil.httpGet(bookDetailHref);
                    Document bookDetailDocument = Jsoup.parse(bookDetailHtml);
                    // 简介
                    String bookIntroduction = bookDetailDocument.select("#header > div.warpper > div.mu_contain > p").text();
                    Elements bookContentElement =  bookDetailDocument.select("#header > div.warpper > div.mu_contain > ul >li");
                    for (Element element : bookContentElement) {
                        String contentName = element.select("li > a").text();
                        String contentHref = element.select("a").attr("href");
                        String contentHtml = HttpClientUtil.httpGet(bookDetailHref + contentHref);
                        Document contentDocument = Jsoup.parse(contentHtml);
                        String content = contentDocument.select("#htmlContent").html();
                        System.out.println(content);
                    }
                }
            }
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
