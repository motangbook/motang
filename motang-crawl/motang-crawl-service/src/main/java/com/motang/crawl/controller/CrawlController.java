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
        String baseUrl = "http://www.uidzhx.com";
        String indexHtml = HttpClientUtil.httpGet(baseUrl);
        Document indexDocument = Jsoup.parse(indexHtml);
        Elements maleCategoryElements = indexDocument.select("body > div.wrap.header > div > a");
        for (Element maleCategoryElement : maleCategoryElements) {
                String categoryHref = maleCategoryElement.select("a").attr("href");
                String categoryName = maleCategoryElement.select("a").text();
              if(!StringUtils.equals("首页",categoryName)){
                  String categoryBookHtml = HttpClientUtil.httpGet(baseUrl+categoryHref);
                  Document categoryBookDocument = Jsoup.parse(categoryBookHtml);
                  // 分类下全部小说总页数
//                String totalCount = categoryBookDocument.select("#pagestats").text().replace("1/","");
                  String totalCount = "2";

                  // 便利全部分页
                  for (int i = 1; i < Integer.parseInt(totalCount)+1; i++) {
                      String  pageUrl = "http://www.uidzhx.com/soft/sort01/index_" + i + ".html";
                      String bookPageHtml = HttpClientUtil.httpGet(pageUrl);
                      Document bookPageDocument = Jsoup.parse(bookPageHtml);
                      // 当前页全部小说内容

                      Elements categoryBookElement = bookPageDocument.select("body > div:nth-child(5) > div.list > div.listBox > ul > li");
                      for (Element bookElement : categoryBookElement) {
                          // 小说详细信息
                          String bookDetailHref = bookElement.select("a").attr("href");
                          String bookDetailImage = bookElement.select("a").attr("src");
                          String bookName = bookElement.select("a").text();
                          String[] info = bookElement.select("div.s").text().split("大小");
                          String author = info[0].replace("作者：", "");
                          String bookIntroduction = bookElement.select("div.u").text();
                          String loveCount ="100";
                          String commentCount = "50";

                          // 小说具体内容
                          String bookDetailHtml = HttpClientUtil.httpGet(baseUrl+bookDetailHref);
                          Document bookDetailDocument = Jsoup.parse(bookDetailHtml);
                          String clickCount = bookDetailDocument.select("#body > div:nth-child(5) > div.show > div:nth-child(1) > div > div.detail_info > div > ul > li:nth-child(1)").text();
                          String status = bookDetailDocument.select("body > div:nth-child(5) > div.show > div:nth-child(1) > div > div.detail_info > div > ul > li:nth-child(5)").text();
                          String updateTime = bookDetailDocument.select("body > div:nth-child(5) > div.show > div:nth-child(1) > div > div.detail_info > div > ul > li:nth-child(4)").text();

                          String chapterHref = bookDetailDocument.select("body > div:nth-child(5) > div.show > div:nth-child(4) > div.showDown > ul > li:nth-child(1) > a").attr("href");
                          String allChapterUrl = baseUrl + chapterHref;
                          String chapterHtml = HttpClientUtil.httpGet(allChapterUrl);
                          Document chapterDocument = Jsoup.parse(chapterHtml);
                          Elements chapterElements = chapterDocument.select("#info > div.pc_list > ul >li");
                          for (Element chapterElement : chapterElements) {
                              String chapterName = chapterElement.select("a").text();
                              String contentHref = chapterElement.select("a").attr("href");
                              String contentDetailHtml = HttpClientUtil.httpGet(baseUrl+chapterHref+contentHref);
                              Document contentDetailDocument = Jsoup.parse(contentDetailHtml);
                              String content = contentDetailDocument.select("#content1").html();
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
