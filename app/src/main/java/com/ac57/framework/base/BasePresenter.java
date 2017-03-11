package com.ac57.framework.base;

import java.lang.ref.WeakReference;

/**
 * Created on 2017/2/27.
 * Desc :
 */

public class BasePresenter<T> {

    protected WeakReference<T> mView;

    /**
     * Presenter 和 View 进行绑定
     *
     * @param view
     */
    public void attachView(T view) {
        mView = new WeakReference<T>(view);
    }

    /**
     * Presenter 和 View 解除绑定
     */
    public void detacheView() {
        if (mView != null) {
            mView.clear();
            mView = null;
        }
    }

    public T getView() {
        if (mView != null) {
            return mView.get();
        } else {
            return null;
        }
    }

    public boolean isViewAttached() {
        return mView != null && mView.get() != null;
    }
}
