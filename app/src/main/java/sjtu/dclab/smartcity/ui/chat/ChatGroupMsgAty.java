package sjtu.dclab.smartcity.ui.chat;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageButton;

import sjtu.dclab.smartcity.R;

/**
 * ChatGroupMsgAty
 *
 * @author Jian Yang
 * @date 2015/7/24
 */
public class ChatGroupMsgAty extends Activity {

    private ImageButton ibtnGroupDetail;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.aty_chat_group_msg);

        ibtnGroupDetail = (ImageButton) findViewById(R.id.chat_groupmsg_ibtn_detail);
        ibtnGroupDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), ChatGroupDetailsAty.class));
            }
        });
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            finish();
        }
        return true;
    }
}