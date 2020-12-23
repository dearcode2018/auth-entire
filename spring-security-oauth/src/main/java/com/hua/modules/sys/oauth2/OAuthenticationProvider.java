/**
  * @filename OAuthenticationProvider.java
  * @description 
  * @version 1.0
  * @author qianye.zheng
 */
package com.hua.modules.sys.oauth2;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;


/**
 * @type OAuthenticationProvider
 * @description 
 * @author qianye.zheng
 */
public class OAuthenticationProvider extends DaoAuthenticationProvider {
    
    
    /**
     * @description 构造方法
     * @param userDetailsService
     * @author qianye.zheng
     */
    public OAuthenticationProvider(final UserDetailsService userDetailsService) {
        setUserDetailsService(userDetailsService);
        //setPasswordEncoder(getPasswordEncoder());
    }
    
    /**
     * @description 
     * @param authentication
     * @return
     * @throws AuthenticationException
     * @author qianye.zheng
     */
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        return super.authenticate(authentication);
    }
    
    /**
     * @description 
     * @param userDetails
     * @param authentication
     * @throws AuthenticationException
     * @author qianye.zheng
     */
    @Override
    protected void additionalAuthenticationChecks(UserDetails userDetails,
            UsernamePasswordAuthenticationToken authentication) throws AuthenticationException {
        super.additionalAuthenticationChecks(userDetails, authentication);
    }
    
}
