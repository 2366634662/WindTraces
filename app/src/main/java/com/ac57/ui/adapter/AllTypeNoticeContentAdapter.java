package com.ac57.ui.adapter;

import android.content.Context;
import android.graphics.Color;
import android.widget.TextView;

import com.ac57.R;
import com.ac57.framework.utils.StringUtils;
import com.ac57.ui.entity.NoticeEntity;

import cn.bingoogolapple.androidcommon.adapter.BGAAdapterViewAdapter;
import cn.bingoogolapple.androidcommon.adapter.BGAViewHolderHelper;

/**
 * Created by Du_Li on 2017/1/2.
 */

public class AllTypeNoticeContentAdapter extends BGAAdapterViewAdapter<NoticeEntity.ArtListBean> {
    private int id;
    private TextView tv_title;

    public void setId(int id) {
        this.id = id;
    }

    public AllTypeNoticeContentAdapter(Context context, int layoutResIds) {
        super(context, layoutResIds);
    }

    @Override
    protected void fillData(BGAViewHolderHelper helper, int position, NoticeEntity.ArtListBean item) {
        tv_title = helper.getView(R.id.cv_notice_item_title);
        String title = item.title;
        if ((id + "").equals("all")) {
            String name = item.art_exc_info.name;
            if (!item.art_exc_info.name.isEmpty() && StringUtils.isNotEmpty(name)) {
                title = "#" + name + "#" + title;
            }
        }
        helper.setText(R.id.tv_notice_item_content, title);
        tv_title.setText(item.art_cate_name);
        if (StringUtils.isNotEmpty(item.art_cate_color)) {
            try {
                tv_title.setBackgroundColor(Color.parseColor("#" + item.art_cate_color));
            } catch (Exception e) {
                tv_title.setBackgroundColor(Color.BLACK);
            }
        } else {
            tv_title.setBackgroundColor(Color.BLACK);
        }
    }
}
