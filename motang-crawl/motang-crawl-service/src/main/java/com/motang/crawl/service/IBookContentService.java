package com.motang.crawl.service;

import com.motang.crawl.api.entity.BookContent;

/**
*  @Description 小说正文
*  @author liuhu
*  @Date 2021-1-12 14:55:20
*/
public interface IBookContentService {
    /**
     * @Description 保存小说正文
     * @author liuhu
     * @param bookContent
     * @date 2021/1/12 16:25
     * @return void
     */
    void insertContent(BookContent bookContent);
}
