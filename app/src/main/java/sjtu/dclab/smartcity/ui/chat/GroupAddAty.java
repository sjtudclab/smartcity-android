package sjtu.dclab.smartcity.ui.chat;

import android.app.Activity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;
import sjtu.dclab.smartcity.R;
import sjtu.dclab.smartcity.chat.Friends;
import sjtu.dclab.smartcity.community.entity.Friend;
import sjtu.dclab.smartcity.webservice.BasicWebService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Yang on 2015/8/16.
 */
public class GroupAddAty extends Activity {

    //data
    private String groupId;
    private List<Friend> friends = null;
    private ArrayList<HashMap<String, Object>> items;

    //component
    private ListView lvFriend;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.aty_groupadd);

        groupId = getIntent().getStringExtra("groupId");
        lvFriend = (ListView) findViewById(R.id.lv_groupadd);
        friends = Friends.getFriends();
    }

    @Override
    protected void onStart() {
        super.onStart();
        init();
    }

    public void init() {
        items = new ArrayList<HashMap<String, Object>>();
        for (Friend f : friends) {
            HashMap<String, Object> map = new HashMap<String, Object>();
            map.put("name", f.getName());
            String pic = f.getImage();
            pic = pic == null ? "tab_img_profile" : pic.split("\\.")[0];
            int picId = getResources().getIdentifier(pic, "drawable", getPackageName());
            map.put("pic", picId);
            items.add(map);
        }

        SimpleAdapter adapter = new SimpleAdapter(getApplicationContext(),
                items, R.layout.list_friend,
                new String[]{"name", "pic"},
                new int[]{R.id.list_friend_name, R.id.list_friend_img});
        lvFriend.setAdapter(adapter);
        lvFriend.setOnItemClickListener(new OnItemClickedListener());
    }

    private class OnItemClickedListener implements AdapterView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
            // TODO check and test
            long chosenUserId = friends.get(i).getId();
            String jsonStr = "{\"userIds\":["+ chosenUserId +"]}";
            String url = getResources().getString(R.string.URLRoot) + "groups/" + groupId + "/users";

            String resp = "";
            try {
                resp = new BasicWebService().sendPostRequestWithJsonString(url, jsonStr);
            }catch (Exception e){
                Toast.makeText(getApplicationContext(),"添加失败",Toast.LENGTH_SHORT).show();
            }

            if(resp.equals("success")){
                Toast.makeText(getApplicationContext(),"添加成功",Toast.LENGTH_SHORT).show();
            }
            setResult(0);
            finish();
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            finish();
        }
        return true;
    }
}