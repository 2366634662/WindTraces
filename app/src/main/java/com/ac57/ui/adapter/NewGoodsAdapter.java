package com.ac57.ui.adapter;

import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.ac57.R;
import com.ac57.ui.entity.NewGoodsEntity;
import com.ac57.ui.view.RoundCornerTextView;
import com.ac57.ui.view.tag.Tag;
import com.ac57.ui.view.tag.TagGroup;

import java.util.ArrayList;
import java.util.List;

import cn.bingoogolapple.androidcommon.adapter.BGARecyclerViewAdapter;
import cn.bingoogolapple.androidcommon.adapter.BGAViewHolderHelper;

/**
 * Created by Du_Li on 2017/1/9.
 */

public class NewGoodsAdapter extends BGARecyclerViewAdapter<NewGoodsEntity> {

    private RoundCornerTextView rcn_new_goods_name;

    public NewGoodsAdapter(RecyclerView recyclerView) {
        super(recyclerView, R.layout.item_new_goods);
    }

    @Override
    protected void fillData(BGAViewHolderHelper helper, int position, NewGoodsEntity item) {
        helper.setText(R.id.tv_new_goods_title, item.title);
        helper.setText(R.id.tv_new_goods_time, item.time_show_str);
        float f = mContext.getResources().getDimension(R.dimen.DIMEN_3PX);
        rcn_new_goods_name = helper.getView(R.id.rcn_new_goods_name);
        try {
            rcn_new_goods_name.setColorAndConer(Color.parseColor("#" + item.art_exc_info.icon_show_color), (int) f);
            rcn_new_goods_name.setText(item.art_exc_info.name);
        } catch (Exception e) {
            rcn_new_goods_name.setColorAndConer(Color.BLACK, (int) f);
            rcn_new_goods_name.setText("暂无");
        }
        if (item.remind_info.btn_type.equals("101")) {
            helper.setVisibility(R.id.new_goods_mess_linear, View.GONE);
        } else if (item.remind_info.btn_type.equals("102")) {
            helper.setText(R.id.tv_new_goods_add, item.remind_info.time_show_str);
        } else if (item.remind_info.btn_type.equals("103")) {
            helper.setVisibility(R.id.iv_new_goods_add_img, View.GONE);
            helper.setText(R.id.tv_new_goods_add, item.remind_info.time_show_str);
        } else if (item.remind_info.btn_type.equals("104")) {
            helper.setVisibility(R.id.iv_new_goods_add_img, View.GONE);
            helper.setText(R.id.tv_new_goods_add, item.remind_info.time_show_str);
        }
        if (item.tag_arr == null || item.tag_arr.size() == 0) {
            helper.setVisibility(R.id.tg_new_goods_tag, View.GONE);
        } else {
            helper.setVisibility(R.id.tg_new_goods_tag, View.VISIBLE);
            List<Tag> tags = new ArrayList<>();
            for (String s : item.tag_arr) {
                tags.add(new Tag(Tag.TYPE_TEXT, 0, s));
            }
            ((TagGroup) (helper.getView(R.id.tg_new_goods_tag))).setTags(tags);
        }

    }
}
