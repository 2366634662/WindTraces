package com.ac57.ui.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.view.View;

import com.ac57.R;
import com.ac57.framework.base.adapter.RecyclerAdapter;
import com.ac57.framework.base.adapter.RecyclerAdapterHelper;
import com.ac57.framework.utils.Untils;
import com.ac57.ui.entity.HomeInfoListEntity;

/**
 * Created by Du_Li on 2016/12/25.
 */

public class HomeListInfoAdapter extends RecyclerAdapter<HomeInfoListEntity> {

    public HomeListInfoAdapter(Context context, @NonNull int... layoutResIds) {
        super(context, layoutResIds);
    }

    @Override
    protected void convert(RecyclerAdapterHelper helper, HomeInfoListEntity item) {
        helper.setText(R.id.tv_colllectinfoms_item_title, item.title);
        helper.setText(R.id.tv_colllectinfoms_item_name, item.type_name);
        if (item.is_read.equals("101")) {
            helper.setTextColor(R.id.tv_colllectinfoms_item_title, Color.parseColor("#9b9b9b"));
        } else {
            helper.setTextColor(R.id.tv_colllectinfoms_item_title, Color.parseColor("#202c3b"));
        }
        String time = Untils.getTimeStr(item.c_time);
        helper.setText(R.id.tv_colllectinfoms_item_time, time);
        int read_count = Integer.parseInt(item.read_times);
        if (read_count > 10000) {
            read_count = (int) read_count / 10000;
            helper.setText(R.id.tv_collectinfoms_item_read, read_count + "次阅读");
        } else {
            helper.setText(R.id.tv_collectinfoms_item_read, read_count + "次阅读");
        }
        helper.setImageUrl(R.id.iv_collectinformas_item_img, item.cover_img);
        String content_type = item.content_type;
        if (content_type.equals("103")) {
            helper.getView(R.id.iv_colllectinfoms_item_stat).setVisibility(View.VISIBLE);
              /*  viewHloderType1.collectinformas_item_img.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        imgBrower(img_url_list, 0);
                    }
                });*/
        } else {
            helper.getView(R.id.iv_colllectinfoms_item_stat).setVisibility(View.GONE);
        }
        if (content_type.equals("104")) {
            helper.getView(R.id.tv_collectinfoms_item_read).setVisibility(View.GONE);
            helper.getView(R.id.tv_colllectinfoms_item_time).setVisibility(View.GONE);
        }
    }
}
