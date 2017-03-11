package com.ac57.framework.base;

import android.widget.Toast;

/**
 * Function:
 * Desc:
 */

public abstract class BaseMVPFragment<V, T extends BasePresenter<V>> extends BaseFragment implements IBaseStatusView {

    protected T mPresenter;

    @Override
    public void initPersenter() {
        //创建代理
        mPresenter = initPresenter();
        //创建关联
        mPresenter.attachView((V) this);
        super.initPersenter();
    }

    protected abstract T initPresenter();

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (mPresenter != null) {
            mPresenter.detacheView();
        }
    }

    @Override
    public void noNet() {
        if (easyStatusView != null) {
            easyStatusView.noNet();
        }
        Toast.makeText(getActivity(), "网络错误！", Toast.LENGTH_SHORT).show();
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
        Toast.makeText(getActivity(), msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void content() {
        if (easyStatusView != null) {
            easyStatusView.content();
        }
    }
}
