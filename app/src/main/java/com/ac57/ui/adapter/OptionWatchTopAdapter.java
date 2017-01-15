package com.ac57.ui.adapter;

import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ac57.R;
import com.ac57.framework.utils.StringUtils;
import com.ac57.ui.entity.OptionWatchTopEntity;
import com.ac57.ui.view.CircleView;

import cn.bingoogolapple.androidcommon.adapter.BGARecyclerViewAdapter;
import cn.bingoogolapple.androidcommon.adapter.BGAViewHolderHelper;

/**
 * Created by Du_Li on 2016/12/31.
 */

public class OptionWatchTopAdapter extends BGARecyclerViewAdapter<OptionWatchTopEntity.DataListBean> {
    private CircleView cv_top_item_img;
    private TextView tv_top_item_count;
    private TextView tv_top_item_mess;
    private LinearLayout linearLayout;
    private LinearLayout llayout_content;

    public OptionWatchTopAdapter(RecyclerView recyclerView) {
        super(recyclerView, R.layout.item_option_watch_top);

    }

    @Override
    protected void fillData(BGAViewHolderHelper helper, int position, OptionWatchTopEntity.DataListBean item) {
        cv_top_item_img = helper.getView(R.id.cv_top_item_img);
        tv_top_item_count = helper.getView(R.id.tv_top_item_count);
        tv_top_item_mess = helper.getView(R.id.tv_top_item_mess);
        linearLayout = helper.getView(R.id.llayout_more);
        llayout_content = helper.getView(R.id.llayout_content);
        cv_top_item_img.setText(item.icon_show_str);
        try {
            cv_top_item_img.setBackgroundColor(Color.parseColor("#" + item.icon_show_color));
        } catch (Exception e) {
            cv_top_item_img.setBackgroundColor(Color.parseColor("#000000"));
        }
        helper.setText(R.id.tv_top_item_name, item.name);
        tv_top_item_count.setText(item.price_show_str);
        tv_top_item_mess.setText(item.rises_show_str);
        if (StringUtils.isNotEmpty(item.is_up)) {
            if (item.is_up.equals("102")) {
                tv_top_item_count.setTextColor(mContext.getResources().getColor(R.color.myoptional_green));
                tv_top_item_mess.setTextColor(mContext.getResources().getColor(R.color.myoptional_green));
            } else if (item.is_up.equals("101")) {
                tv_top_item_count.setTextColor(mContext.getResources().getColor(R.color.myoptional_red));
                tv_top_item_mess.setTextColor(mContext.getResources().getColor(R.color.myoptional_red));
            } else {
                tv_top_item_count.setTextColor(mContext.getResources().getColor(R.color.myoptional_yellow));
                tv_top_item_mess.setTextColor(mContext.getResources().getColor(R.color.myoptional_yellow));
            }
        }
        if (StringUtils.isNotEmpty(item.name)) {
            if (item.name.equals("更多")) {
                linearLayout.setVisibility(View.VISIBLE);
                llayout_content.setVisibility(View.GONE);
            } else {
                linearLayout.setVisibility(View.GONE);
                llayout_content.setVisibility(View.VISIBLE);
            }
        }
    }
}
