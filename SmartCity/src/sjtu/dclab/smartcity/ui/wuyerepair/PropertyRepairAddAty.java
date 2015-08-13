package sjtu.dclab.smartcity.ui.wuyerepair;

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;

import sjtu.dclab.smartcity.R;

/**
 * Created by hp on 2015/7/29.
 */
public class PropertyRepairAddAty extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.property_repair_add);
    }
}
