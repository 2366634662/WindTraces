package com.ac57.ui.adapter;

import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.ac57.R;
import com.ac57.framework.utils.sign.ProjectUntil;
import com.ac57.ui.entity.OptionWatchCenterEntity;
import com.bumptech.glide.Glide;

import cn.bingoogolapple.androidcommon.adapter.BGARecyclerViewAdapter;
import cn.bingoogolapple.androidcommon.adapter.BGAViewHolderHelper;


/**
 * Created by Du_Li on 2016/12/31.
 */

public class OptionWatchCenterAdapter extends BGARecyclerViewAdapter<OptionWatchCenterEntity> {
    private ImageView iv_read_center_item;

    public OptionWatchCenterAdapter(RecyclerView xRecyclerView) {
        super(xRecyclerView, R.layout.item_option_watch_center);
    }

    @Override
    protected void fillData(BGAViewHolderHelper helper, int position, OptionWatchCenterEntity item) {
        iv_read_center_item = helper.getView(R.id.iv_read_center_item);
//        helper.setImageUrl(R.id.iv_read_center_item, item.cover_img_url, R.drawable.celv_bg);
        Glide.with(mContext).load(item.cover_img_url).placeholder(R.drawable.celv_bg).into(iv_read_center_item);
        int width;
        int count = getData().size();
        if (count <= 1) {
            width = (int) (ProjectUntil.getWidth() - mContext.getResources().getDimension(R.dimen.DIMEN_50PX));
        } else if (count == 2) {
            width = (int) (ProjectUntil.getWidth() - mContext.getResources().getDimension(R.dimen.DIMEN_75PX)) / 2;
        } else {
            width = (int) mContext.getResources().getDimension(R.dimen.DIMEN_180PX);
        }
        iv_read_center_item.setScaleType(ImageView.ScaleType.FIT_XY);
        RelativeLayout.LayoutParams pa = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewPager.LayoutParams.MATCH_PARENT);
        iv_read_center_item.setLayoutParams(pa);
        helper.setText(R.id.tv_read_center_item, item.title);
    }
}
