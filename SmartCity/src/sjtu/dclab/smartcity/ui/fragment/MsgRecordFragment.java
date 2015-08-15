package sjtu.dclab.smartcity.ui.fragment;

import android.app.Activity;
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
import sjtu.dclab.smartcity.chat.Friends;
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

    private Activity curAct;
    private View view;

    private MyTalk talk;
    private List<Friend> friends = null;
    private ListView lv_contacts;

    private ArrayList<HashMap<String, Object>> items_contacts = new ArrayList<HashMap<String, Object>>();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        curAct =getActivity();
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_msgrecord, container, false);
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        init();
    }

    public void init() {
        talk = ((GlobalApp) getActivity().getApplication()).getTalk();
        friends = Friends.getFriends();
        if (friends == null || friends.size() == 0) {
            friends = (List<Friend>) talk.getFriends();

            for (Friend f : friends) {
                if (f.getId() != Me.id) {
                    Friends.addFriend(f);
                }
            }
        }

        for (Friend friend : friends) {
            HashMap<String, Object> map = new HashMap<String, Object>();
            String name = friend.getName() == null ? "路人甲" : friend.getName();
            map.put("name", name);
            String pic = friend.getImage();
            pic = pic == null ? "tab_img_profile" : pic.split("\\.")[0];
            int picId = getResources().getIdentifier(pic, "drawable", getActivity().getPackageName());
            map.put("pic", picId);
            map.put("msg", "你好");
            items_contacts.add(map);
        }

        lv_contacts = (ListView) getFragmentManager().findFragmentById(R.id.fragment_msgrecord).getView().findViewById(R.id.lv_msgrecord);

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
                intent.putExtra(String.valueOf(R.string.friend), friends.get(i));
                getActivity().startActivity(intent);
            }
        });
    }
}