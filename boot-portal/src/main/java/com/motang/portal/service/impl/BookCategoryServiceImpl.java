package com.motang.portal.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.motang.common.core.commonenum.WrapperEnum;
import com.motang.common.core.entity.BookCategory;
import com.motang.portal.exception.PortalException;
import com.motang.portal.mapper.BookCategoryMapper;
import com.motang.portal.service.IBookCategoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

/**
 * @Description 书籍分类表 服务实现类
 * @author liuhu
 * @Date 2020/12/15 20:04
 */
@Service
@Slf4j
public class BookCategoryServiceImpl  implements IBookCategoryService {

    @Autowired
    private BookCategoryMapper bookCategoryMapper;

    @Override
    public void saveBookCategory(BookCategory category) {
        try {
            category.setUpdateTime(LocalDateTime.now());
            bookCategoryMapper.insert(category);
        }catch (Exception e){
            log.error("保存书籍分类失败！");
            throw new PortalException("保存书籍分类失败！");
        }
    }

    @Override
    public BookCategory selectByName(String categoryName) {
        BookCategory bookCategory = null;
        try {
            bookCategory = bookCategoryMapper.selectOne(new QueryWrapper<BookCategory>().eq(WrapperEnum.NAME.getColumn(), categoryName));
        }catch (Exception e){
            log.error("通过分类名称获得书籍分类失败！");
            throw new PortalException("通过分类名称获得书籍分类失败！");
        }
        return bookCategory;
    }
}
