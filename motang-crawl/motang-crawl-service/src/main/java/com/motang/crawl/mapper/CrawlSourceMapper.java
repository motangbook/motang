package com.motang.crawl.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.motang.crawl.api.entity.CrawlSource;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
*  @Description msg 持久层接口
*  @author liuhu
*  @Date 2021-1-11 14:28:14
*/
@Repository
public interface CrawlSourceMapper extends BaseMapper<CrawlSource> {
    /**
     * @Description 通过id改变爬虫数据源状态失败
     * @author liuhu
     * @param sourceId
     * @param status
     * @date 2021/1/11 17:14
     * @return void
     */
    void updateSourceStatusById(@Param("sourceId") String sourceId, @Param("status") String status);


}
