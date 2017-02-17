package com.ac57.ui.adapter;

import android.support.v7.widget.RecyclerView;
import android.text.SpannableString;
import android.view.View;

import com.ac57.R;
import com.ac57.framework.utils.StringUtils;
import com.ac57.framework.utils.Untils;
import com.ac57.ui.entity.MyReleaseAndInvoledEntity;
import com.ac57.ui.entity.emoji.DrawPaser;
import com.ac57.ui.entity.emoji.TextBeans;
import com.ac57.ui.entity.emoji.TextImgBean;
import com.ac57.ui.entity.emoji.TextsUntil;
import com.bumptech.glide.Glide;

import java.util.List;

import cn.bingoogolapple.androidcommon.adapter.BGARecyclerViewAdapter;
import cn.bingoogolapple.androidcommon.adapter.BGAViewHolderHelper;
import cn.bingoogolapple.photopicker.widget.BGANinePhotoLayout;

/**
 * Created on 2017/2/13.
 * Desc :
 */

public class MyInvolvedAdapter extends BGARecyclerViewAdapter<MyReleaseAndInvoledEntity> implements BGANinePhotoLayout.Delegate {
    private int draw[];

    public MyInvolvedAdapter(RecyclerView recyclerView) {
        super(recyclerView, R.layout.item_my_involved);
        draw = new int[]{R.drawable.level1,
                R.drawable.level2,
                R.drawable.level3,
                R.drawable.level4,
                R.drawable.level5,
                R.drawable.level6};
    }

    @Override
    protected void fillData(BGAViewHolderHelper helper, int position, MyReleaseAndInvoledEntity model) {
        Glide.with(mContext).load(model.user_show_data.head_img).placeholder(R.drawable.personal_head_portrait).into(helper.getImageView(R.id.involved_item_head));
        helper.setText(R.id.involved_item_name, model.user_show_data.nickname);
        helper.setText(R.id.involved_item_time, Untils.getTimes(model.c_time));

        if (model.latest_one_comment != null && StringUtils.isNotEmpty(model.latest_one_comment.content)) {
            String con1 = model.latest_one_comment.content;
            if (con1.length() > 90) {
                con1 = con1.substring(0, 90);
            }
            List<TextImgBean> ibean = new DrawPaser(mContext).getDrawBean(con1);
            TextBeans beans = new TextBeans(mContext);
            SpannableString[] sp = beans.inits(ibean);
            if (sp.length > 0) {
                TextsUntil.setTextSpan(helper.getTextView(R.id.involved_item_mess), sp);
            }
        }

        String contents = "<font color='#8F8F8F'>" + model.topic + " " + "</font>";
        if (model != null && StringUtils.isNotEmpty(model.content)) {
            String con2 = model.content;
            if (con2.length() > 90) {
                con2 = con2.substring(0, 90);
            }
            List<TextImgBean> ibean1 = new DrawPaser(mContext).getDrawBean(con2 + "");
            TextBeans beans1 = new TextBeans(mContext);
            SpannableString beans = new TextBeans(mContext, contents).init();
            SpannableString[] sp1 = beans1.inits(ibean1);
            TextsUntil.setTextSpan(helper.getTextView(R.id.involved_item_content), beans, sp1);
        } else {
            helper.setVisibility(R.id.involved_item_content, View.GONE);
        }
        int level;
        try {
            level = Integer.parseInt(model.user_show_data.level) - 1;
        } catch (NumberFormatException n) {
            level = 0;
        }
        helper.setImageResource(R.id.involved_item_level, draw[level]);
        if (model.img_url_list != null) {
            BGANinePhotoLayout recyclerView = (BGANinePhotoLayout) (helper.getView(R.id.nine_interact_item_item_img));
            recyclerView.setDelegate(this);
            recyclerView.setData(model.img_url_list);
        }
    }

    @Override
    public void onClickNinePhotoItem(BGANinePhotoLayout ninePhotoLayout, View view, int position, String model, List<String> models) {

    }
}
