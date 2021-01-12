package com.motang.crawl.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.motang.common.core.commonenum.WrapperEnum;
import com.motang.crawl.api.entity.Book;
import com.motang.crawl.api.entity.BookChapter;
import com.motang.crawl.exception.CrawlException;
import com.motang.crawl.mapper.BookChapterMapper;
import com.motang.crawl.mapper.BookMapper;
import com.motang.crawl.service.IBookChapterService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
*  @Description 小说实体
*  @author liuhu
*  @Date 2021-1-12 14:48:24
*/
@Slf4j
@Service
public class BookChapterServiceImpl  implements IBookChapterService {

    @Autowired
    private BookChapterMapper bookChapterMapper;

    @Autowired
    private BookMapper bookMapper;

    @Override
    public void insertChapter(BookChapter chapter) {
        try {
            bookChapterMapper.insert(chapter);
        }catch (Exception e){
            log.error("保存章节信息失败！");
            throw new CrawlException("保存章节信息失败！");
        }
    }

}
