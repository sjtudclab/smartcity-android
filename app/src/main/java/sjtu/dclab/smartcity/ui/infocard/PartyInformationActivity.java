package sjtu.dclab.smartcity.ui.infocard;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.widget.TextView;
import sjtu.dclab.smartcity.R;
import sjtu.dclab.smartcity.community.config.Me;
import sjtu.dclab.smartcity.tools.GsonTool;
import sjtu.dclab.smartcity.webservice.BasicWebService;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

public class PartyInformationActivity extends Activity {
    private static final String TAG = "PartyInformationActivit";

    private String curUserId;
    private String URLRoot;
    private String URL_BASE_REQUEST_FOR_NETINFO;

    private TextView text_organazation,text_branch,text_job,text_kind,text_membership,text_joindate,text_confirmdate,text_inspector,text_bookid;

    private String card_json;

    final int netMessage = 225;

    public Handler netHandler = new Handler() {
        public void handleMessage(android.os.Message msg){
            if (msg.what == netMessage){

                Map<String,Object> content = null;
                if ( card_json != null){
                    content = GsonTool.getMap(card_json);

                    text_organazation.setText((String)content.get("relation"));
                    text_branch.setText((String)content.get("party_branch"));
                    text_job.setText((String)content.get("position"));
                    //to do:  �趨��Ա�����͵���״̬
                    int kind = ((Double)content.get("type")).intValue();
                    int membership = ((Double)content.get("status")).intValue();
                    //text_kind.setText((String)content.get("type"));
                    //text_membership.setText((String) content.get("status"));
                    text_inspector.setText((String)content.get("inspection_person"));
                    text_bookid.setText((String)content.get("application_id"));

                    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                    Date joindate = new Date(((Double)content.get("join_date")).longValue());
                    Date confirmdate = new Date(((Double)content.get("confirm_date")).longValue());

                    text_joindate.setText(formatter.format(joindate));
                    text_confirmdate.setText(formatter.format(confirmdate));


                    //to do case, add more strings in @string
                    switch (kind){
                        case 1:
                            text_kind.setText(R.string.party_infomation_kind_1);
                            break;
                        case 2:
                            text_kind.setText(R.string.party_infomation_kind_2);
                            break;
                        case 3:
                            text_kind.setText(R.string.party_infomation_kind_3);
                            break;
                    }

                    switch (membership){
                        case 1:
                            text_membership.setText(R.string.party_infomation_membership_1);
                            break;
                        case 2:
                            text_membership.setText(R.string.party_infomation_membership_2);
                            break;
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
        setContentView(R.layout.party_information_table);

        URLRoot = getResources().getString(R.string.URLRoot);
        URL_BASE_REQUEST_FOR_NETINFO  = URLRoot + "infocard/partycard/";

        curUserId = Me.id + "";
        text_organazation = (TextView) findViewById(R.id.info_partycard_organization);
        text_branch = (TextView) findViewById(R.id.info_partycard_branch);
        text_job = (TextView) findViewById(R.id.info_partycard_job);
        text_kind = (TextView) findViewById(R.id.info_partycard_kind);
        text_membership = (TextView) findViewById(R.id.info_partycard_membership);
        text_joindate = (TextView) findViewById(R.id.info_partycard_joindate);
        text_confirmdate = (TextView) findViewById(R.id.info_partycard_confirmationdate);
        text_inspector = (TextView) findViewById(R.id.info_partycard_inspection);
        text_bookid = (TextView) findViewById(R.id.info_partycard_bookid);

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {

                card_json = new BasicWebService().sendGetRequest(URL_BASE_REQUEST_FOR_NETINFO+curUserId, null);
                Log.i(TAG, card_json.substring(0, 23));

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
