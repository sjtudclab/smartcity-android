package sjtu.dclab.smartcity.ui.minyisquare;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import org.json.JSONObject;
import sjtu.dclab.smartcity.R;
import sjtu.dclab.smartcity.community.config.Me;
import sjtu.dclab.smartcity.webservice.BasicWebService;

public class MinyiDetail extends Activity {
    private TextView tv_user;
    private TextView tv_title;
    private TextView tv_content;
    private int post_id;

    private EditText ed_content;
    private Button btn_send;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_minyi_detail);

        Intent intent = getIntent();

        tv_user = (TextView) findViewById(R.id.tv_minyi_itemUser);
        tv_user.setText("这里是用户民");
        tv_title = (TextView) findViewById(R.id.itemTit);
        tv_title.setText(intent.getExtras().get("title").toString());
        tv_content = (TextView) findViewById(R.id.itemCon);
        tv_content.setText(intent.getExtras().get("content").toString());
        post_id = Integer.parseInt(intent.getExtras().get("id").toString());

        ed_content = (EditText) findViewById(R.id.et_reply_content);
        btn_send = (Button) findViewById(R.id.btn_reply_send);
        btn_send.setOnClickListener(new ReplyBtnListener());
    }

    protected void init(){

    }

    class ReplyBtnListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            String content = ed_content.getText().toString();
            Context context = getApplicationContext();
            if (!content.isEmpty()) {
                try {
                    String url = getResources().getString(R.string.URLRoot) + "bbspost/reply/" + post_id;
                    JSONObject jb = new JSONObject();
                    jb.put("bbs_post_id", post_id);
                    jb.put("bbs_replier_id", Me.id);
                    jb.put("bbs_reply_content", content);
                    jb.put("bbs_another_reply_id", 1);
                    jb.put("bbs_reply_time", "1444621089260");
                    String res = new BasicWebService().sendPostRequestWithRawJson(url, jb);
                    if (res.equals("success")) {
                        Toast.makeText(context, "发送成功", Toast.LENGTH_SHORT).show();
                        ed_content.setText("");
                    } else {
                        Toast.makeText(context, "发送失败", Toast.LENGTH_SHORT).show();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    Toast.makeText(context, "发送失败", Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(context, "内容不能为空", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
