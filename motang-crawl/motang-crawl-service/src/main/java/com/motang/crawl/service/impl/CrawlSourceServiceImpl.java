 package com.motang.crawl.service.impl;

import com.motang.crawl.api.entity.CrawlSource;
import com.motang.crawl.exception.CrawlException;
import com.motang.crawl.mapper.CrawlSourceMapper;
import com.motang.crawl.service.ICrawlSourceService;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import lombok.extern.slf4j.Slf4j;

/**
*  @Description msg 业务层实现类
*  @author liuhu
*  @Date 2021-1-11 14:28:14
*/
@Slf4j
@Service
public class CrawlSourceServiceImpl  implements ICrawlSourceService {

    @Autowired
    private CrawlSourceMapper crawlSourceMapper;

    @Override
    public CrawlSource selectById(String sourceId) {
       try {
          return crawlSourceMapper.selectById(sourceId);
       }catch (Exception e){
           log.error("通过id获取爬虫数据源失败！");
           throw new CrawlException("通过id获取爬虫数据源失败！");
       }
    }

    @Override
    public void updateSourceStatusById(String sourceId, String status) {
        try {
             crawlSourceMapper.updateSourceStatusById(sourceId,status);
        }catch (Exception e){
            log.error("通过id改变爬虫数据源状态失败！");
            throw new CrawlException("通过id改变爬虫数据源状态失败！");
        }
    }
}
