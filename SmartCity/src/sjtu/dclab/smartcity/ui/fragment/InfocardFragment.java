package sjtu.dclab.smartcity.ui.fragment;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import sjtu.dclab.smartcity.R;
import sjtu.dclab.smartcity.community.config.Me;
import sjtu.dclab.smartcity.ui.infocard.LivingInformationActivity;
import sjtu.dclab.smartcity.ui.infocard.NameInformationActivity;
import sjtu.dclab.smartcity.ui.infocard.NetInformationActivity;
import sjtu.dclab.smartcity.ui.infocard.PartyInformationActivity;
import sjtu.dclab.smartcity.ui.infocard.SettingAty;

/**
 * Created by Yang on 2015/8/5.
 */
public class InfocardFragment extends Fragment {
    private ImageButton imgBtnSetting;
    private TextView tvName;
    private Button name_button,web_button,party_button,living_button;

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

        name_button = (Button) view.findViewById(R.id.person_info_namecardbutton);
        web_button = (Button) view.findViewById(R.id.person_info_webcardbutton);
        party_button = (Button) view.findViewById(R.id.person_info_partycardbutton);
        living_button = (Button) view.findViewById(R.id.person_info_livingcardbutton);

        name_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), NameInformationActivity.class));
            }
        });

        web_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), NetInformationActivity.class));
            }
        });

        party_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), PartyInformationActivity.class));
            }
        });

        living_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), LivingInformationActivity.class));
            }
        });

        imgBtnSetting = (ImageButton) view.findViewById(R.id.setting);
        imgBtnSetting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), SettingAty.class));
            }
        });
    }
}