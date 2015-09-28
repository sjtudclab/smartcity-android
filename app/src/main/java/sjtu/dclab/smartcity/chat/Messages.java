package sjtu.dclab.smartcity.chat;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *2015年5月29日 下午6:35:51
 *@author changyi yuan
 */
public class Messages {
	//username : messages
	private static Map<String,MessageAdapter> msgs = new HashMap<String,MessageAdapter>();
	
	public static MessageAdapter loadMessageAdapter(String username){
		return msgs.get(username);
	}
	
	public static void storeMessageAdapter(String username,MessageAdapter adapter){
		msgs.put(username, adapter);
	}
	
	//必须在上面两个方法之后调用，可重构之处
	public static List<MessageEntity> loadMessageEnities(String username){
		MessageAdapter adapter = msgs.get(username);
		return adapter.getMsgEntities();
	}
	
	//flag是否通知adapter数据更改
	public static void storeMessageEntity(String username, MessageEntity msgEntity, boolean flag){
		MessageAdapter adapter = msgs.get(username);
		List<MessageEntity> msgEntities = adapter.getMsgEntities();
		msgEntities.add(msgEntity);
		if(flag)
			adapter.notifyDataSetChanged();
	}
}
