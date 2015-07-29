package sjtu.dclab.smartcity.ui.login;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import android.widget.Button;
import sjtu.dclab.smartcity.R;
import sjtu.dclab.smartcity.ui.HomeAty;

/**
 * LoginIdentityCommitteeAty
 *
 * @author Jian Yang
 * @date 2015/7/23
 */
public class LoginIdentityCommitteeAty extends Activity {
    private Button juweihui;
    private Button yeweihui;
    private Button dangjian;
    private Button jumin;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.aty_login_identity);
    }

    @Override
    protected void onStart() {
        super.onStart();
        juweihui = (Button)findViewById(R.id.identity_btn_juweihui);
        juweihui.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), HomeAty.class));
            }
        });

        yeweihui = (Button)findViewById(R.id.identity_btn_yeweihui);
        yeweihui.setOnClickListener(null);

        dangjian = (Button)findViewById(R.id.identity_btn_partybuilding);
        dangjian.setOnClickListener(null);

        jumin = (Button)findViewById(R.id.identity_btn_jumin);
        jumin.setOnClickListener(null);
    }

    /**Button�����¼�������
     * @param v
     */
    public void onClickProcess(View v){
        if(v.getId() == R.id.identity_btn_partybuilding){
            startActivity(new Intent(this, HomeAty.class));
            finish();
        }
    }
}