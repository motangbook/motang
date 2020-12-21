package com.motang.auth.service.impl;


import com.motang.auth.entity.SystemUser;
import com.motang.auth.exception.AuthorizeException;
import com.motang.auth.mapper.SystemUserMapper;
import com.motang.auth.service.ISystemUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
*  @Description msg 业务层实现类
*  @author liuhu
*  @Date 2020-12-21 20:09:07
*/
@Service
@Slf4j
public class SystemUserServiceImpl  implements ISystemUserService {

    @Autowired
    private SystemUserMapper systemUserMapper;
    @Override
    public SystemUser selectByUsername(String username) {
        SystemUser systemUser = null;
        try {
            systemUser =  systemUserMapper.selectById(username);
        }catch (Exception e){
            log.error("通过username获取用户失败",e);
            throw new AuthorizeException("通过username获取用户失败！");
        }
        return systemUser;
    }
}
