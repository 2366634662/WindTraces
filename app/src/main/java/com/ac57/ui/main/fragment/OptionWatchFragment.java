package com.ac57.ui.main.fragment;


import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ac57.R;
import com.ac57.framework.base.MVPBaseFragment;
import com.ac57.ui.adapter.OptionWatchBottomAdapter;
import com.ac57.ui.adapter.OptionWatchCenterAdapter;
import com.ac57.ui.adapter.OptionWatchTopAdapter;
import com.ac57.ui.entity.OptionWatchBottomEntity;
import com.ac57.ui.entity.OptionWatchCenterEntity;
import com.ac57.ui.entity.OptionWatchTopEntity;
import com.ac57.ui.presenter.OptionWatchPresenter;
import com.ac57.ui.presenter.view.IOptionWatchView;
import com.ac57.ui.view.SpaceItemDecoration;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.jcodecraeer.xrecyclerview.progressindicator.view.EasyStatusView;

import java.util.List;

import butterknife.BindView;

/**
 * A simple {@link Fragment} subclass.
 */
public class OptionWatchFragment extends MVPBaseFragment<OptionWatchPresenter, IOptionWatchView> implements IOptionWatchView {
    @BindView(R.id.xRecyclerView)
    XRecyclerView xRecyclerView;
    @BindView(R.id.esv_multip_view)
    EasyStatusView esvMultipView;
    private View optionTop;
    //    private View optionCenter;
    private int page = 1;
    private boolean isFirst = true;

    private RelativeLayout rlayout_read_one_mores;

    private RecyclerView recyclerViewTop;
    private TextView tv_top_title;
    private OptionWatchTopAdapter topAdapter;
    private OptionWatchCenterAdapter centerAdapter;
    private OptionWatchBottomAdapter bottomAdapter;

    private RecyclerView recyclerViewCenter;
    private TextView tv_center_title;

    public OptionWatchFragment() {

    }

    public static OptionWatchFragment newInstance() {
        OptionWatchFragment fragment = new OptionWatchFragment();
        return fragment;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_option_watch;
    }

    @Override
    protected void initView(View convertView, Bundle savedInstanceState) {
        optionTop = View.inflate(getActivity(), R.layout.layout_option_watch, null);
        recyclerViewTop = (RecyclerView) optionTop.findViewById(R.id.recyclerView_option);
        recyclerViewCenter = (RecyclerView) optionTop.findViewById(R.id.recyclerView_option_center);
        tv_top_title = (TextView) optionTop.findViewById(R.id.tv_option_watch_title);
        //查看更多
        rlayout_read_one_mores = (RelativeLayout) optionTop.findViewById(R.id.rlayout_read_one_mores);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), 3);
        topAdapter = new OptionWatchTopAdapter(getActivity(), R.layout.item_option_watch_top);
        recyclerViewTop.setLayoutManager(gridLayoutManager);
        recyclerViewTop.setAdapter(topAdapter);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        centerAdapter = new OptionWatchCenterAdapter(getActivity(), R.layout.item_option_watch_center);
        recyclerViewCenter.setLayoutManager(linearLayoutManager);
        int spacingInPixels = getActivity().getResources().getDimensionPixelSize(R.dimen.DIMEN_15PX);
        recyclerViewCenter.addItemDecoration(new SpaceItemDecoration(spacingInPixels));
        recyclerViewCenter.setAdapter(centerAdapter);
        xRecyclerView.setRefreshHeaderBackGround(Color.parseColor("#242438"));
        xRecyclerView.addHeaderView(optionTop);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        bottomAdapter = new OptionWatchBottomAdapter(getActivity(), R.layout.item_option_bottom);
        xRecyclerView.setLayoutManager(layoutManager);
        xRecyclerView.setAdapter(bottomAdapter);
    }

    @Override
    protected void initData() {
        xRecyclerView.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                page = 1;
                mPresenter.getOptionWatchBottomData(page);
            }

            @Override
            public void onLoadMore() {
                page++;
                mPresenter.getOptionWatchBottomData(page);
            }
        });
    }

    @Override
    protected void getData() {
        mPresenter.getOptionWatchTopData();
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
        esvMultipView.content();
    }

    @Override
    public void showError(String msg) {
        esvMultipView.error();
    }

    @Override
    protected OptionWatchPresenter initPresenter() {
        return new OptionWatchPresenter(this);
    }

    @Override
    public void getOptionWatchTopData(OptionWatchTopEntity entity) {
        entity.data_list.add(new OptionWatchTopEntity().new DataListBean("更多"));
        tv_top_title.setText("当前已关注的文交所(前" + entity.follow_num + "家)");
        topAdapter.addAll(entity.data_list);
    }

    @Override
    public void getOptionWatchCenterData(List<OptionWatchCenterEntity> entity) {
        centerAdapter.addAll(entity);
    }

    @Override
    public void getOptionWatchBottomData(List<OptionWatchBottomEntity> entities) {
        if (page == 1) {
            bottomAdapter.replaceAll(entities);
            xRecyclerView.refreshComplete();
        } else {
            bottomAdapter.addAll(entities);
            xRecyclerView.loadMoreComplete();
        }
        if (entities.size() < 10) {
            xRecyclerView.setNoMore(true);
        } else {
            xRecyclerView.setNoMore(false);
        }
        bottomAdapter.notifyDataSetChanged();
    }
}
