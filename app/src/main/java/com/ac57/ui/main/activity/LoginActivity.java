package com.ac57.ui.main.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ac57.R;
import com.ac57.framework.base.MVPBaseActivity;
import com.ac57.framework.tools.AppManager;
import com.ac57.framework.tools.SPHelper;
import com.ac57.framework.utils.IntentUtils;
import com.ac57.framework.utils.StringUtils;
import com.ac57.ui.entity.UserInfoData;
import com.ac57.ui.presenter.LoginPresenter;
import com.ac57.ui.presenter.view.ILoginActivityView;
import com.ac57.ui.view.edittext.AutoCheckEditText;
import com.ac57.ui.view.edittext.AutoCheckEditTextClass;
import com.ac57.ui.view.edittext.EditTextType;
import com.umeng.socialize.bean.SHARE_MEDIA;

import java.util.ArrayList;
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
    @BindView(R.id.login_tel)
    AutoCheckEditText loginTel;
    @BindView(R.id.login_pass)
    AutoCheckEditText loginPass;
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
    @BindView(R.id.tilayout_login_phone)
    TextInputLayout tilayoutLoginPhone;
    @BindView(R.id.tilayout_login_pass)
    TextInputLayout tilayoutLoginPass;
    AutoCheckEditTextClass aClass_phone;
    AutoCheckEditTextClass aClass_pass;

    @Override
    public int getLayout() {
        return R.layout.activity_login;
    }

    @Override
    public LoginPresenter initPresenter() {
        return new LoginPresenter(this, this);
    }

    boolean phone;
    boolean passWord;

    @Override
    public void initView(Bundle savedInstanceState) {
//        StatusBarUtil.setColorNoTranslucent(this, Color.parseColor("#2c8dff"));
        setStatusBarColor(Color.parseColor("#2c8dff"), 0);

        new AutoCheckEditTextClass(tilayoutLoginPhone, loginTel).checkType(EditTextType.TYPE_OF_MOBILE).setHintEnabled(true).setTextWatcher(hasContent -> {
            phone = hasContent;
            if (phone && passWord && loginTel.getText().toString().trim().length() == 11) {
                loginBnt.setClickable(true);
                loginBnt.setBackgroundResource(R.drawable.login_bnt_bg2);
            } else {
                loginBnt.setClickable(false);
                loginBnt.setBackgroundResource(R.drawable.login_bnt_bg1);
            }
        });
        new AutoCheckEditTextClass(tilayoutLoginPass, loginPass).checkType(EditTextType.TYPE_OF_USER_DEFINE).setHintEnabled(true).setTextWatcher(hasContent -> {
            passWord = hasContent;
            if (phone && passWord) {
                loginBnt.setClickable(true);
                loginBnt.setBackgroundResource(R.drawable.login_bnt_bg2);
            } else {
                loginBnt.setClickable(false);
                loginBnt.setBackgroundResource(R.drawable.login_bnt_bg1);
            }

        });
    }

    @Override
    public void initDatas() {
        if (StringUtils.isNotEmpty(SPHelper.getInstence(this).getLoginPhone())) {
            loginTel.setText(SPHelper.getInstence(this).getLoginPhone());
            loginPass.requestFocus();
        } else {
            loginTel.requestFocus();
        }
    }

    @Override
    public void loadData() {

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
//        ToastUtils.success("登陆成功");
        SPHelper.getInstence(this).setUserType(bean.user_model.user_type);
        SPHelper.getInstence(this).setLoginPhone(loginTel.getText().toString().trim());
        saveUser(bean);
        Bundle bundle = new Bundle();
//        bundle.putParcelableArrayList("",bean.exchange_data);
        ArrayList<String> ex_title = new ArrayList<>();
        ex_title.add("全部");
        ArrayList<String> ex_id = new ArrayList<>();
        ex_id.add("all");
        for (UserInfoData.ExchangeDataBean s : bean.exchange_data) {
            ex_title.add(s.name);
            ex_id.add(s.id);
        }
        bundle.putStringArrayList("ex_title", ex_title);
        bundle.putStringArrayList("ex_id", ex_id);
        IntentUtils.startActivity(LoginActivity.this, MainActivity.class, bundle);
        AppManager.getInstance().killAllActivity();
        finish();
    }

    @Override
    public void showDailog(String msg) {
//        ToastUtils.success(msg);
    }

    @Override
    public void disDailog() {

    }

    @Override
    public void showError(String msg) {
//        ToastUtils.success(msg);
    }

    public void saveUser(UserInfoData bean) {
        SPHelper.getInstence(this).saveUserData(bean.user_model);
        SPHelper.getInstence(this).setPassWord(loginPass.getText().toString().trim());

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
