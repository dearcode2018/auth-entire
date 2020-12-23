/**
  * @filename OAuthenticationFilter.java
  * @description 
  * @version 1.0
  * @author qianye.zheng
 */
package com.hua.modules.sys.oauth2;

import java.io.IOException;

import javax.annotation.Resource;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import com.hua.modules.sys.entity.SysUserTokenEntity;
import com.hua.modules.sys.service.PermissionService;


/**
 * @type OAuthenticationFilter
 * @description 
 * @author qianye.zheng
 */
public class OAuthenticationFilter extends BasicAuthenticationFilter {

    @Resource
    private PermissionService permissionService;
    
    /**
     * @description 构造方法
     * @param authenticationManager
     * @author qianye.zheng
     */
    @Autowired
    public OAuthenticationFilter(AuthenticationManager authenticationManager) {
        super(authenticationManager);
    }
    
    /**
     * @description 
     * @param request
     * @param response
     * @param chain
     * @throws IOException
     * @throws ServletException
     * @author qianye.zheng
     */
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        super.doFilterInternal(request, response, chain);
        final String accessToken = getRequestToken(request);
        // 获取token，并检查登录态
        //根据accessToken，查询用户信息
        final SysUserTokenEntity tokenEntity = permissionService.queryByToken(accessToken);
        //token失效
        if(tokenEntity == null || tokenEntity.getExpireTime().getTime() < System.currentTimeMillis()){
            //throw new RuntimeException("token失效，请重新登录");
            return;
        }
        chain.doFilter(request, response);
    }
    
    /**
     * 获取请求的token
     */
    private String getRequestToken(HttpServletRequest httpRequest){
        //从header中获取token
        String token = httpRequest.getHeader("token");

        //如果header中不存在token，则从参数中获取token
        if(StringUtils.isBlank(token)){
            token = httpRequest.getParameter("token");
        }

        return token;
    }

    
}
