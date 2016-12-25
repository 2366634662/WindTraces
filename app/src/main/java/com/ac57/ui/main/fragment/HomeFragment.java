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
import com.ac57.ui.presenter.HomeFragmentPresenter;
import com.ac57.ui.presenter.IHomeViewControll;
import com.ac57.ui.view.EasyStatusView;
import com.ac57.ui.view.customtoast.ToastUtils;
import com.bumptech.glide.Glide;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import cn.bingoogolapple.bgabanner.BGABanner;

/**
 */
public class HomeFragment extends MVPBaseFragment<HomeFragmentPresenter, IHomeViewControll> implements IHomeViewControll {

    @BindView(R.id.xrv_home)
    XRecyclerView xrvHome;
    @BindView(R.id.esv_home_multip)
    EasyStatusView esvHomeMultip;
    BGABanner banner;

    private HomeListInfoAdapter infoAdapter;

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
    }

    @Override
    protected void getData() {
        mPresenter.getHomeBannerData();
        mPresenter.getHomeInfoListData("all", 1);
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
        infoAdapter.addAll(entity);
        infoAdapter.notifyDataSetChanged();
    }

    @Override
    public void showDailog(String msg) {
        esvHomeMultip.loading();
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
    protected HomeFragmentPresenter initPresenter() {
        return new HomeFragmentPresenter(this, getActivity());
    }
}
