package sjtu.dclab.smartcity.ui.minyisquare;

import android.app.Activity;
import android.os.Bundle;

import android.view.View;
import android.widget.ImageButton;
import sjtu.dclab.smartcity.R;

/**
 * Created by HuangZhenyu on 15/7/27.
 */
public class Minsearch extends Activity {

    private ImageButton ibtn;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.aty_search_tiezi);

        ibtn = (ImageButton) findViewById(R.id.search_rtn);
        ibtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}
