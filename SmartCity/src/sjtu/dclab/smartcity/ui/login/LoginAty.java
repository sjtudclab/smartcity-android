package sjtu.dclab.smartcity.ui.login;

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
import sjtu.dclab.smartcity.GlobalApp;
import sjtu.dclab.smartcity.R;
import sjtu.dclab.smartcity.community.common.CommunityApp;
import sjtu.dclab.smartcity.community.login.MyLogin;
import sjtu.dclab.smartcity.community.talk.MyTalk;
import sjtu.dclab.smartcity.entity.Role;
import sjtu.dclab.smartcity.transfer.UserTransfer;
import sjtu.dclab.smartcity.ui.HomeAty;

import java.util.Collection;
import java.util.Iterator;

/**
 * Created by Yang on 2015/7/21.
 */
public class LoginAty extends Activity {
    private String LOGIN;
    private String URLServer;
    private CommunityApp app;
    private MyLogin login;
    private MyTalk talk;

    private String statusKey;
    private GlobalApp globalApp;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.aty_login);

        LOGIN = "LOGIN";
        URLServer = getResources().getString(R.string.URLServer);
        app = new CommunityApp(URLServer);
        login = app.getLoginModule();
        talk = app.getTalkModule();

        globalApp = (GlobalApp) getApplication();
        statusKey = getString(R.string.StatusKey);
    }

    @Override
    protected void onStart() {
        super.onStart();
        Button loginBtn = (Button) findViewById(R.id.login_btn);
        final EditText name = (EditText) findViewById(R.id.login_name_edittext);
        final EditText passwd = (EditText) findViewById(R.id.login_passwd_edittext);

        name.setText("zhangzhizhong");
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

                login.doLogin(username, password);  //长意的Login，为了talk模块中传userID的参数
                UserTransfer ut = Login.login(username, password);
                if (ut != null) {
                    Collection<Role> roles = ut.getRoles();
                    String type = null;

                    if (!roles.isEmpty()) {
                        Iterator iterator = roles.iterator();
                        while (iterator.hasNext()) {
                            type = ((Role) iterator.next()).getRoleType().getType().toString();
                        }
                    } else {
                        type = getString(R.string.Resident);
                    }

                    Intent i;
                    globalApp.setTalk(talk);
//                    globalApp.setStatus(type);
                    globalApp.setUsername(username);

                    if (type.equals(getString(R.string.NormalParty))) {
                        i = new Intent(getApplicationContext(), LoginIdentityNormalParty.class);
                    } else if (type.equals(getString(R.string.Committee))) {
                        i = new Intent(getApplicationContext(), LoginIdentityCommitteeAty.class);
                    } else {
                        //default value
                        i = new Intent(getApplicationContext(), HomeAty.class);
                        i.putExtra(statusKey,getString(R.string.Resident));
                    }
                    startActivity(i);
                    finish();

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
                                            name.setText("zhangzhizhong");
                                            passwd.setText("admin");
                                        }
                                    }).create();
                    alertDialog.show();
                }
            }
        });

    }
}
