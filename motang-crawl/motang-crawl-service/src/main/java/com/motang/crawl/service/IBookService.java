package com.motang.crawl.service;

import com.motang.crawl.api.entity.Book;

/**
*  @Description 小说详情业务层接口
*  @author liuhu
*  @Date 2021-1-12 14:51:37
*/
public interface IBookService {

    /**
     * @Description 保存小说信息
     * @author liuhu
     * @param book
     * @date 2021/1/12 16:23
     * @return void
     */
    void insertBook(Book book);
}
