package com.hua.config;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.cache.CacheManager;
import org.apache.shiro.cache.MemoryConstrainedCacheManager;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.realm.text.TextConfigurationRealm;
import org.apache.shiro.spring.web.config.DefaultShiroFilterChainDefinition;
import org.apache.shiro.spring.web.config.ShiroFilterChainDefinition;
import org.apache.shiro.subject.Subject;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * 
 * @type ShiroConfig
 * @description
 * @author qianye.zheng
 */
@Configuration
public class ShiroConfig {
    
    protected Log log = LogFactory.getLog(this.getClass().getName());
    
    /* 匿名/游客访问 */
    private static final String ANONYMOUS = "anon";
    
    /**
     * 
     * @description 自定义验证
     * @return
     * @author qianye.zheng
     */
    /*
     * @Bean
     * public Realm shiroRealm() {
     * return new CustomRealm();
     * }
     */
    
    /**/
    @Bean
    public Realm realm() {
        TextConfigurationRealm realm = new TextConfigurationRealm();
        /*
         * 用户名=密码, 逗号后面是角色，多个这样的item用换行隔开\n
         * username=password, role \n
         * 
         */
        realm.setUserDefinitions("joe.coder=password,query\n" + "jill.coder=password,admin");
        
        /*
         * 角色=资源(权限) 用逗哥隔开多个资源，用换行符隔开多个角色
         */
        realm.setRoleDefinitions("query=perms:read\n" + "admin=perms:read,perms:write\n");
        
        realm.setCachingEnabled(true);
        
        return realm;
    }
    
    /**
     * 
     * @description 过滤链定义
     * @return
     * @author qianye.zheng
     */
    @Bean
    public ShiroFilterChainDefinition shiroFilterChainDefinition() {
        // final DefaultShiroFilterChainDefinition chainDefinition = new DefaultShiroFilterChainDefinition();
        // chainDefinition.addPathDefinition("/admin/**", "authc, roles[admin]");
        // chainDefinition.addPathDefinition("/docs/**", "authc, perms[document:read]");
        // chainDefinition.addPathDefinition("/**", "authc");
        DefaultShiroFilterChainDefinition chainDefinition = new DefaultShiroFilterChainDefinition();
        
        // 匿名访问 (范围小的 必须放在前面才能生效)
        chainDefinition.addPathDefinition("/anonymous/**", ANONYMOUS);
        
        /* 所有的都需要登录 */
        chainDefinition.addPathDefinition("/**", "authc");
        
        // chainDefinition.addPathDefinition("/anonymous/**", "anon");
        
        chainDefinition.addPathDefinition("/login.html", "authc"); // need to accept POSTs from the login form
        chainDefinition.addPathDefinition("/logout", "logout");
        
        // chainDefinition.addPathDefinition("/query", "authc, roles[query]");
        // chainDefinition.addPathDefinition("/admin", "authc, roles[admin]");
        
        return chainDefinition;
    }
    
    /**
     * 
     * @description 解决 404 问题
     * @return
     * @author qianye.zheng
     */
    @Bean
    public DefaultAdvisorAutoProxyCreator getDefaultAdvisorAutoProxyCreator() {
        DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator = new DefaultAdvisorAutoProxyCreator();
        /**
         * setUsePrefix(false)用于解决一个奇怪的bug。在引入spring aop的情况下。
         * 在@Controller注解的类的方法中加入@RequiresRole等shiro注解，会导致该方法无法映射请求，导致返回404。
         * 加入这项配置能解决这个bug
         */
        defaultAdvisorAutoProxyCreator.setUsePrefix(true);
        return defaultAdvisorAutoProxyCreator;
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
    
    @ModelAttribute(name = "subject")
    public Subject subject() {
        return SecurityUtils.getSubject();
    }
    
    @ExceptionHandler(AuthorizationException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public String handleException(AuthorizationException e, Model model) {
        
        // you could return a 404 here instead (this is how github handles 403, so the user does NOT know there is a
        // resource at that location)
        log.debug("AuthorizationException was thrown", e);
        
        Map<String, Object> map = new HashMap<>();
        map.put("status", HttpStatus.FORBIDDEN.value());
        map.put("message", "No message available");
        model.addAttribute("errors", map);
        
        return "error";
    }
    
}
