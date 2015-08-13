package sjtu.dclab.smartcity.ui.info_look_up;

import com.google.gson.Gson;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.HashMap;

import sjtu.dclab.smartcity.R;
import sjtu.dclab.smartcity.webservice.BasicWebService;
import sjtu.dclab.smartcity.ui.info_look_up.Population;

/**
 * Created by theGODofws on 2015/8/12.
 */
public class SearchResult extends Activity{
    //Gson
    private BasicWebService bws;
    private Gson Gson;
    private String url;
    //ListView
    private ListView listView;
    private ArrayAdapter<String>adapter;
    private String[]result={};

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_result);

        url = "202.120.40.111:8080/community-server/rest/";

        //intent
        Intent intent = getIntent();
        String mode = intent.getStringExtra("mode");
        String condition1;
        String condition2;
        String condition3;
        String condition4;
        if (mode == "Population"){
            url = url+"citizen";
            condition1 = intent.getStringExtra("gender");
            condition2 = intent.getStringExtra("income");
            condition3 = intent.getStringExtra("career");
            condition4 = intent.getStringExtra("marriage");
        }
        else {
            url = url+"apartment";
        }

        //Gson
        Gson = new Gson();
        bws = new BasicWebService();
        Population person = Gson.fromJson(bws.sendGetRequest(url,new HashMap<String, String>()),Population.class);


        listView = (ListView) findViewById(R.id.search_result);
        adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,result);
        listView.setAdapter(adapter);
        listView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }
}
