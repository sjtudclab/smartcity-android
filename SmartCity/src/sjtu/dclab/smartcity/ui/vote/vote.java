package sjtu.dclab.smartcity.ui.vote;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import sjtu.dclab.smartcity.R;

/**
 * Created by theGODofws on 2015/7/30.
 */
public class vote extends Activity{
    //private RadioGroup rg;
    private ListView list_View;
    private ImageButton ibtnAdd;
    private SimpleAdapter simple_Adapter;
    private List<Map<String,Object>> data_List;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.vote);

        ibtnAdd = (ImageButton) findViewById(R.id.vote_ibtn_add);
        ibtnAdd.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), vote_start.class));
            }
        });

        list_View =(ListView) findViewById(R.id.vote_listView);

        data_List = new ArrayList<Map<String,Object>>();
        simple_Adapter = new SimpleAdapter(
                this,getData(),
                R.layout.vote_model,
                new String[]{"vote_pic","vote_title","vote_starter","vote_start_time","vote_end_time"},
                new int[]{R.id.vote_pic,R.id.vote_title,R.id.vote_starter,R.id.vote_start_time,R.id.vote_end_time});
        list_View.setAdapter(simple_Adapter);
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

    //TODO 有关增加RadioGroup的事件
}
