package sjtu.dclab.smartcity.ui;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.Collection;

import cn.edu.sjtu.se.dclab.common.CommunityApp;
import cn.edu.sjtu.se.dclab.login.MyLogin;
import sjtu.dclab.smartcity.R;
import sjtu.dclab.smartcity.entity.Role;
import sjtu.dclab.smartcity.login.Login;
import sjtu.dclab.smartcity.transfer.UserTransfer;

/**
 * Created by Yang on 2015/7/21.
 */
public class LoginAty extends Activity {
    private final String LOGIN = "LOGIN";
    private CommunityApp app = new CommunityApp("http://202.120.40.111:8080/community-server");
    private MyLogin login = app.getLoginModule();

//    private GlobalApp globalApp;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.aty_login);
    }

    @Override
    protected void onStart() {
        super.onStart();
        Button loginBtn = (Button) findViewById(R.id.login_btn);
        final EditText name = (EditText) findViewById(R.id.login_name_edittext);
        final EditText passwd = (EditText) findViewById(R.id.login_passwd_edittext);

        name.setText("resident_test3");
        passwd.setText("admin");

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginAty.this, LoginIdentityCommitteeAty.class);

                intent.putExtra(String.valueOf(R.string.talk), app.getTalkModule());

                String username = name.getText().toString();
                String password = passwd.getText().toString();

                //for network
                StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
                StrictMode.setThreadPolicy(policy);

                UserTransfer ut = Login.login(username, password);
                if (ut != null) {
                    Collection<Role> roles = ut.getRoles();
                    Role [] rolesArray = null;
                    roles.toArray(rolesArray);
                    String type = rolesArray[0].getRoleType().getType();
                    if(type == "居民"){
                        startActivity(new Intent(getApplicationContext(), ResidentHomeAty.class));
                        finish();
                    }else if(type == "普通党员"){
                        startActivity(new Intent(getApplicationContext(), LoginIdentityNormalParty.class));
                        finish();
                    }else if(type == "居委会"){
                        startActivity(new Intent(getApplicationContext(), LoginIdentityCommitteeAty.class));
                        finish();
                    }
                } else {
                    Dialog alertDialog = new AlertDialog.Builder(
                            LoginAty.this)
                            .setTitle("hint")
                            .setMessage("用户名或密码错误！")
                            .setPositiveButton("确定",
                                    new DialogInterface.OnClickListener() {

                                        @Override
                                        public void onClick(
                                                DialogInterface dialog,
                                                int which) {
                                            name.setText("resident_test3");
                                            passwd.setText("admin");
                                        }
                                    }).create();
                    alertDialog.show();
                }
            }
        });

    }
}