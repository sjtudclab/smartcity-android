package sjtu.dclab.smartcity.ui.info_look_up;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
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
    private String condition1;
    private String condition2;
    private String condition3;
    private String condition4;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_result);
        result = new ArrayList<String>();
        url = "http://202.120.40.111:8080/community-server/rest/";

        //intent
        Intent intent = getIntent();
        String mode = intent.getStringExtra("mode");
        System.out.print("mode="+mode);

        //Gson
        Gson = new GsonTool();
        bws = new BasicWebService();
        if (mode == "Population"){
            url = url+"citizen";
            condition1 = intent.getStringExtra("gender");
            condition2 = intent.getStringExtra("income");
            condition3 = intent.getStringExtra("career");
            condition4 = intent.getStringExtra("marriage");
            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    String json = bws.sendGetRequest(url,null);
                    List<CitizenResident>persons = Gson.getObjectList(json,CitizenResident.class);
                    for(CitizenResident p : persons){
                        if ((condition1 == "" || p.getGender() == condition1)
                                &&(condition4 == "" || p.getMarriage_status() == condition4)
                                &&(condition3 == "" || p.getEmployment_status() == condition3)
                                &&(condition2 == "" || p.getIncome_status() == condition2)){
                            result.add(p.getName());
                        }
                    }
                }
            });
            thread.start();
        }
        else {
            url = url+"apartment";
            condition1 = intent.getStringExtra("area");
            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    String json = bws.sendGetRequest(url,null);
                    List<Apartment>apartments = Gson.getObjectList(json, Apartment.class);
                    //TODO:确定区域判定的标准，更改下面的代码
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
            });
            thread.start();
        }

        listView = (ListView) findViewById(R.id.search_result);
        adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,result);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new ListView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                startActivity(new Intent(getApplicationContext(), SomethingMore.class));
            }
        });
    }
}
