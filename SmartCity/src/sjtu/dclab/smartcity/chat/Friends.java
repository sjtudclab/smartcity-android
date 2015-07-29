package sjtu.dclab.smartcity.chat;

import cn.edu.sjtu.se.dclab.entity.Friend;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *2015年5月30日 下午3:03:42
 *@author changyi yuan
 */
public class Friends {
	
	private static Map<Long,Friend> fs = new HashMap<Long,Friend>();
	
	public static Friend getFriend(long id){
		return fs.get(id);
	}
	
	public static void addFriend(Friend friend){
		fs.put(friend.getId(), friend);
	}
	
	public static void addFriends(List<Friend> fs){
		if(fs != null){
			for(Friend f : fs)
				addFriend(f);
		}
	}
	
	public static List<Friend> getFriends(){
		List<Friend> temp = new ArrayList<Friend>();
		for(long id : fs.keySet())
			temp.add(fs.get(id));
		return temp;
	}
}
