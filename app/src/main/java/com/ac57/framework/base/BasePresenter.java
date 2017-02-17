package com.ac57.framework.base;

import android.app.Activity;

/**
 */

public abstract class BasePresenter<V extends BaseViewController> {

    public V model;

    /**
     * 在子类的构造函数中，设定参数为model，这时候可以presenter调用接口来实现对界面的操作。
     */
    public BasePresenter(V model) {
        this.model = model;
    }

    public BasePresenter(V model, Activity activity) {
        this.model = model;
    }
}
