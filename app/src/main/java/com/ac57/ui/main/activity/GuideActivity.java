package com.ac57.ui.main.activity;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.Button;

import com.ac57.R;
import com.ac57.framework.base.BaseActivity;
import com.ac57.framework.tools.SPHelper;
import com.ac57.framework.utils.IntentUtils;
import com.ac57.ui.view.statusbar.StatusBarUtil;

import butterknife.BindView;
import butterknife.OnClick;
import cn.bingoogolapple.bgabanner.BGABanner;

public class GuideActivity extends BaseActivity {
    @BindView(R.id.bga_guide)
    BGABanner bgaGuide;
    @BindView(R.id.btn_guide_enter)
    Button btnGuideEnter;
    private boolean flag;


    @Override
    public int getLayout() {
        return R.layout.activity_guide;
    }

    @Override
    public void initView(Bundle savedInstanceState) {
        StatusBarUtil.setTransparent(this);
    }

    @Override
    public void initDatas() {
        bgaGuide.setData(R.drawable.login_picture, R.drawable.login_picture, R.drawable.login_picture);
        bgaGuide.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

                if (position == bgaGuide.getItemCount() - 1) {
                    btnGuideEnter.setVisibility(View.VISIBLE);
                } else {
                    btnGuideEnter.setVisibility(View.GONE);
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                switch (state) {
                    case ViewPager.SCROLL_STATE_DRAGGING:
                        flag = false;
                        break;
                    case ViewPager.SCROLL_STATE_SETTLING:
                        flag = true;
                        break;
                    case ViewPager.SCROLL_STATE_IDLE:
                        if (bgaGuide.getCurrentItem() == bgaGuide.getItemCount() - 1 && !flag) {
                            openActivity();
                        }
                        flag = true;
                        break;
                }
            }
        });

    }

    @Override
    public void loadData() {

    }

    @OnClick({R.id.btn_guide_enter})
    public void onClick(View view) {
        openActivity();
    }

    private void openActivity() {
        SPHelper.getInstence(this).setIsFirst(false);
        IntentUtils.startActivity(this, AppLogoActivity.class);
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_in_left);
        finish();
    }
}
