package com.hua.bean;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Set;

@Data
@AllArgsConstructor
public class Role {

    private String id;
    
    private String name;
    
    /*  角色对应资源集合 */
    private Set<ResourceObject> resources;

}
