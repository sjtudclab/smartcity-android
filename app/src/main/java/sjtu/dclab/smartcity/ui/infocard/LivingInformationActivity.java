package sjtu.dclab.smartcity.ui.infocard;

import android.app.Activity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import sjtu.dclab.smartcity.R;
import sjtu.dclab.smartcity.community.config.Me;
import sjtu.dclab.smartcity.tools.GsonTool;
import sjtu.dclab.smartcity.webservice.BasicWebService;

import java.util.Map;

/**
 * Created by hp on 2015/7/24.
 */
public class LivingInformationActivity extends Activity {
    private static final String TAG = "LivingInformationActivit";

    private String curUserId;
    private String URLRoot;
    private String URL_BASE_REQUEST_FOR_NETINFO;
    private TextView text_areaname, text_address, text_mailcode, text_doorid;
    private String card_json;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.living_information_table);

        URLRoot = getResources().getString(R.string.URLRoot);
        URL_BASE_REQUEST_FOR_NETINFO = URLRoot + "infocard/livingcard/";

        curUserId = Me.id + "";
        text_areaname = (TextView) findViewById(R.id.info_livingcard_name);
        text_address = (TextView) findViewById(R.id.info_livingcard_address);
        text_mailcode = (TextView) findViewById(R.id.info_livingcard_mailcode);
        text_doorid = (TextView) findViewById(R.id.info_livingcard_doorid);
        ImageButton ibtnBack = (ImageButton) findViewById(R.id.ibtn_living_info_card_back);
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
        updateData();
    }

    public void updateData() {
        card_json = new BasicWebService().sendGetRequest(URL_BASE_REQUEST_FOR_NETINFO + curUserId, null);
        Map<String, Object> content = null;
        if (card_json != null) {
            content = GsonTool.getMap(card_json);
            text_areaname.setText((String) content.get("name"));
            text_address.setText((String) content.get("address"));
            text_mailcode.setText((String) content.get("zip_code"));
            text_doorid.setText((String) content.get("house_number"));
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            finish();
        }
        return true;
    }
}
