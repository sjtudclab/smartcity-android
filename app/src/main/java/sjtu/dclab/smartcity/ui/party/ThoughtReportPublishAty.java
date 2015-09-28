package sjtu.dclab.smartcity.ui.party;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import sjtu.dclab.smartcity.R;

/**
 * Created by 孤独的观测者 on 2015/8/1.
 */
public class ThoughtReportPublishAty extends Activity{
    private ImageButton rtnbutton;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.thought_report_publish);

        rtnbutton = (ImageButton) findViewById(R.id.thought_report_publish_rtn);
        rtnbutton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), ThoughtReportListAty.class));
            }
        });
    }
}
