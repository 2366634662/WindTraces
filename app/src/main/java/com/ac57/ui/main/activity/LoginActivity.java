package com.ac57.ui.main.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ac57.R;
import com.ac57.framework.base.MVPBaseActivity;
import com.ac57.framework.tools.AppManager;
import com.ac57.framework.utils.IntentUtils;
import com.ac57.ui.entity.UserInfoData;
import com.ac57.ui.presenter.LoginPresenter;
import com.ac57.ui.presenter.view.ILoginActivityView;
import com.ac57.ui.view.ClearEditText;
import com.ac57.ui.view.customtoast.ToastUtils;
import com.umeng.socialize.bean.SHARE_MEDIA;

import java.util.HashSet;
import java.util.Set;

import butterknife.BindView;
import butterknife.OnClick;
import cn.jpush.android.api.JPushInterface;
import cn.jpush.android.api.TagAliasCallback;

public class LoginActivity extends MVPBaseActivity<LoginPresenter, ILoginActivityView> implements ILoginActivityView {
    @BindView(R.id.iv_login_back)
    ImageView ivLoginBack;
    @BindView(R.id.tv_login_register)
    TextView tvLoginRegister;
    @BindView(R.id.tv_login_phone_hint)
    TextView tvLoginPhoneHint;
    @BindView(R.id.login_tel)
    ClearEditText loginTel;
    @BindView(R.id.login_pass_total)
    TextView loginPassTotal;
    @BindView(R.id.login_pass)
    ClearEditText loginPass;
    @BindView(R.id.tv_login_forget)
    TextView loginForget;
    @BindView(R.id.login_center)
    LinearLayout loginCenter;
    @BindView(R.id.login_bnt)
    TextView loginBnt;
    @BindView(R.id.login_other)
    LinearLayout loginOther;
    @BindView(R.id.login_weixin)
    ImageView loginWeixin;
    @BindView(R.id.login_qq)
    ImageView loginQq;
    @BindView(R.id.login_bottom)
    LinearLayout loginBottom;
    @BindView(R.id.login_bottoms)
    LinearLayout loginBottoms;
    @BindView(R.id.login_view)
    LinearLayout loginView;


    @Override
    protected int getLayout() {
        return R.layout.activity_login;
    }

    @Override
    protected LoginPresenter initPresenter() {
        return new LoginPresenter(this, this);
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


    @OnClick({R.id.iv_login_back, R.id.tv_login_register, R.id.tv_login_forget, R.id.login_bnt, R.id.login_weixin, R.id.login_qq})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_login_back:
                IntentUtils.finishActivity(this);
                break;
            case R.id.tv_login_register:
                IntentUtils.startActivity(this, RegisterActivity.class);
                break;
            case R.id.tv_login_forget:

                break;
            case R.id.login_bnt:
                mPresenter.doLogin(loginTel.getText().toString().trim(), loginPass.getText().toString().trim());
//                String phoneNum = "";
//                String password = "";
//                String rand = ProjectUntil.randString(8);
//                String login_sign = ProUntil.getLoginSign(rand, phoneNum, password);
//                UserRepository.getInstance().getUserInfoData(phoneNum, rand, login_sign).subscribe(new DefaultSubscriber<UserInfoData>() {
//                    @Override
//                    public void _onNext(UserInfoData entity) {
//
//                        ToastManager.getToastManager().showToast(new ToastBean("登陆成功", MyToast.Types.OK, 1, entity), new ToastManager.ToastManagerListener() {
//                            @Override
//                            public void start(ToastBean bean) {
//                                UserInfoData userInfoData = (UserInfoData) bean.getObj();
//
//                            }
//
//                            @Override
//                            public void stop(ToastBean bean) {
//                                IntentUtils.startActivity(LoginActivity.this, MainActivity.class);
//                                finish();
//                            }
//                        });
//
//                    }
//
//                    @Override
//                    public void _onError(String e) {
//                        ToastManager.getToastManager().showToast(new ToastBean("用户名或密码错误", MyToast.Types.ERREY), null);
//                    }
//                });
                break;
            case R.id.login_weixin:
                mPresenter.doThreeLogin(SHARE_MEDIA.WEIXIN);
                break;
            case R.id.login_qq:
                mPresenter.doThreeLogin(SHARE_MEDIA.QQ);
                break;
        }
    }

    @Override
    public void openHome(UserInfoData bean) {
        ToastUtils.success("登陆成功");
        Log.e("tag", "'" + bean.toString());
        IntentUtils.startActivity(LoginActivity.this, MainActivity.class);
        AppManager.getInstance().killAllActivity();
        finish();
    }

    @Override
    public void showDailog(String msg) {
        ToastUtils.success(msg);
    }

    @Override
    public void disDailog() {

    }

    @Override
    public void showError(String msg) {
        ToastUtils.success(msg);
    }

    public void saveUser(UserInfoData bean) {
//        loginModel.saveUser(data, activitys, passwd);
        String alias = bean.user_model.id;
        Set<String> set = new HashSet<>();
        set.add("register_data" + bean.user_model.register_data + "");
        set.add("profession_id" + bean.user_model.profession_id);
        set.add("auth_status" + bean.user_model.auth_status);//set.add(user_model.get("register_time")+"");
        JPushInterface.setAliasAndTags(LoginActivity.this, alias, set, new TagAliasCallback() {
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
