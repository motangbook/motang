package com.motang.portal.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.motang.common.core.commonenum.WrapperEnum;
import com.motang.admin.entity.Book;
import com.motang.portal.exception.PortalException;
import com.motang.portal.mapper.BookMapper;
import com.motang.portal.service.IBookService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Description 书籍表 服务实现类
 * @author liuhu
 * @Date 2020/12/10 20:03
 */
@Service
@Slf4j
public class BookServiceImpl implements IBookService {

    @Autowired
    private BookMapper bookMapper;

    @Override
    public Book selectByBookName(String name) {
        return bookMapper.selectOne(new QueryWrapper<Book>().eq(WrapperEnum.NAME.getColumn(),name));
    }

    @Override
    public List<Book> selectBookList() {
        try {
        }catch (Exception e){

        }
        return null;
    }

    @Override
    public void saveBook(Book book) {
        try {
            bookMapper.insert(book);
        }catch (Exception e){
            log.error("保存书籍信息失败！");
            throw new PortalException("保存书籍信息失败！");
        }
    }
}
