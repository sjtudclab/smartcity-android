package sjtu.dclab.smartcity.ui.linli;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageButton;
import sjtu.dclab.smartcity.R;

/**
 * Created by HuangZhenyu on 15/7/30.
 */
public class Linlichat extends Activity {
    private ImageButton information;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.aty_chat_linli);
        information = (ImageButton) findViewById(R.id.linli_chat_detail);
        information.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Linlichat.this,Linlichatdetail.class));
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
