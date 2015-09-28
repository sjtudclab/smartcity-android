package sjtu.dclab.smartcity.ui.minyisquare;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import sjtu.dclab.smartcity.R;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by HuangZhenyu on 15/7/27.
 */
public class MinyiAty extends Activity {
    private ListView cur_tie;
    private int[] icons;
    private String[] labels;
    private String[] titles;
    private String[] context;
    private ImageButton add;
    private ImageButton search;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.aty_minyi);

        icons = new int[] { R.drawable.minzhen,
                R.drawable.payment_record};
        labels = new String[] {"眉眼如初soar","眉眼如初soar"};
        titles = new String[] {"没错，感情贴","没错，感情贴"};
        context = new String[] {"故事有点长。有一个蓝孩子C追求楼主已经有一年多，期间表白两次均被拒。我和C原本在同一个城市，一年前他上大学去了外地，本来以为他会就此放弃，但事实"
                ,"故事有点长。有一个蓝孩子C追求楼主已经有一年多，期间表白两次均被拒。我和C原本在同一个城市，一年前他上大学去了外地，本来以为他会就此放弃，但事实"};


        cur_tie = (ListView) findViewById(R.id.listView);

        ArrayList<HashMap<String, Object>> serviceItems = new ArrayList<HashMap<String, Object>>();
        for (int i = 0; i < labels.length; i++) {
            HashMap<String, Object> map = new HashMap<String, Object>();
            map.put("itemIcon", icons[i]);
            map.put("itemLab", labels[i]);
            map.put("itemTit",titles[i]);
            map.put("itemCon",context[i]);

            serviceItems.add(map);
        }
        String[] from = new String[] { "itemIcon", "itemLab","itemTit","itemCon"};
        int[] to = new int[] { R.id.itemIcon, R.id.itemLab ,R.id.itemTit,R.id.itemCon};
        SimpleAdapter itemsAdapter = new SimpleAdapter(this, serviceItems,
                R.layout.minyi_item, from, to);
        cur_tie.setAdapter(itemsAdapter);

        add = (ImageButton) findViewById(R.id.add);
        search = (ImageButton) findViewById(R.id.search);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(MinyiAty.this,Minadd.class);
                startActivity(intent);
            }
        });
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(MinyiAty.this,Minsearch.class);
                startActivity(intent);
            }
        });
    }
}
