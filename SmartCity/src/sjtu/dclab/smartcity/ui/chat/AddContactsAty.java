package sjtu.dclab.smartcity.ui.chat;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.*;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.json.JSONObject;
import sjtu.dclab.smartcity.R;
import sjtu.dclab.smartcity.community.config.Me;
import sjtu.dclab.smartcity.transfer.ApplicationTransfer;
import sjtu.dclab.smartcity.model.CitizenResident;
import sjtu.dclab.smartcity.tools.GsonTool;
import sjtu.dclab.smartcity.webservice.BasicWebService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * AddContactsAty
 *
 * @author Jian Yang
 * @date 2015/7/30
 */
public class AddContactsAty extends Activity {
    private static final String TAG = "AddContactsAty";

    private String curUserId;
    private final String URLROOT = "http://202.120.40.111:8080/community-server/rest/";
    private final String URL_BASE_REQUEST_FOR_FRIEND = URLROOT + "friends/";
    private final String URL_ALL_CITIZENS_IN_APARTMENT = URLROOT + "apartment/101/citizen"; //TODO 测试阶段

    private ListView lvRecommend, lvRequest;
    private ImageButton ibtnBack;
    private ArrayList<HashMap<String, Object>> itemsRecommend, itemsRequest;
    private List<ApplicationTransfer> friendApplications;
    private List<CitizenResident> citizens;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.aty_add_contacts);

        curUserId = Me.id + "";

        ibtnBack = (ImageButton) findViewById(R.id.ibtn_addcontacts_back);
        ibtnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();

        lvRequest = (ListView) findViewById(R.id.add_contacts_questList);
        itemsRequest = new ArrayList<HashMap<String, Object>>();
        //REST请求：好友请求列表
        //完整示例http://202.120.40.111:8080/community-server/rest/friends/3/applications
        String resultRequest = new BasicWebService().sendGetRequest(URL_BASE_REQUEST_FOR_FRIEND + curUserId + "/applications", null);
        if (resultRequest != null) {
            friendApplications = GsonTool.getFriendApplicationTransferList(resultRequest);
        }
        if (friendApplications != null && friendApplications.size() != 0) {
            for (ApplicationTransfer at : friendApplications) {
                HashMap<String, Object> map = new HashMap<String, Object>();
                map.put("name", at.getName());
                itemsRequest.add(map);
            }
        }
        if (itemsRequest != null && itemsRequest.size() != 0) {
            final SimpleAdapter adapter = new SimpleAdapter(
                    getApplication(), itemsRequest, R.layout.list_friend,
                    new String[]{"name"}, new int[]{R.id.list_friend_name});
            lvRequest.setAdapter(adapter);
            lvRequest.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    final ApplicationTransfer at = friendApplications.get(position);
                    AlertDialog.Builder builder = new AlertDialog.Builder(AddContactsAty.this);
                    builder.setMessage("提示");
                    builder.setMessage("确认添加" + at.getName() + "为好友么？");
                    builder.setPositiveButton("确认", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            //TODO 此处发送PUT请求接收好友请求
                            String url = URL_BASE_REQUEST_FOR_FRIEND + curUserId + "/applications/" + at.getApplicationId();
                            String res = new BasicWebService().sendPutRequest(url, null);
                            Log.i(TAG, res);
                            if (res.equals("success")) {
                                Toast.makeText(AddContactsAty.this, "添加成功", Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(AddContactsAty.this, "添加失败", Toast.LENGTH_SHORT).show();
                            }

                        }
                    });
                    builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.dismiss();
                        }
                    });
                    builder.show();
                }
            });
        }

        //************************************************************************
        //TODO 需要重写！！！
        lvRecommend = (ListView) findViewById(R.id.add_contacts_lv);
        itemsRecommend = new ArrayList<HashMap<String, Object>>();
        //REST请求：推荐好友列表
        String resultRecommend = new BasicWebService().sendGetRequest(URL_ALL_CITIZENS_IN_APARTMENT, null);
        if (resultRecommend != null) {
            citizens = new Gson().fromJson(resultRecommend, new TypeToken<List<CitizenResident>>() {
            }.getType());
        }
        if (citizens != null && citizens.size() != 0) {
            Log.i(TAG, citizens.toString());
            for (CitizenResident citizen : citizens) {
                HashMap<String, Object> map = new HashMap<String, Object>();
                map.put("name", citizen.getName());
                itemsRecommend.add(map);
            }
        }
        if (itemsRecommend != null && itemsRecommend.size() != 0) {
            final SimpleAdapter adapter = new SimpleAdapter(getApplication(), itemsRecommend, R.layout.list_friend,
                    new String[]{"name"}, new int[]{R.id.list_friend_name});
            lvRecommend.setAdapter(adapter);
            lvRecommend.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                    AlertDialog.Builder builder = new AlertDialog.Builder(AddContactsAty.this);
                    LayoutInflater factory = LayoutInflater.from(AddContactsAty.this);
                    final View textEntryView = factory.inflate(R.layout.dialog_friend_req, null);
                    builder.setTitle("请求xxx为联系人");
                    builder.setView(textEntryView);
                    builder.setPositiveButton("发送", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int whichButton) {
                                    EditText et_msg = (EditText) textEntryView.findViewById(R.id.et_msg_dialog_fiendreq);
                                    //发送请求到服务器
                                    String friendId = "18";//TODO 测试阶段
                                    String url = URL_BASE_REQUEST_FOR_FRIEND + curUserId + "/applications/" + friendId;
                                    JSONObject jsonObject = new JSONObject();
                                    try {
                                        jsonObject.put("message", et_msg.getText().toString());
                                        String res = new BasicWebService().sendPostRequestWithRawJson(url, jsonObject);
                                        if (res.equals("success")){
                                            Toast.makeText(getApplicationContext(),"请求已成功发送",Toast.LENGTH_SHORT).show();
                                        }
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                        Toast.makeText(getApplicationContext(),"请求失败",Toast.LENGTH_SHORT).show();
                                    }
                                }
                            }
                    );
                    builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int whichButton) {}
                            });
                    builder.create().show();
                }
            });
        }
    }
}