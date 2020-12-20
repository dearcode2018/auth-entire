package com.hua.config;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.cache.CacheManager;
import org.apache.shiro.cache.MemoryConstrainedCacheManager;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.spring.web.config.DefaultShiroFilterChainDefinition;
import org.apache.shiro.spring.web.config.ShiroFilterChainDefinition;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.hua.realm.CustomRealm;

/**
 * 
 * @type ShiroConfig
 * @description 
 * @author qianye.zheng
 */
@Configuration
public class ShiroConfig {

    @Resource
    private SecurityManager securityManager;
    
    
    /**
     * 
     * @description 
     * @author qianye.zheng
     */
    @PostConstruct
    private void initStaticSecurityManger() {
        SecurityUtils.setSecurityManager(securityManager);
    }
    
    /**
     * 
     * @description 将自定义验证方式加入容器
     * @return
     * @author qianye.zheng
     */
    @Bean
    public Realm shiroRealm() {
        return new CustomRealm();
    }
    
    /**
     * 
     * @description 
     * @return
     * @author qianye.zheng
     */
    @Bean
    public ShiroFilterChainDefinition shiroFilterChainDefinition() {
        final DefaultShiroFilterChainDefinition chainDefinition = new DefaultShiroFilterChainDefinition();
        chainDefinition.addPathDefinition("/admin/**", "authc, roles[admin]");
        chainDefinition.addPathDefinition("/docs/**", "authc, perms[document:read]");
        chainDefinition.addPathDefinition("/**", "authc");
        
        return chainDefinition;
    }
    
    /**
     * 
     * @description 
     * @return
     * @author qianye.zheng
     */
    @Bean
    protected CacheManager cacheManager() {
        return new MemoryConstrainedCacheManager();
    }
 
     
}
