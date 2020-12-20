/**
  * @filename DateSerializer.java
  * @description 
  * @version 1.0
  * @author qye.zheng
 */
package com.hua.transform;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.hua.constant.FormatConstant;

 /**
 * @type DateSerializer
 * @description 日期 序列化器
 * @author qye.zheng
 */
public final class DateSerializer extends JsonSerializer<Date> {

	private static final DateFormat format = new SimpleDateFormat(FormatConstant.DATE_FORMAT_yyyy_MM_dd);

	/**
	 * @description 
	 * @param value
	 * @param gen
	 * @param serializers
	 * @throws IOException
	 * @throws JsonProcessingException
	 * @author qianye.zheng
	 */
	@Override
	public void serialize(Date value,
			com.fasterxml.jackson.core.JsonGenerator gen,
			SerializerProvider serializers) throws IOException, JsonProcessingException
	{
		gen.writeString(format.format(value));
	}

}
