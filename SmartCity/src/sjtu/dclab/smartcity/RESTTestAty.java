package sjtu.dclab.smartcity;

import android.app.Activity;
import android.os.Bundle;

import sjtu.dclab.smartcity.tools.Constants;
import sjtu.dclab.smartcity.tools.RESTTest;

/**RESTTestAty extends Activity
 * RESTTestAty
 *
 * @author Jian Yang
 * @date 2015/7/9
 */
public class RESTTestAty extends Activity {
    static final String TAG = "RESTTestAty";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.aty_main);

//        Button btn1 = (Button) findViewById(R.id.btn1);
//        btn1.setVisibility(View.VISIBLE);
//        btn1.setText("");

//        RESTTest.RequestForJson(getApplicationContext(),
//                Constants.URL_REST_MAILBOX_BASE, "3", Constants.WAIT);
        RESTTest.RequestForJson(getApplicationContext(),
                Constants.URL_REST_MAILBOX_BASE, "3", Constants.DONE);
//        RESTTest.RequestForJson(getApplicationContext(),
//                Constants.URL_REST_MAILBOX_BASE, "3", Constants.DISCUSS);
//        RESTTest.RequestForJson(getApplicationContext(),
//                Constants.URL_REST_MAILBOX_BASE, "3", Constants.TRANSFER);

    }
}