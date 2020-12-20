package com.hua.realm;

import javax.annotation.Resource;

import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.util.StringUtils;

import com.hua.bean.ResourceObject;
import com.hua.bean.Role;
import com.hua.bean.User;
import com.hua.service.LoginService;

/**
 * 
 * @type CustomRealm
 * @description 自定义领域-认证/授权
 * @author qianye.zheng
 */
public class CustomRealm extends AuthorizingRealm {

    @Resource
    private LoginService loginService;

    /**
     * @MethodName doGetAuthenticationInfo
     * @Description 认证 (登录)
     * @Param [authenticationToken]
     * @Return AuthenticationInfo
     * @Author WangShiLin
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(final AuthenticationToken authenticationToken)  {
        if (StringUtils.isEmpty(authenticationToken.getPrincipal())) {
            return null;
        }
        //获取用户信息
        final String username = authenticationToken.getPrincipal().toString();
        final User user = loginService.getUserByName(username);
        if (user == null) {
            // TO DO 这里返回后会报出对应异常
            return null;
        } else {
            //这里验证authenticationToken和simpleAuthenticationInfo的信息
           return new SimpleAuthenticationInfo(username, user.getPassword(), getName());
        }
    }
    
    /**
     * @MethodName doGetAuthorizationInfo
     * @Description 授权 (登录之后)
     * @Param [principalCollection]
     * @Return AuthorizationInfo
     * @Author WangShiLin
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(final PrincipalCollection principalCollection) {
        //获取登录用户名
        final String username = principalCollection.getPrimaryPrincipal().toString();
        //查询用户名称
        final User user = loginService.getUserByName(username);
        //添加角色和权限
        final SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        for (Role role : user.getRoles()) {
            // 添加角色
            simpleAuthorizationInfo.addRole(role.getName());
            // 添加资源
            for (ResourceObject resource : role.getResources()) {
                simpleAuthorizationInfo.addStringPermission(resource.getName());
            }
        }
        
        return simpleAuthorizationInfo;
    }
    
}
