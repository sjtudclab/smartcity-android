package sjtu.dclab.smartcity.ui.party;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import sjtu.dclab.smartcity.R;

/**
 * Created by ¹Â¶ÀµÄ¹Û²âÕß on 2015/7/22.
 */
public class OrgLifeAty extends Activity{
    private ListView list_View;
    private LinearLayout llPolicy;
    private SimpleAdapter simple_Adapter;
    private List<Map<String,Object>>data_List;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.aty_party_orglife);

        llPolicy = (LinearLayout) findViewById(R.id.party_ll_policy1);
        llPolicy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO 组织生活政策详情
            }
        });

        list_View =(ListView) findViewById(R.id.org_life_listView);

        data_List = new ArrayList<Map<String,Object>>();
        simple_Adapter = new SimpleAdapter(
                this,getData(),
                R.layout.pty_org_life_item_with_pic,
                new String[]{"org_pic","org_title","org_text","org_num","org_time"},
                new int[]{R.id.org_life_pic,R.id.org_life_title,R.id.org_life_text,R.id.org_life_num,R.id.org_life_time});
        list_View.setAdapter(simple_Adapter);

        list_View.setOnItemClickListener(new ListView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //TODO 测试阶段：默认均跳转至OrgLifeDetailAty
                startActivity(new Intent(getApplicationContext(), OrgLifeDetailAty.class));
            }
        });
    }
    //What need to be changed
    private List<Map<String,Object>> getData(){
        //What to do:
        //create maps and put them into List dataList
        //keys are org_pic,org_tittle,org_text,org_note,org_num,org_time
        return data_List;
    }
}