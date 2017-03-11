package com.ac57.ui.main.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ac57.R;
import com.ac57.framework.base.BaseMVPActivity;
import com.ac57.framework.refresh.RefreshLayout;
import com.ac57.framework.utils.IntentUtils;
import com.ac57.ui.entity.OptionDetailListEntity;
import com.ac57.ui.entity.OptionDetailListTopEntity;
import com.ac57.ui.presenter.OptionDetailListPresenter;
import com.ac57.ui.presenter.view.IOptionDetailListView;
import com.ac57.ui.view.CircleView;
import com.ac57.ui.view.EasyStatusView;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import cn.bingoogolapple.androidcommon.adapter.BGARecyclerViewAdapter;
import cn.bingoogolapple.androidcommon.adapter.BGAViewHolderHelper;

public class OptionDetailListActivity extends BaseMVPActivity<IOptionDetailListView, OptionDetailListPresenter> implements IOptionDetailListView {


    @BindView(R.id.img_option_title_back)
    ImageView imgOptionTitleBack;
    @BindView(R.id.tv_app_title_name)
    TextView tvAppTitleName;
    @BindView(R.id.img_app_title_right)
    ImageView imgAppTitleRight;
    @BindView(R.id.tv_app_title_right)
    TextView tvAppTitleRight;
    @BindView(R.id.llayout_app_title_right)
    LinearLayout llayoutAppTitleRight;
    @BindView(R.id.app_title)
    RelativeLayout appTitle;
    @BindView(R.id.cv_exchangequo_info_img)
    CircleView cvExchangequoInfoImg;
    @BindView(R.id.tv_exchangequo_info_name)
    TextView tvExchangequoInfoName;
    @BindView(R.id.tv_exchangequo_info_num)
    TextView tvExchangequoInfoNum;
    @BindView(R.id.tv_exchangequo_info_count1)
    TextView tvExchangequoInfoCount1;
    @BindView(R.id.tv_exchangequo_info_count2)
    TextView tvExchangequoInfoCount2;
    @BindView(R.id.tv_exchangequo_info_zcount)
    TextView tvExchangequoInfoZcount;
    @BindView(R.id.tv_exchangequo_info_pcount)
    TextView tvExchangequoInfoPcount;
    @BindView(R.id.tv_exchangequo_info_dcount)
    TextView tvExchangequoInfoDcount;
    @BindView(R.id.tv_exchangequo_info_total)
    TextView tvExchangequoInfoTotal;
    @BindView(R.id.tv_exchangequo_info_price)
    TextView tvExchangequoInfoPrice;
    @BindView(R.id.reading_two_tab1)
    TextView readingTwoTab1;
    @BindView(R.id.reading_two_tab2)
    TextView readingTwoTab2;
    @BindView(R.id.reading_two_two_img)
    ImageView readingTwoTwoImg;
    @BindView(R.id.reading_two_tab3)
    LinearLayout readingTwoTab3;
    @BindView(R.id.rv_option_content)
    RecyclerView rvOptionContent;
    @BindView(R.id.refresh_option_layout)
    RefreshLayout refreshOptionLayout;
    @BindView(R.id.content_view)
    LinearLayout contentView;
    @BindView(R.id.esv_status_view)
    EasyStatusView esvStatusView;
    @BindView(R.id.tv_exchangequo_info_bnt)
    TextView tvExchangequoInfoBnt;
    private String id;
    private int page = 1;
    private int stats = 102;


    BGARecyclerViewAdapter<OptionDetailListEntity> adapter;

    @Override
    public int getLayout() {
        return R.layout.activity_option_detail_list;
    }

    @Override
    public void initView(Bundle savedInstanceState) {
        super.initView(savedInstanceState);
        setStatusBarColor(Color.parseColor("#242438"));
    }

    @Override
    public void initDatas() {
        id = getIntent().getStringExtra("id");

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);

        rvOptionContent.setLayoutManager(linearLayoutManager);

