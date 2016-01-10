package sjtu.dclab.smartcity.ui.party;

import android.app.Activity;
import android.os.Bundle;

import android.view.KeyEvent;
import sjtu.dclab.smartcity.R;

/**
 * OrgLifeDetailAty
 *
 * @author Jian Yang
 * @date 2015/7/23
 */
public class OrgLifeDetailAty extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.aty_party_orglife_detail);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            finish();
        }
        return true;
    }
}