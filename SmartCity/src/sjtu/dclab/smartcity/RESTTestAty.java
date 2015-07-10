package sjtu.dclab.smartcity;

import android.app.Activity;
import android.os.Bundle;
<<<<<<< HEAD

import sjtu.dclab.smartcity.tools.Constants;
=======
import android.view.View;
import android.widget.Button;

>>>>>>> b24116a7aa04cbf811e8f374582f4101f0c4df64
import sjtu.dclab.smartcity.tools.RESTTest;

/**RESTTestAty extends Activity
 * RESTTestAty
 *
 * @author Jian Yang
 * @date 2015/7/9
 */
public class RESTTestAty extends Activity {
<<<<<<< HEAD
    static final String TAG = "RESTTestAty";

=======
>>>>>>> b24116a7aa04cbf811e8f374582f4101f0c4df64
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.aty_main);

<<<<<<< HEAD
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
=======
        Button btn1 = (Button) findViewById(R.id.btn1);
        btn1.setVisibility(View.VISIBLE);
        btn1.setText("R");

        RESTTest.RequestForJson(getApplicationContext(),
                RESTTest.URL_REST_MAILBOX_BASE, "3", RESTTest.WAIT);
>>>>>>> b24116a7aa04cbf811e8f374582f4101f0c4df64

    }
}