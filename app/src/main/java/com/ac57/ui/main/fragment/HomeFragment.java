package com.ac57.ui.main.fragment;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;

import com.ac57.R;
import com.ac57.framework.base.BaseMVPFragment;
import com.ac57.framework.refresh.RefreshLayout;
import com.ac57.framework.utils.IntentUtils;
import com.ac57.framework.utils.StringUtils;
import com.ac57.framework.utils.diskcache.DiskLruCacheHelper;
import com.ac57.ui.AppContext;
import com.ac57.ui.adapter.HomeListInfoAdapter;
import com.ac57.ui.entity.HomeBannerEntity;
import com.ac57.ui.entity.HomeInfoListEntity;
import com.ac57.ui.main.activity.SelfActivity;
import com.ac57.ui.main.activity.SelfMessageActivity;
import com.ac57.ui.presenter.HomePresenter;
import com.ac57.ui.presenter.view.IHomeView;
import com.ac57.ui.utils.DiskLruCacheTagUtils;
import com.ac57.ui.utils.GsonUtils;
import com.ac57.ui.view.EasyStatusView;
import com.bumptech.glide.Glide;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import cn.bingoogolapple.bgabanner.BGABanner;

public class HomeFragment extends BaseMVPFragment<IHomeView, HomePresenter> implements IHomeView, RefreshLayout.BGARefreshLayoutDelegate {

    @BindView(R.id.xrv_home)
    RecyclerView xrvHome;
    @BindView(R.id.esv_home_multip)
    EasyStatusView esvHomeMultip;
    BGABanner banner;
    @BindView(R.id.iv_title_left)
    ImageView ivTitleLeft;
    @BindView(R.id.iv_title_right)
    ImageView ivTitleRight;
    @BindView(R.id.refresh_layout)
    RefreshLayout mRefreshLayout;
    private HomeListInfoAdapter infoAdapter;

    private int page = 1;


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
        setEasyStatusView(esvHomeMultip);
        loading();
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.bga_banner_layout, null);
        banner = (BGABanner) view.findViewById(R.id.banner);
        mRefreshLayout.setDelegate(this);
        mRefreshLayout.setCustomHeaderView(view, true);
        if (!AppContext.getMyAppContext().verifyNetwork()) {
            mRefreshLayout.setPullDownRefreshEnable(false);
        }
    }

    @Override
    protected void initData() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        xrvHome.setLayoutManager(linearLayoutManager);
        infoAdapter = new HomeListInfoAdapter(xrvHome);
        xrvHome.setAdapter(infoAdapter);
        esvHomeMultip.setMutipOnClick(() -> {
            page = 1;
            getData();
        });
    }

    @OnClick({R.id.iv_title_left, R.id.iv_title_right})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_title_left:
                IntentUtils.startActivity(getActivity(), SelfMessageActivity.class);
                break;
            case R.id.iv_title_right:
                IntentUtils.startActivity(getActivity(), SelfActivity.class);
                break;
        }
    }

    @Override
    protected void getData() {
        if (AppContext.getMyAppContext().verifyNetwork()) {
            mPresenter.getHomeBannerData();
            mPresenter.getHomeInfoListData("all", page);
        } else {
            ArrayList<HomeInfoListEntity> entities = new ArrayList<>();
            if (StringUtils.isNotEmpty(DiskLruCacheHelper.getIns(getContext()).getAsString(DiskLruCacheTagUtils.HOME_INFO_LIST + page))) {
                entities = GsonUtils.getInstance().fromJson(DiskLruCacheHelper.getIns(getContext()).getAsString(DiskLruCacheTagUtils.HOME_INFO_LIST + page), new TypeToken<List<HomeInfoListEntity>>() {
                }.getType());
                if (!entities.isEmpty())
                    getHomeInfoData(entities);
            }
        }
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

        String json = GsonUtils.getInstance().toJson(entity);

        ArrayList<HomeInfoListEntity> entities = new ArrayList<>();
        entities = GsonUtils.getInstance().fromJson(json, new TypeToken<List<HomeInfoListEntity>>() {
        }.getType());

        DiskLruCacheHelper.getIns(getContext()).put(DiskLruCacheTagUtils.HOME_INFO_LIST + page, json);
//        DiskLruCacheHelper.getIns(getContext()).put("page", "" + page);

        if (page == 1) {
            infoAdapter.setData(entity);
            mRefreshLayout.endRefreshing();
        } else {
            infoAdapter.addMoreData(entity);
            mRefreshLayout.endLoadingMore();
        }
        if (entity.size() < 10) {
            mRefreshLayout.setPullUpRefreshEnable(false);
        } else {
            mRefreshLayout.setPullUpRefreshEnable(true);
        }
    }


    @Override
    protected HomePresenter initPresenter() {
        return new HomePresenter();
    }


    @Override
    public void onBGARefreshLayoutBeginRefreshing(RefreshLayout refreshLayout) {
        page = 1;
        mPresenter.getHomeInfoListData("all", page);
    }

    @Override
    public boolean onBGARefreshLayoutBeginLoadingMore(RefreshLayout refreshLayout) {
        page++;
        getData();
        return true;
    }
}
