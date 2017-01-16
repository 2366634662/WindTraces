package com.ac57.framework.base;

import android.app.Activity;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.v4.view.ViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;

import com.ac57.R;
import com.ac57.framework.tools.AppManager;

import org.greenrobot.eventbus.EventBus;

import butterknife.ButterKnife;
import butterknife.Unbinder;


/**
 * Created by Du_Li on 2016/10/25.
 * Desc:
 */

public abstract class BaseActivity extends AppCompatActivity {

    /**
     * get activity layout
     */
    @LayoutRes
    protected abstract int getLayout();

    /**
     * init activity view
     *
     * @param savedInstanceState
     */
    protected abstract void initView(Bundle savedInstanceState);

    /**
     * init data
     */
    protected abstract void initDatas();

    /*
       * load data in onResume
       */
    protected abstract void loadData();

    /**
     * @return true 支持状态栏透明
     */
//    protected abstract boolean isTranslucentStatus();

    protected Activity mContext;
    protected boolean mIsFirstShow = true;
    private Unbinder mUnbinder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = this;
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(getLayout());
        setTranslucentStatus();
//        initWindow();
        try {
            EventBus.getDefault().register(this);
        } catch (Exception e) {
            Log.e("tag", "错误了？" + e.toString());
        }
        AppManager.getInstance().addActivity(this);
        mUnbinder = ButterKnife.bind(this);
        initView(savedInstanceState);
        initDatas();
    }

    public Context getContext() {
        return this;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        AppManager.getInstance().remove(this);
        EventBus.getDefault().unregister(this);
        mUnbinder.unbind();
    }


    @Override
    protected void onResume() {
        if (mIsFirstShow) {
            mIsFirstShow = false;
            loadData();
        }
        super.onResume();
    }


    /**
     * 状态栏透明只有Android 4.4 以上才支持
     */
    public void setTranslucentStatus() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Window window = getWindow();
            WindowManager.LayoutParams layoutParams = window.getAttributes();
            layoutParams.flags |= WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS;
            window.setAttributes(layoutParams);
        }
    }

    /**
     * 沉浸式状态栏
     */
    private Window window;

    public void initWindow() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window = this.getWindow();
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            initWindowBarColor(getStatusColor(0));
            ViewGroup mContentView = (ViewGroup) this.findViewById(Window.ID_ANDROID_CONTENT);
            View mChildView = mContentView.getChildAt(0);
            if (mChildView != null) {
                ViewCompat.setFitsSystemWindows(mChildView, true);
            }
        }
    }

    protected int getStatusColor(int color) {
        if (color == 0) {
            return getResources().getColor(R.color.white);
        }
        return color;
    }

    /**
     * 设置沉浸式状态栏颜色
     */
    protected void initWindowBarColor(int color) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.setStatusBarColor(color);
        }
    }

}
