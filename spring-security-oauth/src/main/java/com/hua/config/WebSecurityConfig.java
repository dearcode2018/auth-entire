/**
  * @filename WebSecurityConfig.java
  * @description 
  * @version 1.0
  * @author qianye.zheng
 */
package com.hua.config;

import javax.annotation.Resource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.HttpStatusReturningLogoutSuccessHandler;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import com.hua.modules.sys.oauth2.OAuth2LoginFilter;
import com.hua.modules.sys.oauth2.OAuthenticationFilter;
import com.hua.modules.sys.oauth2.OAuthenticationProvider;
import com.hua.modules.sys.service.PermissionService;

/**
 * @type WebSecurityConfig
 * @description WEB 安全配置
 * @author qianye.zheng
 */
@Configuration
@EnableWebSecurity
//@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    
    @Resource
    private PermissionService permissionService;
    
    @Resource
    private UserDetailsService userDetailsService;
    
    
    /**
     * @description 
     * @param auth
     * @throws Exception
     * @author qianye.zheng
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService);
        //auth.authenticationProvider(new OAuthenticationProvider(userDetailsService));
    }
    
    /**
     * @description 
     * @param http
     * @throws Exception
     * @author qianye.zheng
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors().and().csrf().disable()
            .authorizeRequests().antMatchers(HttpMethod.OPTIONS, "/**").permitAll()
            .antMatchers("/sys/login").permitAll()
            .anyRequest().authenticated();
        
        // 登录认证过滤器
       http.addFilterBefore(loginFilter(), UsernamePasswordAuthenticationFilter.class);
        
        // 登录状态检查过滤器
        http.addFilterAfter(oauthFilter(), UsernamePasswordAuthenticationFilter.class);
        
        
        
        
        
        
        
        
        
        
        
        //.and().formLogin().loginProcessingUrl("/sys/login")
        //.and().authorizeRequests()
        //.antMatchers("/sys/login").permitAll()
        //.anyRequest().authenticated();
        
        
        // 退出登录处理器
        //http.logout().logoutSuccessHandler(new HttpStatusReturningLogoutSuccessHandler());
        
        /*
         * http.cors().and().csrf().disable()
         * .authorizeRequests()
         * .antMatchers(HttpMethod.OPTIONS, "/**").permitAll()
         * // 登录url
         * .and().formLogin().loginProcessingUrl("sys/login").permitAll();
         * //.antMatchers("/sys/login").permitAll()
         * // 其他请求需要身份认证
         * // 登录认证过滤器
         * http.addFilterBefore(loginFilter(), UsernamePasswordAuthenticationFilter.class);
         * 
         * // 登录状态检查过滤器
         * //http.addFilterAfter(oauthFilter(), BasicAuthenticationFilter.class);
         * 
         * // 退出登录处理器
         * http.logout().logoutSuccessHandler(new HttpStatusReturningLogoutSuccessHandler());
         */
       // .and().formLogin().loginProcessingUrl("/sys/login");
    }
    
    /**
     * 
     * @description 
     * @return
     * @author qianye.zheng
     */
    @Bean
    public OAuthenticationFilter oauthFilter() {
        return new OAuthenticationFilter(authenticationManager());
    }
    
    /**
     * 
     * @description 
     * @return
     * @author qianye.zheng
     */
    @Bean
    OAuth2LoginFilter loginFilter() {
        return new OAuth2LoginFilter(authenticationManager());
    }
    
    /**
     * @description 
     * @return
     * @throws Exception
     * @author qianye.zheng
     */
    @Bean
    @Override
    protected AuthenticationManager authenticationManager() {
        try {
            return super.authenticationManager();
        } catch (Exception e) {
        }
        return null;
    }
    
    
}
