/**
  * @filename PermissionWayController.java
  * @description 
  * @version 1.0
  * @author qianye.zheng
 */
package com.hua.controller;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @type PermissionWayController
 * @description 权限(资源)的方式 进行授权
 * @author qianye.zheng
 */
@RestController
@RequestMapping("/permission")
public class PermissionWayController extends BaseController {

    
    /**
     * 
     * @description 
     * @param model
     * @return
     * @author qianye.zheng
     */
    @RequiresPermissions("perms:read")
    @GetMapping("/read")
    public String read(Model model) {
        System.out.println("PermissionWayController.read()");
        return "perms:read";
    }
    
    /**
     * 
     * @description 
     * @param model
     * @return
     * @author qianye.zheng
     */
    @RequiresPermissions("perms:write")
    @GetMapping("/write")
    public String write(Model model) {
        System.out.println("PermissionWayController.admin()");
        return "perms:write";
    }
    
}
