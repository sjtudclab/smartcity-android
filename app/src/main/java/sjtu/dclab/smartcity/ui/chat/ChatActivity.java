package sjtu.dclab.smartcity.ui.chat;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.os.PowerManager;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import sjtu.dclab.smartcity.R;
import sjtu.dclab.smartcity.SQLite.DBManager;
import sjtu.dclab.smartcity.chat.DeprecatedMessageAdapter;
import sjtu.dclab.smartcity.chat.DeprecatedMessages;
import sjtu.dclab.smartcity.chat.MessageEntity;
import sjtu.dclab.smartcity.chat.Publisher;
import sjtu.dclab.smartcity.community.config.Me;
import sjtu.dclab.smartcity.community.entity.Friend;
import sjtu.dclab.smartcity.community.entity.Message;
import sjtu.dclab.smartcity.ui.chat.audio.MediaRecordFunc;
import sjtu.dclab.smartcity.ui.chat.utils.CommonUtils;

public class ChatActivity extends Activity {

    private ListView listView;
    private Button btnSendMessage, btnMore;
    private EditText etMessage;
    private View more;
    private View buttonSetModeKeyboard;
    private View buttonPressToSpeak;
    private LinearLayout btnContainer;
    private View buttonSetModeVoice;
    private TextView chatTitle;
    private ImageView micImage;
    private View recordingContainer;
    private TextView recordingHint;

    private int mRecordingState = -1;    // -1:没有在录制，0：录制wav，1：录制amr
    private int mRecordingResult = -1;

    private InputMethodManager manager;
    private AnimationDrawable animationDrawable;
    MediaRecordFunc mRecorder;
    private PowerManager.WakeLock wakeLock;

    private Friend friend;
    private DeprecatedMessageAdapter deprecatedAdapter;

