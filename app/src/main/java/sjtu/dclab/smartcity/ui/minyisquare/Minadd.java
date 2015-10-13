package sjtu.dclab.smartcity.ui.minyisquare;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import sjtu.dclab.smartcity.R;

/**
 * Created by HuangZhenyu on 15/7/27.
 */
public class Minadd extends Activity {

    ImageButton ibtn_back;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.aty_new_tiezi);

        ibtn_back = (ImageButton) findViewById(R.id.ibtn_newpost_back);
        ibtn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
