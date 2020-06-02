package org.cf.catalog.util;

import java.util.Map;

import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonUtil {

	public static String jsonStrFromObject(Object object) throws Exception {
		ObjectMapper objectMapper = new ObjectMapper();
		return objectMapper.writeValueAsString(object);
	}
	
	public static Map<?, ?> getMapFronJsonStr(String jsonStr) throws Exception {
		ObjectMapper objectMapper = new ObjectMapper();
		return objectMapper.readValue(jsonStr, Map.class);
	}
}
