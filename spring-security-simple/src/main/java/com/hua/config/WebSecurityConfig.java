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
//@EnableWebSecurity
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
        manager.createUser(User.withUsername("admin").password("admin").roles("").build());
        manager.createUser(User.withUsername("guest").password("guest").roles("").build());
        
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
       
       
    }
    
  
    
    
}
