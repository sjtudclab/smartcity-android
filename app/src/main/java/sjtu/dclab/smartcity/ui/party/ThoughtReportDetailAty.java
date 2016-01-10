package sjtu.dclab.smartcity.ui.party;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageButton;

import sjtu.dclab.smartcity.R;

/**
 * Created by theGODofws on 2015/8/1.
 */
public class ThoughtReportDetailAty extends Activity{
    private ImageButton rtnbutton;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.thought_report_detail);

        rtnbutton = (ImageButton) findViewById(R.id.thought_report_detail_rtn);
        rtnbutton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), ThoughtReportListAty.class));
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
