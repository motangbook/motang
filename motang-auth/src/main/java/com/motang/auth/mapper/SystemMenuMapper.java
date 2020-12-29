package com.motang.auth.mapper;

import com.motang.auth.entity.SystemMenu;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
*  @Description msg 持久层接口
*  @author liuhu
*  @Date 2020-12-21 20:21:42
*/
@Repository
public interface SystemMenuMapper extends BaseMapper<SystemMenu> {
    /**
     * @description 用户名查询权限标识
     * @author liuhu
     * @param username
     * @date 2020/12/21 21:07
     * @return java.util.List<java.lang.String>
     */
    List<String> selectPermissionByUsername(String username);
}
