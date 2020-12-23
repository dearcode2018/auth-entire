/**
  * @filename DateTimeDeserializer.java
  * @description 
  * @version 1.0
  * @author qye.zheng
 */
package com.hua.transform;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.hua.constant.FormatConstant;

 /**
 * @type DateTimeDeserializer
 * @description 日期-时间 反序列化器
 * @author qye.zheng
 */
public final class DateTimeDeserializer extends JsonDeserializer<Date> {

	private static final DateFormat format = 
			new SimpleDateFormat(FormatConstant.DATE_TIME_FORMAT_yyyy_MM_dd_HH_mm_ss);
	
	/**
	 * @description 
	 * @param p
	 * @param ctxt
	 * @return
	 * @throws IOException
	 * @throws JsonProcessingException
	 * @author qye.zheng
	 */
	@Override
	public Date deserialize(JsonParser p, DeserializationContext ctxt)
			throws IOException, JsonProcessingException {
		String value = p.getValueAsString();
		Date datetime = null;
		try {
			datetime = format.parse(value);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		return datetime;
	}

	
}
