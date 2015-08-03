package sjtu.dclab.smartcity.ui.human_resource_info;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
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
public class RenshiAty extends Activity {
    private ListView list_View;
    private SimpleAdapter simple_Adapter;
    private List<Map<String,Object>> data_List;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.hr_info);

        list_View =(ListView) findViewById(R.id.hr_info_listView);

        data_List = new ArrayList<Map<String,Object>>();
        simple_Adapter = new SimpleAdapter(
                this,getData(),
                R.layout.hr_model,
                new String[]{"hr1_pic","hr1_name","hr1_pos1","hr1_pos2","hr2_pic","hr2_name","hr2_pos1","hr2_pos2"},
                new int[]{R.id.hr1_pic,R.id.hr1_name,R.id.hr1_pos1,R.id.hr1_pos2,R.id.hr2_pic,R.id.hr2_name,R.id.hr2_pos1,R.id.hr2_pos2});
        list_View.setAdapter(simple_Adapter);

        //TODO:to check when the item is clicked, whether left side or right side the click happened,and then decide which man's info should be loaded
        list_View.setOnItemClickListener(new ListView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                startActivity(new Intent(getApplicationContext(), RenshiDetail.class));
            }
        });
    }

    //What need to be changed
    private List<Map<String,Object>> getData(){
        //What to do:
        //create maps and put them into List dataList
        //if someone has just one position,just set pos1 into ""
        //if the number of the items is odd, just set all the factors of the second one blank,the name of the picture is R.drawable.blank
        Map<String,Object> item = new HashMap<String, Object>();
        // for test
        item.put("hr1_pic",R.drawable.ic_launcher);
        item.put("hr1_name","Jack");
        item.put("hr1_pos1","");
        item.put("hr1_pos2","Chairman");
        item.put("hr2_pic",R.drawable.blank);
        item.put("hr2_name","");
        item.put("hr2_pos1","");
        item.put("hr2_pos2","");
        data_List.add(item);
        return data_List;
    }
}
