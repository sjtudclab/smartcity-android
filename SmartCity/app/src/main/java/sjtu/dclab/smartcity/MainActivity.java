package sjtu.dclab.smartcity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

public class MainActivity extends Activity {
    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.dangjian_menu);
        startActivity(new Intent(getApplicationContext(),MenuAct.class));
        finish();
    }
}
