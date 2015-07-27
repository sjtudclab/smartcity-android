package sjtu.dclab.smartcity.ui;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
//import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.Display;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import sjtu.dclab.smartcity.R;
import sjtu.dclab.smartcity.fragment.ContactsFragment;
import sjtu.dclab.smartcity.utility.SerializableMap;


/**
 * Created by Yang on 2015/7/6.
 */
public class HomeAty extends Activity {
    private ViewPager tabPager;
    private ImageView tabCur;
    private ImageView tabHome, tabContacts, tabChat, tabProfile;    //依次为：主页、联系人、聊天、个人主页

    private int offset = 0;
    private int curTabIndex = 0;

    private String labels[] = null;
    private int icons[] = null;
    private GridView serviceGridView = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.aty_home);

        tabPager = (ViewPager) findViewById(R.id.tabpager);
        tabPager.setOnPageChangeListener(new MyOnPageChangeListener());

        tabHome = (ImageView) findViewById(R.id.img_home);
        tabContacts = (ImageView) findViewById(R.id.img_contacts);
        tabChat = (ImageView) findViewById(R.id.img_chat);
        tabProfile = (ImageView) findViewById(R.id.img_profile);
        tabCur = (ImageView) findViewById(R.id.img_tab_now);

        tabHome.setOnClickListener(new MyOnClickListener(0));
        tabContacts.setOnClickListener(new MyOnClickListener(1));
        tabChat.setOnClickListener(new MyOnClickListener(2));
        tabProfile.setOnClickListener(new MyOnClickListener(3));

        Display curDisplay = getWindowManager().getDefaultDisplay();
        offset = curDisplay.getWidth() / 4;

        LayoutInflater inflater = LayoutInflater.from(this);
        View view_home = inflater.inflate(R.layout.tab_home, null);
        View view_contacts = inflater.inflate(R.layout.tab_contacts, null);
        View view_chat = inflater.inflate(R.layout.tab_chat, null);
        View view_profile = inflater.inflate(R.layout.tab_profile, null);

        final ArrayList<View> views = new ArrayList<View>();
        views.add(view_home);
        views.add(view_contacts);
        views.add(view_chat);
        views.add(view_profile);

        // adapter of the ViewPager tabPager
        PagerAdapter pagerAdapter = new PagerAdapter() {
            @Override
            public Object instantiateItem(ViewGroup container, int position) {
                // TODO Auto-generated method stub
                ((ViewPager) container).addView(views.get(position));
                return views.get(position);
            }

            @Override
            public void destroyItem(ViewGroup container, int position,
                                    Object object) {
                // TODO Auto-generated method stub
                ((ViewPager) container).removeView(views.get(position));
            }

            @Override
            public boolean isViewFromObject(View arg0, Object arg1) {
                // TODO Auto-generated method stub
                return arg0 == arg1;
            }

            @Override
            public int getCount() {
                // TODO Auto-generated method stub
                return views.size();
            }
        };
        tabPager.setAdapter(pagerAdapter);

            //setting view_contacts
        view_contacts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            }
        });



        // second page
