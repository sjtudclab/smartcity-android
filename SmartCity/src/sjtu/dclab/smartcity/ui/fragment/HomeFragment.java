package sjtu.dclab.smartcity.ui.fragment;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.SimpleAdapter;
import sjtu.dclab.smartcity.GlobalApp;
import sjtu.dclab.smartcity.R;
import sjtu.dclab.smartcity.ui.ann_committee.AnnouncementAty;
import sjtu.dclab.smartcity.ui.party.orglife.OrgLifeAty;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Yang on 2015/7/22.
 */
public class HomeFragment extends Fragment {
    private int icons[] = null;
    private int labels[] = null;
    private GridView serviceGridView = null;
    private String status;

    private GlobalApp globalApp;

    private int[] icons_committee = new int[]{
            R.drawable.oriental_window, R.drawable.payment_record,
            R.drawable.property_management,
            R.drawable.public_opinion_square, R.drawable.information,
            R.drawable.announcement, R.drawable.community_act,
            R.drawable.work_log, R.drawable.population_information,
            R.drawable.data_management, R.drawable.housing_information,
            R.drawable.resident_mailbox, R.drawable.resident_opnion,
            R.drawable.oriental_window, R.drawable.payment_record,
            R.drawable.property_management, R.drawable.public_opinion_square
    };

    private int[] labels_committee = new int[]{
            R.string.minzheng, R.string.gongyi, R.string.bianmin, R.string.weiji,
            R.string.anfang, R.string.zongzhi, R.string.xinfang, R.string.wenjiao,
            R.string.minyiguangchang, R.string.minyitoupiao, R.string.yuqing, R.string.gonggao,
            R.string.renkou, R.string.fangwu, R.string.renshi, R.string.zhoubian};

    private int[] icons_resident = new int[]{
            R.drawable.oriental_window,
            R.drawable.payment_record, R.drawable.property_management,
            R.drawable.public_opinion_square, R.drawable.information,
            R.drawable.announcement, R.drawable.community_act,
            R.drawable.work_log
    };
    private int[] labels_resident = new int[]{
            R.string.juwei, R.string.yehui, R.string.wuye,
            R.string.bianmin, R.string.linli,
            R.string.minyi, R.string.shequ, R.string.shequhuo
    };

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        globalApp = (GlobalApp)getActivity().getApplication();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onStart() {
        super.onStart();

        serviceGridView = (GridView) getFragmentManager().findFragmentById(R.id.fragment_home).getView().findViewById(R.id.gv_service);
        ArrayList<HashMap<String, Object>> serviceItems = new ArrayList<HashMap<String, Object>>();
        status = globalApp.getStatus();

        if (status.equals(getString(R.string.Committee))){
            icons = icons_committee;
            labels = labels_committee;
        }else if (status.equals(getString(R.string.NormalParty))){
            //TODO
        }else {
            icons = icons_resident;
            labels = labels_resident;
        }


        for (int i = 0; i < labels.length; i++) {
            HashMap<String, Object> map = new HashMap<String, Object>();
            map.put("itemIcon", icons[i]);
            map.put("itemLab", getResources().getString(labels[i]));
            serviceItems.add(map);
        }
        String[] from = new String[]{"itemIcon", "itemLab"};
        int[] to = new int[]{R.id.itemIcon, R.id.itemLab};
        SimpleAdapter itemsAdapter = new SimpleAdapter(getActivity().getApplicationContext(), serviceItems,
                R.layout.service_item, from, to);
        serviceGridView.setAdapter(itemsAdapter);
        serviceGridView.setOnItemClickListener(new ItemClickListener());
    }

    class ItemClickListener implements AdapterView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
            switch (icons[i]) {
                case R.drawable.announcement://��ʱ����������֯����
                    startActivity(new Intent(getActivity(), OrgLifeAty.class));
                    break;
                case R.drawable.resident_mailbox://��ʱ�������Թ������
                    startActivity(new Intent(getActivity(), AnnouncementAty.class));
                    break;
                default:
                    break;
            }
        }
    }
}