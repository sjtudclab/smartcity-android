package sjtu.dclab.smartcity.chat;

import java.util.UUID;

/**
 * 2015年5月31日 上午11:38:10
 *
 * @author changyi yuan
 */
public class Configurations {

    public static String server = "192.168.1.108";

    public static String clientId = "android-" + UUID.randomUUID().toString().substring(0, 4);

    public static int port = 1883;

    public static String uri = "tcp://" + server + ":" + port;

    public static boolean cleanSession = false;

    public static String username = "test";

    public static String password = "test";

    public static int timeout = 60;

    public static int keepalive = 200;

    public static String publicTopic = "upload";

    public static String subscribeTopicPrefix = "recv";

    public static int qos = 1;

    public static boolean retained = true;

    public static String heartTopic = "command";

    public static int heartbeatPeriod = 30;

    public static String heartbeatCommand = "online";
}
