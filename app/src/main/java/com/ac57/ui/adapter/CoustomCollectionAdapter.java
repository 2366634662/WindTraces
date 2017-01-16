package com.ac57.ui.adapter;

import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.ac57.R;
import com.ac57.ui.entity.CoustomCollectionEntity;
import com.ac57.ui.view.RoundCornerTextView;

import cn.bingoogolapple.androidcommon.adapter.BGARecyclerViewAdapter;
import cn.bingoogolapple.androidcommon.adapter.BGAViewHolderHelper;

/**
 * Created by Du_Li on 2017/1/15.
 */

public class CoustomCollectionAdapter extends BGARecyclerViewAdapter<CoustomCollectionEntity> {
    RoundCornerTextView rctv_coustom_collection_name;
    TextView tv_coustom_collection_prcie;
    TextView tv_coustom_collection_title_zf;

    public CoustomCollectionAdapter(RecyclerView recyclerView) {
        super(recyclerView, R.layout.item_coustom_collection_content);
    }

    @Override
    protected void setItemChildListener(BGAViewHolderHelper helper, int viewType) {
        super.setItemChildListener(helper, viewType);
        helper.setItemChildClickListener(R.id.llayout_delete_menu);
        helper.setItemChildClickListener(R.id.rlayout_coustom_content);
    }

    @Override
    protected void fillData(BGAViewHolderHelper helper, int position, CoustomCollectionEntity model) {
        rctv_coustom_collection_name = (helper.getView(R.id.rctv_coustom_collection_name));
        tv_coustom_collection_prcie = (helper.getView(R.id.tv_coustom_collection_prcie));
        tv_coustom_collection_title_zf = (helper.getView(R.id.tv_coustom_collection_title_zf));

        helper.setText(R.id.tv_coustom_collection_title, model.trade_name);
        tv_coustom_collection_prcie.setText(model.price);
        tv_coustom_collection_title_zf.setText(model.rises);
        helper.setText(R.id.tv_coustom_collection_num, model.trade_code);
        float f = mContext.getResources().getDimension(R.dimen.DIMEN_3PX);

        try {
            rctv_coustom_collection_name.setColorAndConer(Color.parseColor("#" + model.exc_info.icon_show_color), (int) f);
        } catch (Exception e) {
            rctv_coustom_collection_name.setColorAndConer(Color.BLACK, (int) f);
        }
        rctv_coustom_collection_name.setText(model.exc_info.name);
        if (model.is_up.equals("102")) { //  #101涨 102跌 103没涨没跌
            tv_coustom_collection_title_zf.setTextColor(mContext.getResources().getColor(R.color.myoptional_green));
            tv_coustom_collection_prcie.setTextColor(mContext.getResources().getColor(R.color.myoptional_green));
        } else if (model.is_up.equals("101")) {
            tv_coustom_collection_title_zf.setTextColor(mContext.getResources().getColor(R.color.myoptional_red));
            tv_coustom_collection_prcie.setTextColor(mContext.getResources().getColor(R.color.myoptional_red));
        } else {
            tv_coustom_collection_title_zf.setTextColor(mContext.getResources().getColor(R.color.myoptional_yellow));
            tv_coustom_collection_prcie.setTextColor(mContext.getResources().getColor(R.color.myoptional_yellow));
        }

    }
}
