package com.ac57.ui.main.activity;

import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.ac57.R;
import com.ac57.framework.base.MVPBaseActivity;
import com.ac57.framework.utils.IntentUtils;
import com.ac57.framework.utils.StringUtils;
import com.ac57.ui.custominterface.IEditText;
import com.ac57.ui.presenter.view.IRegisterView;
import com.ac57.ui.presenter.RegisterPresenter;

import butterknife.BindView;
import butterknife.OnClick;

public class RegisterActivity extends MVPBaseActivity<RegisterPresenter, IRegisterView> implements IRegisterView {

    @BindView(R.id.register_one_back)
    ImageView registerOneBack;
    @BindView(R.id.register_one_login)
    TextView registerOneLogin;
    @BindView(R.id.tv_register_one_hint)
    TextView tvRegisterOneHint;
    @BindView(R.id.ed_register_one_tel)
    EditText edRegisterOneTel;
    @BindView(R.id.tv_register_one_bnt)
    TextView tvRegisterOneBnt;

    @Override
    public int getLayout() {
        return R.layout.activity_register;
    }

    @Override
    public void initView(Bundle savedInstanceState) {

    }

    @Override
    public void initDatas() {
        edRegisterOneTel.addTextChangedListener(new IEditText() {
            @Override
            public void afterTextChanged(Editable editable) {
                super.afterTextChanged(editable);
                if (StringUtils.isNotEmpty(edRegisterOneTel.getText().toString())) {
                    tvRegisterOneBnt.setClickable(true);
                    tvRegisterOneBnt.setFocusable(true);
                    tvRegisterOneBnt.setBackgroundResource(R.drawable.login_bnt_bg2);
                    tvRegisterOneHint.setVisibility(View.VISIBLE);
                } else {
                    tvRegisterOneBnt.setClickable(false);
                    tvRegisterOneBnt.setFocusable(false);
                    tvRegisterOneBnt.setBackgroundResource(R.drawable.login_bnt_bg1);
                    tvRegisterOneHint.setVisibility(View.INVISIBLE);
                }
            }
        });
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
