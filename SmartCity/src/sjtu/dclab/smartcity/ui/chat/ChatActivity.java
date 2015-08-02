package sjtu.dclab.smartcity.ui.chat;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;
import sjtu.dclab.smartcity.R;
import sjtu.dclab.smartcity.chat.MessageAdapter;
import sjtu.dclab.smartcity.chat.MessageEntity;
import sjtu.dclab.smartcity.chat.Messages;
import sjtu.dclab.smartcity.chat.Publisher;
import sjtu.dclab.smartcity.community.config.Me;
import sjtu.dclab.smartcity.community.entity.Friend;
import sjtu.dclab.smartcity.community.entity.Message;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class ChatActivity extends Activity {
    // private static final String TAG = ChatActivity.class.getSimpleName();;

    private ChatActivity chat = this;

    private ListView listView;

    private MessageAdapter adapter;

    private Button messageButton;

    private EditText messageText;

    private Friend friend;

    public void onCreate(Bundle savedInstanceState) {
        // Log.v(TAG, "onCreate >>>>>>");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

    }

    @Override
    protected void onStart() {
        super.onStart();

        listView = (ListView) findViewById(R.id.chat_list);

        Intent intent = getIntent();
        friend = (Friend) intent.getSerializableExtra(String
                .valueOf(R.string.friend));
        adapter = Messages.loadMessageAdapter(friend.getName());

        // 接收消息
        listView.setAdapter(adapter);

        // 发送消息
        messageButton = (Button) findViewById(R.id.MessageButton);
        messageText = (EditText) findViewById(R.id.MessageText);
        messageButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                String content = getText();
                if (content.length() == 0) {
                    Toast.makeText(getApplicationContext(), "请输入内容", Toast.LENGTH_SHORT).show();
                } else {
                    Message msg = new Message();
                    msg.setContent(content);
                    msg.setFrom(Me.id);
                    msg.setTo(friend.getId());
                    msg.setName(friend.getName());
                    msg.setType(1);
                    Publisher.publishMessage(msg);
                    Messages.storeMessageEntity(friend.getName(),
                            new MessageEntity(Me.username, content), true);
                    messageText.setText("");
                }
            }
        });
    }

    private String getText() {
        return messageText.getText().toString();
    }

    private class ChatListener implements PropertyChangeListener {
        @Override
        public void propertyChange(PropertyChangeEvent event) {
            chat.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    adapter.notifyDataSetChanged();
                }
            });
        }

    }

}
