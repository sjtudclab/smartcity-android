package sjtu.dclab.smartcity.ui.minzheng;

import android.app.Activity;
import android.os.Bundle;

import android.view.KeyEvent;
import sjtu.dclab.smartcity.R;

/**
 * Created by hp on 2015/7/30.
 */
public class CivilAffairEditAty extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.civil_report);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            finish();
        }
        return true;
    }
}
