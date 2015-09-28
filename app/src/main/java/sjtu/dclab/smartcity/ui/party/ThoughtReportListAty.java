package sjtu.dclab.smartcity.ui.party;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import sjtu.dclab.smartcity.R;

/**
 * Created by theGODofws on 2015/8/1.
 */
public class ThoughtReportListAty extends Activity{
    private ListView list_View;
    private ImageButton ibtnAdd;
    private LinearLayout llPolicy;
    private SimpleAdapter simple_Adapter;
    private List<Map<String,Object>> data_List;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.thought_reprot_list);

        llPolicy = (LinearLayout) findViewById(R.id.party_ll_policy3);
        llPolicy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO 思想汇报撰写说明
            }
        });

        ibtnAdd = (ImageButton) findViewById(R.id.btn_ann_add);
        ibtnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), ThoughtReportPublishAty.class));
            }
        });

        list_View =(ListView) findViewById(R.id.thought_report_list_listView);

        data_List = new ArrayList<Map<String,Object>>();
        simple_Adapter = new SimpleAdapter(
                this,getData(),
                R.layout.pty_org_life_item_with_pic,
                new String[]{"ses_pic","ses_title","ses_text","ses_author","ses_date"},
                new int[]{R.id.org_life_pic,R.id.org_life_title,R.id.org_life_text,R.id.org_life_num,R.id.org_life_time});
        list_View.setAdapter(simple_Adapter);

        list_View.setOnItemClickListener(new ListView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //TODO
                startActivity(new Intent(getApplicationContext(), ThoughtReportDetailAty.class));
            }
        });
    }
    //What need to be changed
    private List<Map<String,Object>> getData(){
        //What to do:
        //create maps and put them into List dataList
        //keys are ann_pic,ann_tittle,ann_text,ann_note,ann_goto_btn
        Map<String,Object> item = new HashMap<String, Object>();
        // for test
        item.put("ses_pic",R.drawable.ic_launcher);
        item.put("ses_title","title");
        item.put("ses_text","text");
        item.put("ses_author","note");
        item.put("ses_date","date");
        data_List.add(item);
        return data_List;
    }
}
