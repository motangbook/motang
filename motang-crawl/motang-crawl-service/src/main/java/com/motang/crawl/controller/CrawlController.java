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

    @ApiOperation("爬虫爬取源站小说分类保存")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "sourceId",value = "爬虫来源Id")
    })
    @GetMapping("  /{sourceId}")
    public ResponseEntity<Void> saveCategory(@PathVariable("sourceId")String sourceId){
        crawlService.saveCategory(sourceId);
        return ResponseEntity.ok().build();
    }



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
                          String[] bookNameStr = bookElement.select("a").text().split("全集");
                          String bookName = bookNameStr[0].replace("《", "").replace("》", "");
                          String[] info = bookElement.select("div.s").text().split("大小");
                          String level = bookElement.select("div.s>em").attr("class").replace("lstar","");
                          String author = info[0].replace("作者：", "");
                          String bookIntroduction = bookElement.select("div.u").text();
                          String loveCount ="100";
                          String commentCount = "50";

                          // 小说具体内容
                          String bookDetailHtml = HttpClientUtil.httpGet(baseUrl+bookDetailHref);
                          Document bookDetailDocument = Jsoup.parse(bookDetailHtml);
                          String clickCount = bookDetailDocument.select("body > div:nth-child(5) > div.show > div:nth-child(1) > div > div.detail_info > div > ul > li:nth-child(1)").text().replace("点击次数：","");
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

}
