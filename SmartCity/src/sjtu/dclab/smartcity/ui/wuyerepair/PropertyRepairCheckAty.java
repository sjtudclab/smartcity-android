package sjtu.dclab.smartcity.ui.wuyerepair;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import sjtu.dclab.smartcity.R;

/**
 * Created by hp on 2015/7/28.
 */
public class PropertyRepairCheckAty extends Activity {

    private ListView listview;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.property_repair_check);

        listview = (ListView)findViewById(R.id.repair_check_list);
        listview.setAdapter(new ArrayAdapter<String>(this, R.layout.simple_listitem,getData()));
        //setContentView(listview);
    }

    private List<String> getData(){
        List<String> data = new ArrayList<String>();
        data.add("维修报单                              报修项目  人数    状态");
        data.add("200daas-4c82-90b5-2sad63    煤         2     已评价");
        data.add("1234567-4d23-q03d-123456    水         1     已申请");
        data.add("200abcd-1111-1111-111111    电         1     已评价");

        return data;
    }
}
