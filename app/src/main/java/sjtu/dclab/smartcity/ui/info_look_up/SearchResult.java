package sjtu.dclab.smartcity.ui.info_look_up;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import sjtu.dclab.smartcity.R;
import sjtu.dclab.smartcity.model.Apartment;
import sjtu.dclab.smartcity.model.CitizenResident;
import sjtu.dclab.smartcity.tools.GsonTool;
import sjtu.dclab.smartcity.webservice.BasicWebService;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by theGODofws on 2015/8/12.
 */

public class SearchResult extends Activity {
    private final String TAG = "SearchResult";

    //Gson
    private BasicWebService bws;
    private GsonTool Gson;
    private String URLRoot;
    //ListView
    private ListView listView;
    private ArrayAdapter<String> adapter;
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
        URLRoot = getResources().getString(R.string.URLRoot);

        //intent
        Intent intent = getIntent();
        String mode = intent.getStringExtra("mode");

        //Gson
        Gson = new GsonTool();
        bws = new BasicWebService();
        if (mode.equals("Population")) {
            URLRoot = URLRoot + "citizen";
            condition1 = intent.getStringExtra("gender");
            condition2 = intent.getStringExtra("income");
            condition3 = intent.getStringExtra("career");
            condition4 = intent.getStringExtra("marriage");
            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    String json = bws.sendGetRequest(URLRoot, null);
                    List<CitizenResident> persons = Gson.getObjectList(json, CitizenResident[].class);
                    for (int i = 0; i < persons.size(); i++) {
                        if ((condition1.isEmpty() || persons.get(i).getGender().equals(condition1))
                                && (condition4.isEmpty() || persons.get(i).getMarriage_status().equals(condition4))
                                && (condition3.isEmpty() || persons.get(i).getEmployment_status().equals(condition3))
                                && (condition2.isEmpty() || persons.get(i).getIncome_status().equals(condition2))) {
                            result.add(persons.get(i).getName());
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
        } else {
            URLRoot = URLRoot + "apartment";
            condition1 = intent.getStringExtra("area");
            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    String json = bws.sendGetRequest(URLRoot, null);
                    List<Apartment> apartments = GsonTool.getObjectList(json, Apartment[].class);
                    //TODO:确定区域判定的标准，更改下面的代码

                    for (int i = 0; i < apartments.size(); i++) {
                        if (condition1.isEmpty()) {
                            result.add(apartments.get(i).getName());
                        } else if (condition1.equals("A1")) {
                            if (apartments.get(i).getId() < 400) {
                                result.add(apartments.get(i).getName());
                            }
                        } else if (apartments.get(i).getId() >= 400) {
                            result.add(apartments.get(i).getName());
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
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, result);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new ListView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                startActivity(new Intent(getApplicationContext(), SomethingMore.class));
            }
        });
    }
}
