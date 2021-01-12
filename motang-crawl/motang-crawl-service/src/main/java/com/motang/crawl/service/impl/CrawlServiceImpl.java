package com.motang.crawl.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.motang.common.core.utils.ThreadPoolUtil;
import com.motang.common.redis.service.RedisService;
import com.motang.crawl.api.entity.*;
import com.motang.crawl.constant.CrawlConstants;
import com.motang.crawl.exception.CrawlException;
import com.motang.crawl.service.*;
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
import java.util.Random;
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

    @Autowired
    private IAuthorService authorService;

    @Autowired
    private IBookService bookService;

    @Autowired
    private IBookChapterService bookChapterService;

    @Autowired
    private IBookContentService bookContentService;

    @Override
    public void open(String sourceId, String status) {
        CrawlSource crawlSource = crawlSourceService.selectById(sourceId);
        //1.判断当前是关闭还是开启  如果是开启则更改数据库状态 用线程池创建线程执行  并把线程id存入缓存
        if(CrawlConstants.OPEN_CRAWL == Byte.parseByte(status)){
            // 查询当前数据库开启状态 如果已经开启无需关心
            if(crawlSource.getSourceStatus() == CrawlConstants.CLOSE_CRAWL){
                crawlSourceService.updateSourceStatusById(sourceId,status);
                ThreadPoolExecutor threadPoolExecutor = ThreadPoolUtil.buildThreadPoolExecutor();
                List<Long> threadIdList = new ArrayList<>();
                for (int i = 1; i < 11; i++) {
                    int finalI = i;
                    threadPoolExecutor.submit(() -> {
                       //1. 将运行的线程ID放入集合  放入缓存
                        threadIdList.add(Thread.currentThread().getId());
                        // 2.解析 小说信息入库
                        parseBookDetail(crawlSource, finalI);
                        try {
                            Thread.sleep(60000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
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

    @Override
    public void saveCategory(String sourceId) {
        try {
            CrawlSource crawlSource = crawlSourceService.selectById(sourceId);
            String baseUrl = "http://www.uidzhx.com";
            String indexHtml = HttpClientUtil.httpGet(baseUrl);
            Document indexDocument = Jsoup.parse(indexHtml);
            Elements maleCategoryElements = indexDocument.select("body > div.wrap.header > div > a");
            for (Element maleCategoryElement : maleCategoryElements) {
                String categoryName = maleCategoryElement.select("a").text();
                if (!StringUtils.equals("首页", categoryName)) {
                    BookCategory categoryByName = bookCategoryService.getBookCategoryByName(categoryName);
                    if(categoryByName == null){
                        BookCategory bookcategory = BookCategory.builder().name(categoryName).workDirection((byte) 1).build();
                        bookCategoryService.insertBookCategory(bookcategory);
                    }
                }
            }
        }catch (Exception e){
            log.error("爬取小说1分类失败");
            throw new CrawlException("爬取小说1分类失败");
        }
    }


    /**
     * @Description 解析小说入库
     * @author liuhu
     * @param crawlSource
     * @date 2021/1/11 20:17
     * @return void
     */
    private void parseBookDetail(CrawlSource crawlSource,int categoryId) {
            String baseUrl = "http://www.uidzhx.com";
            String  categoryUrlSuffix="/soft/sort0"+categoryId;
            String categoryUrl = baseUrl+categoryUrlSuffix;
//            String indexHtml = HttpClientUtil.httpGet(categoryUrl);
//            Document categoryDocument = Jsoup.parse(indexHtml);
            int totalPage = 2;
            int currentPage = 1;
            while (currentPage <= totalPage) {
                String pageUrl = categoryUrl+"/index_" + currentPage + ".html";
                String bookPageHtml = HttpClientUtil.httpGet(pageUrl);
                Document bookPageDocument = Jsoup.parse(bookPageHtml);
                // 当前页全部小说内容
                Elements categoryBookElement = bookPageDocument.select("body > div:nth-child(5) > div.list > div.listBox > ul > li");
                for (Element bookElement : categoryBookElement) {
                    parseBookAndSave(bookElement,baseUrl,categoryId);
                }
                currentPage += 1;
            }
        }

    /**
     * @Description 解析并保存
     * @author liuhu
     * @param bookElement
     * @param baseUrl
     * @param categoryId
     * @date 2021/1/12 16:17
     * @return void
     */
    private void parseBookAndSave(Element bookElement,String baseUrl,int categoryId) {
        // 作者信息
        Author author = saveAuthorInfo(bookElement);
        Long authorId = author.getId();
        saveBookInfo(bookElement,baseUrl,(long)categoryId,authorId);
    }

    /**
     * @Description 保存作者信息
     * @author liuhu
     * @param bookElement
     * @date 2021/1/12 16:17
     * @return com.motang.crawl.api.entity.Author
     */
    private Author saveAuthorInfo(Element bookElement) {
        String[] info = bookElement.select("div.s").text().split("大小");
        String authorName = info[0].replace("作者：", "");
        Author author = Author.builder().penName(authorName).build();
        authorService.insertAuthor(author);
        return author;
    }

    /**
     * @Description 保存小说信息
     * @author liuhu
     * @param bookElement
     * @param baseUrl
     * @param authorId
     * @param categoryId
     * @date 2021/1/12 16:22
     * @return void
     */
    private void saveBookInfo(Element bookElement,String baseUrl,Long authorId,Long categoryId) {
        // 小说详细信息
        String bookDetailHref = bookElement.select("a").attr("href");
        String bookImageUrl = bookElement.select("a").attr("src");
        String[] bookNameStr = bookElement.select("a").text().split("全集");
        String bookName = bookNameStr[0].replace("《", "").replace("》", "");
        String level = bookElement.select("div.s>em").attr("class").replace("lstar","");
        String bookIntroduction = bookElement.select("div.u").text();
        String bookDetailHtml = HttpClientUtil.httpGet(baseUrl + bookDetailHref);
        Document bookDetailDocument = Jsoup.parse(bookDetailHtml);
        String clickStr = bookDetailDocument.select("body > div:nth-child(5) > div.show > div:nth-child(1) > div > div.detail_info > div > ul > li:nth-child(1)").text().replace("点击次数：","");
        Integer clickCount = StringUtils.isNotBlank(clickStr)?Integer.parseInt(clickStr):0;
        String updateTime = bookDetailDocument.select("body > div:nth-child(5) > div.show > div:nth-child(1) > div > div.detail_info > div > ul > li:nth-child(4)").text();
        String chapterHref = bookDetailDocument.select("body > div:nth-child(5) > div.show > div:nth-child(4) > div.showDown > ul > li:nth-child(1) > a").attr("href");

        Book book = Book.builder().authorId(authorId).name(bookName)
                .categoryId(categoryId).bookIntroduction(bookIntroduction)
                .workDirection((byte) 1)
                .clickCount(clickCount)
                .level(level)
                .subscribeCount(1000L)
                .commentCount(500L)
                .build();

      bookService.insertBook(book);
      // 保存章节信息
      saveChapterInfo(baseUrl,chapterHref,book.getId());
    }



    /**
     * @Description 保存章节正文信息1
     * @author liuhu
     * @param baseUrl
     * @param chapterHref
     * @param bookId
     * @date 2021/1/12 16:23
     * @return void
     */
    private void saveChapterInfo(String baseUrl,String chapterHref,Long bookId) {
        String allChapterUrl = baseUrl + chapterHref;
        String chapterHtml = HttpClientUtil.httpGet(allChapterUrl);
        Document chapterDocument = Jsoup.parse(chapterHtml);
        Elements chapterElements = chapterDocument.select("#info > div.pc_list > ul >li");
        for (Element chapterElement : chapterElements) {
            String chapterName = chapterElement.select("a").text();
            String contentHref = chapterElement.select("a").attr("href");
            BookChapter chapter = BookChapter.builder()
                    .bookId(bookId)
                    .name(chapterName)
                    .build();
            bookChapterService.insertChapter(chapter);
            String contentDetailHtml = HttpClientUtil.httpGet(baseUrl + chapterHref + contentHref);
            Document contentDetailDocument = Jsoup.parse(contentDetailHtml);
            String content = contentDetailDocument.select("#content1").html();
            BookContent bookContent = BookContent.builder().chapterId(chapter.getId()).content(content).build();
            bookContentService.insertContent(bookContent);
            try {
                Thread.sleep(60000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

}
