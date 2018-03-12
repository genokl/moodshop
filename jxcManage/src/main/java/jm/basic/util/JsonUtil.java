package jm.basic.util;

import java.io.IOException;
import java.util.List;

import com.alibaba.fastjson.JSONArray;
import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.MappingJsonFactory;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonUtil {
	/**
	 * 将json字符串转换为实体类对象
	 * 
	 * @param json
	 * @return
	 */
	public static Object json2Obj(String json) {
		try {
			ObjectMapper mapper = new ObjectMapper();
			Object entiy = mapper.readValue(json, Object.class);
			return entiy;
		} catch (JsonParseException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 将对象object转换为json字符串.
	 * 
	 * @param object
	 * @return
	 */
	public static String obj2Json(Object object) {
		try {
			ObjectMapper mapper = new ObjectMapper();
			String json = mapper.writeValueAsString(object);
			return json;
		} catch (JsonGenerationException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * JSON转换为Object对象
	 * 
	 * @param jsonString
	 *            JSON字符串
	 * @param c
	 *            要转换成的类的类型
	 * @return Object对象
	 */
	public static <T> T jsonToObject(String jsonString, Class<T> c) {
		if (jsonString == null || "".equals(jsonString)) {
			return null;
		} else {
			// Jackson方式将Json转换为对象
			MappingJsonFactory f = new MappingJsonFactory();
			try {
				JsonParser parser = f.createJsonParser(jsonString);
				return parser.readValueAs(c);
			} catch (Exception e) {
				e.printStackTrace();
				return null;
			}
		}
	}
	
	/**
	 * 将对象object转换为json字符串.
	 * 
	 * @param object
	 * @return
	 */
	public static String List2Str(List list) {
		try {
			String ja = JSONArray.toJSONString(list);
			return ja;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
}
