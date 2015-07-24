package sjtu.dclab.smartcity.chat;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import cn.edu.sjtu.se.dclab.entity.Friend;
import cn.edu.sjtu.se.dclab.entity.Message;
import cn.edu.sjtu.se.dclab.util.JsonUtil;
import com.fasterxml.jackson.core.type.TypeReference;
import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import sjtu.dclab.smartcity.R;

public class PushCallback implements MqttCallback {

	private Context context;

	public PushCallback(Context context) {

		this.context = context;
	}

	@Override
	public void connectionLost(Throwable cause) {
		// We should reconnect here
	}

	@Override
	public void deliveryComplete(IMqttDeliveryToken arg0) {
	}

	@SuppressWarnings("deprecation")
	@Override
	public void messageArrived(String topic, MqttMessage mqttMessage) throws Exception {
		final NotificationManager notificationManager = (NotificationManager) context
				.getSystemService(Context.NOTIFICATION_SERVICE);

		final Notification notification = new Notification(R.drawable.snow,
				"community_chat消息", System.currentTimeMillis());

		String str = new String(mqttMessage.getPayload());
		Message msg = JsonUtil.getFromJsonStr(str,
				new TypeReference<Message>() {
				});
		Friend friend = Friends.getFriend(msg.getFrom());
		MessageEntity entity = new MessageEntity(friend.getName(), msg.getContent());
		
		Messages.storeMessageEntity(friend.getName(), entity, true);

		// Hide the notification after its selected
		notification.flags |= Notification.FLAG_AUTO_CANCEL;

		final Intent intent = new Intent(context, ChatActivity.class);
		intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		intent.putExtra(String.valueOf(R.string.friend), friend);
		final PendingIntent activity = PendingIntent.getActivity(context, 0,
				intent, PendingIntent.FLAG_UPDATE_CURRENT);

		notification.setLatestEventInfo(context, "好友消息", entity.getContent(), activity);
		notificationManager.notify(notification.number, notification);
		notification.number += 1;
	}
}
