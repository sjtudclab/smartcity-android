package sjtu.dclab.smartcity.ui.human_resource_info;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import sjtu.dclab.smartcity.R;

/**
 * Created by theGODofws on 2015/7/30.
 */
public class hr_changed extends Activity{
    private ImageButton rtnbutton;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.hr_change);

        rtnbutton = (ImageButton) findViewById(R.id.hr_change_rtn);
        rtnbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), hr_info.class));
            }
        });
    }
    //TODO:checkbox事件以及确认按钮事件的增加
}
