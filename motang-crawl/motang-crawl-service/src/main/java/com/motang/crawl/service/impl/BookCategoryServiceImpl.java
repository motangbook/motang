package com.motang.crawl.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.motang.common.core.commonenum.WrapperEnum;
import com.motang.crawl.api.entity.BookCategory;
import com.motang.crawl.exception.CrawlException;
import com.motang.crawl.mapper.BookCategoryMapper;
import com.motang.crawl.service.IBookCategoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
*  @Description msg 业务层实现类
*  @author liuhu
*  @Date 2021-1-9 22:49:39
*/
@Slf4j
@Service
public class BookCategoryServiceImpl  implements IBookCategoryService {

    @Autowired
    private BookCategoryMapper bookCategoryMapper;

    @Override
    public void insertBookCategory(BookCategory bookCategory) {
        try {
            bookCategoryMapper.insert(bookCategory);
           }catch (Exception e){
            log.error("新增小说分类失败！");
            throw new CrawlException("新增小说分类失败！");
        }
    }

    @Override
    public BookCategory getBookCategoryByName(String name) {
        try {
          return   bookCategoryMapper.selectOne(new QueryWrapper<BookCategory>().eq(WrapperEnum.NAME.getColumn(),name));
        }catch (Exception e){
            log.error("通过分类名称获取小说分类失败！");
            throw new CrawlException("通过分类名称获取小说分类失败！");
        }
    }
}
