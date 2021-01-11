package com.motang.crawl.service;


import com.motang.crawl.api.entity.CrawlSource;

/**
*  @Description msg 业务层接口
*  @author liuhu
*  @Date 2021-1-11 14:28:14
*/
public interface ICrawlSourceService {

    /**
     * @Description 根据ID获得爬虫数据源
     * @author liuhu
     * @param sourceId
     * @date 2021/1/11 15:47
     * @return com.motang.crawl.api.entity.CrawlSource
     */
    CrawlSource selectById(String sourceId);
    /**
     * @Description 改变小说网站状态
     * @author liuhu
     * @param sourceId
     * @param status
     * @date 2021/1/11 17:13
     * @return void
     */
    void updateSourceStatusById(String sourceId, String status);
}
