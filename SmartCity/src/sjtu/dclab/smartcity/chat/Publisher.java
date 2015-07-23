package sjtu.dclab.smartcity.chat;

import org.eclipse.paho.android.service.MqttAndroidClient;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttPersistenceException;

import com.fasterxml.jackson.core.JsonProcessingException;

import cn.edu.sjtu.se.dclab.entity.Message;
import cn.edu.sjtu.se.dclab.util.JsonUtil;

public class Publisher {

	private static MqttAndroidClient client;
	
	public static void registe(MqttAndroidClient mqttClient){
		client = mqttClient;
	}

	public static void publishMessage(Message msg){
		String content = "";
		try {
			content = JsonUtil.getJsonStr(msg);
		} catch (JsonProcessingException e1) {
			e1.printStackTrace();
		}

//		final MqttMessage message = new MqttMessage(content.getBytes());
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
