package com.motang.auth.service;


import com.motang.auth.entity.SystemUser;

/**
*  @Description msg 业务层接口
*  @author liuhu
*  @Date 2020-12-21 20:09:07
*/
public interface ISystemUserService {
    /**
     * @description 根据username当前用户
     * @author liuhu
     * @param username
     * @date 2020/12/21 20:54
     * @return com.motang.auth.entity.SystemUser
     */
    SystemUser selectByUsername(String username);
}
