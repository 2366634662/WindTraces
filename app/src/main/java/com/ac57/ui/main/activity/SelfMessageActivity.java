package com.ac57.ui.main.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.ac57.R;
import com.ac57.framework.base.MVPBaseActivity;
import com.ac57.framework.refresh.RefreshLayout;
import com.ac57.framework.utils.IntentUtils;
import com.ac57.ui.adapter.SelfMessageAdapter;
import com.ac57.ui.entity.SelfMessageEntity;
import com.ac57.ui.presenter.SelfMessagePresenter;
import com.ac57.ui.presenter.view.ISelfMessageView;
import com.ac57.ui.view.EasyStatusView;
import com.ac57.ui.view.customtoast.ToastUtils;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class SelfMessageActivity extends MVPBaseActivity<SelfMessagePresenter, ISelfMessageView> implements ISelfMessageView {

    @BindView(R.id.rv_content)
    RecyclerView rvContent;
    @BindView(R.id.refresh_layout)
    RefreshLayout refreshLayout;
    @BindView(R.id.esv_multip_view)
    EasyStatusView esvMultipView;
    @BindView(R.id.iv_title_left)
    ImageView ivTitleLeft;
    @BindView(R.id.iv_title_center)
    ImageView ivTitleCenter;
    @BindView(R.id.iv_title_right)
    ImageView ivTitleRight;
    @BindView(R.id.tv_title_center)
    TextView tvTitleCenter;

    private int page = 1;
    private SelfMessageAdapter adapter;

    @Override
    public int getLayout() {
        return R.layout.activity_self_message;
    }

    @Override
    public void initView(Bundle savedInstanceState) {
        setStatusBarColor(Color.WHITE);
        ivTitleCenter.setVisibility(View.GONE);
        tvTitleCenter.setVisibility(View.VISIBLE);
        tvTitleCenter.setText("消息中心");
        ivTitleLeft.setImageResource(R.drawable.mq_ic_back);
        ivTitleRight.setImageResource(R.drawable.news_service);
    }

    @Override
    public void initDatas() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        rvContent.setLayoutManager(linearLayoutManager);
        adapter = new SelfMessageAdapter(rvContent);
        rvContent.setAdapter(adapter);
        esvMultipView.setMutipOnClick(() -> {
            page = 1;
            loadData();
        });
        refreshLayout.setDelegate(new RefreshLayout.BGARefreshLayoutDelegate() {
            @Override
            public void onBGARefreshLayoutBeginRefreshing(RefreshLayout refreshLayout) {
                page = 1;
                mPresenter.getSelfMessageData(page);
            }

            @Override
            public boolean onBGARefreshLayoutBeginLoadingMore(RefreshLayout refreshLayout) {
                page++;
                mPresenter.getSelfMessageData(page);
                return true;
            }
        });

    }

    @Override
    public void loadData() {
        mPresenter.getSelfMessageData(page);
    }

    @Override
    public void getSelfMessageData(List<SelfMessageEntity> entities) {
        if (entities.isEmpty()) {
            esvMultipView.empty();
        } else {
            esvMultipView.content();
            if (page == 1) {
                adapter.setData(entities);
                refreshLayout.endRefreshing();
            } else {
                adapter.addMoreData(entities);
                refreshLayout.endLoadingMore();
            }
            if (entities.size() < 10) {
                refreshLayout.setPullUpRefreshEnable(false);
            } else {
                refreshLayout.setPullUpRefreshEnable(true);
            }
        }
    }

    @Override
    public void showDailog(String msg) {
        if (adapter.getItemCount() == 0) {
            esvMultipView.loading();
        }
    }

    @Override
    public void disDailog() {

    }

    @Override
    public void showError(String msg) {
        esvMultipView.error();
    }

    @Override
    protected SelfMessagePresenter initPresenter() {
        return new SelfMessagePresenter(this);
    }


    @OnClick({R.id.iv_title_left, R.id.tv_title_center, R.id.iv_title_right})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_title_left:
                IntentUtils.finishActivity(this);
                break;
            case R.id.iv_title_right:
                ToastUtils.success("点击了右边");
                break;
        }
    }
}
