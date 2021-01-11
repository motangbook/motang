package com.motang.crawl.constant;
/**
 * @description 系统常量类
 * @author liuhu
 * @Date 2020/12/15 16:49
 */
public interface CrawlConstants {

    String MAN_WORK_DIRECTION ="男生分类";

    String WOMEN_WORK_DIRECTION ="女生分类";

    /**本地文件存储地址*/
    String LOCAL_FILE_STORE = "D:\\lh\\image";

    /**
     * OAUTH2 令牌头
     */
    String OAUTH2_TOKEN_TYPE = "bearer";


    String CRAWL_THREAD_PREFIX = "CRAWL_SOURCE_";

    int OPEN_CRAWL = 1;

    int CLOSE_CRAWL = 2;
}
