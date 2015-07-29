package sjtu.dclab.smartcity.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import sjtu.dclab.smartcity.R;

/**
 * Created by hp on 2015/7/30.
 */
public class CivilAffairHomeAty extends Activity implements View.OnClickListener {
        private ListView listView;
        private ImageButton ib1,ib2;

        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.civil_affair_home);

            ib1 = (ImageButton)findViewById(R.id.civil_left);
            ib1.setOnClickListener(this);

            ib2 = (ImageButton)findViewById(R.id.civil_right);
            this.ib2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent();
                    intent.setClass(CivilAffairHomeAty.this, CivilAffairEditAty.class);
                    startActivity(intent);
                }
            });

            listView = (ListView)findViewById(R.id.civil_affair_list);
            SimpleAdapter adapter = new SimpleAdapter(this,getData(),R.layout.image_listitem,
                    new String[]{"title","info","img"},
                    new int[]{R.id.title,R.id.info,R.id.img});
            listView.setAdapter(adapter);

        }

        private List<Map<String, Object>> getData(){
            List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();

            Map<String, Object> map = new HashMap<String, Object>();
            map.put("title", "高龄服务");
            map.put("info", "example info...");
            map.put("img", R.drawable.tmp_img);
            list.add(map);

            map = new HashMap<String, Object>();
            map.put("title", "助残服务");
            map.put("info", "example info...");
            map.put("img", R.drawable.tmp_img);
            list.add(map);

            return list;
        }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {


            case R.id.civil_left:
                CivilMenuWindow menuPopupWindow = new CivilMenuWindow(CivilAffairHomeAty.this);
                menuPopupWindow.showPopupWindow(ib1);
                break;
            default:
                break;
        }
    }
}
