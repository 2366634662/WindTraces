package com.ac57.framework.base;

import android.content.Context;

/**
 * Created by Du_Li on 2016/12/25.
 */

public abstract class MVPBaseFragment<T extends BasePresenter, M extends BaseViewController> extends BaseFragment {

    protected T mPresenter;

    //初始化
    protected abstract T initPresenter();

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
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

}
