package com.motang.crawl.service;


import com.motang.crawl.api.entity.Author;

/**
*  @Description msg 业务层接口
*  @author liuhu
*  @Date 2021-1-12 14:28:00
*/
public interface IAuthorService {
    /**
     * @Description 保存作者信息
     * @author liuhu
     * @param author
     * @date 2021/1/12 16:20
     * @return void
     */
    void insertAuthor(Author author);
}
