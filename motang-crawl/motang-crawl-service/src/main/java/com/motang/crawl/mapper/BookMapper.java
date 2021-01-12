package com.motang.crawl.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.motang.crawl.api.entity.Book;
import org.springframework.stereotype.Repository;

/**
*  @Description 小说 持久层接口
*  @author liuhu
*  @Date 2021-1-12 14:51:37
*/
@Repository
public interface BookMapper extends BaseMapper<Book> {

}
