package com.ac57.ui.main.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.ac57.R;
import com.ac57.framework.base.BaseActivity;
import com.ac57.framework.utils.IntentUtils;
import com.ac57.ui.adapter.MyFragmentPageAdapter;
import com.ac57.ui.main.fragment.MyInvolvedFragment;
import com.ac57.ui.main.fragment.MyReleaseFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class MyTopicActivity extends BaseActivity implements RadioGroup.OnCheckedChangeListener, ViewPager.OnPageChangeListener {

    @BindView(R.id.iv_topic_title_back)
    ImageView ivTopicTitleBack;
    @BindView(R.id.rb_mytopic_rb1)
    RadioButton rbMytopicRb1;
    @BindView(R.id.rb_mytopic_rb2)
    RadioButton rbMytopicRb2;
    @BindView(R.id.rg_mytopic_title)
    RadioGroup rgMytopicTitle;
    @BindView(R.id.vp_mytopic_pager)
    ViewPager vpMytopicPager;

    MyFragmentPageAdapter adapter;
    List<Fragment> fragments;

    @Override
    public int getLayout() {
        return R.layout.activity_my_topic;
    }

    @Override
    public void initView(Bundle savedInstanceState) {
        setStatusBarColor(Color.WHITE, 30);
    }

    @Override
    public void initDatas() {
        fragments = new ArrayList<>();
        adapter = new MyFragmentPageAdapter(getSupportFragmentManager());
        fragments.add(MyReleaseFragment.newInstance("101"));
        fragments.add(MyInvolvedFragment.newInstance("102"));
        adapter.setmFragments(fragments);
        vpMytopicPager.setAdapter(adapter);
        vpMytopicPager.addOnPageChangeListener(this);
        rgMytopicTitle.setOnCheckedChangeListener(this);
    }

    @Override
    public void loadData() {

    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        if (position == 0) {
            rgMytopicTitle.check(R.id.rb_mytopic_rb1);
        } else if (position == 1) {
            rgMytopicTitle.check(R.id.rb_mytopic_rb2);
        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch (group.getCheckedRadioButtonId()) {
            case R.id.rb_mytopic_rb1:
                vpMytopicPager.setCurrentItem(0);
                break;
            case R.id.rb_mytopic_rb2:
                vpMytopicPager.setCurrentItem(1);
                break;
        }
    }


    @OnClick(R.id.iv_topic_title_back)
    public void onClick() {
        IntentUtils.finishActivity(this);
    }
}
