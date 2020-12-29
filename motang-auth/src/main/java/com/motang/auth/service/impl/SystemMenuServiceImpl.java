package com.motang.auth.service.impl;

import com.motang.auth.entity.SystemUser;
import com.motang.auth.exception.AuthorizeException;
import com.motang.auth.mapper.SystemMenuMapper;
import com.motang.auth.service.ISystemMenuService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


/**
*  @Description msg 业务层实现类
*  @author liuhu
*  @Date 2020-12-21 20:21:42
*/
@Service
@Slf4j
public class SystemMenuServiceImpl  implements ISystemMenuService {

    @Autowired
    private  SystemMenuMapper systemMenuMapper;

    @Override
    public List<String> selectPermissionByUsername(String username) {
        List<String> permission = null;
        try {
            permission =  systemMenuMapper.selectPermissionByUsername(username);
        }catch (Exception e){
            log.error("通过username查询权限标识失败",e);
            throw new AuthorizeException("通过username查询权限标识失败！");
        }
        return permission;
    }
}
