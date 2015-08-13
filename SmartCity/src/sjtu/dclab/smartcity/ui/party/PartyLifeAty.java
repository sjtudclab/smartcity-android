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
 * Created by theGODofws on 2015/8/1.
 */
public class PartyLifeAty extends Activity{
    private ListView list_View;
    private LinearLayout llPolicy;
    private SimpleAdapter simple_Adapter;
    private List<Map<String,Object>> data_List;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.aty_party_partylife);

        llPolicy = (LinearLayout) findViewById(R.id.party_ll_policy2);
        llPolicy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO 党员活动指导详情
            }
        });

        list_View =(ListView) findViewById(R.id.party_life_listView);

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
                //TODO
                startActivity(new Intent(getApplicationContext(), PartyLifeDetailAty.class));
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
