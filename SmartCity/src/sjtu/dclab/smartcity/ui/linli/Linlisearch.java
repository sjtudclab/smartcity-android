package sjtu.dclab.smartcity.ui.linli;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;

import sjtu.dclab.smartcity.R;

/**
 * Created by HuangZhenyu on 15/7/30.
 */
public class Linlisearch extends Activity {
    private ListView cur_tie;
    private int[] icons;
    private String[] time;
    private String[] titles;
    private String[] context;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.aty_search_linli);

        icons = new int[] { R.drawable.oriental_window,
                R.drawable.payment_record};
        time = new String[] {"19:00 2015-07-12","20:00 2015-07-12"};
        titles = new String[] {"5306","5312"};
        context = new String[] {"你可别闹"
                ,"我很烦，很烦很烦"};


        cur_tie = (ListView) findViewById(R.id.listView5);

        ArrayList<HashMap<String, Object>> serviceItems = new ArrayList<HashMap<String, Object>>();
        for (int i = 0; i < icons.length; i++) {
            HashMap<String, Object> map = new HashMap<String, Object>();
            map.put("itemIcon", icons[i]);
            map.put("itemTim", time[i]);
            map.put("itemTit",titles[i]);
            map.put("itemCon",context[i]);

            serviceItems.add(map);
        }
        String[] from = new String[] { "itemIcon", "itemTim","itemTit","itemCon"};
        int[] to = new int[] { R.id.itemIcon, R.id.itemTim ,R.id.itemTit,R.id.itemCon};
        SimpleAdapter itemsAdapter = new SimpleAdapter(this, serviceItems,
                R.layout.linyi_chat_item, from, to);
        cur_tie.setAdapter(itemsAdapter);
        cur_tie.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                startActivity(new Intent(getApplicationContext(), Linlichatdetail.class));


            }
        });



    }



}
