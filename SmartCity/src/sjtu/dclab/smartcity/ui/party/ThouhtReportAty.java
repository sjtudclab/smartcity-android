package sjtu.dclab.smartcity.ui.party;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import sjtu.dclab.smartcity.R;
import sjtu.dclab.smartcity.ui.ann_committee.AnnPublishAty;

/**
 * Created by theGODofws on 2015/7/29.
 */
public class ThouhtReportAty extends Activity{
    private ImageButton rtnbutton;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pty_thought_report);

        rtnbutton = (ImageButton) findViewById(R.id.thought_report_rtn);
        rtnbutton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), SessionCheck.class));
            }
        });
    }
}