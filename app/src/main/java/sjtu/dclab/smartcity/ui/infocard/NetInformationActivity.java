package sjtu.dclab.smartcity.ui.infocard;

import android.app.Activity;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import sjtu.dclab.smartcity.R;
import sjtu.dclab.smartcity.community.config.Me;
import sjtu.dclab.smartcity.tools.GsonTool;
import sjtu.dclab.smartcity.tools.QRCodeTool;
import sjtu.dclab.smartcity.webservice.BasicWebService;

import java.util.Map;

public class NetInformationActivity extends Activity {
    private static final String TAG = "NetInformationActivit";

    private String curUserId;
    private String URLRoot;
    private String URL_BASE_REQUEST_FOR_NETINFO;

    private ImageView image_avatar, image_qrcode;
    private TextView text_nickname, text_webid;

    private String card_json;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.net_infomation_table);

        URLRoot = getResources().getString(R.string.URLRoot);
        URL_BASE_REQUEST_FOR_NETINFO = URLRoot + "infocard/netcard/";

        curUserId = Me.id + "";
        image_avatar = (ImageView) findViewById(R.id.info_netcard_image);
        image_qrcode = (ImageView) findViewById(R.id.info_netcard_qrcode);
        text_nickname = (TextView) findViewById(R.id.info_netcard_nickname);
        text_webid = (TextView) findViewById(R.id.info_netcard_webid);
        ImageButton ibtnBack = (ImageButton) findViewById(R.id.ibtn_net_info_card_back);
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

            text_nickname.setText((String) content.get("nickname"));
            text_webid.setText(((Double) content.get("community_user_id")).intValue() + "");
            String qrcode_str = curUserId + "_" + Me.username;
            Bitmap QRNamecard = QRCodeTool.createQRBitmap(qrcode_str);
            image_qrcode.setImageBitmap(QRNamecard);
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
