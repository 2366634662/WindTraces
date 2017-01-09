package com.ac57.ui.main.fragment;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;

import com.ac57.R;
import com.ac57.framework.base.MVPBaseFragment;
import com.ac57.ui.adapter.HomeListInfoAdapter;
import com.ac57.ui.entity.HomeBannerEntity;
import com.ac57.ui.entity.HomeInfoListEntity;
import com.ac57.ui.presenter.HomePresenter;
import com.ac57.ui.presenter.view.IHomeView;
import com.ac57.ui.view.customtoast.ToastUtils;
import com.bumptech.glide.Glide;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.jcodecraeer.xrecyclerview.progressindicator.view.EasyStatusView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import cn.bingoogolapple.bgabanner.BGABanner;

/**
 */
public class HomeFragment extends MVPBaseFragment<HomePresenter, IHomeView> implements IHomeView {

    @BindView(R.id.xrv_home)
    XRecyclerView xrvHome;
    @BindView(R.id.esv_home_multip)
    EasyStatusView esvHomeMultip;
    BGABanner banner;
    @BindView(R.id.iv_title_left)
    ImageView ivTitleLeft;
    @BindView(R.id.iv_title_right)
    ImageView ivTitleRight;

    private HomeListInfoAdapter infoAdapter;

    private int page = 1;
    private boolean isFirst = true;

    public static HomeFragment newInstance() {
        HomeFragment fragment = new HomeFragment();
        return fragment;
    }

    public HomeFragment() {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_home;
    }

    @Override
    protected void initView(View convertView, Bundle savedInstanceState) {
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.bga_banner_layout, null);
        banner = (BGABanner) view.findViewById(R.id.banner);
        xrvHome.addHeaderView(view);
    }

    @Override
    protected void initData() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        xrvHome.setLayoutManager(linearLayoutManager);
        infoAdapter = new HomeListInfoAdapter(getActivity(), R.layout.item_home_collication);
        xrvHome.setAdapter(infoAdapter);
        xrvHome.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                page = 1;
                mPresenter.getHomeInfoListData("all", page);
            }

            @Override
            public void onLoadMore() {
                page++;
                mPresenter.getHomeInfoListData("all", page);
            }
        });
    }

    @OnClick({R.id.iv_title_left, R.id.iv_title_right})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_title_left:
                break;
            case R.id.iv_title_right:
                break;
        }
    }

    @Override
    protected void getData() {
        mPresenter.getHomeBannerData();
        mPresenter.getHomeInfoListData("all", page);
    }

    @Override
    public void getHomeBannerData(List<HomeBannerEntity> entity) {
        if (entity == null)
            return;
        banner.setAdapter(new BGABanner.Adapter<ImageView, HomeBannerEntity>() {
            @Override
            public void fillBannerItem(BGABanner banner, ImageView itemView, HomeBannerEntity model, int position) {
                Glide.with(getActivity())
                        .load(model.cover_img)
                        .into(itemView);
            }
        });
        List<String> tips = new ArrayList<>();
        for (HomeBannerEntity entity1 : entity) {
            tips.add(entity1.title);
        }
        banner.setData(entity, tips);
    }

    @Override
    public void getHomeInfoData(List<HomeInfoListEntity> entity) {
        if (page == 1) {
            infoAdapter.replaceAll(entity);
            xrvHome.refreshComplete();
        } else {
            infoAdapter.addAll(entity);
            xrvHome.loadMoreComplete();
        }
        if (entity.size() < 10) {
            xrvHome.setNoMore(true);
        } else {
            xrvHome.setNoMore(false);
        }
        infoAdapter.notifyDataSetChanged();
    }

    @Override
    public void showDailog(String msg) {
        if (isFirst) {
            isFirst = false;
            esvHomeMultip.loading();
        }
    }

    @Override
    public void disDailog() {
        esvHomeMultip.content();
    }

    @Override
    public void showError(String msg) {
        esvHomeMultip.error();
        ToastUtils.success(msg);
    }

    @Override
    protected HomePresenter initPresenter() {
        return new HomePresenter(this, getActivity());
    }


}
