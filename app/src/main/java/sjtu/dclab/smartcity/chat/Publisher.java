package sjtu.dclab.smartcity.chat;

import android.util.Log;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.eclipse.paho.android.service.MqttAndroidClient;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttPersistenceException;
import sjtu.dclab.smartcity.community.entity.Message;
import sjtu.dclab.smartcity.community.util.JsonUtil;

public class Publisher {
	private static String TAG = "Publisher Failure";

	private static MqttAndroidClient client;
	
	public static void register(MqttAndroidClient mqttClient){
		client = mqttClient;
	}

	public static void publishMessage(Message msg){
		if (client == null){
			Log.d(TAG, "MQTT服务器注册失败");
			return;
		}

		Log.i("MQTT", "MQTT服务器注册成功");

		String content = "";
		try {
			content = JsonUtil.getJsonStr(msg);
		} catch (JsonProcessingException e1) {
			e1.printStackTrace();
		}

		try {
			client.publish(Configurations.publicTopic,content.getBytes(), Configurations.qos, Configurations.retained);
		} catch (MqttPersistenceException e) {
			e.printStackTrace();
		} catch (MqttException e) {
			e.printStackTrace();
		}
	}
	
	public static void publicHeart(Heartbeat heart){
		String content = "";
		try {
			content = JsonUtil.getJsonStr(heart);
		} catch (JsonProcessingException e1) {
			e1.printStackTrace();
		}
		try {
			client.publish(Configurations.heartTopic, content.getBytes(), Configurations.qos, false);
		} catch (MqttException e) {
			e.printStackTrace();
		}
	}
}
