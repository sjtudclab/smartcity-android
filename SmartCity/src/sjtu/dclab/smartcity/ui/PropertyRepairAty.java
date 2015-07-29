package sjtu.dclab.smartcity.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.ImageButton;

import sjtu.dclab.smartcity.R;

/**
 * Created by hp on 2015/7/28.
 */
public class PropertyRepairAty extends Activity {
    private ImageButton ib1, ib2, ib3;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.property_repair);

        this.ib1 = (ImageButton)findViewById(R.id.property_button1);
        this.ib1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(PropertyRepairAty.this, PropertyRepairCheckAty.class);
                startActivity(intent);
            }
        });

        this.ib2 = (ImageButton)findViewById(R.id.property_button2);
        this.ib2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(PropertyRepairAty.this, PropertyRepairAddAty.class);
                startActivity(intent);
            }
        });

        this.ib3 = (ImageButton)findViewById(R.id.property_button3);
        this.ib3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(PropertyRepairAty.this, PropertyRepairAssessAty.class);
                startActivity(intent);
            }
        });
    }
}
