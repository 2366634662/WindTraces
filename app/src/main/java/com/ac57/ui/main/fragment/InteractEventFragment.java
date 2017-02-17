package com.ac57.ui.main.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.ac57.R;
import com.ac57.framework.base.MVPBaseFragment;
import com.ac57.framework.refresh.RefreshLayout;
import com.ac57.ui.adapter.InteractEventAdapter;
import com.ac57.ui.entity.InteractEventEntity;
import com.ac57.ui.presenter.InteractEventPresenter;
import com.ac57.ui.presenter.view.IInteractEventView;
import com.ac57.ui.view.EasyStatusView;

import java.util.List;

import butterknife.BindView;

/**
 * A simple {@link Fragment} subclass.
 */
public class InteractEventFragment extends MVPBaseFragment<InteractEventPresenter, IInteractEventView> implements IInteractEventView {

    InteractEventAdapter adapter;

    @BindView(R.id.rv_content)
    RecyclerView xRecyclerView;
    @BindView(R.id.esv_multip_view)
    EasyStatusView esvMultipView;
    @BindView(R.id.refresh_layout)
    RefreshLayout mRefreshLayout;
    private int page = 1;
    private boolean isFirst = true;

    public InteractEventFragment() {
        // Required empty public constructor
    }

    public static InteractEventFragment getInstance(int type) {
        Bundle bundle = new Bundle();
        bundle.putInt("type", type);
        InteractEventFragment interactEventFragment = new InteractEventFragment();
        interactEventFragment.setArguments(bundle);
        return interactEventFragment;
    }

    @Override
    protected InteractEventPresenter initPresenter() {
        return new InteractEventPresenter(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_interact_event;
    }

    @Override
    protected void initView(View convertView, Bundle savedInstanceState) {
        adapter = new InteractEventAdapter(xRecyclerView);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        xRecyclerView.setLayoutManager(linearLayoutManager);
        xRecyclerView.setAdapter(adapter);
    }

    @Override
    protected void initData() {
        mRefreshLayout.setDelegate(new RefreshLayout.BGARefreshLayoutDelegate() {
            @Override
            public void onBGARefreshLayoutBeginRefreshing(RefreshLayout refreshLayout) {
                page = 1;
                mPresenter.getInteractEventData(page);
            }

            @Override
            public boolean onBGARefreshLayoutBeginLoadingMore(RefreshLayout refreshLayout) {
                page++;
                mPresenter.getInteractEventData(page);
                return true;
            }
        });

        esvMultipView.setMutipOnClick(() -> {
            page = 1;
            getData();
        });
    }

    @Override
    protected void getData() {
        mPresenter.getInteractEventData(page);
    }

    @Override
    public void showDailog(String msg) {
        if (isFirst) {
            isFirst = false;
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
    public void getInteractEventData(List<InteractEventEntity> entity) {
        if (entity.isEmpty()) {
            esvMultipView.empty();
        } else {
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
    }
}
