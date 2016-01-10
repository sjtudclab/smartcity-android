package sjtu.dclab.smartcity.ui.yuqing;

import android.app.Activity;
import android.os.Bundle;

import android.view.KeyEvent;
import sjtu.dclab.smartcity.R;

/**
 * Created by HuangZhenyu on 15/7/29.
 */
public class Yqdetail extends Activity {
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.yq_tiezi_detail);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            finish();
        }
        return true;
    }
}