        adapter = new BGARecyclerViewAdapter<OptionDetailListEntity>(rvOptionContent, R.layout.item_exchangequoinfo) {
            @Override
            protected void fillData(BGAViewHolderHelper helper, int position, OptionDetailListEntity model) {

                helper.setTextColor(R.id.tv_exchangequoinfo_item_title, Color.parseColor("#bdbdc3"));
                helper.setTextColor(R.id.tv_exchangequoinfo_item_num, Color.parseColor("#bdbdc3"));
                helper.setText(R.id.tv_exchangequoinfo_item_title, model.trade_name);
                helper.setText(R.id.tv_exchangequoinfo_item_num, model.trade_code);
                helper.setText(R.id.tv_exchangequoinfo_item_item_prcie, model.price);
                helper.setText(R.id.tv_exchangequoinfo_item_zf, model.rises);
                helper.setBackgroundColor(R.id.view_exchangequoinfo_item_line, Color.parseColor("#242438"));
                if (model.is_up.equals("102")) {
                    helper.setTextColor(R.id.tv_exchangequoinfo_item_item_prcie, getResources().getColor(R.color.myoptional_green));
                    helper.setTextColor(R.id.tv_exchangequoinfo_item_zf, getResources().getColor(R.color.myoptional_green));
                } else if (model.is_up.equals("101")) {
                    helper.setTextColor(R.id.tv_exchangequoinfo_item_item_prcie, getResources().getColor(R.color.myoptional_red));
                    helper.setTextColor(R.id.tv_exchangequoinfo_item_zf, getResources().getColor(R.color.myoptional_red));
                } else {
                    helper.setTextColor(R.id.tv_exchangequoinfo_item_item_prcie, getResources().getColor(R.color.myoptional_yellow));
                    helper.setTextColor(R.id.tv_exchangequoinfo_item_zf, getResources().getColor(R.color.myoptional_yellow));
                }
                helper.getView(R.id.llayout_exchangequoinfo_content).setOnClickListener(view -> {
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("content", model);
                    IntentUtils.startActivity(OptionDetailListActivity.this, ReadCollectInfoActivity.class, bundle);
                });
            }
        };
        rvOptionContent.setAdapter(adapter);

        refreshOptionLayout.setDelegate(new RefreshLayout.BGARefreshLayoutDelegate() {
            @Override
            public void onBGARefreshLayoutBeginRefreshing(RefreshLayout refreshLayout) {
                page = 1;
                mPresenter.getListData(page, id, stats);
            }

            @Override
            public boolean onBGARefreshLayoutBeginLoadingMore(RefreshLayout refreshLayout) {
                page++;
                mPresenter.getListData(page, id, stats);
                return true;
            }
        });

    }

    @Override
    public void loadData() {
        mPresenter.getTopData(id);
        mPresenter.getListData(page, id, stats);
    }


    @Override
    public void getTopData(OptionDetailListTopEntity entity) {
        tvExchangequoInfoName.setText(entity.name);
        tvExchangequoInfoNum.setText(entity.price);
        tvExchangequoInfoCount1.setText(entity.rises_price);

        setColors(entity.is_up, entity.rises);
        tvExchangequoInfoZcount.setText(entity.up_num);
        tvExchangequoInfoPcount.setText(entity.balanced_num);
        tvExchangequoInfoDcount.setText(entity.down_num);
        tvExchangequoInfoTotal.setText("量" + entity.volume);
        tvExchangequoInfoPrice.setText("额" + entity.turn_volume);

        setIsFollow(entity.is_follow.equals("101"));
        cvExchangequoInfoImg.setText(entity.icon_show_str);
        try {
            cvExchangequoInfoImg.setBackgroundColor(Color.parseColor("#" + entity.icon_show_color));
        } catch (Exception e) {
            cvExchangequoInfoImg.setBackgroundColor(Color.BLACK);
        }
    }


    private void setColors(String is_up, String rises) {
        if (is_up.equals("102")) {  //  #101涨 102跌 103没涨没跌
            tvExchangequoInfoCount1.setTextColor(this.getResources().getColor(R.color.myoptional_green));
            tvExchangequoInfoCount2.setTextColor(this.getResources().getColor(R.color.myoptional_green));
        } else if (is_up.equals("101")) {
            tvExchangequoInfoCount1.setTextColor(this.getResources().getColor(R.color.myoptional_red));
            tvExchangequoInfoCount2.setTextColor(this.getResources().getColor(R.color.myoptional_red));
            rises = "+" + rises;
        } else {
            tvExchangequoInfoCount1.setTextColor(this.getResources().getColor(R.color.myoptional_yellow));
            tvExchangequoInfoCount2.setTextColor(this.getResources().getColor(R.color.myoptional_yellow));
        }
        tvExchangequoInfoCount2.setText(rises);
    }

    private void setIsFollow(boolean isTrue) {
        if (isTrue) {//已关注
            imgAppTitleRight.setVisibility(View.GONE);
            tvAppTitleRight.setText("取消关注");
        } else {
            imgAppTitleRight.setVisibility(View.VISIBLE);
            tvAppTitleRight.setText("关注");
        }
    }

    @Override
    public void getListData(List<OptionDetailListEntity> entities) {
        if (!entities.isEmpty()) {
            esvStatusView.content();
            if (page == 1) {
                adapter.setData(entities);
                refreshOptionLayout.endRefreshing();
            } else {
                adapter.addMoreData(entities);
                refreshOptionLayout.endLoadingMore();
            }
            if (entities.size() < 10) {
                refreshOptionLayout.setPullUpRefreshEnable(false);
            } else {
                refreshOptionLayout.setPullUpRefreshEnable(true);
            }
        } else {
            esvStatusView.empty();
        }
    }


    @OnClick({R.id.img_option_title_back, R.id.llayout_app_title_right})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.img_option_title_back:
                IntentUtils.finishActivity(this);
                break;
            case R.id.llayout_app_title_right:
                break;
        }
    }

    @Override
    protected OptionDetailListPresenter initPresenter() {
        return new OptionDetailListPresenter();
    }
}
