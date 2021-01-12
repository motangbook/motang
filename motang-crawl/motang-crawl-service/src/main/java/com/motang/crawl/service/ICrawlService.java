package com.motang.crawl.service;
/**
 *  @Description 爬虫接口
 *  @author liuhu
 *  @Date 2021/1/9 16:49
 */
public interface ICrawlService {
    /**
     * @Description 开启 或者关闭爬虫
     * @author liuhu
     * @param sourceId 爬虫资源表来源
     * @param status 状态
     * @date 2021/1/9 16:53
     * @return void
     */
    void open(String sourceId, String status);
    /**
     * @Description 爬取小说源站保存小说分类
     * @author liuhu
     * @param sourceId
     * @date 2021/1/12 10:25
     * @return void
     */
    void saveCategory(String sourceId);
}
