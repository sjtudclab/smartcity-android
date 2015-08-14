package sjtu.dclab.smartcity.ui.fragment;

import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.SimpleAdapter;
import sjtu.dclab.smartcity.R;
import sjtu.dclab.smartcity.ui.ann_committee.AnnouncementAty;
import sjtu.dclab.smartcity.ui.bianminservice.BianminAty;
import sjtu.dclab.smartcity.ui.human_resource_info.RenshiAty;
import sjtu.dclab.smartcity.ui.linli.LinliAty;
import sjtu.dclab.smartcity.ui.minyisquare.MinyiAty;
import sjtu.dclab.smartcity.ui.minzheng.CivilAffairHomeAty;
import sjtu.dclab.smartcity.ui.party.*;
import sjtu.dclab.smartcity.ui.vote.VoteAty;
import sjtu.dclab.smartcity.ui.wuyerepair.PropertyRepairAddAty;
import sjtu.dclab.smartcity.ui.yuqing.YuqingAty;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Yang on 2015/7/22.
 */
public class HomeFragment extends Fragment {
    private int icons[] = null;
    private int labels[] = null;
    private GridView serviceGridView = null;
    private String statusKey = "StatusKey";
    private String status;

//    private GlobalApp globalApp;

    //居委会
    private int[] icons_committee = new int[]{
            R.drawable.minzhen, R.drawable.gongyi, R.drawable.bianmin, R.drawable.weiji,
            R.drawable.anfang, R.drawable.zongzhi, R.drawable.xinfang, R.drawable.wenjiao,
            R.drawable.minyisquare, R.drawable.minyivote, R.drawable.yuqing, R.drawable.gonggao,
            R.drawable.peopleinfo, R.drawable.houseinfo, R.drawable.renshi, R.drawable.zhoubian
    };
    private int[] labels_committee = new int[]{
            R.string.minzheng, R.string.gongyi, R.string.bianmin, R.string.weiji,
            R.string.anfang, R.string.zongzhi, R.string.xinfang, R.string.wenjiao,
            R.string.minyiguangchang, R.string.minyitoupiao, R.string.yuqing, R.string.gonggao,
            R.string.renkou, R.string.fangwu, R.string.renshi, R.string.zhoubian};

    //党建
    private int[] icons_dangjian = new int[]{
            R.drawable.zuzhishenghuo, R.drawable.dangyuanhuodong, R.drawable.sixianghuibao, R.drawable.dangfeijiaona,
            R.drawable.ziwopingjia, R.drawable.dangyuanhuping, R.drawable.zuzhiguanxi, R.drawable.jidukaocha
    };
    private int[] labels_dangjian = new int[]{
            R.string.zuzhishenghuo, R.string.dangyuanhuodong, R.string.sixianghuibao, R.string.dangfeijiaona,
            R.string.ziwopingjia, R.string.dangyuanhuping, R.string.zuzhiguanxi, R.string.jidukaocha
    };

    //居民
    private int[] icons_resident = new int[]{
            R.drawable.juweihui, R.drawable.yeweihui, R.drawable.wuye, R.drawable.bianmin,
            R.drawable.linli, R.drawable.minyisquare, R.drawable.gonggao, R.drawable.huodong
    };
    private int[] labels_resident = new int[]{
            R.string.juwei, R.string.yehui, R.string.wuye, R.string.bianmin,
            R.string.linli, R.string.minyi, R.string.gonggao, R.string.huodong
    };

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getActivity().getIntent();
        status = intent.getStringExtra(statusKey);
//        globalApp = (GlobalApp)getActivity().getApplication();

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
//        status = globalApp.getStatus();


        if (status.equals(getString(R.string.Committee))) {
            icons = icons_committee;
            labels = labels_committee;
        } else if (status.equals(getString(R.string.NormalParty))) {
            icons = icons_dangjian;
            labels = labels_dangjian;
        } else if (status.equals(getString(R.string.Yeweihu))) {
        } else {
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
            Context context = getActivity().getApplicationContext();
            switch (icons[i]) {
                //居委会工作部分
                case R.drawable.minzhen:
                    startActivity(new Intent(context, CivilAffairHomeAty.class));
                    break;
                case R.drawable.gongyi:
                    break;
                case R.drawable.bianmin:
                    startActivity(new Intent(context, BianminAty.class));
                    break;
                case R.drawable.weiji:
                    break;

                case R.drawable.anfang:
                    break;
                case R.drawable.zongzhi:
                    break;
                case R.drawable.xinfang:
                    break;
                case R.drawable.wenjiao:
                    break;

                case R.drawable.minyisquare:
                    startActivity(new Intent(context, MinyiAty.class));
                    break;
                case R.drawable.minyivote:
                    startActivity(new Intent(context, VoteAty.class));
                    break;
                case R.drawable.yuqing:
                    startActivity(new Intent(context, YuqingAty.class));
                    break;
                case R.drawable.gonggao:
                    startActivity(new Intent(context, AnnouncementAty.class));
                    break;

                case R.drawable.peopleinfo:
                    break;
                case R.drawable.houseinfo:
                    break;
                case R.drawable.renshi:
                    startActivity(new Intent(context, RenshiAty.class));
                    break;
                case R.drawable.zhoubian:
                    break;

                //TODO:党建部分
                case R.drawable.zuzhishenghuo:
                    startActivity(new Intent(context, OrgLifeAty.class));
                    break;
                case R.drawable.dangyuanhuodong:
                    startActivity(new Intent(context, PartyLifeAty.class));
                    break;
                case R.drawable.sixianghuibao:
                    startActivity(new Intent(context, ThouhtReportAty.class));
                    break;
                case R.drawable.dangfeijiaona:
                    startActivity(new Intent(context, PartyExpensePayAty.class));
                    break;
                case R.drawable.ziwopingjia:
//                    startActivity(new Intent(context, ));
                    break;
                case R.drawable.dangyuanhuping:
                    break;
                case R.drawable.zuzhiguanxi:
                    break;
                case R.drawable.jidukaocha:
                    startActivity(new Intent(context, SeasonCheck.class));
                    break;

                //居民部分
                case R.drawable.juweihui:
                    startActivity(new Intent(context, RenshiAty.class));
                    break;
                case R.drawable.yeweihui:
                    break;
                case R.drawable.wuye:
                    startActivity(new Intent(context, PropertyRepairAddAty.class));
                    break;
//                case R.drawable.bianmin:
//                    break;
                case R.drawable.linli:
                    startActivity(new Intent(context, LinliAty.class));
                    break;
//                case R.drawable.minyisquare:
//                    break;
//                case R.drawable.gonggao:
//                    break;
                case R.drawable.huodong:
                    break;

                default:
                    break;
            }
        }
    }
}