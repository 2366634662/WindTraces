package com.ac57.wxapi;


import android.content.Intent;

import com.umeng.socialize.UMAuthListener;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.utils.Log;
import com.umeng.socialize.weixin.view.WXCallbackActivity;

import java.util.Map;


public class WXEntryActivity extends WXCallbackActivity {
    @Override
    protected void handleIntent(Intent intent){

        mWxHandler.setAuthListener(new UMAuthListener() {
            @Override
            public void onComplete(SHARE_MEDIA platform, int action, Map<String, String> data) {
                Log.e("UMWXHandler fsdfsdfs");
            }

            @Override
            public void onError(SHARE_MEDIA platform, int action, Throwable t) {

            }

            @Override
            public void onCancel(SHARE_MEDIA platform, int action) {

            }
        });
        super.handleIntent(intent);
    }

}
