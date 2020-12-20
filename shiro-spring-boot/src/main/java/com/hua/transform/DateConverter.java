/**
  * @filename DateConverter.java
  * @description 
  * @version 1.0
  * @author qianye.zheng
 */
package com.hua.transform;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;

import com.thoughtworks.xstream.converters.ConversionException;
import com.thoughtworks.xstream.converters.Converter;
import com.thoughtworks.xstream.converters.MarshallingContext;
import com.thoughtworks.xstream.converters.UnmarshallingContext;
import com.thoughtworks.xstream.io.HierarchicalStreamReader;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;

 /**
 * @type DateConverter
 * @description 
 * @author qianye.zheng
 */
public class DateConverter implements Converter
{
	/* 日期格式 */
	private String dateFormat;
	
	/**
	 * @description 构造方法
	 * @author qianye.zheng
	 */
	public DateConverter()
	{
		this("yyyy-MM-dd'T'HH:mm:ss");
	}
	
	/**
	 * @description 构造方法
	 * @param dateFormat
	 * @author qianye.zheng
	 */
	public DateConverter(String dateFormat)
	{
		super();
		this.dateFormat = dateFormat;
	}



	/**
	 * 
	 * @description 
	 * @param type
	 * @return
	 * @author qianye.zheng
	 */
	@SuppressWarnings({"all"})
	@Override  
	 public boolean canConvert(Class type) {  
	  
	  return Date.class == type;  
	 }  
	
	/**
	 * 
	 * @description 
	 * @param arg0
	 * @param writer
	 * @param context
	 * @author qianye.zheng
	 */
	 @Override  
	 public void marshal(Object arg0, HierarchicalStreamWriter writer,  
	   MarshallingContext context) {  
	    
	 }  
	 
	 /**
	  * 
	  * @description 
	  * @param reader
	  * @param context
	  * @return
	  * @author qianye.zheng
	  */
	 @Override  
	 public Object unmarshal(HierarchicalStreamReader reader,  
	   UnmarshallingContext context) {  
		 final GregorianCalendar calendar = new GregorianCalendar();   
	        // yyyy-MM-dd'T'HH:mm:ss
	        //格式化当前系统日期  
	        final DateFormat df = new SimpleDateFormat(dateFormat);
	        try {  
	                calendar.setTime(df.parse(reader.getValue()));  
	        } catch (ParseException e) {  
	                throw new ConversionException(e.getMessage(), e);  
	        }   
	        
	        return calendar.getTime();  
	 }

	/**
	 * @return the dateFormat
	 */
	public final String getDateFormat()
	{
		return dateFormat;
	}

	/**
	 * @param dateFormat the dateFormat to set
	 */
	public final void setDateFormat(String dateFormat)
	{
		this.dateFormat = dateFormat;
	}  
	 
}
