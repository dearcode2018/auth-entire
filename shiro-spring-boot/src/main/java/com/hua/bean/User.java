package com.hua.bean;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Set;

@Data
@AllArgsConstructor
public class User {
    private String id;
    
    private String username;
    
    private String password;
    /**
     * 用户对应的角色集合
     */
    private Set<Role> roles;
}
