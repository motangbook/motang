package com.motang.crawl.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.motang.common.core.utils.ThreadPoolUtil;
import com.motang.common.redis.service.RedisService;
import com.motang.crawl.api.entity.BookCategory;
import com.motang.crawl.api.entity.CrawlSource;
import com.motang.crawl.constant.CrawlConstants;
import com.motang.crawl.service.IBookCategoryService;
import com.motang.crawl.service.ICrawlService;
import com.motang.crawl.service.ICrawlSourceService;
import com.motang.crawl.util.HttpClientUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadPoolExecutor;

/**
 *  @Description 爬虫业务层实现类
 *  @author liuhu
 *  @Date 2021/1/9 16:50
 */
@Slf4j
@Service
public class CrawlServiceImpl implements ICrawlService {

    @Autowired
    private ICrawlSourceService crawlSourceService;

    @Autowired
    private RedisService redisService;

    @Autowired
    private IBookCategoryService bookCategoryService;

    @Override
    public void open(String sourceId, String status) {
        CrawlSource crawlSource = crawlSourceService.selectById(sourceId);
        //1.判断当前是关闭还是开启  如果是开启则更改数据库状态 用线程池创建线程执行  并把线程id存入缓存
        if(CrawlConstants.OPEN_CRAWL == Byte.parseByte(status)){
            // 查询当前数据库开启状态 如果已经开启无需关心
            if(crawlSource.getSourceStatus() == CrawlConstants.CLOSE_CRAWL){
                crawlSourceService.updateSourceStatusById(sourceId,status);
                parseBookAndSave(crawlSource);
                ThreadPoolExecutor threadPoolExecutor = ThreadPoolUtil.buildThreadPoolExecutor();
                List<Long> threadIdList = new ArrayList<>();
                for (int i = 0; i < 8; i++) {
                    threadPoolExecutor.submit(() -> {
                       //1. 将运行的线程ID放入集合  放入缓存
                        threadIdList.add(Thread.currentThread().getId());
                        // 2.解析 小说信息入库

                    });
                }
                redisService.set(CrawlConstants.CRAWL_THREAD_PREFIX+sourceId,threadIdList);
            }
        }else {
            // 判断数据库状态是否一直  更新数据库数据状态  关闭当前小说源站的运行线程
          if(CrawlConstants.OPEN_CRAWL == Byte.parseByte(status)){
              crawlSourceService.updateSourceStatusById(sourceId,status);
              String threadIdListCache = (String) redisService.get(CrawlConstants.CRAWL_THREAD_PREFIX + sourceId);
              if(StringUtils.isNotBlank(threadIdListCache)){
                  List<Long> threadIdList = JSONObject.parseArray(threadIdListCache, Long.class);
                  threadIdList.forEach(id->{
                      Thread thread = ThreadPoolUtil.findThread(id);
                      if(thread != null && thread.isAlive()){
                          thread.interrupt();
                      }
                  });
              }
          }
        }
    }


    /**
     * @Description 解析小说入库
     * @author liuhu
     * @param crawlSource
     * @date 2021/1/11 20:17
     * @return void
     */
    private void parseBookAndSave(CrawlSource crawlSource) {
        String baseUrl = "http://www.uidzhx.com";
        String indexHtml = HttpClientUtil.httpGet(baseUrl);
        Document indexDocument = Jsoup.parse(indexHtml);
        Elements maleCategoryElements = indexDocument.select("body > div.wrap.header > div > a");
        for (Element maleCategoryElement : maleCategoryElements) {

            String categoryHref = maleCategoryElement.select("a").attr("href");
            String categoryName = maleCategoryElement.select("a").text();
            if (!StringUtils.equals("首页", categoryName)) {
               parseXx(baseUrl,categoryHref);
            }
        }
    }

    // 分类抽出来  然后 每个分类一个线程
    private void parseXx(String baseUrl,String categoryHref) {

    }




    public void xx(){
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


    /**
     * @Description 解析小说分类
     * @author liuhu
     * @param baseUrl
     * @param maleCategoryElement
     * @date 2021/1/11 20:35
     * @return void
     */
    private void parseCategory(String baseUrl,Element maleCategoryElement) {
        String categoryHref = maleCategoryElement.select("a").attr("href");
        if(StringUtils.isNotBlank(categoryHref)){
            String categoryName = maleCategoryElement.select("a").text();
            BookCategory bookCategory = bookCategoryService.getBookCategoryByName(categoryName);
            if(null == bookCategory){
                BookCategory bookCategoryEntity = new BookCategory();
                bookCategoryEntity.setName(categoryName);
                bookCategoryEntity.setWorkDirection((byte)1);
                bookCategoryService.insertBookCategory(bookCategoryEntity);
            }
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
                  parseBookDetail(baseUrl,bookElement);
                }
            }
        }
    }

    /**
     * @Description 解析小说详情
     * @author liuhu
     * @param baseUrl
     * @param bookElement
     * @date 2021/1/11 20:35
     * @return void
     */
    private void parseBookDetail(String baseUrl,Element bookElement) {
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
        String bookId = "";
        String[] list = countStr.split("read_");
        for (String s : list) {
            if (s.contains(".html")) {
                contentCount = s.replace(".html", "");
            }else {
                bookId = s;
            }
        }
        for (int j = 1; j < Integer.parseInt(contentCount)+1; j++) {
           parseBookContent(baseUrl,bookId,contentCount,bookName);
        }
    }

    /**
     * @Description 解析小说正文
     * @author liuhu
     * @param baseUrl
     * @param bookId
     * @param contentCount
     * @param bookName
     * @date 2021/1/11 20:36
     * @return void
     */
    private void parseBookContent(String baseUrl, String bookId, String contentCount,String bookName) {
        for (int j = 1; j < Integer.parseInt(contentCount)+1; j++) {
            String contentUrl = baseUrl + bookId + "read_" + j + ".html";
            // 小说具体内容
            String contentDetailHtml = HttpClientUtil.httpGet(contentUrl);
            Document contentDetailDocument = Jsoup.parse(contentDetailHtml);
            String contentName = contentDetailDocument.select("body > section > div > article > div.title > h1 > a").text().replace(bookName,"");
            String contentUpdateTime = contentDetailDocument.select("body > section > div > article > div.title > div > span:nth-child(3)").text().replace("更新时间：","");
            String content = contentDetailDocument.select("#chaptercontent").html();
            System.out.println(content);
        }
    }


    public static void main(String[] args) {
        String baseUrl = "http://www.uidzhx.com";
        String indexHtml = HttpClientUtil.httpGet(baseUrl);
        Document indexDocument = Jsoup.parse(indexHtml);
        Elements maleCategoryElements = indexDocument.select("body > div.wrap.header > div > a");
        for (Element maleCategoryElement : maleCategoryElements) {
//            parseCategory(baseUrl,maleCategoryElements);
        }
    }
}
