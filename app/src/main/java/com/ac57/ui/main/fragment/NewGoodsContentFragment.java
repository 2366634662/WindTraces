package com.ac57.ui.main.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.ac57.R;
import com.ac57.framework.base.MVPBaseFragment;
import com.ac57.framework.refresh.RefreshLayout;
import com.ac57.ui.adapter.NewGoodsAdapter;
import com.ac57.ui.entity.NewGoodsEntity;
import com.ac57.ui.presenter.NewGoodsContentPresenter;
import com.ac57.ui.presenter.view.INewGoodsContentView;
import com.ac57.ui.view.EasyStatusView;

import java.util.List;

import butterknife.BindView;

/**
 * A simple {@link Fragment} subclass.
 * 新品申购 / 托管 内容呈现
 */
public class NewGoodsContentFragment extends MVPBaseFragment<NewGoodsContentPresenter, INewGoodsContentView> implements INewGoodsContentView {
    @BindView(R.id.rv_content)
    RecyclerView xRecyclerView;
    @BindView(R.id.esv_multip_view)
    EasyStatusView esvMultipView;
    @BindView(R.id.refresh_layout)
    RefreshLayout mRefreshLayout;

    private String type;
    private String ex_id;
    private int page = 1;
    private NewGoodsAdapter adapter;

    public static NewGoodsContentFragment newInstance(String ex_id, String type) {
        Bundle bundle = new Bundle();
        bundle.putString("ex_id", ex_id);
        bundle.putString("type", type);
        NewGoodsContentFragment fragment = new NewGoodsContentFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    public NewGoodsContentFragment() {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_ngpcontent;
    }

    @Override
    protected void initView(View convertView, Bundle savedInstanceState) {
        adapter = new NewGoodsAdapter(xRecyclerView);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        xRecyclerView.setLayoutManager(linearLayoutManager);
        xRecyclerView.setAdapter(adapter);

        mRefreshLayout.setDelegate(new RefreshLayout.BGARefreshLayoutDelegate() {
            @Override
            public void onBGARefreshLayoutBeginRefreshing(RefreshLayout refreshLayout) {
                page = 1;
                mPresenter.getNewGoodsData(page, ex_id, type);
            }

            @Override
            public boolean onBGARefreshLayoutBeginLoadingMore(RefreshLayout refreshLayout) {
                page++;
                mPresenter.getNewGoodsData(page, ex_id, type);
                return true;
            }
        });
        esvMultipView.setMutipOnClick(() -> {
            mPresenter.getNewGoodsData(page, ex_id, type);
            esvMultipView.loading();
        });
    }

    @Override
    protected void initData() {
        type = getArguments().getString("type");
        ex_id = getArguments().getString("ex_id");
    }

    @Override
    protected void getData() {
        mPresenter.getNewGoodsData(page, ex_id, type);
    }

    @Override
    public void showDailog(String msg) {
        if (adapter == null || adapter.getData().size() == 0) {
            esvMultipView.loading();
        }
    }

    @Override
    public void disDailog() {

    }

    @Override
    public void getNewGoodsData(List<NewGoodsEntity> newGoodsEntities) {
        if (newGoodsEntities != null && newGoodsEntities.size() != 0) {
            esvMultipView.content();
            if (page == 1) {
                adapter.setData(newGoodsEntities);
                mRefreshLayout.endRefreshing();
            } else {
                adapter.addMoreData(newGoodsEntities);
                mRefreshLayout.endLoadingMore();
            }
            if (newGoodsEntities.size() < 10) {
                mRefreshLayout.setPullUpRefreshEnable(false);
            } else {
                mRefreshLayout.setPullUpRefreshEnable(true);
            }
            adapter.notifyDataSetChanged();
        } else {
            esvMultipView.empty();
        }
    }

    @Override
    public void showError(String msg) {
        esvMultipView.error();
    }

    @Override
    protected NewGoodsContentPresenter initPresenter() {
        return new NewGoodsContentPresenter(this);
    }
}
