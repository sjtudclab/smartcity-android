package sjtu.dclab.smartcity.ui;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import sjtu.dclab.smartcity.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by ¹Â¶ÀµÄ¹Û²âÕß on 2015/7/22.
 */
public class OrgLifeAty extends Activity{
    private ListView list_View;
    private SimpleAdapter simple_Adapter;
    private List<Map<String,Object>>data_List;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pty_org_life);
        list_View =(ListView) findViewById(R.id.org_life_listView);

        data_List = new ArrayList<Map<String,Object>>();
        simple_Adapter = new SimpleAdapter(
                this,getData(),
                R.layout.pty_org_life_item_with_pic,
                new String[]{"org_pic","org_title","org_text","org_num","org_time"},
                new int[]{R.id.org_life_pic,R.id.org_life_title,R.id.org_life_text,R.id.org_life_num,R.id.org_life_time});
        list_View.setAdapter(simple_Adapter);
    }
    //What need to be changed
    private List<Map<String,Object>> getData(){
        //What to do:
        //create maps and put them into List dataList
        //keys are org_pic,org_tittle,org_text,org_note,org_num,org_time
        return data_List;
    }
}