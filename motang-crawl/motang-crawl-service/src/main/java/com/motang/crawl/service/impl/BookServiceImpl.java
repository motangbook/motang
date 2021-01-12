package com.motang.crawl.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.motang.common.core.commonenum.WrapperEnum;
import com.motang.crawl.api.entity.Book;
import com.motang.crawl.exception.CrawlException;
import com.motang.crawl.mapper.BookMapper;
import com.motang.crawl.service.IBookService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

;

/**
*  @Description 章节 业务层实现类
*  @author liuhu
*  @Date 2021-1-12 14:51:37
*/
@Slf4j
@Service
public class BookServiceImpl  implements IBookService {

    @Autowired
    private BookMapper bookMapper;

    @Override
    public void insertBook(Book book) {
        try {
            bookMapper.insert(book);
        }catch (Exception e){
            log.error("保存小说信息失败！");
            throw new CrawlException("保存小说信息失败！");
        }
    }

    @Override
    public Book selectByName(String bookName) {
        try {
         return   bookMapper.selectOne(new QueryWrapper<Book>().eq(WrapperEnum.NAME.getColumn(),bookName));
        }catch (Exception e){
            log.error("根据小说名查询小说信息失败！");
            throw new CrawlException("根据小说名查询小说信息失败！");
        }
    }

    @Override
    public Book selectById(Long bookId) {
        try {
            return   bookMapper.selectById(bookId);
        }catch (Exception e){
            log.error("根据小说名查询小说信息失败！");
            throw new CrawlException("根据小说名查询小说信息失败！");
        }
    }

    @Override
    public void updateLastIndex(Long bookId) {
        try {
               bookMapper.updateLastIndex(bookId);
        }catch (Exception e){
            log.error("根据小说名查询小说信息失败！");
            throw new CrawlException("根据小说名查询小说信息失败！");
        }
    }
}
