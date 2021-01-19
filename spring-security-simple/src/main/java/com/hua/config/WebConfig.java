/**
  * @filename WebConfig.java
  * @description 
  * @version 1.0
  * @author qianye.zheng
 */
package com.hua.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


/**
 * @type WebConfig
 * @description 
 * @author qianye.zheng
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {
    
    /**
     * @description 
     * @param registry
     * @author qianye.zheng
     */
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        WebMvcConfigurer.super.addViewControllers(registry);
        /*
         * 设置页面和url映射
         * addViewController urlPath 对应访问的url，
         * 例如跟WebSecurityConfig. HttpSecurity.formLogin.loginPage("/login")
         */
        
        registry.addViewController("/login").setViewName("/loginView");
        registry.addViewController("/").setViewName("/index");
        registry.addViewController("/logout").setViewName("/logout");
    }
    
}
