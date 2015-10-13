package sjtu.dclab.smartcity.ui.minyisquare;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import sjtu.dclab.smartcity.R;
import sjtu.dclab.smartcity.tools.GsonTool;
import sjtu.dclab.smartcity.transfer.GoodTransfer;
import sjtu.dclab.smartcity.transfer.PostTransfer;
import sjtu.dclab.smartcity.webservice.BasicWebService;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by HuangZhenyu on 15/7/27.
 */
public class MinyiAty extends Activity {
    private static final String TAG = "MinyiAty";

    private String URLROOT = null;

    private ListView cur_tie;
    private int[] icons;
    private String[] labels;
    private String[] titles;
    private String[] context;
    private ImageButton add;
    private ImageButton search;

    private ArrayList<HashMap<String, Object>> items;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.aty_minyi);

        URLROOT = getResources().getString(R.string.URLRoot);

        cur_tie = (ListView) findViewById(R.id.listView);

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
                intent.setClass(MinyiAty.this, Minsearch.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        loadPostList();
    }

    private void loadPostList() {
        //REST请求：获取最新的帖子列表
        //完整示例http://202.120.40.111:8080/community-server/rest/bbspost
        String resultRequest = new BasicWebService().sendGetRequest(URLROOT + "bbspost", null);
        List<PostTransfer> posts = null;
        items = new ArrayList<HashMap<String, Object>>();
        try {
            if (resultRequest != null) {
                resultRequest = URLDecoder.decode(resultRequest, "utf-8");
                posts = GsonTool.getObjectList(resultRequest, PostTransfer[].class);
                if (posts != null && posts.size() != 0) {
                    for (PostTransfer post : posts) {
                        HashMap<String, Object> map = new HashMap<String, Object>();
                        map.put("itemIcon", R.drawable.ic_launcher0);//TODO 默认图片
                        map.put("itemUser", post.getPosterid());//TODO 需要替换为用户名
                        map.put("itemTit",post.getTitle());
                        map.put("itemCon",post.getContent());
//                        map.put("type",post.getType());
//                        map.put("id",post.getId());
//                        map.put("communityId",post.getCommunityId());
//                        map.put("goodNums",post.getGoodNums());
//                        map.put("replyNums",post.getReplyNums());
                        items.add(map);
                    }
                }
                if (items != null && items.size() != 0) {
                    final SimpleAdapter adapter = new SimpleAdapter(
                            getApplication(), items, R.layout.minyi_item,
                            new String[] { "itemIcon", "itemUser","itemTit","itemCon"},
                            new int[] { R.id.itemIcon, R.id.tv_minyi_itemUser ,R.id.itemTit,R.id.itemCon});
                    cur_tie.setAdapter(adapter);
//                    cur_tie.setOnItemClickListener(new ItemClickedListener());//TODO 单击跳转到帖子详情
                    adapter.notifyDataSetChanged();
                }
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

}
