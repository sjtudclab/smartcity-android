package sjtu.dclab.smartcity.ui.bianminservice;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import sjtu.dclab.smartcity.R;
import sjtu.dclab.smartcity.tools.GsonTool;
import sjtu.dclab.smartcity.transfer.GoodTransfer;
import sjtu.dclab.smartcity.webservice.BasicWebService;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

/**
 * Created by HuangZhenyu on 15/7/27.
 */
public class BianminAty extends Activity {
    private static final String TAG = "BianminAty";

    private String URLROOT = null;

    private ListView cur_tie;
    private int[] icons;
    private ImageButton add;
    private ImageButton search;

    private ArrayList<HashMap<String, Object>> items;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.aty_bianmin);

        URLROOT = getResources().getString(R.string.URLRoot);

        cur_tie = (ListView) findViewById(R.id.listView2);

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

    @Override
    protected void onStart() {
        super.onStart();
        loadGoodsList();
    }

    private void loadGoodsList() {
        //REST请求：获取最新的公共物品信息
        //完整示例http://202.120.40.111:8080/community-server/rest/SecondGoods/latest
        String resultRequest = new BasicWebService().sendGetRequest(URLROOT + "SecondGoods/latest", null);
        List<GoodTransfer> goods = null;
        items = new ArrayList<HashMap<String, Object>>();
        try {
            if (resultRequest != null) {
                resultRequest = URLDecoder.decode(resultRequest, "utf-8");
                goods = GsonTool.getObjectList(resultRequest, GoodTransfer[].class);
                Collections.reverse(goods);
                if (goods != null && goods.size() != 0) {
                    for (GoodTransfer good : goods) {
                        HashMap<String, Object> map = new HashMap<String, Object>();
                        map.put("icon", R.drawable.ic_launcher0);//TODO
                        map.put("title", good.getTitle());
                        map.put("category", "类别：" + good.getCategory());
//                        map.put("type", good.getType());
//                        map.put("id", good.getId());
//                        map.put("desc", good.getDesc());
                        items.add(map);
                    }
                }
                if (items != null && items.size() != 0) {
                    final SimpleAdapter adapter = new SimpleAdapter(
                            getApplication(), items, R.layout.bianmin_item,
                            new String[] { "icon", "title", "category"},
                            new int[] { R.id.itemIcon, R.id.itemLab ,R.id.itemPhone});
                    cur_tie.setAdapter(adapter);
//                    cur_tie.setOnItemClickListener(new ItemClickedListener());//TODO
                    adapter.notifyDataSetChanged();
                }
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

}
