package com.motang.common.redis.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.concurrent.TimeUnit;
/**
 * @Description 封装redis常用工具类
 * @author liuhu
 * @Date 2020/12/16 21:13
 */
public class RedisService {

    @Autowired
    private RedisTemplate<String,Object> redisTemplate;

    /**
     * @description  string get
     * @author liuhu
     * @param key
     * @date 2020/12/16 21:11
     * @return java.lang.Object
     */
    public Object get(String key) {
        return key == null ? null : redisTemplate.opsForValue().get(key);
    }

    /**
     * @description  string set
     * @author liuhu
     * @param key
     * @param value
     * @date 2020/12/16 21:11
     * @return void
     */
    public void set(String key, Object value) {
        try {
            redisTemplate.opsForValue().set(key, value);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * @description
     * @author liuhu
     * @param key
     * @param value
     * @param milliseconds 毫秒值
     * @date 2020/12/16 21:11
     * @return java.lang.Boolean
     */
    public void set(String key, Object value, Long milliseconds) {
        try {
            if (milliseconds > 0) {
                redisTemplate.opsForValue().set(key, value, milliseconds, TimeUnit.MILLISECONDS);
            } else {
                set(key, value);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

   /**
    * @description  获取
    * @author liuhu
    * @param key
    * @param item
    * @date 2020/12/17 21:50
    * @return java.lang.Object
    */
    public Object hget(String key, String item) {
        return redisTemplate.opsForHash().get(key, item);
    }

    /**
     * @description  向一张hash表中放入数据,如果不存在将创建
     * @author liuhu
     * @param key
     * @param item
     * @param value
     * @date 2020/12/17 21:50
     * @return java.lang.Boolean
     */
    public Boolean hset(String key, String item, Object value) {
        try {
            redisTemplate.opsForHash().put(key, item, value);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

  /**
   * @description  删除
   * @author liuhu
   * @param key
   * @param item
   * @date 2020/12/17 21:50
   * @return java.lang.Boolean
   */
    public Boolean hdet(String key, String item) {
        try {
            redisTemplate.opsForHash().delete(key, item);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
