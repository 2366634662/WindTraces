package com.ac57.ui.main.activity;

import android.graphics.Color;
import android.os.Bundle;

import com.ac57.R;
import com.ac57.framework.base.BaseActivity;

/**
 * 帮助反馈
 */
public class HelpFeedbackActivity extends BaseActivity {

    @Override
    public int getLayout() {
        return R.layout.activity_help_feedback;
    }

    @Override
    public void initView(Bundle savedInstanceState) {
        setStatusBarColor(Color.WHITE,30);
    }

    @Override
    public void initDatas() {

    }

    @Override
    public void loadData() {

    }
}
