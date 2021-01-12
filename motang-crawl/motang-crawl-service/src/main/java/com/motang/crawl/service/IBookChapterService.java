package com.motang.crawl.service;

import com.motang.crawl.api.entity.BookChapter;

/**
*  @Description 小说章节
*  @author liuhu
*  @Date 2021-1-12 14:48:24
*/
public interface IBookChapterService {
    /**
     * @Description 保存章节信息
     * @author liuhu
     * @param chapter
     * @date 2021/1/12 16:24
     * @return void
     */
    void insertChapter(BookChapter chapter);

}
