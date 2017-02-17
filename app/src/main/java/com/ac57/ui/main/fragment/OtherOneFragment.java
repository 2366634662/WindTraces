package com.ac57.ui.main.fragment;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.ac57.R;
import com.ac57.framework.base.MVPBaseFragment;
import com.ac57.framework.refresh.RefreshLayout;
import com.ac57.ui.adapter.ReleaseOrInvoledAdapter;
import com.ac57.ui.entity.ReleaseOrInvoledEntity;
import com.ac57.ui.presenter.ReleaseOrInvoledPresneter;
import com.ac57.ui.presenter.view.IReleaseOrInvoledView;
import com.ac57.ui.view.EasyStatusView;

import java.util.List;

import butterknife.BindView;

/**
 * Created on 2017/2/16.
 * Desc :
 */

public class OtherOneFragment extends MVPBaseFragment<ReleaseOrInvoledPresneter, IReleaseOrInvoledView> implements IReleaseOrInvoledView {

    @BindView(R.id.rv_content)
    RecyclerView rvContent;
    @BindView(R.id.refresh_layout)
    RefreshLayout refreshLayout;
    @BindView(R.id.esv_multip_view)
    EasyStatusView esvMultipView;
    private String id;

    private int page = 1;
    ReleaseOrInvoledAdapter adapter;


    public static OtherOneFragment newInstance(String id) {
        Bundle bundle = new Bundle();
        bundle.putString("id", id);
        OtherOneFragment otherOneFragment = new OtherOneFragment();
        otherOneFragment.setArguments(bundle);
        return otherOneFragment;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_other_one;
    }

    @Override
    protected void initView(View convertView, Bundle savedInstanceState) {

    }

    @Override
    protected void initData() {
        id = getArguments().getString("id");
        adapter = new ReleaseOrInvoledAdapter(rvContent);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        rvContent.setLayoutManager(linearLayoutManager);
        rvContent.setAdapter(adapter);
        esvMultipView.setMutipOnClick(() -> {
            page = 1;
            getData();
        });

        refreshLayout.setDelegate(new RefreshLayout.BGARefreshLayoutDelegate() {
            @Override
            public void onBGARefreshLayoutBeginRefreshing(RefreshLayout refreshLayout) {
                page = 1;
                mPresenter.getReleaseOrInvoledListDat(page, "101", id);
            }

            @Override
            public boolean onBGARefreshLayoutBeginLoadingMore(RefreshLayout refreshLayout) {
                page++;
                mPresenter.getReleaseOrInvoledListDat(page, "101", id);
                return true;
            }
        });

    }

    @Override
    protected void getData() {
        mPresenter.getReleaseOrInvoledListDat(page, "101", id);
    }

    @Override
    public void getReleaseOrInvoledListData(List<ReleaseOrInvoledEntity> entities) {
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
    protected ReleaseOrInvoledPresneter initPresenter() {
        return new ReleaseOrInvoledPresneter(this);
    }
}
