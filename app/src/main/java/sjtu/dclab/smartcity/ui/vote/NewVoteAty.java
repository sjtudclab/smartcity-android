package sjtu.dclab.smartcity.ui.vote;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import sjtu.dclab.smartcity.R;

/**
 * Created by 孤独的观测者 on 2015/7/30.
 */
public class NewVoteAty extends Activity{
    private ImageButton rtnbutton;

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
    }
}