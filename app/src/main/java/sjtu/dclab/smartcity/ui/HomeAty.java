package sjtu.dclab.smartcity.ui;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.*;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import sjtu.dclab.smartcity.R;

import java.util.ArrayList;

/**
 * Created by Yang on 2015/7/6.
 */
public class HomeAty extends FragmentActivity {
    private ViewPager tabPager;
    private ImageView tabCur;
    private ImageView tabHome, tabContacts, tabChat, tabProfile;    //依次为：主页、联系人、聊天、个人主页

    private int offset = 0;
    private int curTabIndex = 0;

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
        View view_chat = inflater.inflate(R.layout.tab_msgrecord, null);
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