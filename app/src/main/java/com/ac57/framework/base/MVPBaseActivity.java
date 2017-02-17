package com.ac57.framework.base;

import android.os.Bundle;

/**
 */

public abstract class MVPBaseActivity<T extends BasePresenter, M extends BaseViewController> extends BaseActivity {

    protected T mPresenter;

    protected abstract T initPresenter();    //

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //初始化Presenter
        if (mPresenter == null) {
            mPresenter = initPresenter();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        //如果presenter为空的时候，重新初始化presenter
        if (mPresenter == null) {
            mPresenter = initPresenter();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mPresenter = null;
    }

    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        if (mPresenter == null)
            mPresenter = initPresenter();
    }
}
