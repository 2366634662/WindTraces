package com.ac57.framework.base;

import android.os.Bundle;

/**
 * Function:
 * Desc:
 */

public abstract class BaseMVPActivity<V, T extends BasePresenter<V>> extends BaseActivity implements IBaseStatusView {

    protected T mPresenter;

    @Override
    public void initView(Bundle bundle) {
        //创建代理
        mPresenter = initPresenter();
        //创建关联
        mPresenter.attachView((V) this);
    }

    protected abstract T initPresenter();

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mPresenter != null) {
            mPresenter.detacheView();
        }
    }


    @Override
    public void noNet() {
        if (easyStatusView != null) {
            easyStatusView.noNet();
        }
//        ToastUtil.show("请检查当前网络状态");
    }

    @Override
    public void empty() {
        if (easyStatusView != null) {
            easyStatusView.empty();
        }
    }

    @Override
    public void loading() {
        if (easyStatusView != null) {
            easyStatusView.loading();
        }
    }

    @Override
    public void error(String msg) {
        if (easyStatusView != null) {
            easyStatusView.error();
        }
//        ToastUtil.show(msg);
    }

    @Override
    public void content() {
        if (easyStatusView != null) {
            easyStatusView.content();
        }
    }
}
