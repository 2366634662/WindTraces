package com.ac57.ui.main.activity;

import android.os.Bundle;

import com.ac57.R;
import com.ac57.framework.base.BaseActivity;
import com.ac57.framework.tools.SPHelper;
import com.ac57.framework.utils.IntentUtils;

import java.util.Timer;
import java.util.TimerTask;

public class WelcomeActivity extends BaseActivity {

    @Override
    protected int getLayout() {
        return R.layout.activity_welcome;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
    }

    @Override
    protected void initDatas() {

        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                if (SPHelper.getInstence(WelcomeActivity.this).isFirst()) {
                    IntentUtils.startActivity(WelcomeActivity.this, GuideActivity.class);
                    finish();
                } else {
                    IntentUtils.startActivity(WelcomeActivity.this, AppLogoActivity.class);
                    finish();
                }
            }
        }, 2000);
    }

    @Override
    protected void loadData() {

    }

}
