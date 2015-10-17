package sjtu.dclab.smartcity.ui.minyisquare;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import sjtu.dclab.smartcity.R;
import sjtu.dclab.smartcity.community.config.Me;
import sjtu.dclab.smartcity.webservice.BasicWebService;

/**
 * Created by HuangZhenyu on 15/7/27.
 */
public class Minadd extends Activity {
    private static final String TAG = "Minadd";

    private String URLRoot = null;

    private ImageButton ibtn_back;
    private EditText etTitle, etContent;
    private Button btnPublish;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.aty_new_tiezi);

        URLRoot = getResources().getString(R.string.URLRoot);

        ibtn_back = (ImageButton) findViewById(R.id.ibtn_newpost_back);
        ibtn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        etTitle = (EditText) findViewById(R.id.et_new_post_title);
        etContent = (EditText) findViewById(R.id.et_new_post_content);
        btnPublish = (Button) findViewById(R.id.btn_new_post_publish);

        btnPublish.setOnClickListener(new PublishListener());
    }

    private class PublishListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            String title = etTitle.getText().toString();
            String content = etContent.getText().toString();

            JSONObject jsonObject = new JSONObject();
            try {
                jsonObject.put("communityId", 1);
                jsonObject.put("title", title);
                jsonObject.put("type", "normal");
                jsonObject.put("posterId", (int)Me.id);
                jsonObject.put("replyNums", 0);
                jsonObject.put("goodNums", 0);
                jsonObject.put("content", content);
            } catch (JSONException e) {
                e.printStackTrace();
            }

            String resp = new BasicWebService().sendPostRequestWithRawJson(URLRoot + "bbspost", jsonObject);
            if (resp.contains("success")) {
                Toast.makeText(getApplicationContext(), "发贴成功！", Toast.LENGTH_SHORT).show();
                finish();
            } else {
                Toast.makeText(getApplicationContext(), "发贴失败！", Toast.LENGTH_SHORT).show();
                Log.e(TAG, "状态码!=200");
            }
        }
    }
}
