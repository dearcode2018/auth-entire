/**
 * Copyright (c) 2016-2019 人人开源 All rights reserved.
 *
 * https://www.renren.io
 *
 * 版权所有，侵权必究！
 */

package com.hua.modules.sys.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import com.hua.modules.sys.entity.SysUserEntity;

/**
 * Controller公共组件
 *
 * @author Mark sunlightcs@gmail.com
 */
public abstract class AbstractController {
	protected Logger logger = LoggerFactory.getLogger(getClass());
	
	
	
	
	/**
	 * 
	 * @description 
	 * @return
	 * @author qianye.zheng
	 */
	protected SysUserEntity getUser() {
		return (SysUserEntity) getAuthentication().getPrincipal();
	}

	/**
	 * 
	 * @description 
	 * @return
	 * @author qianye.zheng
	 */
	protected static final Authentication getAuthentication() {
	    
	    return SecurityContextHolder.getContext().getAuthentication();
	}
	
	protected Long getUserId() {
		return getUser().getUserId();
	}
}
