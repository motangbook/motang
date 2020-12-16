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
}
