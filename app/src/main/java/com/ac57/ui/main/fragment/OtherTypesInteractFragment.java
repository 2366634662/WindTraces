package com.ac57.ui.main.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.View;

import com.ac57.R;
import com.ac57.framework.base.MVPBaseFragment;
import com.ac57.ui.adapter.OtherTypesInteractAdapter;
import com.ac57.ui.entity.OtherTypesInteractEntity;
import com.ac57.ui.presenter.OtherTypesInteractPresenter;
import com.ac57.ui.presenter.view.IOtherTypesInteractView;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.jcodecraeer.xrecyclerview.progressindicator.view.EasyStatusView;

import java.util.List;

import butterknife.BindView;

/**
 * A simple {@link Fragment} subclass.
 */
public class OtherTypesInteractFragment extends MVPBaseFragment<OtherTypesInteractPresenter, IOtherTypesInteractView> implements IOtherTypesInteractView {

    @BindView(R.id.xRecyclerView)
    XRecyclerView xRecyclerView;
    @BindView(R.id.esv_multip_view)
    EasyStatusView esvMultipView;
    private String type;
    private int page = 1;
    private OtherTypesInteractAdapter adapter;
    private boolean isFirst = true;

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
        Log.e("tag", "type = " + type);

    }

    @Override
    protected void initData() {
        adapter = new OtherTypesInteractAdapter(getActivity(), R.layout.item_other_type_interact);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        xRecyclerView.setLayoutManager(linearLayoutManager);
        xRecyclerView.setAdapter(adapter);
        xRecyclerView.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                page = 1;
                mPresenter.getOtherTypeInteractData(page, type);
            }

            @Override
            public void onLoadMore() {
                page++;
                mPresenter.getOtherTypeInteractData(page, type);
            }
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
    protected OtherTypesInteractPresenter initPresenter() {
        return new OtherTypesInteractPresenter(this);
    }
}
