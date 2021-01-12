package com.motang.crawl.service.impl;


import com.motang.crawl.api.entity.BookContent;
import com.motang.crawl.exception.CrawlException;
import com.motang.crawl.mapper.BookContentMapper;
import com.motang.crawl.service.IBookContentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
*  @Description 正文业务层实现类
*  @author liuhu
*  @Date 2021-1-12 14:55:20
*/
@Slf4j
@Service
public class BookContentServiceImpl  implements IBookContentService {

    @Autowired
    private BookContentMapper bookContentMapper;

    @Override
    public void insertContent(BookContent bookContent) {
        try {
            bookContentMapper.insert(bookContent);
        }catch (Exception e){
            log.error("保存小说正文信息失败！");
            throw new CrawlException("保存小说正文信息失败！");
        }
    }
}
