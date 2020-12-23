/**
 * 描述: 
 * SqlUtil.java
 * 
 * @author qye.zheng
 *  version 1.0
 */
package com.hua.util;

/**
 * 描述:
 * 
 * @author qye.zheng SqlUtil
 */
public final class SqlUtil
{

	/** 无参构造方法 */
	private SqlUtil()
	{
	}

	public static final String ESCAPE = " ESCAPE '/' ";

	/**
	 * 将查询内容格式化
	 * 
	 * @param content
	 * @return
	 */
	public static final String likeQuery(String content)
	{
		// 单引号
		/*
		 * if (content.contains("'")) { content = content.replace("'", "''"); }
		 */
		if (content.contains("_"))
		{
			content = content.replace("_", "\\_");
		}

		if (content.contains("%"))
		{
			content = content.replace("%", "\\%");
		}
		
		// 单引号
		if (content.contains("'"))
		{
			content = content.replace("'", "\'");
		}
		

		return content;
	}
	
	/**
	 * 
	 * @description 全模糊匹配
	 * @param value
	 * @return
	 * @author qianye.zheng
	 */
	public static final String bothLike(final String value)
	{
		return "%" + likeQuery(value) + "%";
	}
	
	/**
	 * 
	 * @description 前缀模糊匹配
	 * @param value
	 * @return
	 * @author qianye.zheng
	 */
	public static final String prefixLike(final String value)
	{
		return "%" + likeQuery(value);
	}
	
	/**
	 * 
	 * @description 后缀模糊匹配
	 * @param value
	 * @return
	 * @author qianye.zheng
	 */
	public static final String suffixLike(final String value)
	{
		return likeQuery(value) + "%";
	}

}
