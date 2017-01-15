package com.ac57.ui.adapter;

import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.ac57.R;
import com.ac57.ui.entity.OptionWatchBottomEntity;
import com.ac57.ui.view.CircleView;

import cn.bingoogolapple.androidcommon.adapter.BGARecyclerViewAdapter;
import cn.bingoogolapple.androidcommon.adapter.BGAViewHolderHelper;


/**
 * Created by Du_Li on 2016/12/31.
 */

public class OptionWatchBottomAdapter extends BGARecyclerViewAdapter<OptionWatchBottomEntity> {
    private CircleView cv_bottom_item_img;
    private TextView tv_bottom_item_tab2;
    private TextView tv_bottom_item_tab3;

    public OptionWatchBottomAdapter(RecyclerView xRecyclerView) {
        super(xRecyclerView,  R.layout.item_option_bottom);
    }

    @Override
    protected void fillData(BGAViewHolderHelper helper, int position, OptionWatchBottomEntity item) {
        cv_bottom_item_img = helper.getView(R.id.cv_bottom_item_img);
        tv_bottom_item_tab2 = helper.getView(R.id.tv_bottom_item_tab2);
        tv_bottom_item_tab3 = helper.getView(R.id.tv_bottom_item_tab3);
        cv_bottom_item_img.setText(item.icon_show_str);
        try {
            cv_bottom_item_img.setBackgroundColor(Color.parseColor("#" + item.icon_show_color));
        } catch (Exception e) {
            cv_bottom_item_img.setBackgroundColor(Color.BLACK);
        }
        helper.setText(R.id.tv_bottom_item_name, item.name);
        tv_bottom_item_tab2.setText(item.price_show_str);
        tv_bottom_item_tab3.setText(item.rises_show_str);
        if (item.is_up.equals("102")) {  //  #101涨 102跌 103没涨没跌
            tv_bottom_item_tab2.setTextColor(mContext.getResources().getColor(R.color.myoptional_green));
            tv_bottom_item_tab3.setTextColor(mContext.getResources().getColor(R.color.myoptional_green));
        } else if (item.is_up.equals("101")) {
            tv_bottom_item_tab2.setTextColor(mContext.getResources().getColor(R.color.myoptional_red));
            tv_bottom_item_tab3.setTextColor(mContext.getResources().getColor(R.color.myoptional_red));
        } else {
            tv_bottom_item_tab2.setTextColor(mContext.getResources().getColor(R.color.myoptional_yellow));
            tv_bottom_item_tab3.setTextColor(mContext.getResources().getColor(R.color.myoptional_yellow));
        }

    }
}
