package sjtu.dclab.smartcity.ui;

import android.app.Activity;
import android.os.Bundle;
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
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.ViewFlipper;

import java.util.ArrayList;
import java.util.HashMap;

import sjtu.dclab.smartcity.R;

/**
 * Created by Yang on 2015/7/6.
 */
public class ResidentHomeAty extends Activity {
    private ViewPager tabPager;
    private ImageView tabCur;
    private ImageView tabHome, tabContacts, tabChat, tabProfile;    //依次为：主页、联系人、聊天、个人主页
    private ViewFlipper vflipper;
    private ListView lview;

    // 图片数组

    /*// 要显示的图片在图片数组中的Index
    private int pictureIndex = 0;
    // 左右滑动时手指按下的X坐标
    private float touchDownX;
    // 左右滑动时手指松开的X坐标
    private float touchUpX;*/




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




        icons = new int[] { R.drawable.oriental_window,
                R.drawable.payment_record, R.drawable.property_management,
                R.drawable.public_opinion_square, R.drawable.information,
                R.drawable.announcement, R.drawable.community_act,
                R.drawable.work_log
        };
        labels = new String[] { getString(R.string.juwei), getString(R.string.yehui), getString(R.string.wuye),
                getString(R.string.bianmin), getString(R.string.linli),
                getString(R.string.minyi), getString(R.string.shequ), getString(R.string.shequhuo),
                };

        serviceGridView = (GridView) view_home.findViewById(R.id.gv_service);
        System.out.println(serviceGridView == null);
        ArrayList<HashMap<String, Object>> serviceItems = new ArrayList<HashMap<String, Object>>();
        for (int i = 0; i < labels.length; i++) {
            HashMap<String, Object> map = new HashMap<String, Object>();
            map.put("itemIcon", icons[i]);
            map.put("itemLab", labels[i]);
            serviceItems.add(map);
        }
        String[] from = new String[] { "itemIcon", "itemLab"};
        int[] to = new int[] { R.id.itemIcon, R.id.itemLab };
        SimpleAdapter itemsAdapter = new SimpleAdapter(this, serviceItems,
                R.layout.service_item, from,
                to);
        serviceGridView.setAdapter(itemsAdapter);


//    //viewflipper
//        vflipper = (ViewFlipper) view_home.findViewById(R.id.vflipper);
//        vflipper.startFlipping();
//
//
//    //listview
//        lview = (ListView)tabChat.findViewById(R.id.);
//        SimpleAdapter adapter = new SimpleAdapter(this,getData(),R.layout.friend_list,
//                new String[]{"title","info","img"},
//                new int[]{R.id.title,R.id.info,R.id.img});
//        lview.setAdapter(adapter);


    }

//    private List<Map<String, Object>> getData() {
//        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
//
//        Map<String, Object> map = new HashMap<String, Object>();
//        map.put("title", "G1");
//        map.put("info", "google 1");
//        map.put("img", R.drawable.i1);
//        list.add(map);
//
//        map = new HashMap<String, Object>();
//        map.put("title", "G2");
//        map.put("info", "google 2");
//        map.put("img", R.drawable.i2);
//        list.add(map);
//
//        map = new HashMap<String, Object>();
//        map.put("title", "G3");
//        map.put("info", "google 3");
//        map.put("img", R.drawable.i3);
//        list.add(map);
//
//        return list;
//    }

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