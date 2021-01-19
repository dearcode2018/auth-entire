/**
  * @filename WebSecurityConfig.java
  * @description 
  * @version 1.0
  * @author qianye.zheng
 */
package com.hua.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

/**
 * @type WebSecurityConfig
 * @description WEB 安全配置
 * @author qianye.zheng
 */
@EnableWebSecurity
//@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
  
    /**
     * 
     * @description 
     * @return
     * @author qianye.zheng
     */
    @Bean
    @Override
    public UserDetailsService userDetailsService() {
        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
        manager.createUser(User.withUsername("admin").password("admin").roles("admin").build());
        manager.createUser(User.withUsername("guest").password("guest").roles("guest").build());
        
        return manager;
    }
    
  
    /**
     * @description 
     * @param auth
     * @throws Exception
     * @author qianye.zheng
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    }
    
    /**
     * @description 
     * @param http
     * @throws Exception
     * @author qianye.zheng
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
       http.authorizeRequests()
       .antMatchers("/", "/index").permitAll()
       .anyRequest().authenticated()
       .and()
       .formLogin()
       /*指定自定义登录页面 在classpath:templates/ 目录下
        * 若不指定，则默认使用
        * org.springframework.security.web.server.ui.LoginPageGeneratingWebFilter.createPage
        * 生成的html，可以从浏览器的页面源码拷贝一份，进行改造，然后设置自定义的登录页
        */
      .loginPage("/login").permitAll()
       .and()
       /*
        * 退出登录页 /logout
        * 在org.springframework.security.web.server.ui.LogoutPageGeneratingWebFilter
        * 生成的html，可以从浏览器的页面源码拷贝一份，进行改造，然后设置自定义的退出登录
        */
       .logout().logoutUrl("/logout").permitAll();
    }
    
  
    
    
}
