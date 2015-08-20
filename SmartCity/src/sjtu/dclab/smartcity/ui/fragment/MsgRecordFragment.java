package sjtu.dclab.smartcity.ui.fragment;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import sjtu.dclab.smartcity.GlobalApp;
import sjtu.dclab.smartcity.R;
import sjtu.dclab.smartcity.SQLite.DBManager;
import sjtu.dclab.smartcity.chat.Friends;
import sjtu.dclab.smartcity.chat.MessageEntity;
import sjtu.dclab.smartcity.community.config.Me;
import sjtu.dclab.smartcity.community.entity.Friend;
import sjtu.dclab.smartcity.community.talk.MyTalk;
import sjtu.dclab.smartcity.ui.chat.ChatActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Yang on 2015/8/15.
 */
public class MsgRecordFragment extends Fragment {

    private View view;

    private MyTalk talk;
    private List<Friend> friends = null;
    private List<Friend> friendsWithRecords = null;
    private ListView lv_contacts;
    private ArrayList<HashMap<String, Object>> items_contacts;
    private DBManager dbm;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dbm = new DBManager(getActivity());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_msgrecord, container, false);
        return view;
    }

    @Override
    public void onStart() {
        lv_contacts = (ListView) getFragmentManager().findFragmentById(R.id.fragment_msgrecord).getView().findViewById(R.id.lv_msgrecord);
        init();
        super.onStart();
    }

    public void init() {
        talk = ((GlobalApp) getActivity().getApplication()).getTalk();
        friends = Friends.getFriends();
        friendsWithRecords = new ArrayList<Friend>();
        items_contacts = new ArrayList<HashMap<String, Object>>();

        for (Friend f : friends) {
            MessageEntity msg = dbm.getLastMsg(Me.id,f.getId());
            if (msg!=null){
                friendsWithRecords.add(f);
                HashMap<String,Object> map = new HashMap<String, Object>();
                String name = f.getName() == null ? "路人甲" : f.getName();
                map.put("name", name);
                String pic = f.getImage();
                pic = pic == null ? "tab_img_profile" : pic.split("\\.")[0];
                int picId = getResources().getIdentifier(pic, "drawable", getActivity().getPackageName());
                map.put("pic", picId);
                map.put("msg", msg.getContent());
                items_contacts.add(map);
            }
        }

        SimpleAdapter adapter = new SimpleAdapter(
                getActivity().getApplicationContext(), items_contacts, R.layout.msgrecord_item,
                new String[]{"pic", "name", "msg"},
                new int[]{R.id.iv_msg_pic, R.id.tv_msg_contact, R.id.tv_msg_content}
        );
        lv_contacts.setAdapter(adapter);
        lv_contacts.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(getActivity(), ChatActivity.class);
                intent.putExtra(String.valueOf(R.string.friend), friendsWithRecords.get(i));
                getActivity().startActivity(intent);
            }
        });
    }
}