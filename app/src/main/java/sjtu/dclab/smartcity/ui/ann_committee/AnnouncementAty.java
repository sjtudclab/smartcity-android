package sjtu.dclab.smartcity.ui.ann_committee;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import sjtu.dclab.smartcity.GlobalApp;
import sjtu.dclab.smartcity.R;
import sjtu.dclab.smartcity.tools.GsonTool;
import sjtu.dclab.smartcity.transfer.InformationTransfer;
import sjtu.dclab.smartcity.webservice.BasicWebService;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class AnnouncementAty extends Activity {
    private String TAG = "AnnouncementAty";

    private ListView lvAnn;
    private ImageButton ibtnAdd;
    private ImageButton ibtnRtn;

    private GlobalApp globalApp;


    private List<HashMap<String, Object>> itemList;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.aty_ann_man);

        globalApp = (GlobalApp) getApplication();

        lvAnn = (ListView) findViewById(R.id.lv_annlist);
        ibtnRtn = (ImageButton) findViewById(R.id.btn_ann_rtn);
        ibtnAdd = (ImageButton) findViewById(R.id.btn_ann_add);
    }

    @Override
    protected void onStart() {
        super.onStart();
        updateData();
    }

    public void updateData() {
        String url = getResources().getString(R.string.URLRoot) + getResources().getString(R.string.URLAnnouncement);
        List<InformationTransfer> infoList = null;
        String resp = new BasicWebService().sendGetRequest(url, null);
        infoList = GsonTool.getObjectList(resp, InformationTransfer[].class);
        itemList = new ArrayList<HashMap<String, Object>>();
        //TODO 时间转换格式不对
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyMMdd");
//        ParsePosition pos = new ParsePosition(8);
        for (InformationTransfer info : infoList) {
            HashMap<String, Object> item = new HashMap<String, Object>();
            String title = info.getTitle();
            String content = info.getContent();
            String pic = info.getAttachment();
            String dateTime = "";
            try {
                dateTime = dateFormat.parse(info.getSubmitTime()).toString();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                dateTime = "发布时间：" + dateTime;
            }

            item.put("ann_title", title);
            item.put("ann_date", dateTime);
            item.put("ann_content", content);
            item.put("ann_pic", pic);
            itemList.add(item);
        }

        SimpleAdapter adapter = new SimpleAdapter(
                getApplicationContext(), itemList, R.layout.announcement_item,
                new String[]{"ann_title", "ann_date", "ann_content", "ann_pic"},
                new int[]{R.id.tv_ann_title, R.id.tv_ann_time, R.id.tv_ann_content, R.id.iv_ann_pic}
        );
        lvAnn.setAdapter(adapter);


        if (!globalApp.getStatus().equals(getString(R.string.Resident))) {
            ibtnAdd.setVisibility(View.VISIBLE);
        }

        ibtnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), AnnPublishAty.class));
            }
        });

        ibtnRtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}