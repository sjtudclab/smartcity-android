package sjtu.dclab.smartcity.ui.infocard;

import android.app.Activity;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.reflect.TypeToken;

import sjtu.dclab.smartcity.R;
import sjtu.dclab.smartcity.community.config.Me;
import sjtu.dclab.smartcity.tools.GsonTool;
import sjtu.dclab.smartcity.tools.QRCodeTool;
import sjtu.dclab.smartcity.webservice.BasicWebService;

import java.util.List;
import java.util.Map;

public class NameInformationActivity extends Activity {
    private static final String TAG = "NameInformationActivity";

    private String curUserId;
    private final String URLROOT = "http://202.120.40.111:8080/community-server/rest/";
    private final String URL_BASE_REQUEST_FOR_CITIZEN  = URLROOT + "citizen/";
    private ImageView image_avatar;
    private TextView text_name,text_sex,text_race;

    final int netMessage = 222;
    String citizen_content;


    public Handler netHandler = new Handler() {
       public void handleMessage(android.os.Message msg){
           if (msg.what == netMessage){

               List<Map<String,Object>> content = null;
               if ( citizen_content != null){
                   content = GsonTool.getListMaps(citizen_content);
                   if (content != null) {
                       for (Map<String, Object> people : content) {
                           int userid_int = ((Double) people.get("user")).intValue();

                           String userid = userid_int + "";
                           //text_race.setText(userid);
                           if (userid.equals(curUserId)) {
                               text_name.setText((String) people.get("name"));
                               text_sex.setText((String) people.get("gender"));
                               text_race.setText((String) people.get("nation"));

//                           //QRTool Test
//                           Bitmap bmap = QRCodeTool.createQRBitmap("Resident Evil");
//                           image_avatar.setImageBitmap(bmap);
//                           text_sex.setText(QRCodeTool.decodeQRBitmap(bmap));
                               break;
                           }
                       }
                   }
                   //text_sex.setText(curUserId);
               }


           }
           super.handleMessage(msg);
       }
    };

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.name_information_table);
        image_avatar = (ImageView) findViewById(R.id.info_namecard_image);
        text_name = (TextView) findViewById(R.id.info_namecard_name);
        text_sex = (TextView) findViewById(R.id.info_namecard_sex);
        text_race = (TextView) findViewById(R.id.info_namecard_race);
        curUserId = Me.id + "";

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {

                citizen_content = new BasicWebService().sendGetRequest(URL_BASE_REQUEST_FOR_CITIZEN, null);
                Log.i(TAG, citizen_content);

                android.os.Message msg = new android.os.Message();
                msg.what = netMessage;
                netHandler.sendMessage(msg);
            }
        });
        thread.start();
    }


    @Override
    protected void onStart(){
        super.onStart();
    }
}
