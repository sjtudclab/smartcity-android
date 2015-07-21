package sjtu.dclab.smartcity.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

public class MainAty extends Activity {
    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.aty_main);

        //app入口，可以修改来测试自己的activity
        startActivity(new Intent(getApplicationContext(), HomeAty.class));
        finish();
    }
}
