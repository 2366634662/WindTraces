package com.ac57.ui.adapter;

import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.ac57.R;
import com.ac57.ui.entity.ExchangeEntity;
import com.ac57.ui.view.CircleView;

import cn.bingoogolapple.androidcommon.adapter.BGARecyclerViewAdapter;
import cn.bingoogolapple.androidcommon.adapter.BGAViewHolderHelper;

/**
 * Created on 2017/2/5.
 * Desc :
 */

public class ExchangeAdapter extends BGARecyclerViewAdapter<ExchangeEntity> {
    private CircleView exchange_img;

    public ExchangeAdapter(RecyclerView recyclerView) {
        super(recyclerView, R.layout.item_exchange_layout);
    }

    @Override
    protected void fillData(BGAViewHolderHelper helper, int position, ExchangeEntity model) {
        exchange_img = helper.getView(R.id.cv_exchange_img);
        exchange_img.setNotifiText(model.icon_show_str);
        try {
            exchange_img.setBackgroundColor(Color.parseColor("#" + model.icon_show_color));
        } catch (Exception e) {
            exchange_img.setBackgroundColor(Color.BLACK);
        }

        if (model.is_follow.equals("101")) {
            helper.setVisibility(R.id.iv_exchange_stat, View.VISIBLE);
            try {
                helper.setTextColor(R.id.tv_exchange_name, Color.parseColor("#172434"));
            } catch (Exception e) {
                helper.setTextColor(R.id.tv_exchange_name, Color.BLACK);
            }
        }else{
            try {
                helper.setTextColor(R.id.tv_exchange_name, Color.parseColor("#9b9b9b"));
            } catch (Exception e) {
                helper.setTextColor(R.id.tv_exchange_name, Color.BLACK);
            }
            helper.setVisibility(R.id.iv_exchange_stat, View.GONE);
        }
        helper.setText(R.id.tv_exchange_name,model.name);

    }
}
