package com.ac57.ui.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.View;

import com.ac57.R;
import com.ac57.framework.base.adapter.RecyclerAdapter;
import com.ac57.framework.base.adapter.RecyclerAdapterHelper;
import com.ac57.ui.entity.InteractEventEntity;

/**
 * Created by Du_Li on 2016/12/31.
 */

public class InteractEventAdapter extends RecyclerAdapter<InteractEventEntity> {
    public InteractEventAdapter(Context context, @NonNull int... layoutResIds) {
        super(context, layoutResIds);
    }

    @Override
    protected void convert(RecyclerAdapterHelper helper, InteractEventEntity item) {
        helper.setText(R.id.tv_interact_event_item_name, item.art_exc_info.name);
        helper.setText(R.id.tv_interact_event_item_title, item.title);
        helper.setText(R.id.tv_interact_event_item_time, item.e_time_show_str);
        helper.setImageUrl(R.id.iv_interact_event_item_img, item.main_img_url, R.drawable.holde);
        if (item.is_over.equals("102")) {
            helper.getView(R.id.tv_interact_event_item_stat).setVisibility(View.GONE);
        } else {
            helper.getView(R.id.tv_interact_event_item_stat).setVisibility(View.VISIBLE);
        }
    }
}
