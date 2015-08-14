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
 * Created by theGODofws on 2015/8/9.
 */
public class PopulationAty extends Activity{
    //spinner gender
    private Spinner spinner_gender;
    private List<String>list_gender;
    private ArrayAdapter<String>adapter_gender;
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
                }
                else {
                    gender_choice = "";
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                gender_choice = "";
            }
        });

        //spinner income
        spinner_income = (Spinner) findViewById(R.id.spinner2);
        list_income = new ArrayList<String>();
        //TODO:从服务器端获取数据库信息，并自动生成list来替换以下代码
        list_income.add("收入");
        list_income.add("退休");
        list_income.add("纳保");
        list_income.add("无业");
        list_income.add("低保");
        list_income.add("未知");
        adapter_income = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, list_income);
        adapter_income.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_income.setAdapter(adapter_income);
        spinner_income.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (i != 0) {
                    income_choice = adapter_income.getItem(i);
                }
                else {
                    income_choice = "";
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                income_choice = "";
            }
        });

        //spinner career
        spinner_career = (Spinner) findViewById(R.id.spinner3);
        list_career = new ArrayList<String>();
        //TODO:从服务器端获取数据库信息，并自动生成list来替换以下代码
        list_career.add("就业状态");
        list_career.add("就业");
        list_career.add("失业");
        list_career.add("无业");
        list_career.add("退休");
        list_career.add("务农");
        list_career.add("学生");
        list_career.add("学龄前儿童");
        list_career.add("未知");
        adapter_career = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, list_career);
        adapter_career.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_career.setAdapter(adapter_career);
        spinner_career.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (i != 0) {
                    career_choice = adapter_career.getItem(i);
                }
                else {
                    career_choice = "";
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                career_choice = "";
            }
        });

        //spinner marriage
        spinner_marriage = (Spinner) findViewById(R.id.spinner4);
        list_marriage = new ArrayList<String>();
        //TODO:从服务器端获取数据库信息，并自动生成list来替换以下代码
        list_marriage.add("婚姻状态");
        list_marriage.add("已婚");
        list_marriage.add("未婚");
        list_marriage.add("丧偶");
        list_marriage.add("离婚");
        list_marriage.add("未知");
        adapter_marriage = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, list_marriage);
        adapter_marriage.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_marriage.setAdapter(adapter_marriage);
        spinner_marriage.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (i != 0) {
                    marriage_choice = adapter_marriage.getItem(i);
                }
                else {
                    marriage_choice = "";
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                marriage_choice = "";
            }
        });

        //button search
        button_search = (Button) findViewById(R.id.button_search);
        button_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent();
                intent.setClass(PopulationAty.this, SearchResult.class);
                intent.putExtra("mode","Population");
                intent.putExtra("gender", gender_choice);
                intent.putExtra("career", career_choice);
                intent.putExtra("income", income_choice);
                intent.putExtra("marriage", marriage_choice);
                startActivity(intent);
            }
        });
    }
}