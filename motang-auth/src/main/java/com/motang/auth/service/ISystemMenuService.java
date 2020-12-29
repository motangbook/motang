package com.motang.auth.service;

import com.motang.auth.entity.SystemUser;

import java.util.List;

/**
*  @Description msg 业务层接口
*  @author liuhu
*  @Date 2020-12-21 20:21:42
*/
public interface ISystemMenuService {

    /**
     * @description  查询权限字符串
     * @author liuhu
     * @param username
     * @date 2020/12/21 21:05
     * @return java.lang.String
     */
    List<String> selectPermissionByUsername(String username);
}
