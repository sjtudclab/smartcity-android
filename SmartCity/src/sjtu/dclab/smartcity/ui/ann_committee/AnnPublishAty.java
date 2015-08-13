package sjtu.dclab.smartcity.ui.ann_committee;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import sjtu.dclab.smartcity.R;
import sjtu.dclab.smartcity.ui.party.ThoughtReportListAty;

/**
 * AnnPublishAty
 *
 * @author Jian Yang
 * @date 2015/7/23
 */
public class AnnPublishAty extends Activity {
    private ImageButton rtnbutton;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.aty_ann_pubish);

        rtnbutton = (ImageButton) findViewById(R.id.ann_publish_rtn);
        rtnbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), AnnouncementAty.class));
            }
        });
    }
}