package sjtu.dclab.smartcity.ui.chat;

import android.app.Activity;
import android.os.Bundle;
import android.widget.GridView;
import android.widget.SimpleAdapter;
import sjtu.dclab.smartcity.R;
import sjtu.dclab.smartcity.tools.GsonTool;
import sjtu.dclab.smartcity.transfer.GroupMemberTransfer;
import sjtu.dclab.smartcity.webservice.BasicWebService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Yang on 2015/8/14.
 */
public class GroupDetail extends Activity {

    private String TAG = "GroupDetail";

    private String groupId;

    private GridView gvMembers;
    private List<GroupMemberTransfer> gmtList;
    private ArrayList<HashMap<String, Object>> itemList = new ArrayList<HashMap<String, Object>>();

    private BasicWebService webService = new BasicWebService();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.aty_group_detail);

        gvMembers = (GridView) findViewById(R.id.gv_members);
        groupId = getIntent().getStringExtra("groupId");

        init();
    }

    public void init() {
        String url = getResources().getString(R.string.URLRoot) + "/groups/" + groupId + "/memberlist";
        String resp = webService.sendGetRequest(url, null);
        gmtList = GsonTool.getObjectList(resp, GroupMemberTransfer[].class);
        for (GroupMemberTransfer gmt : gmtList) {
            HashMap<String, Object> map = new HashMap<String, Object>();
            map.put("name", gmt.getName());
            String pic = gmt.getImage();
            pic = pic.split("\\.")[0];
            int picId = getResources().getIdentifier(pic, "drawable", getPackageName()); // 通过资源名获得资源id
            map.put("pic", picId);
            itemList.add(map);
        }

        SimpleAdapter adapter = new SimpleAdapter(
                getApplicationContext(), itemList, R.layout.group_member_item,
                new String[]{"name", "pic"}, new int[]{R.id.tv_member_name, R.id.iv_member_pic}
        );
        gvMembers.setAdapter(adapter);
    }
}