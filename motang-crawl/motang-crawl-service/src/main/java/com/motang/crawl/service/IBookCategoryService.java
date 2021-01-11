package com.motang.crawl.service;


import com.motang.crawl.api.entity.BookCategory;

/**
*  @Description msg 业务层接口
*  @author liuhu
*  @Date 2021-1-9 22:49:39
*/
public interface IBookCategoryService {

    /**
     * @Description 新增分类
     * @author liuhu
     * @param bookCategory
     * @date 2021/1/11 14:05
     * @return void
     */
    void insertBookCategory(BookCategory bookCategory);

    /**
     * @Description 通过分类名称获得分类
     * @author liuhu
     * @param name
     * @date 2021/1/11 14:06
     * @return com.motang.crawl.api.entity.BookCategory
     */
    BookCategory getBookCategoryByName(String name);
}
