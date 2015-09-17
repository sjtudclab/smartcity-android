package sjtu.dclab.smartcity.community.talk;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import sjtu.dclab.smartcity.community.common.CommunityApp;
import sjtu.dclab.smartcity.community.config.Config;
import sjtu.dclab.smartcity.community.config.Me;
import sjtu.dclab.smartcity.community.entity.Friend;
import sjtu.dclab.smartcity.community.entity.Message;
import sjtu.dclab.smartcity.community.login.MyLogin;
import sjtu.dclab.smartcity.community.util.JsonUtil;
import sjtu.dclab.smartcity.community.util.NetUtilWithHttpClient;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

/**
 * 2015年5月6日 下午3:14:12
 *
 * @author changyi yuan
 */
public class MyTalk implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1145095889897419251L;

    private static MyTalk instance;

    private MyTalk() {}

    public static MyTalk getInstance() {
        if (instance == null) {
            synchronized (MyTalk.class) {
                if (instance == null)
                    instance = new MyTalk();
            }
        }
        return instance;
    }

    //private String tmp;

    /**
     * 获取好友列表
     *
     * @return
     */
    public Collection<Friend> getFriends() {
        /*Thread thread = new Thread(new Runnable() {
			public void run() {
				try {
					tmp = NetUtil.sendGet(Config.getFriendsUrl(), "");
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		thread.start();
		thread.join();*/

        String result = NetUtilWithHttpClient.sendGet(Config.getFriendsUrl(), "");


        Collection<Friend> friends = null;
        Collection<Friend> friendsWithoutMe = new ArrayList<Friend>();
        try {
            friends = JsonUtil.getFromJsonStr(result, new TypeReference<Collection<Friend>>() {});
        } catch (JsonParseException e) {
            friends = new ArrayList<Friend>();
            e.printStackTrace();
        } catch (JsonMappingException e) {
            friends = new ArrayList<Friend>();
            e.printStackTrace();
        } catch (IOException e) {
            friends = new ArrayList<Friend>();
            e.printStackTrace();
        }

        for (Friend f:friends){
            if (f.getId()!= Me.id)
                friendsWithoutMe.add(f);
        }

        return friendsWithoutMe;
    }

    /**
     * 发送消息给好友
     *
     * @param friend  好友
     * @param message 消息
     * @return 该条消息，主要指该消息的id
     * @throws Exception
     */
    public Message sendMessage(Friend friend, String message) throws Exception {
        String sendMsg = "{\"message\":\"" + message + "\"}";
        String result = NetUtilWithHttpClient.sendPost(Config.postMessageUrl(friend.getId()),
                sendMsg);
        Message msg = (Message) JsonUtil.getFromJsonStr(result,
                new TypeReference<Message>() {
                });

        return msg;
    }

    /**
     * 获取消息
     *
     * @param friend  好友
     * @param message 起始消息，如果为null，表示起始消息为与该好友的第一条的聊天消息
     * @param count   要获取的消息数量
     * @return 与该好友的聊天消息列表
     * @throws Exception
     */
    public Collection<Message> receiveMessage(Friend friend, Message message,
                                              long count) throws Exception {
        long startId = 0;
        if (message != null)
            startId = message.getId();
        String result = NetUtilWithHttpClient.sendGet(
                Config.getMessageUrl(friend.getId(), startId, count), "");

        Collection<Message> msgs = (Collection<Message>) JsonUtil
                .getFromJsonStr(result,
                        new TypeReference<Collection<Message>>() {
                        });

        return msgs;
    }

    public static void main(String[] args) throws Exception {
        CommunityApp app = new CommunityApp("http://202.120.40.111:8080/community-server/");
        MyLogin login = app.getLoginModule();
        login.doLogin("resident_test", "admin");

        MyTalk talk = app.getTalkModule();

        Collection<Friend> friends = talk.getFriends();
        //System.out.println(friends);
        Friend friend = null;
        for (Friend f : friends) {
            friend = f;
            break;
        }

        Message msg = null;
        if (friend != null) {
//			msg = talk.sendMessage(friend, "testtest1");
//			System.out.println(msg);
        }

//		System.out.println(talk.receiveMessage(friend, msg, 5));
        System.out.println(talk.receiveMessage(friend, null, 100));
    }
}
