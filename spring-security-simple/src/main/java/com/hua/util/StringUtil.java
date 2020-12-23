/**
 * 描述: 
 * StringUtil.java
 * 
 * @author qye.zheng
 *  version 1.0
 */
package com.hua.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;

import com.hua.constant.Constant;
import com.hua.constant.RegexConstant;

/**
 * 描述: 字符串 工具类
 * 
 * @author qye.zheng
 * StringUtil
 */
public final class StringUtil extends org.apache.commons.lang3.StringUtils {

	/** 禁止实例化 */
	private StringUtil() {}
	
	/**
	 * 
	 * 描述: 字符串是否为空
	 * trim 之后""或null 
	 * @author qye.zheng
	 * 
	 * @param target
	 * @return
	 */
	public static final boolean isEmpty(final String target) {
		// 为null 或 trim 之后为空字符串
		if (null == target || Constant.WHITE_SPACE.equals(target.trim())) {
			
			return true;
		}
		
		return false;
	}
	
	/**
	 * 
	 * 描述: 字符串是否不为空
	 * trim 之后""或null 
	 * @author qye.zheng
	 * 
	 * @param target
	 * @return
	 */
	public static final boolean isNotEmpty(final String target) {
		// 为null 或 trim 之后为空字符串
		if (null != target && !Constant.WHITE_SPACE.equals(target.trim())) {
			
			return true;
		}
		
		return false;
	}
	
	/**
	 * 
	 * @description 是否所有字符串都为空
	 * @param target
	 * @return
	 * @author qye.zheng
	 */
	public static final boolean isAllEmpty(final String... target)
	{
		if (null != target)
		{
			for (int i = 0; i < target.length; i++)
			{
				// 存在不为空
				if (!isEmpty(target[i]))
				{
					return false;
				} else
				{
					continue;
				}
			}
		}
		
		return false;
	}
	
	/**
	 * 
	 * 描述: 生成一个1024大小的StringBuilder 
	 * @author qye.zheng
	 * 
	 * @return
	 */
	public static StringBuilder getBuilder() {
		
		return getBuilder(Constant.DEFAULT_BYTE);
	}

	/**
	 * 
	 * 描述: 生成一个指定大小的StringBuilder 
	 * @author qye.zheng
	 * @param size
	 * @return
	 */
	public static StringBuilder getBuilder(final int size) {
		
		return new StringBuilder(size);
	}
	
	/**
	 * 
	 * 描述: 生成一个1024大小的StringBuffer
	 * @author qye.zheng
	 * 
	 * @return
	 */
	public static StringBuffer getBuffer() {
		
		return getBuffer(Constant.DEFAULT_BYTE);
	}
	
	/**
	 * 
	 * 描述: 生成一个指定大小的StringBuffer
	 * @author qye.zheng
	 * 
	 * @return
	 */
	public static StringBuffer getBuffer(final int size) {
		
		return new StringBuffer(size);
	}
	
	/**
	 * 
	 * 描述: 将流转换成字符串
	 * @author qye.zheng
	 * 
	 * @param input
	 * @return
	 */
	public static String streamToString(final InputStream input) 
	{      
		final StringBuilder builder = getBuilder();     
        String line = null;      
        try 
        {      
        	final BufferedReader reader = new BufferedReader(new InputStreamReader(input, Constant.CHART_SET_UTF_8));      
            while (null != (line = reader.readLine())) {  
                builder.append(line + "\n");      
            }      
        } catch (IOException e) 
        {      
            e.printStackTrace();      
        } finally 
        {      
            try 
            {      
                input.close();      
            } catch (IOException e) 
            {      
               e.printStackTrace();      
            }      
        }      
        
        return builder.toString();      
    }  
	
	/**
	 * 
	 * 描述: 数字字符串比较
	 例如: 3 和 123，现实的排序逻辑，应该是3排在
	 123的前面; 而java.lang.String.compareTo方法是
	 按照unicode编码的位置逐个去比较，而没有去甄别
	 多个数字字符的存在
	 * @author qye.zheng
	 * 
	 * @param str1
	 * @param str2
	 * @return
	 */
	public static int digitStringCompare(final String str1, final String str2)
	{
		// 将字符串 转成字符串数字，逐个甄别数字字符
		char[] chs1 = str1.toCharArray();
		char[] chs2 = str2.toCharArray();
		// 最小的数组长度
		int minLength = (chs1.length > chs2.length) ? chs2.length : chs1.length;
		StringBuilder builder1 = null;
		StringBuilder builder2 = null;
		int n1 = 0;
		int n2 = 0;
		for (int i = 0; i < minLength; i++)
		{
			if (isNumeric(String.valueOf(chs1[i])))
			{
				builder1 = getBuilder();
				builder2 = getBuilder();
				// 把 chs1 连续数字找出来
				for (int j = i; j < minLength; j++)
				{
					if (!isNumeric(String.valueOf(chs1[j])))
					{
						break;
					}
					builder1.append(chs1[j]);
				}
				// 把 chs2 连续数字找出来
				for (int j = i; j < minLength; j++)
				{
					if (!isNumeric(String.valueOf(chs2[j])))
					{
						break;
					}
					builder2.append(chs2[j]);
				}
				if (!StringUtil.isEmpty(builder2.toString()))
				{
					// 转成 整型数字
					n1 = Integer.valueOf(builder1.toString());
					n2 = Integer.valueOf(builder2.toString());
					if (n1 == n2)
					{
						// 修改 i 的位置，并继续下次 (消除返回后的 i++)
						i += builder1.length() - 1;
						continue;
					} else {
						// 不等
						// 返回两者的差值
						return n1 - n2;
					}
				} else
				{
					// builder2 为空，没有数字
					
					return str1.compareTo(str2);
				}
			}
		}
		// 比较之后，还是不能区分大小
		// 用长度差来区分
		return str1.length() - str2.length();
	}
	
	/**
	 * 
	 * 描述: 是否包含数字
	 * @author qye.zheng
	 * 
	 * @param value
	 * @return
	 */
	public static boolean hasNumber(final String value)
	{
		
		return value.matches(RegexConstant.HAS_NUMBER);
	}
	
	/**
	 * 
	 * @description 是否是手机号码
	 * 可以修改规则来满足当前  手机号码 规则
	 * @param phoneNumber
	 * @return
	 * @author qye.zheng
	 */
	public static final boolean isPhoneNumber(final String phoneNumber)
	{
		// 11位
		// String regex = "1[3,5,8]\\d{9}";
		String regex = "1\\d{10}";
		
		return phoneNumber.matches(regex);
	}
	
	/**
	 * 
	 * @description 获取异常链(所有) 信息
	 * @param e
	 * @return
	 * @author qye.zheng
	 */
	public static final String getExceptionStackTrace(final Throwable e)
	{
		final Writer writer = new StringWriter();
		final PrintWriter printWriter = new PrintWriter(writer);
		e.printStackTrace(printWriter);
		Throwable cause = e.getCause();
		// 逐步获取 上一级(调用者) 异常
		while (null != cause)
		{
			cause.printStackTrace(printWriter);
			cause = cause.getCause();
		}
		printWriter.close();
		
		return writer.toString();
	}
	
	/**
	 * 
	 * @description 添加前缀零，例如 位数为3 的数字 1 补0后结果为 001
	 * @param bitCount 位数
	 * @param num 数字
	 * @return
	 * @author qianye.zheng
	 */
	public static final String addPrefixZero(final Integer bitCount, final Integer num)
	{
		String result = String.valueOf(num);
		while (result.length() < bitCount)
		{
			result = "0" + result;
		}
		
		return result;
	}
}
