package com.ac57.ui.main.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.ac57.R;
import com.ac57.framework.base.MVPBaseFragment;
import com.ac57.framework.refresh.RefreshLayout;
import com.ac57.ui.adapter.OtherTypesInteractAdapter;
import com.ac57.ui.entity.OtherTypesInteractEntity;
import com.ac57.ui.presenter.OtherTypesInteractPresenter;
import com.ac57.ui.presenter.view.IOtherTypesInteractView;

import java.util.List;

import butterknife.BindView;


/**
 * A simple {@link Fragment} subclass.
 */
public class OtherTypesInteractFragment extends MVPBaseFragment<OtherTypesInteractPresenter, IOtherTypesInteractView> implements IOtherTypesInteractView {

    @BindView(R.id.rv_content)
    RecyclerView xRecyclerView;
    @BindView(R.id.esv_multip_view)
    com.ac57.ui.view.EasyStatusView esvMultipView;
    @BindView(R.id.refresh_layout)
    RefreshLayout mRefreshLayout;
    private String type;
    private int page = 1;
    private OtherTypesInteractAdapter adapter;

    public OtherTypesInteractFragment() {
        // Required empty public constructor
    }

    public static OtherTypesInteractFragment getInstance(String type) {
        Bundle bundle = new Bundle();
        OtherTypesInteractFragment otherTypesInteractFragment = new OtherTypesInteractFragment();
        bundle.putString("type", type);
        otherTypesInteractFragment.setArguments(bundle);
        return otherTypesInteractFragment;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_other_types_interact;
    }

    @Override
    protected void initView(View convertView, Bundle savedInstanceState) {
        type = getArguments().getString("type");
    }

    @Override
    protected void initData() {
        adapter = new OtherTypesInteractAdapter(xRecyclerView);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        xRecyclerView.setLayoutManager(linearLayoutManager);
        xRecyclerView.setAdapter(adapter);

        mRefreshLayout.setDelegate(new RefreshLayout.BGARefreshLayoutDelegate() {
            @Override
            public void onBGARefreshLayoutBeginRefreshing(RefreshLayout refreshLayout) {
                page = 1;
                mPresenter.getOtherTypeInteractData(page, type);
            }

            @Override
            public boolean onBGARefreshLayoutBeginLoadingMore(RefreshLayout refreshLayout) {
                page++;
                mPresenter.getOtherTypeInteractData(page, type);
                return true;
            }
        });
        esvMultipView.setMutipOnClick(() -> {
            getData();
        });
    }

    @Override
    protected void getData() {
        mPresenter.getOtherTypeInteractData(page, type);
    }

    @Override
    public void getOtherTypesInteractDatas(List<OtherTypesInteractEntity> entity) {
        esvMultipView.content();
        if (page == 1) {
            adapter.setData(entity);
            mRefreshLayout.endRefreshing();
        } else {
            adapter.addMoreData(entity);
            mRefreshLayout.endLoadingMore();
        }
        if (entity.size() < 10) {
            mRefreshLayout.setPullUpRefreshEnable(false);
        } else {
            mRefreshLayout.setPullUpRefreshEnable(true);
        }
        adapter.notifyDataSetChanged();
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
    public void showError(String msg) {
        esvMultipView.error();
    }

    @Override
    protected OtherTypesInteractPresenter initPresenter() {
        return new OtherTypesInteractPresenter(this);
    }
}
