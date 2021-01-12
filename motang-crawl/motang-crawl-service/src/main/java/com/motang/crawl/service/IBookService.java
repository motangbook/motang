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

    /**
     * @Description 通过书名获得
     * @author liuhu
     * @param bookName
     * @date 2021/1/12 20:27
     * @return com.motang.crawl.api.entity.Book
     */
    Book selectByName(String bookName);

    /**
     * @Description 通过ID查询
     * @author liuhu
     * @param bookId
     * @date 2021/1/12 20:47
     * @return com.motang.crawl.api.entity.Book
     */
    Book selectById(Long bookId);

   /**
    * @Description 最新章节数+1
    * @author liuhu
    * @date 2021/1/12 20:53
    * @return void
    */
    void updateLastIndex(Long bookId);
}
