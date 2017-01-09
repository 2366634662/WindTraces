package com.ac57.ui.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.ViewPager;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.ac57.R;
import com.ac57.framework.base.adapter.RecyclerAdapter;
import com.ac57.framework.base.adapter.RecyclerAdapterHelper;
import com.ac57.framework.utils.sign.ProjectUntil;
import com.ac57.ui.entity.OptionWatchCenterEntity;
import com.bumptech.glide.Glide;

/**
 * Created by Du_Li on 2016/12/31.
 */

public class OptionWatchCenterAdapter extends RecyclerAdapter<OptionWatchCenterEntity> {
    private ImageView iv_read_center_item;

    public OptionWatchCenterAdapter(Context context, @NonNull int... layoutResIds) {
        super(context, layoutResIds);
    }

    @Override
    protected void convert(RecyclerAdapterHelper helper, OptionWatchCenterEntity item) {
        iv_read_center_item = helper.getView(R.id.iv_read_center_item);
//        helper.setImageUrl(R.id.iv_read_center_item, item.cover_img_url, R.drawable.celv_bg);
        Glide.with(context).load(item.cover_img_url).placeholder(R.drawable.celv_bg).into(iv_read_center_item);
        int width;
        int count = getSize();
        if (count <= 1) {
            width = (int) (ProjectUntil.getWidth() - context.getResources().getDimension(R.dimen.DIMEN_50PX));
        } else if (count == 2) {
            width = (int) (ProjectUntil.getWidth() - context.getResources().getDimension(R.dimen.DIMEN_75PX)) / 2;
        } else {
            width = (int) context.getResources().getDimension(R.dimen.DIMEN_180PX);
        }
        iv_read_center_item.setScaleType(ImageView.ScaleType.FIT_XY);
        RelativeLayout.LayoutParams pa = new RelativeLayout.LayoutParams(width, ViewPager.LayoutParams.MATCH_PARENT);
        iv_read_center_item.setLayoutParams(pa);
        helper.setText(R.id.tv_read_center_item, item.title);
    }
}
