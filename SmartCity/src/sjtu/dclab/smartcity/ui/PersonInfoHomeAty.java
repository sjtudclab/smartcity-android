package sjtu.dclab.smartcity.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import sjtu.dclab.smartcity.R;

/**
 * Created by hp on 2015/7/30.
 */
public class PersonInfoHomeAty extends Activity {
    private ImageButton ib1;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.person_info);

        ib1 = (ImageButton)findViewById(R.id.person_info_right);
        this.ib1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(PersonInfoHomeAty.this, PersonInfoEditAty.class);
                startActivity(intent);
            }
        });
    }
}
