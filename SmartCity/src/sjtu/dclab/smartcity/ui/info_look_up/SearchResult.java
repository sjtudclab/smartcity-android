package sjtu.dclab.smartcity.ui.info_look_up;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import sjtu.dclab.smartcity.R;
import sjtu.dclab.smartcity.model.Apartment;
import sjtu.dclab.smartcity.model.CitizenResident;
import sjtu.dclab.smartcity.tools.GsonTool;
import sjtu.dclab.smartcity.webservice.BasicWebService;

/**
 * Created by theGODofws on 2015/8/12.
 */
public class SearchResult extends Activity{
    //Gson
    private BasicWebService bws;
    private GsonTool Gson;
    private String url;
    //ListView
    private ListView listView;
    private ArrayAdapter<String>adapter;
    private ArrayList<String> result;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_result);
        result = new ArrayList<String>();
        url = "202.120.40.111:8080/community-server/rest/";

        //intent
        Intent intent = getIntent();
        String mode = intent.getStringExtra("mode");
        String condition1;
        String condition2;
        String condition3;
        String condition4;

        //Gson
        Gson = new GsonTool();
        bws = new BasicWebService();
        if (mode == "Population"){
            url = url+"citizen";
            condition1 = intent.getStringExtra("gender");
            condition2 = intent.getStringExtra("income");
            condition3 = intent.getStringExtra("career");
            condition4 = intent.getStringExtra("marriage");
            List<CitizenResident>persons = Gson.getObjectList(bws.sendGetRequest(url,new HashMap<String, String>()),CitizenResident.class);
            for(CitizenResident p : persons){
                if ((condition1 == "" || p.getGender() == condition1)
                        &&(condition4 == "" || p.getMarriage_status() == condition4)
                        &&(condition3 == "" || p.getEmployment_status() == condition3)
                        &&(condition2 == "" || p.getIncome_status() == condition2)){
                    //TODO:加入列表，传递至下个页面，提供详细信息
                    result.add(p.getName());
                }
            }
        }
        else {
            url = url+"apartment";
            condition1 = intent.getStringExtra("area");
            List<Apartment>apartments = Gson.getObjectList(bws.sendGetRequest(url,new HashMap<String, String>()),Apartment.class);
            //TODO:确认房屋信息划分区域的标准，并修改此处判定的条件
            for (Apartment a :apartments){
                if (condition1 == ""){
                    result.add(a.getName());
                }
                else if (condition1 == "A1"){
                    if (Integer.parseInt(a.getArea()) < 80){
                        result.add(a.getName());
                    }
                }
                else if (Integer.parseInt(a.getArea()) >= 80){
                    result.add(a.getName());
                }
            }
        }

        listView = (ListView) findViewById(R.id.search_result);
        adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,result);
        listView.setAdapter(adapter);
        listView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //TODO:待添加详细信息页面，点击条目可查看详细信息
            }
        });
    }
}
