package com.ac57.ui.main.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.widget.Toast;

import com.ac57.R;
import com.ac57.framework.base.BaseActivity;
import com.ac57.ui.adapter.MyFragmentPageAdapter;
import com.ac57.ui.entity.TabEntity;
import com.ac57.ui.main.fragment.HomeFragment;
import com.ac57.ui.main.fragment.InteractiveFragment;
import com.ac57.ui.main.fragment.NewGoodsFragment;
import com.ac57.ui.main.fragment.NoticeFragment;
import com.ac57.ui.main.fragment.ReadingTypeFragment;
import com.ac57.ui.utils.EventBusUtils;
import com.ac57.ui.view.MyViewPager;
import com.flyco.tablayout.CommonTabLayout;
import com.flyco.tablayout.listener.CustomTabEntity;
import com.flyco.tablayout.listener.OnTabSelectListener;

import org.simple.eventbus.Subscriber;
import org.simple.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import butterknife.BindView;

public class MainActivity extends BaseActivity {
    @BindView(R.id.ctlayout_main)
    CommonTabLayout ctlayoutMain;
    @BindView(R.id.vp_main_page)
    MyViewPager vp_main_page;

    private String[] mTitles = {"新品", "公告", "咨询", "互动", "看盘"};
    private ArrayList<Fragment> mFragments = new ArrayList<>();
    private int[] mIconUnselectIds = {
            R.drawable.tab_newgoods,
            R.drawable.tab_news_announcement,
            R.drawable.tab_information_unselected,
            R.drawable.tab_interaction,
            R.drawable.tab_market};
    private int[] mIconSelectIds = {
            R.drawable.tab_newgoods_selected,
            R.drawable.tab_news_announcement_selected,
            R.drawable.tab_information,
            R.drawable.tab_interaction_selected,
            R.drawable.tab_market_selected};
    private ArrayList<CustomTabEntity> mTabEntities = new ArrayList<>();
    private MyFragmentPageAdapter adapter;

    @Override
    public int getLayout() {
        return R.layout.activity_main;
    }


    @Override
    public void initView(Bundle savedInstanceState) {
        initWindow();
    }

    public void initDatas() {

        adapter = new MyFragmentPageAdapter(getSupportFragmentManager());
        //新品
        mFragments.add(NewGoodsFragment.newInstance(getIntent().getStringArrayListExtra("ex_title"), getIntent().getStringArrayListExtra("ex_id")));
        //公告
        mFragments.add(NoticeFragment.newInstance(getIntent().getStringArrayListExtra("ex_title"), getIntent().getStringArrayListExtra("ex_id")));
        //咨询
        mFragments.add(HomeFragment.newInstance());
        //互动
        mFragments.add(InteractiveFragment.newInstance());
        //看盘
        mFragments.add(ReadingTypeFragment.newInstance());
        for (int i = 0; i < mTitles.length; i++) {
            mTabEntities.add(new TabEntity(mTitles[i], mIconSelectIds[i], mIconUnselectIds[i]));
        }
        adapter.setmFragments(mFragments);
        vp_main_page.setScanScroll(false);
        vp_main_page.setAdapter(adapter);
        ctlayoutMain.setTabData(mTabEntities);
        //设置进入主页面时默认进入咨询页面
        ctlayoutMain.setCurrentTab(2);
        vp_main_page.setCurrentItem(2);
        ctlayoutMain.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelect(int position) {
                vp_main_page.setCurrentItem(position);
                if (position == 4) {
                    initWindowBarColor(Color.parseColor("#242438"));
                    ctlayoutMain.setBackgroundColor(Color.parseColor("#242438"));
                } else {
                    initWindowBarColor(Color.WHITE);
                    ctlayoutMain.setBackgroundColor(Color.WHITE);
                }
            }

            @Override
            public void onTabReselect(int position) {

            }
        });
    }

    @Override
    public void loadData() {

    }

    @Override
    public void onBackPressed() {
        quitApp();
    }

    protected int isFirstBack;

    protected void quitApp() {
        if (isFirstBack == 0) {
            Toast.makeText(this, "再点一次退出", Toast.LENGTH_SHORT).show();
            isFirstBack = 1;
            new Timer().schedule(new TimerTask() {
                @Override
                public void run() {
                    isFirstBack = 0;
                }
            }, 1500);
        } else if (isFirstBack == 1) {
            finish();
            System.exit(0);
        }
    }

    @Subscriber(mode = ThreadMode.MAIN, tag = EventBusUtils.ID_AND_NAME)
    public void getName(int id) {
        Log.e("taggg", "ex_name " + id);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
