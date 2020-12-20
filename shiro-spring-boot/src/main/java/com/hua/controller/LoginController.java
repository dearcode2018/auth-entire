package com.hua.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;

import com.hua.bean.User;

import lombok.extern.slf4j.Slf4j;

/**
 * 
 * @type LoginController
 * @description 
 * @author qianye.zheng
 */
//@RestController
@Controller
@Slf4j
public class LoginController {

    /**
     * 
     * @description 
     * @return
     * @author qianye.zheng
     */
    @GetMapping("/login.html")
    public String loginTemplate() {

        return "login";
    }
    
    /**
     * 
     * @description 
     * @param user
     * @return
     * @author qianye.zheng
     */
    @GetMapping("/login")
    public String login(final User user) {
        if (StringUtils.isEmpty(user.getUsername()) || StringUtils.isEmpty(user.getPassword())) {
            return "请输入用户名和密码！";
        }
        //用户认证信息
        final Subject subject = SecurityUtils.getSubject();
        final UsernamePasswordToken usernamePasswordToken = new 
                UsernamePasswordToken(user.getUsername(), user.getPassword());
        try {
            // 进行验证，这里可以捕获异常，然后返回对应信息
            subject.login(usernamePasswordToken);
//            subject.checkRole("admin");
//            subject.checkPermissions("query", "add");
        } catch (UnknownAccountException e) {
            log.error("用户名不存在！", e);
            return "用户名不存在！";
        } catch (AuthenticationException e) {
            log.error("账号或密码错误！", e);
            return "账号或密码错误！";
        } catch (AuthorizationException e) {
            log.error("没有权限！", e);
            return "没有权限";
        }
        
        return "login success";
    }

}
