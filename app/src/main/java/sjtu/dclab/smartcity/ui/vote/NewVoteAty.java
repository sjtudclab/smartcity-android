package sjtu.dclab.smartcity.ui.vote;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;
import sjtu.dclab.smartcity.R;
import sjtu.dclab.smartcity.community.config.Me;
import sjtu.dclab.smartcity.tools.IOTools;
import sjtu.dclab.smartcity.webservice.BasicWebService;

import java.io.File;
import java.io.InputStream;

/**
 * Created by 孤独的观测者 on 2015/7/30.
 */
public class NewVoteAty extends Activity {
    private ImageButton rtnbutton;
    private EditText vote_title;
    private EditText vote_content;
    private Button vote_btn;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.vote_start);

        rtnbutton = (ImageButton) findViewById(R.id.vote_start_rtn);
        rtnbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        vote_title = (EditText) findViewById(R.id.et_new_vote_title);
        vote_content = (EditText) findViewById(R.id.et_new_vote_content);
        vote_btn = (Button) findViewById(R.id.btn_new_vote);
        vote_btn.setOnClickListener(new VoteListener());
    }

    private class VoteListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            Context context = getApplicationContext();
            String title = vote_title.getText().toString();
            String content = vote_content.getText().toString();
            String url = getResources().getString(R.string.URLRoot) + "/topic";
            if (title.isEmpty() || content.isEmpty()) {
                Toast.makeText(context, "请填写投票内容", Toast.LENGTH_SHORT).show();
            } else {
                try{
                    //TODO add attachment
                    MultipartEntity args = new MultipartEntity();
                    args.addPart("title", new StringBody(title));
                    args.addPart("content", new StringBody(content));
                    args.addPart("topic_type_id", new StringBody("1"));
                    args.addPart("options", new StringBody("yes, no"));
                    args.addPart("from", new StringBody(Me.id+""));
                    InputStream is = getResources().getAssets().open("defaultpic.png");
                    File file = new File(getFilesDir()+"/defaultpic.png");
                    if (!file.exists()) file.createNewFile();
                    IOTools.inputStreamToFile(is, file);
                    args.addPart("file", new FileBody(file));
                    String res = new BasicWebService().sendPostRequestWithMultipartEntity(url, args, false);
                    if(res.equals("success")){
                        Toast.makeText(context, "发布成功", Toast.LENGTH_SHORT).show();
                        finish();
                    }
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
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
