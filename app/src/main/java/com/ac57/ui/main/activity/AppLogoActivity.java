package com.ac57.ui.main.activity;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ac57.R;
import com.ac57.framework.base.BaseMVPActivity;
import com.ac57.framework.tools.SPHelper;
import com.ac57.framework.utils.IntentUtils;
import com.ac57.ui.AppContext;
import com.ac57.ui.entity.UserInfoData;
import com.ac57.ui.presenter.LoginPresenter;
import com.ac57.ui.presenter.view.ILoginActivityView;
import com.ac57.ui.view.customtoast.ToastUtils;
import com.ac57.ui.view.statusbar.StatusBarUtil;
import com.tbruyelle.rxpermissions.RxPermissions;
import com.umeng.socialize.bean.SHARE_MEDIA;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import butterknife.BindView;
import butterknife.OnClick;
import cn.jpush.android.api.JPushInterface;
import cn.jpush.android.api.TagAliasCallback;

public class AppLogoActivity extends BaseMVPActivity<ILoginActivityView, LoginPresenter> implements ILoginActivityView {

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
    public int getLayout() {
        return R.layout.activity_app_logo;
    }

    @Override
    public void initView(Bundle savedInstanceState) {
        super.initView(savedInstanceState);
        StatusBarUtil.setTransparent(this);
    }

    @Override
    public boolean isSupportSwipeBack() {
        return false;
    }

    @Override
    public void initDatas() {
        RxPermissions rxPermissions = new RxPermissions(this);
        rxPermissions
                .request(Manifest.permission.READ_PHONE_STATE)
                .subscribe(granted -> {
                    if (granted) { // Always true pre-M
                        // 同意了权限
                    } else {
                        // 权限被拒绝了
                    }
                });
    }

    ArrayList<String> ex_id;
    ArrayList<String> ex_title;

    @Override
    public void loadData() {
        ex_title = new ArrayList<>();
        ex_title.add("全部");
        ex_id = new ArrayList<>();
        ex_id.add("all");
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
                    Bundle bundle = new Bundle();
                    bundle.putStringArrayList("ex_title", ex_title);
                    bundle.putStringArrayList("ex_id", ex_id);
                    IntentUtils.startActivity(AppLogoActivity.this, MainActivity.class, bundle);
                }
                break;
        }
    }

    @Override
    public void openHome(UserInfoData bean) {
        //保存登陆用户的类型
        saveUser(bean);
        SPHelper.getInstence(this).setUserType(bean.user_model.user_type);
        if (!isVisitor) {
//            ToastUtils.success("登陆成功");
        }
        Bundle bundle = new Bundle();

        for (UserInfoData.ExchangeDataBean s : bean.exchange_data) {
            ex_title.add(s.name);
            ex_id.add(s.id);
        }
        bundle.putStringArrayList("ex_title", ex_title);
        bundle.putStringArrayList("ex_id", ex_id);
        IntentUtils.startActivity(AppLogoActivity.this, MainActivity.class, bundle);
        finish();
    }


    @Override
    protected LoginPresenter initPresenter() {
        return new LoginPresenter(this);
    }

    public void saveUser(UserInfoData bean) {
        SPHelper.getInstence(this).saveUserData(bean.user_model);
        String alias = bean.user_model.id;
        Set<String> set = new HashSet<>();
        set.add("register_data" + bean.user_model.register_data + "");
        set.add("profession_id" + bean.user_model.profession_id);
        set.add("auth_status" + bean.user_model.auth_status);//set.add(user_model.get("register_time")+"");
        JPushInterface.setAliasAndTags(AppLogoActivity.this, alias, set, new TagAliasCallback() {
            @Override
            public void gotResult(int i, String s, Set<String> set) {

            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        mPresenter.threeLoginResult(requestCode, resultCode, data);
    }
}
