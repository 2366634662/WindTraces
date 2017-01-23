package com.ac57.ui.main.activity;

import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.ac57.R;
import com.ac57.framework.base.MVPBaseActivity;
import com.ac57.framework.utils.IntentUtils;
import com.ac57.ui.presenter.RegisterPresenter;
import com.ac57.ui.presenter.view.IRegisterView;
import com.ac57.ui.view.edittext.AutoCheckEditText;
import com.ac57.ui.view.edittext.AutoCheckEditTextClass;
import com.ac57.ui.view.edittext.EditTextType;

import butterknife.BindView;
import butterknife.OnClick;

public class RegisterActivity extends MVPBaseActivity<RegisterPresenter, IRegisterView> implements IRegisterView {

    @BindView(R.id.register_one_back)
    ImageView registerOneBack;
    @BindView(R.id.register_one_login)
    TextView registerOneLogin;
    @BindView(R.id.tv_register_one_bnt)
    TextView tvRegisterOneBnt;
    @BindView(R.id.acet_register_phone)
    AutoCheckEditText acetRegisterPhone;
    @BindView(R.id.tilayout_register)
    TextInputLayout tilayoutRegister;
    AutoCheckEditTextClass aClass;

    @Override
    public int getLayout() {
        return R.layout.activity_register;
    }

    @Override
    public void initView(Bundle savedInstanceState) {
        aClass = new AutoCheckEditTextClass(tilayoutRegister, acetRegisterPhone);
        aClass.checkType(EditTextType.TYPE_OF_MOBILE).setMaxLength(11, true).setTextWatcher(hasContent -> {
            if (hasContent) {
                tvRegisterOneBnt.setClickable(true);
                tvRegisterOneBnt.setBackgroundResource(R.drawable.login_bnt_bg2);
            } else {
                tvRegisterOneBnt.setClickable(false);
                tvRegisterOneBnt.setBackgroundResource(R.drawable.login_bnt_bg1);
            }
        });
    }

    @Override
    public void initDatas() {

    }

    @Override
    public void loadData() {

    }

    @OnClick({R.id.register_one_back, R.id.register_one_login, R.id.tv_register_one_bnt})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.register_one_back:
                IntentUtils.finishActivity(this);
                break;
            case R.id.register_one_login:
                IntentUtils.finishActivity(this);
                break;
            case R.id.tv_register_one_bnt:

                break;
        }
    }

    @Override
    protected RegisterPresenter initPresenter() {
        return new RegisterPresenter(this);
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
}
