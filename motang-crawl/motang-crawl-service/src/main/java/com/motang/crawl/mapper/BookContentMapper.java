package com.motang.crawl.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.motang.crawl.api.entity.BookContent;
import org.springframework.stereotype.Repository;

/**
*  @Description 小说正文 持久层接口
*  @author liuhu
*  @Date 2021-1-12 14:55:20
*/
@Repository
public interface BookContentMapper extends BaseMapper<BookContent> {

}
