package sjtu.dclab.smartcity.ui.infocard;

import android.app.Activity;
import android.media.Image;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

import sjtu.dclab.smartcity.R;
import sjtu.dclab.smartcity.community.config.Me;

public class NetInformationActivity extends Activity {
    private static final String TAG = "NameInformationActivit";

    private String curUserId;
    private final String URLROOT = "http://202.120.40.111:8080/community-server/rest/";

    private ImageView image_avatar,image_qrcode;
    private TextView text_nickname,text_webid;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.net_infomation_table);

        curUserId = Me.id + "";
        image_avatar = (ImageView) findViewById(R.id.info_netcard_image);
        image_qrcode = (ImageView) findViewById(R.id.info_netcard_qrcode);
        text_nickname = (TextView) findViewById(R.id.info_netcard_nickname);
        text_webid = (TextView) findViewById(R.id.info_netcard_webid);

    }
}
