package sjtu.dclab.smartcity.ui.info_look_up;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
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

        //Gson
        Gson = new GsonTool();
        bws = new BasicWebService();
        if (mode.equals("Population")){
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
                        if ((condition1.isEmpty() || p.getGender().equals(condition1))
                                &&(condition4.isEmpty() || p.getMarriage_status().equals(condition4))
                                &&(condition3.isEmpty() || p.getEmployment_status().equals(condition3))
                                &&(condition2.isEmpty() || p.getIncome_status().equals(condition2))){
                            result.add(p.getName());
                        }
                    }
                }
            });
            thread.start();
            try {
                thread.join();
            } catch  (InterruptedException e) {
                e.printStackTrace();
            }
        }
        else {
            url = url+"apartment";
            condition1 = intent.getStringExtra("area");
            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    String json = bws.sendGetRequest(url,null);
                    List<Apartment>apartments = null;
                    try {
                        apartments = Gson.fromJsonArray(json,Apartment.class);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    //TODO:确定区域判定的标准，更改下面的代码
                    for (Apartment a :apartments){
                        if (condition1.isEmpty()){
                            result.add(a.getName());
                        }
                        else if (condition1.equals("A1")){
                            if (a.getId() < 400){
                                result.add(a.getName());
                            }
                        }
                        else if (a.getId() >= 400){
                            result.add(a.getName());
                        }
                    }
                }
            });
            thread.start();
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
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
