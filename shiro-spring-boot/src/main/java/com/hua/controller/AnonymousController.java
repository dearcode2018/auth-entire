/**
  * @filename AnonymousController.java
  * @description 
  * @version 1.0
  * @author qianye.zheng
 */
package com.hua.controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @type AnonymousController
 * @description 
 * @author qianye.zheng
 */
@RestController
@RequestMapping("/anonymous")
public class AnonymousController extends BaseController {
    
    
    /**
     * 
     * @description 
     * @param model
     * @return
     * @author qianye.zheng
     */
    @GetMapping("/get")
    public String get(Model model) {
        return "get guest resource";
    }
    
}
