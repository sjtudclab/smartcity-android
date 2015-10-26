package sjtu.dclab.smartcity.ui.chat;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.*;
import sjtu.dclab.smartcity.R;
import sjtu.dclab.smartcity.SQLite.DBManager;
import sjtu.dclab.smartcity.chat.DeprecatedMessageAdapter;
import sjtu.dclab.smartcity.chat.DeprecatedMessages;
import sjtu.dclab.smartcity.chat.MessageEntity;
import sjtu.dclab.smartcity.chat.Publisher;
import sjtu.dclab.smartcity.community.config.Me;
import sjtu.dclab.smartcity.community.entity.Friend;
import sjtu.dclab.smartcity.community.entity.Message;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ChatActivity extends Activity {
    private ListView listView;
    private Button messageButton;
    private EditText messageText;

    private Friend friend;
    private DeprecatedMessageAdapter deprecatedAdapter;
    //DB
    private DBManager dbm;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.aty_chat);

        dbm = new DBManager(this);

        listView = (ListView) findViewById(R.id.chat_list);
        messageButton = (Button) findViewById(R.id.MessageButton);
        messageText = (EditText) findViewById(R.id.MessageText);
        messageButton.setOnClickListener(new SendMsgListener());

        Intent intent = getIntent();
        friend = (Friend) intent.getSerializableExtra(String.valueOf(R.string.friend));
        deprecatedAdapter = DeprecatedMessages.loadMessages(friend.getName());

        // 设置聊天名称
        TextView chatTitle = (TextView) findViewById(R.id.tv_chat_title);
        chatTitle.setText(friend.getName());

        updateChatList();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        dbm.closeDB();
    }

    public void updateChatList() {
        List<MessageEntity> msgs = reloadMsg();
        List<HashMap<String, Object>> history = new ArrayList<HashMap<String, Object>>();
        for (MessageEntity msg : msgs) {
            HashMap<String, Object> map = new HashMap<String, Object>();
            map.put("name", msg.getName());
            map.put("content", msg.getContent());
            history.add(map);
        }
        deprecatedAdapter.setMsgEntities(msgs);
        SimpleAdapter adapter = new SimpleAdapter(getApplicationContext(), history, R.layout.chat_item,
                new String[]{"name", "content"}, new int[]{R.id.messagedetail_row_name, R.id.messagedetail_row_text});
        listView.setAdapter(adapter);
        listView.setSelection(history.size() - 1);
    }

    protected List<MessageEntity> reloadMsg() {
        List<MessageEntity> msgList = dbm.getMsg(Me.id, friend.getId());
        return msgList;
    }

    private class SendMsgListener implements OnClickListener {
        @Override
        public void onClick(View view) {
            String content = messageText.getText().toString();
            if (content.length() == 0) {
                Toast.makeText(getApplicationContext(), "请输入内容", Toast.LENGTH_SHORT).show();
            } else {
                Message msg = new Message();
                msg.setContent(content);
                msg.setFrom(Me.id);
                msg.setTo(friend.getId());
                msg.setName("我");
                msg.setType(1); //TODO
                msg.setSerialId("");
                Publisher.publishMessage(msg);
                messageText.setText("");
                //db
                dbm.saveMsg(msg);
                updateChatList();
            }
        }
    }
}
