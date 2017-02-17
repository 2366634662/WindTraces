package com.ac57.ui.main.activity;

import android.Manifest;
import android.os.Bundle;

import com.ac57.R;
import com.ac57.framework.base.BaseActivity;
import com.ac57.framework.tools.SPHelper;
import com.ac57.framework.utils.IntentUtils;
import com.ac57.ui.view.statusbar.StatusBarUtil;
import com.tbruyelle.rxpermissions.RxPermissions;

import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;

public class WelcomeActivity extends BaseActivity {

    @Override
    public int getLayout() {
        return R.layout.activity_welcome;
    }

    @Override
    public void initView(Bundle savedInstanceState) {
        StatusBarUtil.setTransparent(this);
    }

    @Override
    public boolean isSupportSwipeBack() {
        return false;
    }

    RxPermissions rxPermissions;

    @Override
    public void initDatas() {
        rxPermissions = new RxPermissions(this);
        rxPermissions
                .request(Manifest.permission.READ_PHONE_STATE)
                .subscribe(granted -> {
                    if (granted) { // Always true pre-M
                        // 同意了权限
//                        new Timer().schedule(new TimerTask() {
//                            @Override
//                            public void run() {
//
//                            }
//                        }, 2000);
                        Observable.timer(2, TimeUnit.SECONDS).observeOn(AndroidSchedulers.mainThread()).subscribe(aLong -> {
                            if (SPHelper.getInstence(WelcomeActivity.this).isFirst()) {
                                IntentUtils.startActivity(WelcomeActivity.this, GuideActivity.class);
                                finish();
                            } else {
                                IntentUtils.startActivity(WelcomeActivity.this, AppLogoActivity.class);
                                finish();
                            }
                        });
//                        Observable.just("1").delay(2000,TimeUnit.MILLISECONDS).subscribe(new Action1<String>() {
//                            @Override
//                            public void call(String s) {
//
//                            }
//                        });

//                        Observable.just("1").delay(2000, TimeUnit.MILLISECONDS).subscribe(new Subscriber<String>() {
//                            @Override
//                            public void onCompleted() {
//
//                            }
//
//                            @Override
//                            public void onError(Throwable e) {
//
//                            }
//
//                            @Override
//                            public void onNext(String s) {
//
//                            }
//                        });

                    } else {
                        // 权限被拒绝了
                        IntentUtils.finishActivity(WelcomeActivity.this);
                    }
                });
    }

    @Override
    public void loadData() {

    }
}
