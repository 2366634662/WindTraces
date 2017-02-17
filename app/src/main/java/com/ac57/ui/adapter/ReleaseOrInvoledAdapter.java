package com.ac57.ui.adapter;

import android.support.v7.widget.RecyclerView;

import com.ac57.R;
import com.ac57.ui.entity.ReleaseOrInvoledEntity;
import com.bumptech.glide.Glide;

import cn.bingoogolapple.androidcommon.adapter.BGARecyclerViewAdapter;
import cn.bingoogolapple.androidcommon.adapter.BGAViewHolderHelper;

/**
 * Created on 2017/2/16.
 * Desc :
 */

public class ReleaseOrInvoledAdapter extends BGARecyclerViewAdapter<ReleaseOrInvoledEntity> {
    public ReleaseOrInvoledAdapter(RecyclerView recyclerView) {
        super(recyclerView, R.layout.other_release_item);
    }

    @Override
    protected void fillData(BGAViewHolderHelper helper, int position, ReleaseOrInvoledEntity model) {
        Glide.with(mContext).load(model.user_show_data.head_img).placeholder(R.drawable.personal_head_portrait).into(helper.getImageView(R.id.release_item_head));
    }
}
