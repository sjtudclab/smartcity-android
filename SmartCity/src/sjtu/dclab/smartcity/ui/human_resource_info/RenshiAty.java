package sjtu.dclab.smartcity.ui.human_resource_info;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import sjtu.dclab.smartcity.R;
import sjtu.dclab.smartcity.community.entity.Friend;
import sjtu.dclab.smartcity.entity.Role;
import sjtu.dclab.smartcity.tools.GsonTool;
import sjtu.dclab.smartcity.transfer.ManagementCitizenTransfer;
import sjtu.dclab.smartcity.ui.chat.ChatActivity;
import sjtu.dclab.smartcity.webservice.BasicWebService;

import java.util.*;

/**
 * Created by theGODofws on 2015/7/30.
 * modified by kaffa on 2015/08/12
 */
public class RenshiAty extends Activity {
    private final String TAG = "RenshiAty";

    private ListView lvRenshi;
    private List<Map<String, Object>> itemList = new ArrayList<Map<String, Object>>();
    private List<ManagementCitizenTransfer> mctlist;
    private List<Friend> committeeList = new ArrayList<Friend>();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.hr_info);

        lvRenshi = (ListView) findViewById(R.id.lv_renshi);
        init();
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    public void init() {
        String urlRoot = getString(R.string.URLRoot);
        String urlCommittee = getString(R.string.URLCommittee);
        String url = urlRoot + urlCommittee;
        String resp = new BasicWebService().sendGetRequest(url, null);

        mctlist = GsonTool.getObjectList(resp,ManagementCitizenTransfer[].class);

        for (ManagementCitizenTransfer mct : mctlist) {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("name", mct.getName());
            String job = "";
            Collection<Role> roles = mct.getRoles();
            if (!roles.isEmpty()) {
                Iterator iterator = roles.iterator();
                while (iterator.hasNext()) {
                    job += ((Role) iterator.next()).getName() + " ";
                }
            }
            String pic = mct.getImage();
            pic = pic.split("\\.")[0];
            int picId = getResources().getIdentifier(pic,"drawable",getPackageName()); // 通过资源名获得资源id
            map.put("job", job);
            map.put("pic", picId);
            itemList.add(map);

            // 添加通讯list
            Friend f = new Friend();
            f.setId(mct.getUserId());
            f.setName(mct.getName());
            committeeList.add(f);
        }

        SimpleAdapter adapter = new SimpleAdapter(getApplicationContext(), itemList, R.layout.hr_item,
                new String[]{"name", "job", "pic"}, new int[]{R.id.renshi_name, R.id.renshi_job, R.id.renshi_pic});
        lvRenshi.setAdapter(adapter);
        lvRenshi.setOnItemClickListener(new ItemListener());
    }

    private class ItemListener implements AdapterView.OnItemClickListener{
        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
            Intent intent = new Intent(getApplicationContext(), ChatActivity.class);
            intent.putExtra(String.valueOf(R.string.friend),committeeList.get(i));
            startActivity(intent);
        }
    }
}
