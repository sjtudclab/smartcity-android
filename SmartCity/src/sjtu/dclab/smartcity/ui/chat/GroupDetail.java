package sjtu.dclab.smartcity.ui.chat;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
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
    private ArrayList<HashMap<String, Object>> itemList;
    private List<Integer> icons;

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
        String url = getResources().getString(R.string.URLRoot) + "groups/" + groupId + "/memberlist";
        String resp = webService.sendGetRequest(url, null);
        itemList = new ArrayList<HashMap<String, Object>>();
        icons = new ArrayList<Integer>();
        gmtList = GsonTool.getObjectList(resp, GroupMemberTransfer[].class);
        for (GroupMemberTransfer gmt : gmtList) {
            HashMap<String, Object> map = new HashMap<String, Object>();
            map.put("name", gmt.getName());
            String pic = gmt.getImage();
            pic = pic.split("\\.")[0];
            int picId = getResources().getIdentifier(pic, "drawable", getPackageName()); // 通过资源名获得资源id
            map.put("pic", picId);
            itemList.add(map);
            icons.add(picId);
        }

        //add member icon
        HashMap<String, Object> addMap = new HashMap<String, Object>();
        addMap.put("name", "添加");
        addMap.put("pic", R.drawable.group_add);
        itemList.add(addMap);
        icons.add(R.drawable.group_add);

        SimpleAdapter adapter = new SimpleAdapter(
                getApplicationContext(), itemList, R.layout.group_member_item,
                new String[]{"name", "pic"}, new int[]{R.id.tv_member_name, R.id.iv_member_pic}
        );
        gvMembers.setAdapter(adapter);
        gvMembers.setOnItemClickListener(new ItemListener());
    }

    private class ItemListener implements AdapterView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
            switch (icons.get(i)) {
                case R.drawable.group_add:
//                    startActivityForResult();
                    Intent intent = new Intent(getApplicationContext(), GroupAddAty.class);
                    intent.putExtra("groupId", groupId);
                    startActivityForResult(intent, 0);
                    break;
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK)
            init();
    }
}