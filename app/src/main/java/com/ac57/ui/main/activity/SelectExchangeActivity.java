package com.ac57.ui.main.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ac57.R;
import com.ac57.framework.base.MVPBaseActivity;
import com.ac57.framework.utils.IntentUtils;
import com.ac57.ui.adapter.SelectExchangeAdapter;
import com.ac57.ui.entity.SelectExchangeEntity;
import com.ac57.ui.presenter.SelectExchangePresenter;
import com.ac57.ui.presenter.view.ISelectExchangeView;
import com.ac57.ui.view.SideBar;
import com.ac57.ui.view.stickyheaders.StickyHeaderLayoutManager;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.OnClick;
import cn.bingoogolapple.androidcommon.adapter.BGARecyclerViewAdapter;
import cn.bingoogolapple.androidcommon.adapter.BGAViewHolderHelper;
import rx.Observable;

/**
 * 文交所开户
 */
public class SelectExchangeActivity extends MVPBaseActivity<SelectExchangePresenter, ISelectExchangeView> implements ISelectExchangeView {

    @BindView(R.id.iv_title_left)
    ImageView ivTitleLeft;
    @BindView(R.id.iv_title_center)
    ImageView ivTitleCenter;
    @BindView(R.id.iv_title_right)
    ImageView ivTitleRight;
    @BindView(R.id.tv_select_exchange_hot_title)
    TextView tvSelectExchangeHotTitle;
    @BindView(R.id.rv_select_exchange_hot)
    RecyclerView rvSelectExchangeHot;
    @BindView(R.id.rv_select_exchange_all_list)
    RecyclerView rvSelectExchangeAllList;
    @BindView(R.id.activity_select_exchange)
    LinearLayout activitySelectExchange;
    @BindView(R.id.tv_title_center)
    TextView tvTitleCenter;
    @BindView(R.id.sidebar)
    SideBar sidebar;

    private SelectExchangeEntity entity;
    private BGARecyclerViewAdapter<SelectExchangeEntity.HotExcListBean> adapter;
    private SelectExchangeAdapter alllistadapter = new SelectExchangeAdapter();

    @Override
    public int getLayout() {
        return R.layout.activity_select_exchange;
    }

    @Override
    public void initView(Bundle savedInstanceState) {
        setStatusBarColor(Color.WHITE, 30);
        ivTitleLeft.setImageResource(R.drawable.personal_back);
        tvTitleCenter.setText("123456");
        ivTitleCenter.setVisibility(View.GONE);
        tvTitleCenter.setVisibility(View.VISIBLE);
        ivTitleRight.setImageResource(R.drawable.personal_search);
    }

    @Override
    public void initDatas() {

        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 4);
        rvSelectExchangeHot.setLayoutManager(gridLayoutManager);
        adapter = new BGARecyclerViewAdapter<SelectExchangeEntity.HotExcListBean>(rvSelectExchangeHot, R.layout.item_select_eschange_hot_list) {
            @Override
            protected void fillData(BGAViewHolderHelper helper, int position, SelectExchangeEntity.HotExcListBean model) {
                helper.setText(R.id.cv_select_exchange_hot_tab, model.icon_show_str);
                try {
                    helper.setBackgroundColor(R.id.cv_select_exchange_hot_tab, Color.parseColor("#" + model.icon_show_color));
                } catch (Exception e) {
                    helper.setBackgroundColor(R.id.cv_select_exchange_hot_tab, Color.BLACK);
                }
                helper.setText(R.id.tv_select_exchange_hot_name, model.name);
            }
        };
        rvSelectExchangeHot.setAdapter(adapter);
        rvSelectExchangeAllList.setLayoutManager(new StickyHeaderLayoutManager());
        rvSelectExchangeAllList.setAdapter(alllistadapter);
        sidebar.setOnTouchingLetterChangedListener(new SideBar.OnTouchingLetterChangedListener() {
            @Override
            public void onTouchingLetterChanged(String s) {
                int position = alllistadapter.getPosition(s.charAt(0));
                if (position != -1) {
                    if (position == 0) {
                        rvSelectExchangeAllList.scrollToPosition(position);
                    } else
                        rvSelectExchangeAllList.scrollToPosition(position + 1);
                }
            }
        });
    }

    @Override
    public void showDailog(String msg) {

    }

    @Override
    public void loadData() {
        mPresenter.getSelectExchangeData();
    }

    ArrayList<String> strings = new ArrayList<>();
    ArrayList<String> strings1 = new ArrayList<>();
    ArrayList<SelectExchangeEntity.ExcListBean.ExcSectionListBean> listBeen = new ArrayList<>();

    @Override
    public void getSelectExchangeData(SelectExchangeEntity entity) {
        this.entity = entity;
        adapter.setData(entity.hot_exc_list);


        Observable.from(entity.exc_list).subscribe(excListBean -> {
            listBeen.addAll(excListBean.exc_section_list);
        });

        alllistadapter.setIndexData(listBeen);

        Observable.from(listBeen).subscribe(excSectionListBean -> {
            strings.add(excSectionListBean.pinyin.toUpperCase().charAt(0) + "");
        });

        Observable.from(strings).distinct().subscribe(s -> {
            strings1.add(s);
        });

        sidebar.setMy_sideBar(strings1);

    }


    @Override
    public void disDailog() {

    }

    @Override
    public void showError(String msg) {

    }

    @Override
    protected SelectExchangePresenter initPresenter() {
        return new SelectExchangePresenter(this);
    }

    @OnClick({R.id.iv_title_left, R.id.iv_title_right})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_title_left:
                IntentUtils.finishActivity(this);
                break;
            case R.id.iv_title_right:
                break;
        }
    }

}
