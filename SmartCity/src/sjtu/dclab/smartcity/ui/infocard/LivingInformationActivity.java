package sjtu.dclab.smartcity.ui.infocard;

import android.app.Activity;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.Map;

import sjtu.dclab.smartcity.R;
import sjtu.dclab.smartcity.community.config.Me;
import sjtu.dclab.smartcity.tools.GsonTool;
import sjtu.dclab.smartcity.tools.QRCodeTool;
import sjtu.dclab.smartcity.webservice.BasicWebService;

/**
 * Created by hp on 2015/7/24.
 */
public class LivingInformationActivity extends Activity {
    private static final String TAG = "LivingInformationActivit";

    private String curUserId;
    private final String URLROOT = "http://202.120.40.111:8080/community-server/rest/";
    private final String URL_BASE_REQUEST_FOR_NETINFO  = URLROOT + "infocard/livingcard/";

    private TextView text_areaname,text_address,text_mailcode,text_doorid;

    private String card_json;

    final int netMessage = 224;

    public Handler netHandler = new Handler() {
        public void handleMessage(android.os.Message msg){
            if (msg.what == netMessage){

                Map<String,Object> content = null;
                if ( card_json != null){
                    content = GsonTool.getMap(card_json);

                    text_areaname.setText((String)content.get("name"));
                    text_address.setText((String)content.get("address"));
                    text_mailcode.setText((String)content.get("zip_code"));
                    text_doorid.setText((String) content.get("house_number"));

                    //text_sex.setText(curUserId);
                }


            }
            super.handleMessage(msg);
        }
    };


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.living_information_table);

        curUserId = Me.id + "";
        text_areaname = (TextView) findViewById(R.id.info_livingcard_name);
        text_address = (TextView) findViewById(R.id.info_livingcard_address);
        text_mailcode = (TextView) findViewById(R.id.info_livingcard_mailcode);
        text_doorid = (TextView) findViewById(R.id.info_livingcard_doorid);

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {

                card_json = new BasicWebService().sendGetRequest(URL_BASE_REQUEST_FOR_NETINFO+curUserId, null);
                Log.i(TAG, card_json.substring(0,23));

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
