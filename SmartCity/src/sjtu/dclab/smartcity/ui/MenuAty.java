package sjtu.dclab.smartcity.ui;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;

import sjtu.dclab.smartcity.R;

/**
 * Created by HuangZhenyu on 15/7/23.
 */
public class MenuAty extends Activity implements OnClickListener {
    private Button setButton;
    private Button addButton;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.dangjian_menu);


        setButton = (Button) findViewById(R.id.btnSet);

        setButton.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {


            case R.id.btnSet:
                MenuPopupWindow menuPopupWindow = new MenuPopupWindow(MenuAty.this);
                menuPopupWindow.showPopupWindow(setButton);
                break;
            default:
                break;
        }
    }
}
