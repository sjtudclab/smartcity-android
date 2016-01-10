package sjtu.dclab.smartcity.ui.minyisquare;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;
import org.json.JSONObject;
import sjtu.dclab.smartcity.R;
import sjtu.dclab.smartcity.community.config.Me;
import sjtu.dclab.smartcity.webservice.BasicWebService;

public class SecondaryReplyAty extends Activity {
    private ImageButton ibtnBack;
    private EditText edContent;
    private Button btnSend;

    private String post_id;
    private String reply_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reply_aty);

        post_id = getIntent().getExtras().get("post_id").toString();
        reply_id = getIntent().getExtras().get("reply_id").toString();

        ibtnBack = (ImageButton) findViewById(R.id.ibtn_reply_back);
        edContent = (EditText) findViewById(R.id.et_new_secondary_reply);
        btnSend = (Button) findViewById(R.id.btn_secondary_reply_send);
        ibtnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        btnSend.setOnClickListener(new View.OnClickListener() {
            Context context = getApplicationContext();

            @Override
            public void onClick(View view) {
                String url = getResources().getString(R.string.URLRoot) + "bbspost/reply/" + post_id + "/" + reply_id;
                String content = edContent.getText().toString();
                if (content.isEmpty()) {
                    Toast.makeText(context, "内容不能为空", Toast.LENGTH_SHORT).show();
                } else {
                    try {
                        JSONObject jo = new JSONObject();
                        jo.put("bbs_reply_time", System.currentTimeMillis());
                        jo.put("bbs_replier_id", Me.id);
                        jo.put("bbs_reply_content", content);
                        String res = new BasicWebService().sendPostRequestWithRawJson(url, jo);
                        if (res.equals("success")) {
                            Toast.makeText(context, "发送成功", Toast.LENGTH_SHORT).show();
                            finish();
                        } else {
                            Toast.makeText(context, "发送失败", Toast.LENGTH_SHORT).show();
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            finish();
        }
        return true;
    }
}