//        icons = new int[] { R.drawable.item_icon_repair,
//                R.drawable.item_icon_return, R.drawable.item_icon_change,
//                R.drawable.item_icon_record, R.drawable.item_icon_comp };
//        labels = new String[] { "����ά��", "�����˻�", "���뻻��", "ά�޼�¼", "Ͷ��" };
//
//        serviceGridView = (GridView) view_contacts.findViewById(R.id.serviceItems);
//        System.out.println(serviceGridView == null);
//        ArrayList<HashMap<String, Object>> serviceItems = new ArrayList<HashMap<String, Object>>();
//        for (int i = 0; i < labels.length; i++) {
//            HashMap<String, Object> map = new HashMap<String, Object>();
//            map.put("itemIcon", icons[i]);
//            map.put("itemLab", labels[i]);
//            serviceItems.add(map);
//        }
//        SimpleAdapter itemsAdapter = new SimpleAdapter(this, serviceItems,
//                R.layout.service_item, new String[] { "itemIcon", "itemLab" },
//                new int[] { R.id.itemIcon, R.id.itemLab });
//        serviceGridView.setAdapter(itemsAdapter);
//        serviceGridView.setOnItemClickListener(new ServiceItemClickListener());
    }


    private void getData() {
        Map<String,Object> questMap = new HashMap<String,Object>();
        Map<String,Object> groupMap = new HashMap<String,Object>();
        Map<String,Object> friendMap = new HashMap<String,Object>();

        //simulate data
        questMap.put("小明",1000001);

        groupMap.put("党建群",1000001);
        groupMap.put("小区居民群A3",1000002);
        groupMap.put("我的家庭",1000003);
        groupMap.put("小区居委会群",1000004);
        groupMap.put("A5201",1105201);

        friendMap.put("张三",1000001);
        friendMap.put("李四",1000002);
        friendMap.put("王五",1000003);
        friendMap.put("儿子",1000004);
        friendMap.put("爸爸",1000005);
        friendMap.put("妈妈",1000006);
        friendMap.put("老婆",1000007);
        friendMap.put("居委会主任", 1001001);

        Bundle bundle = new Bundle();

        //set data Serializable
        SerializableMap sQuestMap = new SerializableMap();
        sQuestMap.setMap(questMap);

        SerializableMap sGroupMap = new SerializableMap();
        sGroupMap.setMap(groupMap);

        SerializableMap sFriendMap = new SerializableMap();
        sFriendMap.setMap(friendMap);

        //put into bundle
        bundle.putSerializable("questMap", sQuestMap);
        bundle.putSerializable("groupMap", sGroupMap);
        bundle.putSerializable("friendMap", sFriendMap);

        //transfer to fragment
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fTransaction = fragmentManager.beginTransaction();
        ContactsFragment cfragment = new ContactsFragment();
        cfragment.setArguments(bundle);
        //fTransaction.addToBackStack(null);
        //fTransaction.commitAllowingStateLoss();
        //fTransaction.add(cfragment, "1234567");
        fTransaction.replace(R.id.fragment_contacts, cfragment);
        fTransaction.commit();

    }
    /**
     *
     */
    public class MyOnClickListener implements View.OnClickListener {
        private int index = 0;

        public MyOnClickListener(int i) {
            // TODO Auto-generated constructor stub
            index = i;
        }

        @Override
        public void onClick(View v) {
            // TODO Auto-generated method stub
            tabPager.setCurrentItem(index);
        }
    }

    /**
     *
     */
    public class MyOnPageChangeListener implements ViewPager.OnPageChangeListener {

        @Override
        public void onPageScrollStateChanged(int arg0) {
            // TODO Auto-generated method stub

        }

        @Override
        public void onPageScrolled(int arg0, float arg1, int arg2) {
            // TODO Auto-generated method stub

        }

        @Override
        public void onPageSelected(int arg0) {
            // TODO Auto-generated method stub
            Animation animation = null;
            switch (arg0) {
                case 0:
                    if (curTabIndex == 1) {
                        animation = new TranslateAnimation(offset, 0, 0, 0);
                    } else if (curTabIndex == 2) {
                        animation = new TranslateAnimation(offset * 2, 0, 0, 0);
                    } else if (curTabIndex == 3) {
                        animation = new TranslateAnimation(offset * 3, 0, 0, 0);
                    }
                    break;

                case 1:
                    if (curTabIndex == 0) {
                        animation = new TranslateAnimation(0, offset, 0, 0);
                    } else if (curTabIndex == 2) {
                        animation = new TranslateAnimation(offset * 2, offset, 0, 0);
                    } else if (curTabIndex == 3) {
                        animation = new TranslateAnimation(offset * 3, offset, 0, 0);
                    }
                    break;

                case 2:
                    if (curTabIndex == 0) {
                        animation = new TranslateAnimation(0, offset * 2, 0, 0);
                    } else if (curTabIndex == 1) {
                        animation = new TranslateAnimation(offset, offset * 2, 0, 0);
                    } else if (curTabIndex == 3) {
                        animation = new TranslateAnimation(offset * 3, offset * 2,
                                0, 0);
                    }
                    break;

                case 3:
                    if (curTabIndex == 0) {
                        animation = new TranslateAnimation(0, offset * 3, 0, 0);
                    } else if (curTabIndex == 1) {
                        animation = new TranslateAnimation(offset, offset * 3, 0, 0);
                    } else if (curTabIndex == 2) {
                        animation = new TranslateAnimation(offset * 2, offset * 3,
                                0, 0);
                    }
                    break;
            }
            curTabIndex = arg0;
            animation.setFillAfter(true);
            animation.setDuration(150);
            tabCur.startAnimation(animation);
        }

    }

    class ServiceItemClickListener implements AdapterView.OnItemClickListener {

        @Override
        public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
                                long arg3) {
            // TODO Auto-generated method stub
            switch (icons[arg2]) {
//                case R.drawable.item_icon_repair:
//                    startActivity(new Intent(MainPage.this,
//                            ApplyRepairActivity.class));
//                    break;
//
//                case R.drawable.item_icon_return:
//                    startActivity(new Intent(MainPage.this,
//                            ApplyReturnActivity.class));
//                    break;
//
//                case R.drawable.item_icon_change:
//                    startActivity(new Intent(MainPage.this,
//                            ApplyChangeActivity.class));
//                    break;
//
//                case R.drawable.item_icon_comp:
//                    startActivity(new Intent(MainPage.this,
//                            ApplyComplaintActivity.class));
//                    break;

                default:
                    break;
            }
        }

    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        // TODO Auto-generated method stub
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
            this.finish();
            return true;
        }
        return false;
    }
}