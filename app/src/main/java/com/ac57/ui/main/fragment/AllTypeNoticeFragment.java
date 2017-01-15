package com.ac57.ui.main.fragment;


import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.ac57.R;
import com.ac57.framework.base.MVPBaseFragment;
import com.ac57.framework.refresh.RefreshLayout;
import com.ac57.ui.adapter.AllTypeNoticeAdapter;
import com.ac57.ui.entity.NoticeEntity;
import com.ac57.ui.presenter.AllTypeNoticePresenter;
import com.ac57.ui.presenter.view.IAllTypeNoticeView;
import com.ac57.ui.view.EasyStatusView;

import java.util.List;

import butterknife.BindView;


/**
 * 公告呈现数据的fragment
 */
public class AllTypeNoticeFragment extends MVPBaseFragment<AllTypeNoticePresenter, IAllTypeNoticeView> implements IAllTypeNoticeView {

    @BindView(R.id.rv_content)
    RecyclerView xRecyclerView;
    @BindView(R.id.esv_multip_view)
    EasyStatusView esvMultipView;
    @BindView(R.id.refresh_layout)
    RefreshLayout mRefreshLayout;

    private AllTypeNoticeAdapter adapter;
    private int page = 1;

    public static AllTypeNoticeFragment newInstance(String ex_id) {
        Bundle bundle = new Bundle();
        bundle.putString("ex_id", ex_id);
        AllTypeNoticeFragment allTypeNoticeFragment = new AllTypeNoticeFragment();
        allTypeNoticeFragment.setArguments(bundle);
        return allTypeNoticeFragment;
    }

    public AllTypeNoticeFragment() {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_all_type_notice;
    }

    String id;

    @Override
    protected void initView(View convertView, Bundle savedInstanceState) {
        id = getArguments().getString("ex_id");
    }

    @Override
    protected void initData() {
        adapter = new AllTypeNoticeAdapter(xRecyclerView);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        xRecyclerView.setLayoutManager(linearLayoutManager);
        xRecyclerView.setAdapter(adapter);
        mRefreshLayout.setDelegate(new RefreshLayout.BGARefreshLayoutDelegate() {
            @Override
            public void onBGARefreshLayoutBeginRefreshing(RefreshLayout refreshLayout) {
                page = 1;
                mPresenter.getAllTypeNoticeData(page, id);
            }

            @Override
            public boolean onBGARefreshLayoutBeginLoadingMore(RefreshLayout refreshLayout) {
                page++;
                mPresenter.getAllTypeNoticeData(page, id);
                return true;
            }
        });
        esvMultipView.setMutipOnClick(() -> {
            page = 1;
            mPresenter.getAllTypeNoticeData(page, id);
            esvMultipView.loading();
        });
    }

    @Override
    protected void getData() {
        mPresenter.getAllTypeNoticeData(page, getArguments().getString("ex_id"));
    }

    @Override
    public void showDailog(String msg) {
        if (adapter == null || adapter.getData().size() == 0) {
            esvMultipView.loading();
        }
    }

    @Override
    public void getAllTypeNoticeData(List<NoticeEntity> noticeEntities) {
        if (noticeEntities != null && noticeEntities.size() != 0) {
            esvMultipView.content();
            if (page == 1) {
                adapter.setData(noticeEntities);
                mRefreshLayout.endRefreshing();
            } else {
                adapter.addMoreData(noticeEntities);
                mRefreshLayout.endLoadingMore();
            }
            if (noticeEntities.get(0).art_list.size() < 10) {
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
    public void disDailog() {

    }

    @Override
    public void showError(String msg) {
        esvMultipView.error();
    }

    @Override
    protected AllTypeNoticePresenter initPresenter() {
        return new AllTypeNoticePresenter(this);
    }

}
