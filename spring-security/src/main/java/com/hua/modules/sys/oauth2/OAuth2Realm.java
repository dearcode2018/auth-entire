/**
 * Copyright (c) 2016-2019 人人开源 All rights reserved.
 *
 * https://www.renren.io
 *
 * 版权所有，侵权必究！
 */

package com.hua.modules.sys.oauth2;

import java.util.Set;

import javax.annotation.Resource;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

import com.hua.modules.sys.entity.SysUserEntity;
import com.hua.modules.sys.entity.SysUserTokenEntity;
import com.hua.modules.sys.service.PermissionService;

/**
 * 认证
 *
 * @author Mark sunlightcs@gmail.com
 */
//@Component
public class OAuth2Realm extends AuthorizingRealm {
    
    @Resource
    private PermissionService permissionService;

    @Override
    public boolean supports(final AuthenticationToken token) {
        return token instanceof OAuth2Token;
    }

    /**
     * 认证(登录时调用)
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(final AuthenticationToken token) throws AuthenticationException {
        final String accessToken = (String) token.getPrincipal();

        //根据accessToken，查询用户信息
        final SysUserTokenEntity tokenEntity = permissionService.queryByToken(accessToken);
        //token失效
        if(tokenEntity == null || tokenEntity.getExpireTime().getTime() < System.currentTimeMillis()){
            throw new IncorrectCredentialsException("token失效，请重新登录");
        }

        //查询用户信息
        final SysUserEntity user = permissionService.queryUser(tokenEntity.getUserId());
        //账号锁定
        if(user.getStatus() == 0){
            throw new LockedAccountException("账号已被锁定,请联系管理员");
        }
        final SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(user, accessToken, getName());
        
        return info;
    }
    
    /**
     * 授权(验证权限时调用)
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(final PrincipalCollection principals) {
        final SysUserEntity user = (SysUserEntity)principals.getPrimaryPrincipal();
        final Long userId = user.getUserId();

        //用户权限列表
        final Set<String> permsSet = permissionService.getUserPermissions(userId);
        final SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        info.setStringPermissions(permsSet);
        
        return info;
    }
}
