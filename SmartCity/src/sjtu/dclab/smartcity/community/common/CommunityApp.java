package sjtu.dclab.smartcity.community.common;

import sjtu.dclab.smartcity.community.config.Config;
import sjtu.dclab.smartcity.community.login.MyLogin;
import sjtu.dclab.smartcity.community.talk.MyTalk;

/**
 * 2015年5月7日 上午9:35:31
 *
 * @author changyi yuan
 */
public class CommunityApp {
	
	public CommunityApp(){ 
		//
	}
	
	/**
	 * 
	 * @param url 服务器端根目录，如：http://192.168.1.254:8080/community-server/
	 */
	public CommunityApp(final String url){
		String tempUrl = url;
		if(tempUrl != null && !tempUrl.endsWith("/")){
			tempUrl += "/";
		}
		Config.GATEWAY = tempUrl;
	}

	/**
	 * 获取登录模块
	 * @return 登录模块
	 */
	public MyLogin getLoginModule() {
		return MyLogin.getInstance();
	}

	/**
	 * 获取社交模块
	 * @return 社交模块
	 */
	public MyTalk getTalkModule() {
		return MyTalk.getInstance();
	}

}