    private DBManager dbm;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.aty_chat);

        initView();
        setUpView();

        //发消息按钮监听
        btnSendMessage.setOnClickListener(new SendMsgListener());

        //语音消息相关
        mRecorder = MediaRecordFunc.getInstance();
        buttonPressToSpeak.setOnTouchListener(new PressToSpeakListen());

        Intent intent = getIntent();
        friend = (Friend) intent.getSerializableExtra(String.valueOf(R.string.friend));
        deprecatedAdapter = DeprecatedMessages.loadMessages(friend.getName());

        // 设置聊天名称
        chatTitle.setText(friend.getName());

        updateChatList();
    }

    private void initView() {
        listView = (ListView) findViewById(R.id.chat_list);
        btnSendMessage = (Button) findViewById(R.id.MessageButton);
        etMessage = (EditText) findViewById(R.id.MessageText);
        more = findViewById(R.id.more);
        buttonSetModeKeyboard = findViewById(R.id.btn_set_mode_keyboard);
        btnMore = (Button) findViewById(R.id.btn_more);
        buttonPressToSpeak = findViewById(R.id.btn_press_to_speak);
        btnContainer = (LinearLayout) findViewById(R.id.ll_btn_container);
        buttonSetModeVoice = findViewById(R.id.btn_set_mode_voice);
        chatTitle = (TextView) findViewById(R.id.tv_chat_title);

        micImage = (ImageView) findViewById(R.id.mic_image);
        animationDrawable = (AnimationDrawable) micImage.getBackground();
        animationDrawable.setOneShot(false);

        recordingContainer = findViewById(R.id.view_talk);
        recordingHint = (TextView) findViewById(R.id.recording_hint);

    }
    private void setUpView() {
        dbm = new DBManager(this);

        manager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        getWindow().setSoftInputMode(
                WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        wakeLock = ((PowerManager) getSystemService(Context.POWER_SERVICE))
                .newWakeLock(PowerManager.SCREEN_DIM_WAKE_LOCK, "demo");
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (wakeLock.isHeld()) wakeLock.release();
        // 停止语音播放 TODO

        // 停止录音
        mRecorder.stopRecordAndFile();
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
            String content = etMessage.getText().toString();
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
                etMessage.setText("");
                //db
                dbm.saveMsg(msg);
                updateChatList();
            }
        }
    }

    /**
     * 点击文字输入框
     * @param v
     */
    public void editClick(View v) {
        listView.setSelection(listView.getCount() - 1);
        if (more.getVisibility() == View.VISIBLE) {
            more.setVisibility(View.GONE);
//            iv_emoticons_normal.setVisibility(View.VISIBLE);
//            iv_emoticons_checked.setVisibility(View.INVISIBLE);
        }
    }
    /**
     * 显示或隐藏图标按钮页
     * @param view
     */
    public void more(View view) {
        if (more.getVisibility() == View.GONE) {
            System.out.println("more gone");
            hideKeyboard();
            more.setVisibility(View.VISIBLE);
            btnContainer.setVisibility(View.VISIBLE);
//            emojiIconContainer.setVisibility(View.GONE);
        }
//        else {
//            if (emojiIconContainer.getVisibility() == View.VISIBLE) {
//                emojiIconContainer.setVisibility(View.GONE);
//                btnContainer.setVisibility(View.VISIBLE);
//                iv_emoticons_normal.setVisibility(View.VISIBLE);
//                iv_emoticons_checked.setVisibility(View.INVISIBLE);
//            } else {
//                more.setVisibility(View.GONE);
//            }
//        }
    }
    /**
     * 显示语音图标按钮
     * @param view
     */
    public void setModeVoice(View view) {
        hideKeyboard();
        etMessage.setVisibility(View.GONE);
        more.setVisibility(View.GONE);
        view.setVisibility(View.GONE);
        buttonSetModeKeyboard.setVisibility(View.VISIBLE);
        btnSendMessage.setVisibility(View.GONE);
        btnMore.setVisibility(View.VISIBLE);
        buttonPressToSpeak.setVisibility(View.VISIBLE);
        btnContainer.setVisibility(View.VISIBLE);
//        iv_emoticons_normal.setVisibility(View.VISIBLE);
//        iv_emoticons_checked.setVisibility(View.INVISIBLE);
//        emojiIconContainer.setVisibility(View.GONE);
    }
    /**
     * 显示键盘图标
     * @param view
     */
    public void setModeKeyboard(View view) {
        etMessage.setVisibility(View.VISIBLE);
        more.setVisibility(View.GONE);
        view.setVisibility(View.GONE);
        buttonSetModeVoice.setVisibility(View.VISIBLE);
        etMessage.requestFocus();
        buttonPressToSpeak.setVisibility(View.GONE);
        btnSendMessage.setVisibility(View.VISIBLE);
    }

    /**
     * 隐藏软键盘
     */
    private void hideKeyboard() {
        if (getWindow().getAttributes().softInputMode != WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN) {
            if (getCurrentFocus() != null)
                manager.hideSoftInputFromWindow(getCurrentFocus()
                        .getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }

    /**
     * 发送语音
     * @param filePath
     * @param fileName
     * @param length
     * @param isResend
     */
    private void sendVoice(String filePath, String fileName, String length,
                           boolean isResend) {
        if (!(new File(filePath).exists())) {
            return;
        }
        try {

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**按住说话listener
     */
    class PressToSpeakListen implements View.OnTouchListener {
        @Override
        public boolean onTouch(View v, MotionEvent event) {
            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    animationDrawable.start();
                    /*if (!CommonUtils.isExitsSdcard()) {
                        String st4 = getResources().getString(R.string.Send_voice_need_sdcard_support);
                        Toast.makeText(ChatActivity.this, st4, Toast.LENGTH_SHORT).show();
                        return false;
                    }*/
                    try {
                        v.setPressed(true);
                        wakeLock.acquire();
                        //if (VoicePlayClickListener.isPlaying) VoicePlayClickListener.currentPlayListener.stopPlayVoice();
                        recordingContainer.setVisibility(View.VISIBLE);
                        recordingHint.setText(getString(R.string.move_up_to_cancel));
                        recordingHint.setBackgroundColor(Color.TRANSPARENT);
                        //录制开始
                        //voiceRecorder.startRecording(null, String.valueOf(friend.getId()), getApplicationContext());
                        new Thread(new Runnable() {
                            @Override
                            public void run() {
                                mRecordingResult = mRecorder.startRecordAndFile(getFilesDir() +
                                        "/" + Me.id + "_" + friend.getId() + "_" + "test.amr");//TODO:修改存储路径
                            }
                        }).start();
                    } catch (Exception e) {
                        e.printStackTrace();
                        v.setPressed(false);
                        if (wakeLock.isHeld()) wakeLock.release();
                        //if (voiceRecorder != null) voiceRecorder.discardRecording();
                        recordingContainer.setVisibility(View.INVISIBLE);
                        Toast.makeText(ChatActivity.this, R.string.recoding_fail, Toast.LENGTH_SHORT).show();
                        return false;
                    }
                    return true;
                case MotionEvent.ACTION_MOVE: {
                    if (event.getY() < 0) {
                        recordingHint.setText(getString(R.string.release_to_cancel));
                        recordingHint.setBackgroundResource(R.drawable.recording_text_hint_bg);
                    } else {
                        recordingHint.setText(getString(R.string.move_up_to_cancel));
                        recordingHint.setBackgroundColor(Color.TRANSPARENT);
                        animationDrawable.start();
                    }
                    return true;
                }
                case MotionEvent.ACTION_UP:
                    if (animationDrawable.isRunning()) animationDrawable.stop();
                    v.setPressed(false);
                    recordingContainer.setVisibility(View.INVISIBLE);
                    if (wakeLock.isHeld()) wakeLock.release();
                    if (event.getY() < 0) {
                        // 丢弃录音文件
                        //voiceRecorder.discardRecording();
                        new Thread(new Runnable() {
                            @Override
                            public void run() {
                                mRecorder.stopRecordAndFile();  // 停止录音
                                // 丢弃录音文件 TODO
                            }
                        }).start();
                    } else {
                        String st1 = getResources().getString(R.string.Recording_without_permission);
                        String st2 = getResources().getString(R.string.The_recording_time_is_too_short);
                        String st3 = getResources().getString(R.string.send_failure_please);
                        try {
                            new Thread(new Runnable() {
                                @Override
                                public void run() {
                                    mRecorder.stopRecordAndFile();  // 停止录音
                                    // 发送录音文件 TODO
                                }
                            }).start();
//                            int length = voiceRecorder.stopRecoding();
//                            if (length > 0)
//                                sendVoice(voiceRecorder.getVoiceFilePath(),
//                                        voiceRecorder.getVoiceFileName(String.valueOf(friend.getId())),
//                                        Integer.toString(length), false);
//                            else if (length == EMError.INVALID_FILE) {
//                                Toast.makeText(getApplicationContext(), st1, Toast.LENGTH_SHORT).show();
//                            }
//                            else {
//                                Toast.makeText(getApplicationContext(), st2, Toast.LENGTH_SHORT).show();
//                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                            Toast.makeText(ChatActivity.this, st3, Toast.LENGTH_SHORT).show();
                        }
                    }
                    return true;
                default:
                    recordingContainer.setVisibility(View.INVISIBLE);
                    //if (voiceRecorder != null) voiceRecorder.discardRecording();
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            mRecorder.stopRecordAndFile();  // 停止录音
                        }
                    }).start();
                    return false;
            }
        }
    }


}

