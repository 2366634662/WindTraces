package com.ac57.ui.main.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ac57.R;
import com.ac57.framework.base.MVPBaseActivity;
import com.ac57.framework.tools.AppManager;
import com.ac57.framework.utils.IntentUtils;
import com.ac57.ui.AppContext;
import com.ac57.ui.entity.UserInfoData;
import com.ac57.ui.presenter.LoginActivityPresenter;
import com.ac57.ui.presenter.ILoginActivityViewController;
import com.ac57.ui.view.customtoast.ToastUtils;
import com.umeng.socialize.bean.SHARE_MEDIA;

import butterknife.BindView;
import butterknife.OnClick;

public class AppLogoActivity extends MVPBaseActivity<LoginActivityPresenter, ILoginActivityViewController> implements ILoginActivityViewController {

    @BindView(R.id.btn_login_or_reg)
    Button btnLoginOrReg;
    @BindView(R.id.llayout_login_weixin)
    LinearLayout llayoutLoginWeixin;
    @BindView(R.id.llayout_login_qq)
    LinearLayout llayoutLoginQq;
    @BindView(R.id.tv_login_visitor)
    TextView tvLoginVisitor;

    private boolean isVisitor = false;

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
                break;
            case R.id.llayout_login_weixin:
                isVisitor = false;
                if (AppContext.getMyAppContext().verifyNetwork()) {
                    mPresenter.doThreeLogin(SHARE_MEDIA.WEIXIN);
                } else {
                    ToastUtils.success("没有网络,请检查");
                }
                break;
            case R.id.llayout_login_qq:
                isVisitor = false;
                if (AppContext.getMyAppContext().verifyNetwork()) {
                    mPresenter.doThreeLogin(SHARE_MEDIA.QQ);
                } else {
                    ToastUtils.success("没有网络,请检查");
                }
                break;
            case R.id.tv_login_visitor:
                isVisitor = true;
                if (AppContext.getMyAppContext().verifyNetwork()) {
                    mPresenter.doVisitorLogin();
                } else {
                    ToastUtils.success("没有网络,请检查");
                }
                break;
        }
    }

    @Override
    public void openHome(UserInfoData bean) {
        if (!isVisitor) {
            ToastUtils.success("登陆成功");
        }
        Log.e("tag", "'" + bean.toString());
        IntentUtils.startActivity(AppLogoActivity.this, MainActivity.class);
        AppManager.getInstance().killAllActivity();
        finish();
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
    protected LoginActivityPresenter initPresenter() {
        return new LoginActivityPresenter(this, this);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        mPresenter.threeLoginResult(requestCode, resultCode, data);
    }
}
