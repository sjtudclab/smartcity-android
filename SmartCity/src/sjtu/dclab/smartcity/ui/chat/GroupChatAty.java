package sjtu.dclab.smartcity.ui.chat;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.*;
import sjtu.dclab.smartcity.R;
import sjtu.dclab.smartcity.SQLite.DBManager;
import sjtu.dclab.smartcity.chat.MessageAdapter;
import sjtu.dclab.smartcity.chat.MessageEntity;
import sjtu.dclab.smartcity.chat.Messages;
import sjtu.dclab.smartcity.chat.Publisher;
import sjtu.dclab.smartcity.community.config.Me;
import sjtu.dclab.smartcity.community.entity.Group;
import sjtu.dclab.smartcity.community.entity.Message;

/**
 * Created by Yang on 2015/8/4.
 */
public class GroupChatAty extends Activity {
    private GroupChatAty chat = this;

    private ImageButton ibtnGroupDetail;
    private ListView listView;
    private MessageAdapter adapter;
    private Button messageButton;
    private EditText messageText;
    private Group group;

    private long groupId;

    //db
    private DBManager dbm;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.aty_chat);

        listView = (ListView) findViewById(R.id.chat_list);

        Intent intent = getIntent();
        group = (Group) intent.getSerializableExtra(String.valueOf(R.string.group));
        groupId = group.getId();
        adapter = Messages.loadMessageAdapter(group.getName());

        // 设置聊天名称
        TextView chatTitle = (TextView) findViewById(R.id.tv_chat_title);
        chatTitle.setText(group.getName());

        // 接收消息
        listView.setAdapter(adapter);

        // 发送消息
        messageButton = (Button) findViewById(R.id.MessageButton);
        messageText = (EditText) findViewById(R.id.MessageText);
        messageButton.setOnClickListener(new SendBtnListener());


        // 群组详情
        ibtnGroupDetail = (ImageButton) findViewById(R.id.ibtn_group_detail);
        ibtnGroupDetail.setVisibility(View.VISIBLE);
        ibtnGroupDetail.setOnClickListener(new DetailBtnListener());

        dbm = new DBManager(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        dbm.closeDB();
    }

    private class DetailBtnListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            Intent intent = new Intent(getApplicationContext(), GroupDetail.class);
            intent.putExtra("groupId", groupId + "");
            startActivity(intent);
        }
    }

    private class SendBtnListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            String content = messageText.getText().toString();
            if (content.length() == 0) {
                Toast.makeText(getApplicationContext(), "请输入内容", Toast.LENGTH_SHORT).show();
            } else {
                Message msg = new Message();
                msg.setContent(content);
                msg.setFrom(Me.id);
                msg.setTo(group.getId());
                msg.setName(group.getName());
                msg.setType(2); //group msg type
                Publisher.publishMessage(msg);
                Messages.storeMessageEntity(group.getName(), new MessageEntity("我", content), true);
                messageText.setText("");
                dbm.saveMsg(msg);
            }
        }
    }
}