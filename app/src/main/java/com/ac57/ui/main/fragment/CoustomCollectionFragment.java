package com.ac57.ui.main.fragment;


import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.ac57.R;
import com.ac57.framework.base.BaseMVPFragment;
import com.ac57.framework.refresh.RefreshLayout;
import com.ac57.ui.adapter.CoustomCollectionAdapter;
import com.ac57.ui.entity.CoustomCollectionEntity;
import com.ac57.ui.entity.DeleteCoustomCollectionData;
import com.ac57.ui.presenter.CoustomCollectionPresenter;
import com.ac57.ui.presenter.view.ICoustomCollectionView;
import com.ac57.ui.view.EasyStatusView;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;


/**
 */
public class CoustomCollectionFragment extends BaseMVPFragment<ICoustomCollectionView, CoustomCollectionPresenter> implements ICoustomCollectionView {

    @BindView(R.id.llayout_coustom_collection)
    LinearLayout llayoutCoustomCollection;
    @BindView(R.id.iv_coustom_collection_img)
    ImageView ivCoustomCollectionImg;

    @BindView(R.id.rv_content)
    RecyclerView xRecyclerView;
    @BindView(R.id.esv_multip_view)
    EasyStatusView esvMultipView;
    @BindView(R.id.refresh_layout)
    RefreshLayout mRefreshLayout;

    private int page = 1;
    private String is_desc = "102";
    private CoustomCollectionAdapter adapter;

    public CoustomCollectionFragment() {
    }

    public static CoustomCollectionFragment newInstance() {
        CoustomCollectionFragment fragment = new CoustomCollectionFragment();
        return fragment;
    }


    @Override
    protected int getLayoutId() {
        return R.layout.fragment_coustom_collection;
    }

    @Override
    protected void initView(View convertView, Bundle savedInstanceState) {
        setEasyStatusView(esvMultipView);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        adapter = new CoustomCollectionAdapter(xRecyclerView);
        xRecyclerView.setLayoutManager(linearLayoutManager);
        xRecyclerView.setAdapter(adapter);
    }

    private int position;

    @Override
    protected void initData() {
        mRefreshLayout.setDelegate(new RefreshLayout.BGARefreshLayoutDelegate() {
            @Override
            public void onBGARefreshLayoutBeginRefreshing(RefreshLayout refreshLayout) {
                page = 1;
                mPresenter.getCoustomCollectionData(page, is_desc);
            }

            @Override
            public boolean onBGARefreshLayoutBeginLoadingMore(RefreshLayout refreshLayout) {
                page++;
                mPresenter.getCoustomCollectionData(page, is_desc);
                return true;
            }
        });

        adapter.setOnItemChildClickListener((parent, childView, position) -> {
            if (childView.getId() == R.id.llayout_delete_menu) {
                this.position = position;
                Log.e("tag", "藏品id  ==  " + adapter.getItem(position).id);
                mPresenter.deleteData(adapter.getItem(position).id);
            }
            if (childView.getId() == R.id.rlayout_coustom_content) {
                //// TODO: 2017/1/15   自选藏品详情页面 
            }
        });
    }

    @Override
    protected void getData() {
        loading();
        mPresenter.getCoustomCollectionData(page, is_desc);
    }


    @OnClick(R.id.llayout_coustom_collection)
    public void onClick() {
        if (is_desc.equals("102"))
            is_desc = "101";
        else
            is_desc = "102";
        page = 1;
        mPresenter.getCoustomCollectionData(page, is_desc);
    }

    @Override
    public void deleteCoustomCollectionData(DeleteCoustomCollectionData entity) {
        if (entity.record_id.equals("1")) {
            adapter.removeItem(position);
        }
    }


    @Override
    public void getCoustomCollectionData(List<CoustomCollectionEntity> entity) {
        Log.e("tag", "" + entity.toString());
        if (is_desc.equals("102")) {
            ivCoustomCollectionImg.setImageResource(R.drawable.personal_arrow_down);
        } else {
            ivCoustomCollectionImg.setImageResource(R.drawable.market_arrow_up_gary);
        }
        if (entity != null && entity.size() != 0) {
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
        } else {
            esvMultipView.empty();
        }
    }

    @Override
    protected CoustomCollectionPresenter initPresenter() {
        return new CoustomCollectionPresenter();
    }


}
