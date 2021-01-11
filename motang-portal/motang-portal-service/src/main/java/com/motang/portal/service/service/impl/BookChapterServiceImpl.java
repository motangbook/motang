package com.motang.portal.service.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.motang.common.core.commonenum.WrapperEnum;
import com.motang.admin.entity.BookChapter;
import com.motang.portal.service.exception.PortalException;
import com.motang.portal.service.mapper.BookChapterMapper;
import com.motang.portal.service.service.IBookChapterService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Description 章节表 服务实现类
 * @author liuhu
 * @Date 2020/12/10 21:19
 */
@Service
@Slf4j
public class BookChapterServiceImpl implements IBookChapterService {

    @Autowired
    private BookChapterMapper bookChapterMapper;

    @Override
    public void saveChapter(BookChapter bookChapter) {
        try {
            bookChapterMapper.insert(bookChapter);
        }catch (Exception e){
            log.error("保存书籍章节信息失败！");
            throw new PortalException("保存书籍章节信息失败！");
        }
    }

    @Override
    public BookChapter selectByName(String chapterName) {
        return bookChapterMapper.selectOne(new QueryWrapper<BookChapter>().eq(WrapperEnum.NAME.getColumn(),chapterName));
    }
}
