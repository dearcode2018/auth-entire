/**
  * @filename UserDetailServiceImpl.java
  * @description 
  * @version 1.0
  * @author qianye.zheng
 */
package com.hua.modules.sys.oauth2;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.annotation.Resource;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.hua.modules.sys.entity.SysUserEntity;
import com.hua.modules.sys.service.PermissionService;
import com.hua.modules.sys.service.SysUserService;


/**
 * @type UserDetailServiceImpl
 * @description 
 * @author qianye.zheng
 */
@Service
public class UserDetailServiceImpl implements UserDetailsService {
    
    @Resource
    private PermissionService permissionService;
    
    @Resource
    private SysUserService sysUserService;
    
    /**
     * @description 
     * @param username
     * @return
     * @throws UsernameNotFoundException
     * @author qianye.zheng
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        
        //用户信息
        final SysUserEntity user = sysUserService.queryByUserName(username);
        if (null == user) {
            throw new UsernameNotFoundException("用户不存在");
        }
        // 权限列表
        final Set<String> permissions = permissionService.getUserPermissions(user.getUserId());
        final List<GrantedAuthority> grantedAuthorities = permissions.stream().map(GrantedAuthorityImpl :: new).collect(Collectors.toList());
        
        return new OAuthUser(username, user.getPassword(), grantedAuthorities);
    }
    
}
