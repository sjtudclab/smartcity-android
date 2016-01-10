package sjtu.dclab.smartcity.ui.login;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;

import android.widget.Button;
import android.widget.Toast;
import sjtu.dclab.smartcity.GlobalApp;
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

    private GlobalApp globalApp;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.aty_login_identity);

        globalApp = (GlobalApp) getApplication();
    }

    @Override
    protected void onStart() {
        super.onStart();
        final Intent intent = new Intent(getApplicationContext(), HomeAty.class);
        final String statusKey = getString(R.string.StatusKey);

        juweihui = (Button) findViewById(R.id.identity_btn_juweihui);
        juweihui.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                globalApp.setStatus(getString(R.string.Committee));
                intent.putExtra(statusKey, getString(R.string.Committee));
                startActivity(intent);
                finish();
            }
        });

        yeweihui = (Button) findViewById(R.id.identity_btn_yeweihui);
        yeweihui.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(),"业委会模块正在开发中 ...",Toast.LENGTH_SHORT).show();
            }
        });

        dangjian = (Button) findViewById(R.id.identity_btn_partybuilding);
        dangjian.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent.putExtra(statusKey, getString(R.string.NormalParty));
                startActivity(intent);
                finish();
            }
        });

        jumin = (Button) findViewById(R.id.identity_btn_jumin);
        jumin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                globalApp.setStatus(getString(R.string.Resident));
                intent.putExtra(statusKey, getString(R.string.Resident));

                startActivity(intent);
                finish();
            }
        });
    }

    public void onClickProcess(View v) {
        if (v.getId() == R.id.identity_btn_partybuilding) {
            startActivity(new Intent(this, HomeAty.class));
            finish();
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            finish();
        }
        return true;
    }
}