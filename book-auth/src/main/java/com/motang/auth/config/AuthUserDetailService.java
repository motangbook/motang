package com.motang.auth.config;

import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.motang.auth.entity.AuthorizeUser;
import com.motang.auth.entity.SystemUser;
import com.motang.auth.service.ISystemMenuService;
import com.motang.auth.service.ISystemUserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @Description 用户名密码校验逻辑
 * @author liuhu
 * @Date 2020/12/21 20:51
 */
@Component
public class AuthUserDetailService implements UserDetailsService {

    @Autowired
    private ISystemUserService systemUserService;

    @Autowired
    private ISystemMenuService systemMenuService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        SystemUser systemUser = systemUserService.selectByUsername(username);
        List<String> permission = systemMenuService.selectPermissionByUsername(username);
        if(null != systemUser){
            // 是否锁定
            boolean notLocked = false;
            if (SystemUser.STATUS_VALID == systemUser.getStatus()) {
                notLocked = true;
            }
            // 权限集合
            List<GrantedAuthority> grantedAuthorities = AuthorityUtils.NO_AUTHORITIES;
            if(!CollectionUtils.isEmpty(permission)){
                String permissionStr = permission.stream().collect(Collectors.joining(StringPool.COMMA));
                grantedAuthorities = AuthorityUtils.commaSeparatedStringToAuthorityList(permissionStr);
            }
            // 构建返回用户信息
            AuthorizeUser authorizeUser = new AuthorizeUser
                    (systemUser.getUsername(),systemUser.getPassword(), true, true, true, notLocked,grantedAuthorities);
         
            BeanUtils.copyProperties(systemUser,authorizeUser);
            return authorizeUser;
        }else {
            throw new UsernameNotFoundException("");
        }
    }
}
