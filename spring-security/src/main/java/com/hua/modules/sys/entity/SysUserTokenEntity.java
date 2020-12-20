/**
 * Copyright (c) 2016-2019 人人开源 All rights reserved.
 *
 * https://www.renren.io
 *
 * 版权所有，侵权必究！
 */

package com.hua.modules.sys.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.hua.transform.DateTimeSerializer;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;


/**
 * 系统用户Token
 *
 * @author Mark sunlightcs@gmail.com
 */
@Data
@TableName("sys_user_token")
public class SysUserTokenEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//用户ID
	@TableId(type = IdType.INPUT)
	private Long userId;
	//token
	private String token;
	
	//过期时间
	@JsonSerialize(using = DateTimeSerializer.class)
	private Date expireTime;
	
	//更新时间
	@JsonSerialize(using = DateTimeSerializer.class)
	private Date updateTime;

}
