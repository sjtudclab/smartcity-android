package sjtu.dclab.smartcity.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import sjtu.dclab.smartcity.R;

/**
 * LoginIdentityCommitteeAty
 *
 * @author Jian Yang
 * @date 2015/7/23
 */
public class LoginIdentityCommitteeAty extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.aty_login_identity);

    }

    /**Button单击事件处理函数
     * @param v
     */
    public void onClickProcess(View v){
        if(v.getId() == R.id.identity_btn_partybuilding){
            startActivity(new Intent(this, CommitteeHomeAty.class));
            finish();
        }
    }
}