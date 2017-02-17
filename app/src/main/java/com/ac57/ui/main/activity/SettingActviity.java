package com.ac57.ui.main.activity;

import android.Manifest;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.ac57.R;
import com.ac57.framework.base.BaseActivity;
import com.ac57.framework.tools.AppManager;
import com.ac57.framework.utils.IntentUtils;
import com.ac57.ui.utils.DataCleanManager;
import com.ac57.ui.view.ActionSheetDialog;
import com.ac57.ui.view.SuperTextView;
import com.tbruyelle.rxpermissions.RxPermissions;

import butterknife.BindView;
import butterknife.OnClick;
import cn.jpush.android.api.JPushInterface;

/**
 * 设置
 */
public class SettingActviity extends BaseActivity {
    @BindView(R.id.self_stv_tab1)
    SuperTextView selfStvTab1;
    @BindView(R.id.self_stv_tab2)
    SuperTextView selfStvTab2;
    @BindView(R.id.setting_loginout)
    TextView settingLoginout;
    @BindView(R.id.iv_setting_back)
    ImageView ivSettingBack;

    @Override
    public int getLayout() {
        return R.layout.activity_setting_actviity;
    }

    @Override
    public void initView(Bundle savedInstanceState) {

    }



    @Override
    public void initDatas() {
        rxPermissions = new RxPermissions(this);
        rxPermissions.request(Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.WRITE_EXTERNAL_STORAGE)
                .subscribe(granted -> {
                    if (granted) { // Always true pre-M
                        try {
                            selfStvTab2.setRightString(DataCleanManager.getTotalCacheSize(SettingActviity.this));
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    } else {
                        Toast.makeText(SettingActviity.this, "您拒绝了必要的权限,无法获取内存空间", Toast.LENGTH_SHORT).show();
                    }
                });
    }

    private void showDialogs() {
        new ActionSheetDialog(this).builder().addSheetItem("清理", ActionSheetDialog.SheetItemColor.Red, which -> {
            DataCleanManager.clearAllCache(SettingActviity.this);
            try {
                String str = DataCleanManager.getTotalCacheSize(SettingActviity.this);
                selfStvTab2.setRightString(str);
            } catch (Exception e) {
            }
        }).show();
    }

    //去打分
    private static void goToMarket(Context context, String packageName) {
        Uri uri = Uri.parse("market://details?id=" + packageName);
        Intent goToMarket = new Intent(Intent.ACTION_VIEW, uri);
        try {
            goToMarket.setClassName("com.tencent.android.qqdownloader", "com.tencent.pangu.link.LinkProxyActivity");
            context.startActivity(goToMarket);
        } catch (ActivityNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void loadData() {

    }

    RxPermissions rxPermissions;

    @OnClick({R.id.self_stv_tab1, R.id.self_stv_tab2, R.id.setting_loginout, R.id.iv_setting_back})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_setting_back:
                finish();
                break;
            case R.id.self_stv_tab1:
                Bundle bundle = new Bundle();
                bundle.putInt("type", 2);
                IntentUtils.startActivity(this, WebViewActivity.class, bundle);
                break;
            case R.id.self_stv_tab2:
                rxPermissions.request(Manifest.permission.READ_EXTERNAL_STORAGE,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE)
                        .subscribe(granted -> {
                            if (granted) { // Always true pre-M
                                showDialogs();
                            } else {
                                Toast.makeText(SettingActviity.this, "您拒绝了必要的权限，将无法清理", Toast.LENGTH_SHORT).show();
                            }
                        });
                break;
            case R.id.setting_loginout:
                JPushInterface.setAliasAndTags(this, null, null, null);
                IntentUtils.startActivity(this, AppLogoActivity.class);
                AppManager.getInstance().killAllActivity();
                break;
        }
    }
}
