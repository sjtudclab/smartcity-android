package sjtu.dclab.smartcity.community.config;

/**
 * 2015年5月6日 下午3:24:18
 *
 * @author changyi yuan
 */
public class Config {

    public static String GATEWAY = "http://192.168.1.108:8080/community-server/";

    public static String getLoginUrl() {
        return GATEWAY + "rest/users/login";
    }

    public static String getFriendsUrl() {
        return GATEWAY + "rest/friends/" + Me.id + "/relations";
    }

    public static String postMessageUrl(long friendId) {
        return GATEWAY + "rest/friends/" + Me.id + "/users/" + friendId
                + "/messages";
    }

    public static String getMessageUrl(long friendId, long startId, long count) {
        return GATEWAY + "rest/friends/" + Me.id + "/users/" + friendId
                + "/messages/" + startId + "/forward/counts/" + count;
    }
}
