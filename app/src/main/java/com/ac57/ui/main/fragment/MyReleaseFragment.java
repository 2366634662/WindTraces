package com.ac57.ui.main.fragment;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.ac57.R;
import com.ac57.framework.base.MVPBaseFragment;
import com.ac57.framework.refresh.RefreshLayout;
import com.ac57.framework.tools.SPHelper;
import com.ac57.ui.adapter.MyReleaseAdapter;
import com.ac57.ui.entity.MyReleaseAndInvoledEntity;
import com.ac57.ui.presenter.MyReleaseAndInvoledPresenter;
import com.ac57.ui.presenter.view.IMyReleaseAndInvoledView;
import com.ac57.ui.view.EasyStatusView;

import java.util.List;

import butterknife.BindView;

/**
 * Created on 2017/2/5.
 * Desc :我的发布
 */

public class MyReleaseFragment extends MVPBaseFragment<MyReleaseAndInvoledPresenter, IMyReleaseAndInvoledView> implements IMyReleaseAndInvoledView {

    @BindView(R.id.rv_content)
    RecyclerView rvContent;
    @BindView(R.id.refresh_layout)
    RefreshLayout refreshLayout;
    @BindView(R.id.esv_multip_view)
    EasyStatusView esvMultipView;

    private String type;
    private int page;
    private String id;
    private MyReleaseAdapter adapter;

    public static MyReleaseFragment newInstance(String type) {
        Bundle bundle = new Bundle();
        bundle.putString("type", type);
        MyReleaseFragment fragment = new MyReleaseFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_release;
    }

    @Override
    protected void initView(View convertView, Bundle savedInstanceState) {

    }

    @Override
    protected void initData() {
        type = getArguments().getString("type");
        id = SPHelper.getInstence(getActivity()).getUserId();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        rvContent.setLayoutManager(linearLayoutManager);
        adapter = new MyReleaseAdapter(rvContent);
        rvContent.setAdapter(adapter);
        esvMultipView.setMutipOnClick(() -> {
            page = 1;
            getData();
        });
        refreshLayout.setDelegate(new RefreshLayout.BGARefreshLayoutDelegate() {
            @Override
            public void onBGARefreshLayoutBeginRefreshing(RefreshLayout refreshLayout) {
                page = 1;
                mPresenter.getMyReleaseData(page, type, id);
            }

            @Override
            public boolean onBGARefreshLayoutBeginLoadingMore(RefreshLayout refreshLayout) {
                page++;
                mPresenter.getMyReleaseData(page, type, id);
                return true;
            }
        });

    }

    @Override
    protected void getData() {
        mPresenter.getMyReleaseData(page, type, id);
    }

    @Override
    public void getMyReleaseData(List<MyReleaseAndInvoledEntity> entities) {
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
    protected MyReleaseAndInvoledPresenter initPresenter() {
        return new MyReleaseAndInvoledPresenter(this);
    }
}
