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
    private Spinner spinner_gender;
    private List<String> list_gender;
    private ArrayAdapter<String> adapter_gender;
    //spinner income
    private Spinner spinner_income;
    private List<String>list_income;
    private ArrayAdapter<String>adapter_income;
    //spinner career
    private Spinner spinner_career;
    private List<String>list_career;
    private ArrayAdapter<String>adapter_career;
    //spinner marriage
    private Spinner spinner_marriage;
    private List<String>list_marriage;
    private ArrayAdapter<String>adapter_marriage;
    //Button Search
    private Button button_search;
    //String url
    private String gender_choice;
    private String income_choice;
    private String career_choice;
    private String marriage_choice;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.population_info);

        //spinner gender
        spinner_gender = (Spinner) findViewById(R.id.spinner);
        list_gender = new ArrayList<String>();
        //TODO:从服务器端获取数据库信息，并自动生成list来替换以下代码
        list_gender.add("性别");
        list_gender.add("男");
        list_gender.add("女");
        adapter_gender = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, list_gender);
        adapter_gender.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_gender.setAdapter(adapter_gender);
        spinner_gender.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (i != 0) {
                    gender_choice = adapter_gender.getItem(i);
                } else {
                    gender_choice = "";
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                gender_choice = "";
            }
        });

        //button search
        button_search = (Button) findViewById(R.id.button_search);
        button_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO:上传搜索条件，并获取搜索结果
                String url = "202.120.40.111:8080/community-server/information/search/" + "?" + gender_choice + "&" + income_choice + "&" + career_choice + "&" + marriage_choice;
                startActivity(new Intent(getApplicationContext(), SearchResult.class));
            }
        });
    }
}
