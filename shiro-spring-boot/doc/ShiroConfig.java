package com.hua.config;

import java.util.HashMap;
import java.util.Map;

import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
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

    /**
     * 
     * @description 
     * @return
     * @author qianye.zheng
     */
    /*
     * @Bean
     * 
     * @ConditionalOnMissingBean
     * public DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator() {
     * final DefaultAdvisorAutoProxyCreator defaultAAP = new DefaultAdvisorAutoProxyCreator();
     * // 代理类的方式
     * defaultAAP.setProxyTargetClass(true);
     * 
     * return defaultAAP;
     * }
     */

    /**
     * 
     * @description 设置过滤和跳转条件
     * @param securityManager
     * @return
     * @author qianye.zheng
     */
    @Bean
    public ShiroFilterFactoryBean shiroFilterFactoryBean(final SecurityManager securityManager) {
        final ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(securityManager);
        final Map<String, String> map = new HashMap<>();
        // 登出
        map.put("/logout", "logout");
        // 对所有用户认证
        map.put("/**", "authc");
        // 登录
        shiroFilterFactoryBean.setLoginUrl("/login");
        // 首页
        shiroFilterFactoryBean.setSuccessUrl("/index");
        // 错误页面，认证不通过跳转
        shiroFilterFactoryBean.setUnauthorizedUrl("/error");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(map);
        
        return shiroFilterFactoryBean;
    }
    
    /**
     * 
     * @description 将自定义验证方式加入容器
     * @return
     * @author qianye.zheng
     */
    @Bean
    public CustomRealm shiroRealm() {
        return new CustomRealm();
    }

    /**
     * 
     * @description Realm的管理认证
     * @return
     * @author qianye.zheng
     */
    /*
     * @Bean
     * public SecurityManager securityManager() {
     * final DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
     * securityManager.setRealm(shiroRealm());
     * 
     * return securityManager;
     * }
     */

    /**
     * 
     * @description 注入权限管理
     * @param securityManager
     * @return
     * @author qianye.zheng
     */
    
      @Bean
      public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(final SecurityManager securityManager) {
      final AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor =
      new AuthorizationAttributeSourceAdvisor();
      authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);
      
      return authorizationAttributeSourceAdvisor;
      }
     
}
