package com.hua.handler;

import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.AuthorizationException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * 
 * @type DefaultExceptionHandler
 * @description 默认异常处理器
 * @author qianye.zheng
 */
@ControllerAdvice
@Slf4j
public class DefaultExceptionHandler {

    /**
     * 
     * @description 
     * @param e
     * @return
     * @author qianye.zheng
     */
    @ExceptionHandler
    public String handler(AuthorizationException e) {
        log.error("没有通过权限验证！", e);
        return "my_error";
    }
}
