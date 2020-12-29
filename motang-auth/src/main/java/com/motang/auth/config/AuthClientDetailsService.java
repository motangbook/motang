package com.motang.auth.config;

import com.alibaba.fastjson.JSONObject;
import com.motang.common.redis.service.RedisService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.common.exceptions.InvalidClientException;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.client.BaseClientDetails;
import org.springframework.security.oauth2.provider.client.JdbcClientDetailsService;

import javax.sql.DataSource;

/**
 * @Description 客户端信息获取 先从mysql取出放入redis
 * @author liuhu
 * @Date 2020/12/17 20:54
 */
@Configuration
public class AuthClientDetailsService extends JdbcClientDetailsService {

     @Autowired
     private RedisService redisService;

     private static final String REDIS_CLIENT_KEY = "redis_client:";
    /**
     * @Description
     * @author liuhu
     * @Date 2020/12/17 21:36
     */
    public AuthClientDetailsService(DataSource dataSource) {
        super(dataSource);
    }

    /**
     * @description  通过clientID加载客户端信息
     * @author liuhu
     * @param clientId
     * @date 2020/12/17 21:59
     * @return org.springframework.security.oauth2.provider.ClientDetails
     */
    @Override
    public ClientDetails loadClientByClientId(String clientId) throws InvalidClientException {
        ClientDetails clientDetails = null;
        String clientDetailsStr = (String)redisService.hget(REDIS_CLIENT_KEY, clientId);
        if(StringUtils.isNoneBlank(clientDetailsStr)){
             clientDetails = JSONObject.parseObject(clientDetailsStr, BaseClientDetails.class);
        }else {
            clientDetails = getAndCache(clientId);
        }
        return clientDetails;
    }

    /**
     * @description  redis不存在直接从mysql中出去   JdbcClientDetailsService地城会默认查oauth2.0系统表oauth_client_details
     * @author liuhu
     * @param clientId
     * @date 2020/12/17 21:58
     * @return org.springframework.security.oauth2.provider.ClientDetails
     */
    public ClientDetails getAndCache(String clientId) {
        ClientDetails  clientDetails = super.loadClientByClientId(clientId);
        if(null != clientDetails){
            redisService.hset(REDIS_CLIENT_KEY,clientId,JSONObject.toJSONString(clientDetails));
        }
        return clientDetails;
    }
    /**
     * @Description 清除客户端缓存
     * @author liuhu
     * @createTime 2020/12/17 22:10
     * @param clientId
     * @return void
     */
    public void deleteClientCache(String clientId){
        if(StringUtils.isNotBlank(clientId)){
            redisService.hdet(REDIS_CLIENT_KEY,clientId);
        }
    }

}
