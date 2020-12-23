/**
 * Copyright (c) 2016-2019 人人开源 All rights reserved.
 *
 * https://www.renren.io
 *
 * 版权所有，侵权必究！
 */

package com.hua.modules.sys.oauth2;

import java.io.IOException;

import javax.annotation.Resource;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.hua.common.util.R;
import com.hua.modules.sys.entity.SysUserEntity;
import com.hua.modules.sys.service.SysUserService;
import com.hua.modules.sys.service.SysUserTokenService;

import cn.hutool.crypto.digest.DigestAlgorithm;
import cn.hutool.crypto.digest.Digester;

/**
 * oauth2过滤器
 *
 * @author Mark sunlightcs@gmail.com
 */
public class OAuth2LoginFilter extends UsernamePasswordAuthenticationFilter {

    @Resource
    private SysUserService sysUserService;
    
    @Resource
    private SysUserTokenService sysUserTokenService;
    
    /**
     * 
     * @description 构造方法
     * @param authManager
     * @author qianye.zheng
     */
    public OAuth2LoginFilter(final AuthenticationManager authManager) {
        super.setAuthenticationManager(authManager);
    }
    
    /**
     * @description 
     * @param req
     * @param res
     * @param chain
     * @throws IOException
     * @throws ServletException
     * @author qianye.zheng
     */
    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
            throws IOException, ServletException {
        super.doFilter(req, res, chain);
    }
    
    
    /**
     * @description 
     * @param request
     * @param response
     * @return
     * @throws AuthenticationException
     * @author qianye.zheng
     */
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
            throws AuthenticationException {
        
        // 获取用户名和密码
        String username = obtainUsername(request);
        String password = obtainPassword(request);
        //用户信息
        SysUserEntity user = sysUserService.queryByUserName(username);
        
        if (user == null) {
            throw new UsernameNotFoundException("账号或密码不正确2");
        }
        final Digester digester = new Digester(DigestAlgorithm.SHA256);
        digester.setSalt(user.getSalt().getBytes());                
        final String passwordEncode = digester.digestHex(password);
        if (!user.getPassword().equals(passwordEncode)) {
            throw new UsernameNotFoundException("账号或密码不正确2");
        }
        
        //账号锁定
        if(user.getStatus() == 0){
            throw new UsernameNotFoundException("账号已被锁定,请联系管理员");
        }
        //生成token，并保存到数据库
        R r = sysUserTokenService.createToken(user.getUserId());        
        
        return super.attemptAuthentication(request, response);
    }
    
    /**
     * @description 
     * @param request
     * @param response
     * @param chain
     * @param authResult
     * @throws IOException
     * @throws ServletException
     * @author qianye.zheng
     */
    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
            Authentication authResult) throws IOException, ServletException {
        // 存储登录认证信息到上下文
        SecurityContextHolder.getContext().setAuthentication(authResult);
        //super.successfulAuthentication(request, response, chain, authResult);
    }
    
    
    
    
    
    
    
    
    /*
     * //@Override
     * protected AuthenticationToken createToken(ServletRequest request, ServletResponse response) throws Exception {
     * //获取请求token
     * String token = getRequestToken((HttpServletRequest) request);
     * 
     * if(StringUtils.isBlank(token)){
     * return null;
     * }
     * 
     * return new OAuth2Token(token);
     * }
     * 
     * @Override
     * protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) {
     * if(((HttpServletRequest) request).getMethod().equals(RequestMethod.OPTIONS.name())){
     * return true;
     * }
     * 
     * return false;
     * }
     * 
     * //@Override
     * protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
     * //获取请求token，如果token不存在，直接返回401
     * String token = getRequestToken((HttpServletRequest) request);
     * if(StringUtils.isBlank(token)){
     * HttpServletResponse httpResponse = (HttpServletResponse) response;
     * httpResponse.setHeader("Access-Control-Allow-Credentials", "true");
     * httpResponse.setHeader("Access-Control-Allow-Origin", HttpContextUtils.getOrigin());
     * 
     * String json = new Gson().toJson(R.error(HttpStatus.SC_UNAUTHORIZED, "invalid token"));
     * 
     * httpResponse.getWriter().print(json);
     * 
     * return false;
     * }
     * 
     * return executeLogin(request, response);
     * }
     * 
     * //@Override
     * protected boolean onLoginFailure(AuthenticationToken token, AuthenticationException e, ServletRequest request, ServletResponse
     * response) {
     * HttpServletResponse httpResponse = (HttpServletResponse) response;
     * httpResponse.setContentType("application/json;charset=utf-8");
     * httpResponse.setHeader("Access-Control-Allow-Credentials", "true");
     * httpResponse.setHeader("Access-Control-Allow-Origin", HttpContextUtils.getOrigin());
     * try {
     * //处理登录失败的异常
     * Throwable throwable = e.getCause() == null ? e : e.getCause();
     * R r = R.error(HttpStatus.SC_UNAUTHORIZED, throwable.getMessage());
     * 
     * String json = new Gson().toJson(r);
     * httpResponse.getWriter().print(json);
     * } catch (IOException e1) {
     * 
     * }
     * 
     * return false;
     * }
     */

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
