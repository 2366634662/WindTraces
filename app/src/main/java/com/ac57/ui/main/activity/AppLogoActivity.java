package com.ac57.ui.main.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ac57.R;
import com.ac57.framework.base.BaseActivity;
import com.ac57.framework.utils.IntentUtils;

import butterknife.BindView;
import butterknife.OnClick;

public class AppLogoActivity extends BaseActivity {

    @BindView(R.id.btn_login_or_reg)
    Button btnLoginOrReg;
    @BindView(R.id.llayout_login_weixin)
    LinearLayout llayoutLoginWeixin;
    @BindView(R.id.llayout_login_qq)
    LinearLayout llayoutLoginQq;
    @BindView(R.id.tv_login_visitor)
    TextView tvLoginVisitor;



    @Override
    protected int getLayout() {
        return R.layout.activity_app_logo;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {

    }

    @Override
    protected void initDatas() {

    }

    @Override
    protected void loadData() {

    }


    @OnClick({R.id.btn_login_or_reg, R.id.llayout_login_weixin, R.id.llayout_login_qq, R.id.tv_login_visitor})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_login_or_reg:
                IntentUtils.startActivity(this, LoginActivity.class);
                finish();
                break;
            case R.id.llayout_login_weixin:

                break;
            case R.id.llayout_login_qq:

                break;
            case R.id.tv_login_visitor:

                break;
        }
    }
}
