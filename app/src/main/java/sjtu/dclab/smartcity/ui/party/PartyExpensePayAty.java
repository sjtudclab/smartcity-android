package sjtu.dclab.smartcity.ui.party;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageButton;

import sjtu.dclab.smartcity.R;

/**
 * Created by theGODofws 2015/8/1.
 */
public class PartyExpensePayAty extends Activity{
    private ImageButton detailbutton;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.party_expense_pay);

        detailbutton = (ImageButton) findViewById(R.id.party_expense_detail_btn);
        detailbutton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                //TODO:添加党费缴纳统计class并替换ThoughtReportListAty
                startActivity(new Intent(getApplicationContext(), ThoughtReportListAty.class));
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
