package com.motang.crawl.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.motang.common.core.utils.ThreadPoolUtil;
import com.motang.common.redis.service.RedisService;
import com.motang.crawl.api.entity.CrawlSource;
import com.motang.crawl.service.ICrawlService;
import com.motang.crawl.service.ICrawlSourceService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadPoolExecutor;

/**
 *  @Description 爬虫业务层实现类
 *  @author liuhu
 *  @Date 2021/1/9 16:50
 */
@Slf4j
@Service
public class CrawlServiceImpl implements ICrawlService {

    @Autowired
    private ICrawlSourceService crawlSourceService;

    @Autowired
    private RedisService redisService;

    @Override
    public void open(String sourceId, String status) {
        CrawlSource crawlSource = crawlSourceService.selectById(sourceId);
        //1.判断当前是关闭还是开启  如果是开启则更改数据库状态 用线程池创建线程执行  并把线程id存入缓存
        if(0 == Byte.parseByte(status)){
            // 查询当前数据库开启状态 如果已经开启无需关心
            if(crawlSource.getSourceStatus() == 0){
                crawlSourceService.updateSourceStatusById(sourceId,status);
                ThreadPoolExecutor threadPoolExecutor = ThreadPoolUtil.buildThreadPoolExecutor();
                List<Long> threadIdList = new ArrayList<>();
                for (int i = 0; i < 8; i++) {
                    threadPoolExecutor.submit(() -> {
                       //1. 将运行的线程ID放入集合  放入缓存
                        threadIdList.add(Thread.currentThread().getId());
                        // 2.解析 小说信息入库
                    });
                }
                redisService.set("CRAWL_SOURCE_"+sourceId,threadIdList);
            }
        }else {
            // 判断数据库状态是否一直  更新数据库数据状态  关闭当前小说源站的运行线程
          if(1 == Byte.parseByte(status)){
              crawlSourceService.updateSourceStatusById(sourceId,status);
              String threadIdListCache = (String) redisService.get("CRAWL_SOURCE_" + sourceId);
              if(StringUtils.isNotBlank(threadIdListCache)){
                  List<Long> threadIdList = JSONObject.parseArray(threadIdListCache, Long.class);
                  threadIdList.forEach(id->{
                      Thread thread = ThreadPoolUtil.findThread(id);
                      if(thread != null && thread.isAlive()){
                          thread.interrupt();
                      }
                  });
              }
          }
        }
    }

}
