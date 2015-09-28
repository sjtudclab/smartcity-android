package sjtu.dclab.smartcity.ui.info_look_up;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

import sjtu.dclab.smartcity.R;

/**
 * Created by theGODofws on 2015/8/13.
 */
public class HouseAty extends Activity {
    //spinner gender
    private Spinner spinner_area;
    private List<String> list_area;
    private ArrayAdapter<String> adapter_area;
    //Button Search
    private Button button_search;
    private String area_choice;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.house_info);

        //spinner gender
        spinner_area = (Spinner) findViewById(R.id.spinner);
        list_area = new ArrayList<String>();
        //TODO:从服务器端获取数据库信息，并自动生成list来替换以下代码
        list_area.add("区域");
        list_area.add("A1");
        list_area.add("B1");
        adapter_area = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, list_area);
        adapter_area.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_area.setAdapter(adapter_area);
        spinner_area.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (i != 0) {
                    area_choice = adapter_area.getItem(i);
                } else {
                    area_choice = "";
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                area_choice = "";
            }
        });

        //button search
        button_search = (Button) findViewById(R.id.button_search);
        button_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent();
                intent.setClass(HouseAty.this, SearchResult.class);
                intent.putExtra("mode","House");
                intent.putExtra("area", area_choice);
                startActivity(intent);
            }
        });
    }
}
