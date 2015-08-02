package sjtu.dclab.smartcity.community.login;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import sjtu.dclab.smartcity.community.config.Config;
import sjtu.dclab.smartcity.community.config.Me;
import sjtu.dclab.smartcity.community.util.JsonUtil;
import sjtu.dclab.smartcity.community.util.NetUtilWithHttpClient;
import sjtu.dclab.smartcity.community.entity.User;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * 2015年5月6日 下午3:52:48
 *
 * @author changyi yuan
 */
public class MyLogin {

	private static MyLogin instance;

	private MyLogin() {
	}

	public static MyLogin getInstance() {
		if (instance == null) {
			synchronized (MyLogin.class) {
				if (instance == null)
					instance = new MyLogin();
			}
		}
		return instance;
	}

	/**
	 * 用户登录
	 * 
	 * @param username
	 *            用户名
	 * @param password
	 *            密码
	 * @return
	 * @throws Exception
	 */
	public String doLogin(String username, String password) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("username", username);
		map.put("password", password);
		String result = "";
		User user = null;

		result = NetUtilWithHttpClient.sendPost(Config.getLoginUrl(), map);

		if(result == null)
			return "fail";
		
		try {
			user = JsonUtil.getFromJsonStr(result, new TypeReference<User>() {
			});
		} catch (JsonParseException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		if (user == null)
			return "fail";

		Me.id = user.getId();
		Me.username = user.getUsername();

		return "success";
	}

}
