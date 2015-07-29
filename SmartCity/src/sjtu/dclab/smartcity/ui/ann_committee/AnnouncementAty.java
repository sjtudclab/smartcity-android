package sjtu.dclab.smartcity.ui.ann_committee;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import sjtu.dclab.smartcity.R;

/**
 * Created by theGODofws on 2015/7/22.
 */
public class AnnouncementAty extends Activity{
    private ListView list_View;
    private ImageButton ibtnAdd;
    private SimpleAdapter simple_Adapter;
    private List<Map<String,Object>>data_List;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.aty_ann_man);

        ibtnAdd = (ImageButton) findViewById(R.id.ann_ibtn_add);
        ibtnAdd.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), AnnPublishAty.class));
            }
        });

        list_View =(ListView) findViewById(R.id.ann_listView);

        data_List = new ArrayList<Map<String,Object>>();
        simple_Adapter = new SimpleAdapter(
                this,getData(),
                R.layout.aty_ann_item,
                new String[]{"ann_pic","ann_title","ann_text","ann_note"},
                new int[]{R.id.ann_pic,R.id.ann_title,R.id.ann_text,R.id.ann_note});
        list_View.setAdapter(simple_Adapter);

        list_View.setOnItemClickListener(new ListView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //TODO 测试阶段：默认均跳转至AnnDetailAty
                startActivity(new Intent(getApplicationContext(), AnnDetailAty.class));
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
        item.put("ann_pic",R.drawable.ic_launcher);
        item.put("ann_title","title");
        item.put("ann_text","text");
        item.put("ann_note","note");
        data_List.add(item);
        return data_List;
    }
}