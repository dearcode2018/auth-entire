/**
  * @filename GrantedAuthorityImpl.java
  * @description 
  * @version 1.0
  * @author qianye.zheng
 */
package com.hua.modules.sys.oauth2;

import org.springframework.security.core.GrantedAuthority;


/**
 * @type GrantedAuthorityImpl
 * @description 
 * @author qianye.zheng
 */
public class GrantedAuthorityImpl implements GrantedAuthority {
    
    private String authority;
    
    
    /**
     * @description 构造方法
     * @author qianye.zheng
     */
    public GrantedAuthorityImpl(String authority) {
        this.authority = authority;
    }
    
    /**
     * @description 
     * @return
     * @author qianye.zheng
     */
    @Override
    public String getAuthority() {
        return authority;
    }
    
}
