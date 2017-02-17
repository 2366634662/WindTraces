package com.ac57.ui.main.activity;

import android.app.AlertDialog;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.JsResult;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ac57.R;
import com.ac57.framework.base.BaseActivity;
import com.ac57.framework.utils.IntentUtils;
import com.ac57.ui.entity.OptionDetailListEntity;
import com.ac57.ui.view.EasyStatusView;

import java.util.Timer;
import java.util.TimerTask;

import butterknife.BindView;
import butterknife.OnClick;

import static com.ac57.R.id.read_collect_info_ww;


/**
 * Function:
 * Desc:
 */

public class ReadCollectInfoActivity extends BaseActivity {
    @BindView(R.id.read_collect_info_back)
    ImageView readCollectInfoBack;
    @BindView(R.id.read_collect_info_title)
    TextView readCollectInfoTitle;
    @BindView(R.id.read_collect_info_name)
    TextView readCollectInfoName;
    @BindView(R.id.read_collect_info_num)
    TextView readCollectInfoNum;
    @BindView(R.id.read_collect_info_gg)
    TextView readCollectInfoGg;
    @BindView(read_collect_info_ww)
    WebView readCollectInfoWw;
    @BindView(R.id.content_view)
    LinearLayout contentView;
    @BindView(R.id.mess_mult)
    EasyStatusView messMult;
    @BindView(R.id.read_collect_info_pinglun)
    LinearLayout readCollectInfoPinglun;
    @BindView(R.id.read_collect_info_share)
    LinearLayout readCollectInfoShare;

    OptionDetailListEntity model;
    private String urls;


    @Override
    public int getLayout() {
        return R.layout.activity_read_collect_info;
    }

    @Override
    public void initView(Bundle savedInstanceState) {
        setStatusBarColor(Color.parseColor("#242438"));
    }

    @Override
    public void initDatas() {
        model = (OptionDetailListEntity) getIntent().getSerializableExtra("content");

        readCollectInfoTitle.setText(model.trade_name);
        readCollectInfoName.setText(model.exc_info.name);
        try {
            readCollectInfoName.setBackgroundColor(Color.parseColor("#" + model.exc_info.icon_show_color));
        } catch (Exception e) {
            readCollectInfoName.setBackgroundColor(Color.BLACK);
        }
        readCollectInfoNum.setText(model.trade_code);

        urls = model.content_url;

        WebSettings websettings = readCollectInfoWw.getSettings();
        setWebViewAttribute(websettings);
        try {
            readCollectInfoWw.loadUrl(urls);
        } catch (Exception e) {
        }

        messMult.loading();
    }

    public void setWebViewAttribute(WebSettings webSetting) {
        readCollectInfoWw.setWebViewClient(new CustomWebViewClient());
        readCollectInfoWw.setWebChromeClient(new CustomWebChromeClient());
        //设置比例缩放
        //  read_collect_info_ww.setInitialScale(50);
        webSetting.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.NARROW_COLUMNS);
        webSetting.setJavaScriptEnabled(true);
        //适应手机屏幕
        webSetting.setUseWideViewPort(true);
        webSetting.setLoadWithOverviewMode(true);
        webSetting.setSupportZoom(true);
        //无限放大
        webSetting.setBuiltInZoomControls(true);
        //放大设置
        webSetting.setDisplayZoomControls(false);
    }

    @Override
    public void loadData() {

    }

    Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            setLoads(2);
            super.handleMessage(msg);
        }
    };

    private class CustomWebViewClient extends WebViewClient {
        @Override
        public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
            setLoads(3);
        }

        @Override
        public void onLoadResource(WebView webView, String s) {
            super.onLoadResource(webView, s);
            //            LogUtils.d("======" + s);
        }

        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            super.onPageStarted(view, url, favicon);
            setLoads(1);
        }

        @Override
        public void onPageFinished(WebView view, String url) {
            super.onPageFinished(view, url);

            new Timer().schedule(new TimerTask() {
                @Override
                public void run() {
                    mHandler.sendMessage(new Message());
                }
            }, 2000);
        }

        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            if (!TextUtils.isEmpty(url)) {
                //                LogUtils.d("shouldOverrideUrlLoading---" + url);
                String starts = "";
                if (url.startsWith(starts)) {
                    //                    parseGame(url);
                } else {
                     /*如果是apk文件结尾的启动外部浏览器打开*/
                    if (url.endsWith(".apk")) {
                        //                        Tools.toast("正在打开外部浏览器下载文件！");
                        //                        IntentUtils.startBrowser(WebActivity.this, url);
                        finish();
                        return true;
                    } else {
                        if (url.contains("?")) {
                            url = url + "&time=" + System.currentTimeMillis();
                        } else {
                            url = url + "?time=" + System.currentTimeMillis();
                        }
                        view.loadUrl(url);
                        //                        LogUtils.d("webview loaded url success");
                        return true;
                    }
                }
                return true;
            }
            return super.shouldOverrideUrlLoading(view, url);
        }
    }

    private class CustomWebChromeClient extends WebChromeClient {

        private AlertDialog alertDialog;

        @Override
        public void onReceivedTitle(WebView view, String showTitle) {
            super.onReceivedTitle(view, showTitle);
            //            LogUtils.d("页面内的头部地址是----" + showTitle);
            /*如果没有设置页面头部则显示网页的标题信息*/
            //            setTopTitle(showTitle);
            if (TextUtils.isEmpty(showTitle) && showTitle.toLowerCase().contains("error")) {
                new Timer().schedule(new TimerTask() {
                    @Override
                    public void run() {
                        mHandler.sendMessage(new Message());
                    }
                }, 2000);
            } else {
                setLoads(3);
            }

        }

        @Override
        public boolean onJsAlert(WebView view, String url, final String message, final JsResult result) {
            AlertDialog.Builder builder = new AlertDialog.Builder(getApplicationContext());
            builder.setTitle("提示");
            builder.setMessage(message);
            builder.setPositiveButton(android.R.string.ok, (dialog, which) -> result.confirm());
            builder.setCancelable(false);
            alertDialog = builder.create();
            alertDialog.show();
            return true;
        }

        @Override
        public void onCloseWindow(WebView window) {
            super.onCloseWindow(window);
            if (null != alertDialog && alertDialog.isShowing()) {
                alertDialog.dismiss();
            }
        }
    }

    private void setLoads(int stat) {
        if (stat == 1) {
            messMult.loading();
        } else if (stat == 2) {
            messMult.content();
        } else if (stat == 3) {
            messMult.error();
        }
    }

    @OnClick({R.id.read_collect_info_back, R.id.read_collect_info_pinglun, R.id.read_collect_info_share})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.read_collect_info_back:
                deleteView();
                break;
            case R.id.read_collect_info_pinglun:
                break;
            case R.id.read_collect_info_share:
                break;
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (event.getKeyCode() == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN && event.getRepeatCount() == 0) {
            deleteView();
        }
        return false;
    }

    private void deleteView() {
        if (readCollectInfoWw != null) {
            readCollectInfoWw.removeAllViews();
            readCollectInfoWw.clearHistory();
            readCollectInfoWw.clearCache(true);
            readCollectInfoWw.freeMemory();
            readCollectInfoWw.pauseTimers();
            readCollectInfoWw = null; // Note that mWebView.destroy() and mWebView = null do the exact same thing
        }
        IntentUtils.finishActivity(this);
    }

}
