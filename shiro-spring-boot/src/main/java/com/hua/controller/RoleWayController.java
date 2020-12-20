/**
  * @filename RoleWayController.java
  * @description 
  * @version 1.0
  * @author qianye.zheng
 */
package com.hua.controller;

import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @type RoleWayController
 * @description 角色的方式 进行授权
 * @author qianye.zheng
 */
@RestController
@RequestMapping("/role")
public class RoleWayController extends BaseController {

    
    /**
     * 
     * @description 
     * @param model
     * @return
     * @author qianye.zheng
     */
    @RequiresRoles("query")
    @GetMapping("/query")
    public String query(Model model) {
        System.out.println("RoleWayController.read()");
        return "role: query";
    }
    
    /**
     * 
     * @description 
     * @param model
     * @return
     * @author qianye.zheng
     */
    @RequiresRoles("admin")
    @GetMapping("/admin")
    public String admin(Model model) {
        System.out.println("RoleWayController.admin()");
        return "role: admin";
    }
    
}
