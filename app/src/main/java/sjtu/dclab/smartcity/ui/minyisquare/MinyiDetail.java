package sjtu.dclab.smartcity.ui.minyisquare;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import sjtu.dclab.smartcity.R;

public class MinyiDetail extends Activity {
    private TextView tv_user;
    private TextView tv_title;
    private TextView tv_content;

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

    }
}
