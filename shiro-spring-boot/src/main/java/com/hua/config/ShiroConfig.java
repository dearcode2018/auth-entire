package com.hua.config;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.Filter;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.spring.web.config.DefaultShiroFilterChainDefinition;
import org.apache.shiro.spring.web.config.ShiroFilterChainDefinition;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.hua.modules.sys.oauth2.OAuth2Filter;
import com.hua.modules.sys.oauth2.OAuth2Realm;

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
    
    /**
     * 
     * @description
     * @return
     * @author qianye.zheng
     */
    @Bean
    public Realm realm() {
        return new OAuth2Realm();
    }
    
    /**/
    
    /*
     * @Bean
     * public Realm realm() {
     * TextConfigurationRealm realm = new TextConfigurationRealm();
     * // 用户名=密码, 逗号后面是角色，多个这样的item用换行隔开\n
     * // username=password, role \n
     * realm.setUserDefinitions("joe.coder=password,query\n" + "jill.coder=password,admin");
     * 
     * // 角色=资源(权限) 用逗哥隔开多个资源，用换行符隔开多个角色
     * realm.setRoleDefinitions("query=perms:read\n" + "admin=perms:read,perms:write\n");
     * 
     * realm.setCachingEnabled(true);
     * 
     * return realm;
     * }
     */
    
    /**
     * 
     * @description 
     * @param securityManager
     * @return
     * @author qianye.zheng
     */
    /*
     * 需要定义Bean名称为 shiroFilterFactoryBean，否则shiro依赖此对象的找不到
     * 或者将方法名命名为 shiroFilterFactoryBean，让bean名称取方法名
     * 参照:
     * org.apache.shiro.spring.config.web.autoconfigure.ShiroWebFilterConfiguration
     */
    @Bean
    public ShiroFilterFactoryBean shiroFilterFactoryBean(final SecurityManager securityManager) {
        final ShiroFilterFactoryBean shiroFilter = new ShiroFilterFactoryBean();
        shiroFilter.setSecurityManager(securityManager);
        final String filterKey = "oauth2";
        // oauth过滤
        final Map<String, Filter> filters = new HashMap<>();
        filters.put(filterKey, new OAuth2Filter());
        shiroFilter.setFilters(filters);
        
        final Map<String, String> filterMap = new LinkedHashMap<>();
        filterMap.put("/webjars/**", ANONYMOUS);
        filterMap.put("/druid/**", ANONYMOUS);
        filterMap.put("/app/**", ANONYMOUS);
        filterMap.put("/sys/login", ANONYMOUS);
        filterMap.put("/swagger/**", ANONYMOUS);
        filterMap.put("/v2/api-docs", ANONYMOUS);
        filterMap.put("/swagger-ui.html", ANONYMOUS);
        filterMap.put("/swagger-resources/**", ANONYMOUS);
        filterMap.put("/captcha.jpg", ANONYMOUS);
        filterMap.put("/aaa.txt", ANONYMOUS);
        filterMap.put("/**", filterKey);
        shiroFilter.setFilterChainDefinitionMap(filterMap);
        
        return shiroFilter;
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
        final DefaultShiroFilterChainDefinition chainDefinition = new DefaultShiroFilterChainDefinition();
        
        // 匿名访问 (范围小的 必须放在前面才能生效)
        // chainDefinition.addPathDefinition("/anonymous/**", ANONYMOUS);
        
        // chainDefinition.addPathDefinition("/anonymous/**", ANONYMOUS);
        
        // chainDefinition.addPathDefinition("/login.html", "authc"); // need to accept POSTs from the login form
        // chainDefinition.addPathDefinition("/logout", "logout");
        // chainDefinition.addPathDefinition("/query", "authc, roles[query]");
        // chainDefinition.addPathDefinition("/admin", "authc, roles[admin]");
        
        /*
         * chainDefinition.addPathDefinition("/webjars/**", ANONYMOUS);
         * chainDefinition.addPathDefinition("/druid/**", ANONYMOUS);
         * chainDefinition.addPathDefinition("/app/**", ANONYMOUS);
         * chainDefinition.addPathDefinition("/sys/login", ANONYMOUS);
         * chainDefinition.addPathDefinition("/swagger/**", ANONYMOUS);
         * chainDefinition.addPathDefinition("/v2/api-docs", ANONYMOUS);
         * chainDefinition.addPathDefinition("/swagger-ui.html", ANONYMOUS);
         * chainDefinition.addPathDefinition("/swagger-resources/**", ANONYMOUS);
         * chainDefinition.addPathDefinition("/captcha.jpg", ANONYMOUS);
         * chainDefinition.addPathDefinition("/aaa.txt", ANONYMOUS);
         */
        
        // chainDefinition.addPathDefinition("/**", "oauth2");
        
        /* 所有的都需要登录 */
        // chainDefinition.addPathDefinition("/**", "authc");
        
        return chainDefinition;
    }
    
    /**
     * 
     * @description
     * @return
     * @author qianye.zheng
     */
    /*
     * @Bean(name = "globalFilters")
     * protected List<String> globalFilters() {
     * return Arrays.asList("oauth2");
     * }
     */
    /**
     * 
     * @description
     * @return
     * @author qianye.zheng
     */
    /*
     * @Bean("oauth2")
     * public OAuth2Filter oAuth2Filter() {
     * return new OAuth2Filter();
     * }
     */
    
    /**
     * 
     * @description 解决 404 问题
     * @return
     * @author qianye.zheng
     */
    @Bean
    public DefaultAdvisorAutoProxyCreator getDefaultAdvisorAutoProxyCreator() {
        final DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator = new DefaultAdvisorAutoProxyCreator();
        /**
         * setUsePrefix(false)用于解决一个奇怪的bug。在引入spring aop的情况下。
         * 在@Controller注解的类的方法中加入@RequiresRole等shiro注解，会导致该方法无法映射请求，导致返回404。
         * 加入这项配置能解决这个bug
         */
        defaultAdvisorAutoProxyCreator.setUsePrefix(true);
        return defaultAdvisorAutoProxyCreator;
    }
    
    @ExceptionHandler(AuthorizationException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public String handleException(final AuthorizationException e, final Model model) {
        
        // you could return a 404 here instead (this is how github handles 403, so the user does NOT know there is a
        // resource at that location)
        log.debug("AuthorizationException was thrown", e);
        
        final Map<String, Object> map = new HashMap<>();
        map.put("status", HttpStatus.FORBIDDEN.value());
        map.put("message", "No message available");
        model.addAttribute("errors", map);
        
        return "error";
    }
    
}
