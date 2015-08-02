package sjtu.dclab.smartcity.ui.vote;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import sjtu.dclab.smartcity.R;
import sjtu.dclab.smartcity.ui.vote.vote;

/**
 * Created by 孤独的观测者 on 2015/7/30.
 */
public class vote_start extends Activity{
    private ImageButton rtnbutton;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.vote_start);

        rtnbutton = (ImageButton) findViewById(R.id.vote_start_rtn);
        rtnbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), vote.class));
            }
        });
    }
}
