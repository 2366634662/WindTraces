package com.ac57.ui.main.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ac57.R;
import com.ac57.framework.base.BaseMVPActivity;
import com.ac57.framework.utils.IntentUtils;
import com.ac57.ui.adapter.ExchangeAdapter;
import com.ac57.ui.entity.ExchangeEntity;
import com.ac57.ui.presenter.ExchangePresenter;
import com.ac57.ui.presenter.view.IExchangeView;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 定制的文交所
 */
public class ExchangeActivity extends BaseMVPActivity<IExchangeView, ExchangePresenter> implements IExchangeView {

    @BindView(R.id.iv_title_left)
    ImageView ivTitleLeft;
    @BindView(R.id.iv_title_right)
    TextView ivTitleRight;
    @BindView(R.id.rlayout_exchange_title)
    RelativeLayout rlayoutExchangeTitle;
    @BindView(R.id.tv_exchange_toast)
    TextView tvExchangeToast;
    @BindView(R.id.rv_exchange_content)
    RecyclerView rvExchangeContent;
    @BindView(R.id.tv_exchange_skip)
    TextView tvExchangeSkip;
    @BindView(R.id.tv_exchange_confim)
    TextView tvExchangeConfim;
    @BindView(R.id.llayout_exchange_first)
    LinearLayout llayoutExchangeFirst;

    ExchangeAdapter adapter;

    @Override
    public int getLayout() {
        return R.layout.activity_exchange;
    }

    @Override
    public void initView(Bundle savedInstanceState) {
        super.initView(savedInstanceState);
        setStatusBarColor(Color.WHITE, 30);
    }

    @Override
    public void initDatas() {
        adapter = new ExchangeAdapter(rvExchangeContent);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 4);
        rvExchangeContent.setLayoutManager(gridLayoutManager);
        rvExchangeContent.setAdapter(adapter);
    }

    @Override
    public void loadData() {
        mPresenter.getExchangeData();
    }


    @Override
    public void getExchangeData(List<ExchangeEntity> entities) {
        if (!entities.isEmpty()) {
            adapter.setData(entities);
        }

    }


    @Override
    protected ExchangePresenter initPresenter() {
        return new ExchangePresenter();
    }

    @OnClick({R.id.iv_title_left, R.id.iv_title_right, R.id.tv_exchange_skip, R.id.tv_exchange_confim})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_title_left:
                IntentUtils.finishActivity(this);
                break;
            case R.id.iv_title_right:
                break;
            case R.id.tv_exchange_skip:
                break;
            case R.id.tv_exchange_confim:
                break;
        }
    }
}
