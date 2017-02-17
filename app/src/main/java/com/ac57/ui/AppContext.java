package com.ac57.ui;

import android.app.Application;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

import com.meiqia.core.MQManager;
import com.meiqia.core.callback.FakeCallback;
import com.meiqia.core.callback.OnInitCallback;
import com.meiqia.meiqiasdk.util.MQConfig;
import com.umeng.socialize.PlatformConfig;
import com.umeng.socialize.UMShareAPI;

import cn.bingoogolapple.swipebacklayout.BGASwipeBackManager;
import cn.jpush.android.api.JPushInterface;

/**
 * Created by Du_Li on 2016/12/17.
 */

public class AppContext extends Application {

    private static AppContext context;

    @Override
    public void onCreate() {
        super.onCreate();
        context = this;
        JPushInterface.init(this);
        UMShareAPI.get(this);
        initShareLogin();
        BGASwipeBackManager.getInstance().init(this);
        initMQ();

    }

    private void initMQ() {
        MQManager.setDebugMode(true);
        String MQ_Key = "2e30490df072686c120da888b0fd6ece";
        MQConfig.init(this, MQ_Key, new OnInitCallback() {
            @Override
            public void onSuccess(String clientId) {
                MQManager.getInstance(AppContext.context).setClientOnlineWithClientId(clientId, new FakeCallback() {
                    @Override
                    public void onSuccess(String s) {
                        Log.i("user", "上线成功");
                    }

                    @Override
                    public void onFailure(int i, String s) {
                        Log.i("user", "上线失败");
                    }
                });
            }

            @Override
            public void onFailure(int code, String message) {
                Log.d("user", "上线失败");
            }
        });
    }


    /**
     * 检查网络
     *
     * @return true 有网
     */
    public boolean verifyNetwork() {
        Context con = getApplicationContext();
        ConnectivityManager connectivityManager = (ConnectivityManager) con
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetInfo = connectivityManager.getActiveNetworkInfo();
        if (activeNetInfo != null) {
            if (activeNetInfo.isConnected()) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    /**
     * 友盟第三方登陆与分享
     */
    private void initShareLogin() {
        PlatformConfig.setWeixin("wxa96be782c0c6c46e", "c6c83bc6aebbdd1758d8af38ca8bb70c");
        PlatformConfig.setQQZone("1104942784", "l73o21HySrKDqmCA");
    }

    public static AppContext getMyAppContext() {
        return context;
    }

    public static Context getAppContext() {
        return context.getApplicationContext();
    }
}
