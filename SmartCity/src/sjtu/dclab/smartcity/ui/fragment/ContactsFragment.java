package sjtu.dclab.smartcity.ui.fragment;

import android.app.ActivityManager;
import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import cn.edu.sjtu.se.dclab.config.Me;
import cn.edu.sjtu.se.dclab.entity.Friend;
import cn.edu.sjtu.se.dclab.talk.MyTalk;
import org.eclipse.paho.android.service.MqttAndroidClient;
import org.eclipse.paho.client.mqttv3.*;
import sjtu.dclab.smartcity.GlobalApp;
import sjtu.dclab.smartcity.R;
import sjtu.dclab.smartcity.chat.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Yang on 2015/7/22.
 */
public class ContactsFragment extends Fragment {
    final private String TAG = "ContactFragment";

    // 长意的
    final static String TOKEN = "cn.edu.sjtu.se.dclab.community_chat.ListActivity";
    //	private static final String SERVICE_CLASSNAME = "cn.edu.sjtu.se.dclab.community_chat.MQTTService";
    private static final String SERVICE_CLASSNAME = "org.eclipse.paho.android.service.MqttService";
    private MyTalk talk;
    private List<Friend> friends = null;
    private ListView lv;
    private ArrayList<HashMap<String, Object>> items;
    private MqttAndroidClient client;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        Log.i(TAG, "Fragment created");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.i(TAG, "Fragment created");
        return inflater.inflate(R.layout.fragment_contacts, container, false);
    }

    @Override
    public void onStart() {
        super.onStart();
        initList();
        setUpMessageAdapters();
        startMQTTService();
    }

    public void initList() {
        Intent intent = getActivity().getIntent();
//        talk = (MyTalk) intent.getSerializableExtra(String.valueOf(R.string.talk));
        talk = ((GlobalApp)getActivity().getApplication()).getTalk();

        //for network
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        friends = Friends.getFriends();
        if (friends == null || friends.size() == 0) {
            friends = (List<Friend>) talk.getFriends();
            Friends.addFriends(friends);
        }

        lv = (ListView) getFragmentManager()
                .findFragmentById(R.id.fragment_contact)
                .getView().findViewById(R.id.lv_contacts);
        items = new ArrayList<HashMap<String, Object>>();

        for (Friend friend : friends) {
            HashMap<String, Object> map = new HashMap<String, Object>();
            map.put("name", friend.getName());
            items.add(map);
        }

        SimpleAdapter adapter = new SimpleAdapter(getActivity(),
                items, R.layout.list_friend,
                new String[] { "name" },
                new int[] { R.id.list_friend_name});
        lv.setAdapter(adapter);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
                Intent intent = new Intent(getActivity(), ChatActivity.class);
                intent.putExtra(String.valueOf(R.string.friend), friends.get(arg2));
                getActivity().startActivity(intent);
            }
        });
    }

    private void setUpMessageAdapters() {
        if (friends != null) {
            for (Friend f : friends) {
                String username = f.getName();
                MessageAdapter adapter = Messages.loadMessageAdapter(username);
                if (adapter == null) {
                    List<MessageEntity> msgEntities = new ArrayList<MessageEntity>();
                    adapter = new MessageAdapter(getActivity(), R.layout.chat_item,
                            R.id.messagedetail_row_text, msgEntities);
                    Messages.storeMessageAdapter(username, adapter);
                }
            }
        }
    }

    private void startMQTTService() {
        MqttConnectOptions conOpt = new MqttConnectOptions();

        client = new MqttAndroidClient(getActivity(), Configurations.uri, Configurations.clientId);

        conOpt.setCleanSession(Configurations.cleanSession);
        conOpt.setConnectionTimeout(Configurations.timeout);
        conOpt.setKeepAliveInterval(Configurations.keepalive);
        conOpt.setUserName(Configurations.username);
        conOpt.setPassword(Configurations.password.toCharArray());

        client.setCallback(new PushCallback(getActivity()));

        try {
            client.connect(conOpt, null, new IMqttActionListener() {

                @Override
                public void onSuccess(IMqttToken arg0) {
                    try {
                        client.subscribe(Configurations.subscribeTopicPrefix + Me.id, Configurations.qos);
                        Publisher.register(client);
                        startHeartService();
                    } catch (MqttSecurityException e) {
                        Log.e(TAG, e.getMessage());
                        e.printStackTrace();
                    } catch (MqttException e) {
                        Log.e(TAG, e.getMessage());
                        e.printStackTrace();
                    }
                }

                @Override
                public void onFailure(IMqttToken arg0, Throwable arg1) {
                    Log.e(TAG+" Failure", arg1.getMessage());
                }
            });
        } catch (MqttException e) {
            e.printStackTrace();
        }
    }

    private void startHeartService() {
        final Intent intent = new Intent(getActivity(), HeartbeatService.class);
        getActivity().startService(intent);
    }

    private boolean serviceIsRunning() {
        ActivityManager manager = (ActivityManager) getActivity().getSystemService(Context.ACTIVITY_SERVICE);
        for (ActivityManager.RunningServiceInfo service : manager
                .getRunningServices(Integer.MAX_VALUE)) {
            if (SERVICE_CLASSNAME.equals(service.service.getClassName())) {
                return true;
            }
        }
        return false;
    }
}
