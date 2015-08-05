package sjtu.dclab.smartcity.ui.fragment;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import sjtu.dclab.smartcity.R;
import sjtu.dclab.smartcity.community.config.Me;
import sjtu.dclab.smartcity.ui.infocard.SettingAty;

/**
 * Created by Yang on 2015/8/5.
 */
public class InfocardFragment extends Fragment {
    private ImageButton imgBtnSetting;
    private TextView tvName;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.person_info, container, false);
    }

    @Override
    public void onStart() {
        super.onStart();

        View view = getView();
        tvName = (TextView) view.findViewById(R.id.person_info_name);
        tvName.setText(Me.username);

        imgBtnSetting = (ImageButton) view.findViewById(R.id.setting);
        imgBtnSetting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), SettingAty.class));
            }
        });
    }
}