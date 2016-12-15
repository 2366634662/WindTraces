package com.ac57.framework.base;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.v4.view.ViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;

import com.ac57.R;
import com.ac57.framework.tools.AppManager;

import butterknife.ButterKnife;
import butterknife.Unbinder;


/**
 * Created by Du_Li on 2016/10/25.
 * Desc:
 */

public abstract class BaseActivity extends AppCompatActivity implements View.OnClickListener {
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
        initWindow();
//        EventBus.getDefault().register(this);
        AppManager.getInstance().addActivity(this);
        mUnbinder = ButterKnife.bind(this);
        initView(savedInstanceState);
        initDatas();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        AppManager.getInstance().remove(this);
//        EventBus.getDefault().unregister(this);
        mUnbinder.unbind();
    }

    /**
     * 沉浸式状态栏
     */
    private Window window;

    private void initWindow() {
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

    protected <T extends View> T findView(int id) {

        T view = (T) findViewById(id);

        return view;
    }

    protected void setOnClick(Object... objects) {

        for (Object object : objects) {
            if (object instanceof View) {
                ((View) object).setOnClickListener(this);
            }
            if (object instanceof Integer) {
                findView((int) object).setOnClickListener(this);
            }
        }

    }

    @Override
    protected void onResume() {
        if (mIsFirstShow) {
            mIsFirstShow = false;
            loadData();
        }
        super.onResume();
    }
}
