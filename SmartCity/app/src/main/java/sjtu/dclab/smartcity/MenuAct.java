package sjtu.dclab.smartcity;

import android.app.Activity;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.view.View.OnClickListener;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by HuangZhenyu on 15/7/23.
 */
public class MenuAct extends Activity implements OnClickListener {
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
                AddPopWindow addPopWindow = new AddPopWindow(MenuAct.this);
                addPopWindow.showPopupWindow(setButton);
                break;
            default:
                break;
        }
    }
}
