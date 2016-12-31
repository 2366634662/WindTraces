package com.ac57.ui.main.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.ac57.R;
import com.ac57.framework.base.MVPBaseFragment;
import com.ac57.ui.adapter.InteractEventAdapter;
import com.ac57.ui.entity.InteractEventEntity;
import com.ac57.ui.presenter.InteractEventPresenter;
import com.ac57.ui.presenter.view.IInteractEventView;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.jcodecraeer.xrecyclerview.progressindicator.view.EasyStatusView;

import java.util.List;

import butterknife.BindView;

/**
 * A simple {@link Fragment} subclass.
 */
public class InteractEventFragment extends MVPBaseFragment<InteractEventPresenter, IInteractEventView> implements IInteractEventView {

    InteractEventAdapter adapter;
    @BindView(R.id.xRecyclerView)
    XRecyclerView xRecyclerView;
    @BindView(R.id.esv_multip_view)
    EasyStatusView esvMultipView;
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
        adapter = new InteractEventAdapter(getActivity(), R.layout.item_interact_event);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        xRecyclerView.setLayoutManager(linearLayoutManager);
        xRecyclerView.setAdapter(adapter);
    }

    @Override
    protected void initData() {
        xRecyclerView.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                page = 1;
                mPresenter.getInteractEventData(page);
            }

            @Override
            public void onLoadMore() {
                page++;
                mPresenter.getInteractEventData(page);
            }
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
        esvMultipView.content();
        if (page == 1) {
            adapter.replaceAll(entity);
            xRecyclerView.refreshComplete();
        } else {
            adapter.addAll(entity);
            xRecyclerView.loadMoreComplete();
        }
        if (entity.size() < 10) {
            xRecyclerView.setNoMore(true);
        } else {
            xRecyclerView.setNoMore(false);
        }
        adapter.notifyDataSetChanged();
    }


}
