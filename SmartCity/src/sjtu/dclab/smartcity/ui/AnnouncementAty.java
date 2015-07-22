package sjtu.dclab.smartcity.ui;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import sjtu.dclab.smartcity.R;

/**
 * Created by ¹Â¶ÀµÄ¹Û²âÕß on 2015/7/22.
 */
public class AnnouncementAty extends Activity{
    private ListView list_View;
    private SimpleAdapter simple_Adapter;
    private List<Map<String,Object>>data_List;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.aty_ann_man);
        list_View =(ListView) findViewById(R.id.ann_listView);

        data_List = new ArrayList<Map<String,Object>>();
        simple_Adapter = new SimpleAdapter(this,getData(), R.layout.aty_ann_items,new String[]{"ann_pic","ann_tittle","ann_text","ann_note"},new int[]{R.id.ann_pic,R.id.ann_title,R.id.ann_text,R.id.ann_note});
        list_View.setAdapter(simple_Adapter);
    }
    //What need to be changed
    private List<Map<String,Object>> getData(){
        //What to do:
        //create maps and put them into List dataList
        //keys are ann_pic,ann_tittle,ann_text,ann_note,ann_goto_btn
        return data_List;
    }
}
