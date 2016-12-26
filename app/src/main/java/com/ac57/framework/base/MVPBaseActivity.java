package com.ac57.framework.base;

import android.os.Bundle;

/**
 * Created by Du_Li on 2016/12/17.
 */

public abstract class MVPBaseActivity<T extends BasePresenter, M extends BaseViewController> extends BaseActivity {

    protected T mPresenter;

    protected abstract T initPresenter();    //获取到主持人

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //初始化Presenter
        if (mPresenter != null) {
            mPresenter = initPresenter();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        //如果presenter为空的时候，我们需要重新初始化presenter
        if (mPresenter == null) {
            mPresenter = initPresenter();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPresenter = null;
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        if (mPresenter == null)
            mPresenter = initPresenter();
    }
}
