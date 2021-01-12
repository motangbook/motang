package com.motang.crawl.service.impl;

import com.motang.crawl.api.entity.Author;
import com.motang.crawl.exception.CrawlException;
import com.motang.crawl.mapper.AuthorMapper;
import com.motang.crawl.mapper.BookMapper;
import com.motang.crawl.service.IAuthorService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

;

/**
*  @Description 作者业务层实现类
*  @author liuhu
*  @Date 2021-1-12 14:51:37
*/
@Slf4j
@Service
public class AuthorServiceImpl implements IAuthorService {

    @Autowired
    private AuthorMapper authorMapper;

    @Override
    public void insertAuthor(Author author) {
        try {
            authorMapper.insert(author);
        }catch (Exception e){
            log.error("保存作者信息失败！");
            throw new CrawlException("保存作者信息失败！");
        }
    }
}
