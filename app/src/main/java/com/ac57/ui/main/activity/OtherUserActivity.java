package com.ac57.ui.main.activity;

import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ac57.R;
import com.ac57.framework.base.MVPBaseActivity;
import com.ac57.ui.adapter.MyFragmentPageAdapter;
import com.ac57.ui.entity.OtherUserInfoEntity;
import com.ac57.ui.main.fragment.OtherOneFragment;
import com.ac57.ui.main.fragment.OtherThreeFragment;
import com.ac57.ui.main.fragment.OtherTwoFragment;
import com.ac57.ui.presenter.OtherUserInfoPresenter;
import com.ac57.ui.presenter.view.IOtherUserInfoView;
import com.ac57.ui.view.CircleImageView;
import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

import static com.ac57.R.id.otheruser_center_tab3;
import static com.ac57.R.id.otheruser_pager;

/**
 * 个人主页
 */
public class OtherUserActivity extends MVPBaseActivity<OtherUserInfoPresenter, IOtherUserInfoView> implements IOtherUserInfoView {

    @BindView(R.id.iv_title_left)
    ImageView ivTitleLeft;
    @BindView(R.id.tv_title_center)
    TextView tvTitleCenter;
    @BindView(R.id.iv_title_center)
    ImageView ivTitleCenter;
    @BindView(R.id.iv_title_right)
    ImageView ivTitleRight;
    @BindView(R.id.otheruser_info_top_head)
    CircleImageView otheruserInfoTopHead;
    @BindView(R.id.otheruser_info_top_name)
    TextView otheruserInfoTopName;
    @BindView(R.id.otheruser_info_top_level)
    ImageView otheruserInfoTopLevel;
    @BindView(R.id.otheruser_info_top_auto)
    TextView otheruserInfoTopAuto;
    @BindView(R.id.otheruser_info_top_count)
    TextView otheruserInfoTopCount;
    @BindView(R.id.otheruser_info_top_v)
    LinearLayout otheruserInfoTopV;
    @BindView(R.id.otheruser_info_top_bnt)
    TextView otheruserInfoTopBnt;
    @BindView(R.id.toolbar)
    LinearLayout toolbar;
    @BindView(R.id.otheruser_center_tab1)
    TextView otheruserCenterTab1;
    @BindView(R.id.otheruser_center_tab2)
    TextView otheruserCenterTab2;
    @BindView(otheruser_center_tab3)
    TextView otheruserCenterTab3;
    @BindView(R.id.appbar)
    AppBarLayout appbar;
    @BindView(otheruser_pager)
    ViewPager otheruserPager;
    @BindView(R.id.main_content)
    CoordinatorLayout mainContent;
    @BindView(R.id.activity_other_user)
    LinearLayout activityOtherUser;

    private String show_user_id;
    private int draw[];
    private TextView[] tvs;

    private List<Fragment> list = new ArrayList<>();
    MyFragmentPageAdapter adapter;

    @Override
    public int getLayout() {
        return R.layout.activity_other_user;
    }

    @Override
    public void initView(Bundle savedInstanceState) {
        tvs = new TextView[]{otheruserCenterTab1, otheruserCenterTab2, otheruserCenterTab3};
        draw = new int[]{R.drawable.level1,
                R.drawable.level2,
                R.drawable.level3,
                R.drawable.level4,
                R.drawable.level5,
                R.drawable.level6};

    }

    @Override
    public void initDatas() {
        show_user_id = getIntent().getStringExtra("show_user_id");
        list.add(OtherOneFragment.newInstance(show_user_id));
        list.add(OtherTwoFragment.newInstance(show_user_id));
        list.add(OtherThreeFragment.newInstance(show_user_id));
        adapter = new MyFragmentPageAdapter(getSupportFragmentManager());
        adapter.setmFragments(list);
        otheruserPager.setAdapter(adapter);

        otheruserPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                setBackColor(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

    }

    private void setBackColor(int posi) {
        for (int i = 0; i < tvs.length; i++) {
            if (i == posi) {
                tvs[i].setBackgroundResource(R.drawable.otheruser_center_item_bg);
                tvs[i].setTextColor(this.getResources().getColor(android.R.color.white));
            } else {
                tvs[i].setBackgroundResource(R.drawable.otheruser_center_item_bg_not);
                tvs[i].setTextColor(this.getResources().getColor(R.color.otheruser_center_tv_cor));
            }
        }
        otheruserPager.setCurrentItem(posi);
    }

    @Override
    public void loadData() {
        mPresenter.getOtherUserInfoData(show_user_id);
    }


    @OnClick({R.id.iv_title_left, R.id.otheruser_center_tab1, R.id.otheruser_center_tab2, otheruser_center_tab3})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_title_left:
                break;
            case R.id.otheruser_center_tab1:
                setBackColor(0);
                break;
            case R.id.otheruser_center_tab2:
                setBackColor(1);
                break;
            case otheruser_center_tab3:
                setBackColor(2);
                break;
        }
    }

    @Override
    public void getOtherUserInfoData(OtherUserInfoEntity entity) {
        Glide.with(this).load(entity.user_data.head_img).placeholder(R.drawable.personal_head_portrait).into(otheruserInfoTopHead);
        otheruserInfoTopName.setText(entity.user_data.nickname);
        if (entity.user_data.auth_status.equals("101")) {
            otheruserInfoTopAuto.setText("已认证");
        } else {
            otheruserInfoTopAuto.setText("未认证");
        }
        int level = Integer.parseInt(entity.user_data.level);
        otheruserInfoTopLevel.setImageResource(draw[level]);
//        if (level == 1) {
//            otheruserInfoTopLevel.setImageResource(R.drawable.level1);
//        } else if (level == 2) {
//            otheruserInfoTopLevel.setImageResource(R.drawable.level2);
//        } else if (level == 3) {
//            otheruserInfoTopLevel.setImageResource(R.drawable.level3);
//        } else if (level == 4) {
//            otheruserInfoTopLevel.setImageResource(R.drawable.level4);
//        } else if (level == 5) {
//            otheruserInfoTopLevel.setImageResource(R.drawable.level5);
//        } else if (level == 6) {
//            otheruserInfoTopLevel.setImageResource(R.drawable.level6);
//        }

        int follow_num = Integer.parseInt(entity.user_data.follow_info.follow_num);
        if (follow_num > 0) {
            otheruserInfoTopCount.setText("关注：" + entity.user_data.follow_info.follow_num);
        } else {
            otheruserInfoTopCount.setText("关注");
        }
        setConcern(entity.is_follow.equals("102"));
    }

    private void setConcern(boolean istrue) {
        if (istrue) {
            otheruserInfoTopBnt.setText("+关注");
            otheruserInfoTopBnt.setBackgroundResource(R.drawable.release_item_bnt_bg);
            otheruserInfoTopBnt.setTextColor(this.getResources().getColor(R.color.otheruser_top_bnt_cor));
        } else {
            otheruserInfoTopBnt.setText("取消关注");
            otheruserInfoTopBnt.setBackgroundResource(R.drawable.otheruser_top_bnt_no);
            otheruserInfoTopBnt.setTextColor(this.getResources().getColor(R.color.otheruser_top_bnt_no_cor));
        }
    }


    @Override
    public void showDailog(String msg) {

    }

    @Override
    public void disDailog() {

    }

    @Override
    public void showError(String msg) {

    }

    @Override
    protected OtherUserInfoPresenter initPresenter() {
        return new OtherUserInfoPresenter(this);
    }
}
