package com.ac57.ui.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.ac57.R;
import com.ac57.ui.entity.InteractEventEntity;
import com.bumptech.glide.Glide;

import cn.bingoogolapple.androidcommon.adapter.BGARecyclerViewAdapter;
import cn.bingoogolapple.androidcommon.adapter.BGAViewHolderHelper;

/**
 * Created by Du_Li on 2016/12/31.
 */

public class InteractEventAdapter extends BGARecyclerViewAdapter<InteractEventEntity> {
    public InteractEventAdapter(RecyclerView recyclerView) {
        super(recyclerView, R.layout.item_interact_event);
    }

    @Override
    protected void fillData(BGAViewHolderHelper helper, int position, InteractEventEntity item) {
        helper.setText(R.id.tv_interact_event_item_name, item.art_exc_info.name);
        helper.setText(R.id.tv_interact_event_item_title, item.title);
        helper.setText(R.id.tv_interact_event_item_time, item.e_time_show_str);
        Glide.with(mContext).load(item.main_img_url).placeholder(R.drawable.holde).into(helper.getImageView(R.id.iv_interact_event_item_img));
        if (item.is_over.equals("102")) {
            helper.getView(R.id.tv_interact_event_item_stat).setVisibility(View.GONE);
        } else {
            helper.getView(R.id.tv_interact_event_item_stat).setVisibility(View.VISIBLE);
        }
    }
}
