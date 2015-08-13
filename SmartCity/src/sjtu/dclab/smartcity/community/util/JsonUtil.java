package sjtu.dclab.smartcity.community.util;

import java.io.IOException;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * 2015年5月6日 下午4:11:12
 *
 * @author changyi yuan
 */
public class JsonUtil {
	public static <T> T getFromJsonStr(String str, TypeReference<T> ref)
			throws JsonParseException, JsonMappingException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		return mapper.readValue(str, ref);
	}
	
	public static String getJsonStr(Object obj) throws JsonProcessingException{
		ObjectMapper mapper = new ObjectMapper();
		 mapper.setSerializationInclusion(Include.NON_NULL);
		return mapper.writeValueAsString(obj);
	}
}
