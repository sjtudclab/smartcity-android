package sjtu.dclab.smartcity.ui.bianminservice;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;

import sjtu.dclab.smartcity.R;

/**
 * Created by HuangZhenyu on 15/7/27.
 */
public class BianminAty extends Activity {
    private ListView cur_tie;
    private int[] icons;
    private String[] labels;
    private String[] titles;
    private ImageButton add;
    private ImageButton search;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.aty_bianmin);

        icons = new int[] { R.drawable.oriental_window,
                R.drawable.payment_record};
        labels = new String[] {"玩具熊","自行车"};
        titles = new String[] {"电话:131112345678","电话:11113456789"};


        cur_tie = (ListView) findViewById(R.id.listView2);

        ArrayList<HashMap<String, Object>> serviceItems = new ArrayList<HashMap<String, Object>>();
        for (int i = 0; i < labels.length; i++) {
            HashMap<String, Object> map = new HashMap<String, Object>();
            map.put("itemIcon", icons[i]);
            map.put("itemLab", labels[i]);
            map.put("itemPhone",titles[i]);
            serviceItems.add(map);
        }
        String[] from = new String[] { "itemIcon", "itemLab","itemPhone"};
        int[] to = new int[] { R.id.itemIcon, R.id.itemLab ,R.id.itemPhone};
        SimpleAdapter itemsAdapter = new SimpleAdapter(this, serviceItems,
                R.layout.bianmin_item, from, to);
        cur_tie.setAdapter(itemsAdapter);

        add = (ImageButton) findViewById(R.id.add);
        search = (ImageButton) findViewById(R.id.search);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(BianminAty.this,Bianadd.class);
                startActivity(intent);
            }
        });
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(BianminAty.this,Biansearch.class);
                startActivity(intent);
            }
        });
    }
}
