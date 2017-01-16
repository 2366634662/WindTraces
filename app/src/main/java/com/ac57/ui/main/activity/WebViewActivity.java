package com.ac57.ui.main.activity;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceResponse;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.TextView;

import com.ac57.R;
import com.ac57.framework.base.BaseActivity;
import com.ac57.framework.tools.SPHelper;
import com.ac57.framework.utils.sign.ProjectUntil;

import butterknife.BindView;
import butterknife.OnClick;

public class WebViewActivity extends BaseActivity {
    @BindView(R.id.iv_web_back)
    ImageView ivWebBack;
    @BindView(R.id.tv_web_title)
    TextView tvWebTitle;
    @BindView(R.id.web_line)
    View webLine;
    @BindView(R.id.webview_all_type)
    WebView webviewAllType;

    private int type;
    private String exp_desc_url;

    @Override
    public int getLayout() {
        return R.layout.activity_web_view;
    }

    @Override
    public void initView(Bundle savedInstanceState) {

    }

    @Override
    public void initDatas() {
        type = getIntent().getIntExtra("type", 0);
        tvWebTitle.setText("风迹");
        tvWebTitle.setTextSize(18);
        if (type == 1) {//积分
            exp_desc_url = SPHelper.getInstence(this).getAnyString("exp_desc_url");
        } else if (type == 2) {//关于
            exp_desc_url = "http://112.74.106.149/wind/Htdoc/Index/web/about?version=" + ProjectUntil.getVersionName();
        } else if (type == 3) {//投资策略

        } else if (type == 4) {//广告
            exp_desc_url = getIntent().getStringExtra("url");
        } else if (type == 5) {//消息里面的活动
            exp_desc_url = this.getIntent().getStringExtra("content_url");
        }
        webviewAllType.setWebViewClient(new WebViewClient() {
            @Override
            public WebResourceResponse shouldInterceptRequest(WebView view, String url) {
                return null;
            }

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                //Log.i("user", url);
                // TODO Auto-generated method stub
                if (url.contains("shidian://invest_list")) {
//                    Intent intent = new Intent(WebViewActivity.this, OtherWebActivity.class);
//                    intent.putExtra("desc_url", desc_url);
//                    startActivity(intent);
                }
                //return super.shouldOverrideUrlLoading(view, url);
                return false;
            }
        });
        webviewAllType.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onReceivedTitle(WebView view, String title) {
                super.onReceivedTitle(view, title);
                if (type != 3) {
                    tvWebTitle.setText(title);
                }
            }
        });
        WebSettings websettings = webviewAllType.getSettings();
        websettings.setJavaScriptEnabled(true); // Warning! You can have XSS
        websettings.setSupportZoom(true);
        websettings.setCacheMode(WebSettings.LOAD_DEFAULT);
        websettings.setDomStorageEnabled(true);
        websettings.setDisplayZoomControls(false);
        websettings.setDefaultTextEncodingName("utf-8");
        webviewAllType.loadUrl(exp_desc_url);
    }

    @Override
    public void loadData() {

    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (event.getKeyCode() == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN && event.getRepeatCount() == 0) {
            deleteView();
        }
        return false;
    }

    @OnClick(R.id.iv_web_back)
    public void onClick() {
        deleteView();
    }

    private void deleteView() {
        if (webviewAllType != null) {
            webviewAllType.removeAllViews();
            webviewAllType.clearHistory();
            webviewAllType.clearCache(true);
            webviewAllType.freeMemory();
            webviewAllType.pauseTimers();
            webviewAllType = null; // Note that mWebView.destroy() and mWebView = null do the exact same thing
        }
        finish();
        overridePendingTransition(R.anim.enter_lefttoright,
                R.anim.exit_lefttoright);
    }
}
