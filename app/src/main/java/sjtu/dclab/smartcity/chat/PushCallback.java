package sjtu.dclab.smartcity.chat;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import com.fasterxml.jackson.core.type.TypeReference;
import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import sjtu.dclab.smartcity.R;
import sjtu.dclab.smartcity.SQLite.DBManager;
import sjtu.dclab.smartcity.community.entity.Friend;
import sjtu.dclab.smartcity.community.entity.Group;
import sjtu.dclab.smartcity.community.entity.Message;
import sjtu.dclab.smartcity.community.util.JsonUtil;
import sjtu.dclab.smartcity.tools.GsonTool;
import sjtu.dclab.smartcity.transfer.GroupMemberTransfer;
import sjtu.dclab.smartcity.ui.chat.ChatActivity;
import sjtu.dclab.smartcity.ui.chat.GroupChatAty;
import sjtu.dclab.smartcity.webservice.BasicWebService;

import java.util.List;

public class PushCallback implements MqttCallback {

    private final String TAG = "PushCallback";

    private Context context;
    private DBManager dbManager;

    public static final String ACTION = "NEWMSG";

    public PushCallback(Context context) {
        this.context = context;
        this.dbManager = new DBManager(context);
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

        // 收到msg
        /**
         * content  内容
         * from     发送者
         * to       接收者，群聊时为groupID
         * type     1为单聊，2为群聊
         */
        Message msg = JsonUtil.getFromJsonStr(str, new TypeReference<Message>() {
        });

        Log.i(TAG, "save received msg");

        int type = msg.getType();

        switch (type) {
            case 1:
                Friend friend = Friends.getFriend(msg.getFrom());

                Intent bc = new Intent();
                bc.setAction(ACTION);
                context.sendBroadcast(bc);

                msg.setName(friend.getName());
                dbManager.saveMsg(msg);
                MessageEntity msgEntity = new MessageEntity(friend.getName(), msg.getContent());

                notification.flags |= Notification.FLAG_AUTO_CANCEL;
                final Intent intent = new Intent(context, ChatActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.putExtra(String.valueOf(R.string.friend), friend);
                final PendingIntent activity = PendingIntent.getActivity(context, 0,
                        intent, PendingIntent.FLAG_UPDATE_CURRENT);
                notification.setLatestEventInfo(context, "好友消息", msgEntity.getContent(), activity);
                notificationManager.notify(notification.number, notification);
                notification.number += 1;
                break;

            case 2:
                Group group = Groups.getGroup(msg.getTo());

                String url = context.getString(R.string.URLRoot) + "groups/" + group.getId() + "/memberlist";
                String resp = new BasicWebService().sendGetRequest(url, null);
                List<GroupMemberTransfer> gmtList = GsonTool.getObjectList(resp, GroupMemberTransfer[].class);
                String sender = "";
                for (GroupMemberTransfer gmt : gmtList) {
                    if (gmt.getId() == msg.getFrom()) {
                        sender = gmt.getName();
                    }
                }

                msg.setName(sender);
                dbManager.saveMsg(msg);

                MessageEntity gmsgEntity = new MessageEntity(sender, msg.getContent());
                DeprecatedMessages.storeMessageEntity(group.getName(), gmsgEntity, true);
                notification.flags |= Notification.FLAG_AUTO_CANCEL;
                final Intent gintent = new Intent(context, GroupChatAty.class);
                gintent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                gintent.putExtra(String.valueOf(R.string.group), group);
                final PendingIntent gactivity = PendingIntent.getActivity(context, 0,
                        gintent, PendingIntent.FLAG_UPDATE_CURRENT);
                notification.setLatestEventInfo(context, "群组消息", gmsgEntity.getContent(), gactivity);
                notificationManager.notify(notification.number, notification);
                notification.number += 1;
                break;
        }
    }
}
