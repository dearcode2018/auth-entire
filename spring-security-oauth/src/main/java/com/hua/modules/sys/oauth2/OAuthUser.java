/**
  * @filename OAuthUser.java
  * @description 
  * @version 1.0
  * @author qianye.zheng
 */
package com.hua.modules.sys.oauth2;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;


/**
 * @type OAuthUser
 * @description 
 * @author qianye.zheng
 */
public class OAuthUser extends User {

    
    
    private static final long serialVersionUID = 1L;

    /**
     * 
     * @description 构造方法
     * @param username
     * @param password
     * @param authorities
     * @author qianye.zheng
     */
    public OAuthUser(String username, String password, Collection<? extends GrantedAuthority> authorities)
    {
        this(username, password, true, true, true, true, authorities);
    }
    
    /**
     * @description 构造方法
     * @param username
     * @param password
     * @param enabled
     * @param accountNonExpired
     * @param credentialsNonExpired
     * @param accountNonLocked
     * @param authorities
     * @author qianye.zheng
     */
    public OAuthUser(String username, String password, boolean enabled, boolean accountNonExpired,
            boolean credentialsNonExpired, boolean accountNonLocked,
            Collection<? extends GrantedAuthority> authorities) {
        super(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
    }
    
    
    
}
