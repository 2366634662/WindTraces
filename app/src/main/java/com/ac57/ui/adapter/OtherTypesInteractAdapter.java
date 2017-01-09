package com.ac57.ui.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.text.SpannableString;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.TextView;

import com.ac57.R;
import com.ac57.framework.base.adapter.RecyclerAdapter;
import com.ac57.framework.base.adapter.RecyclerAdapterHelper;
import com.ac57.framework.utils.StringUtils;
import com.ac57.ui.entity.OtherTypesInteractEntity;
import com.ac57.ui.entity.emoji.DrawPaser;
import com.ac57.ui.entity.emoji.TextBeans;
import com.ac57.ui.entity.emoji.TextImgBean;
import com.ac57.ui.entity.emoji.TextsUntil;

import java.util.List;


/**
 * Created by Du_Li on 2016/12/31.
 */

public class OtherTypesInteractAdapter extends RecyclerAdapter<OtherTypesInteractEntity> {
    private int draw[];

    private TextView tv_open_content;
    private TextView tv_interact_item_item_content;

    public OtherTypesInteractAdapter(Context context, @NonNull int... layoutResIds) {
        super(context, layoutResIds);
        draw = new int[]{R.drawable.level1,
                R.drawable.level2,
                R.drawable.level3,
                R.drawable.level4,
                R.drawable.level5,
                R.drawable.level6};

    }

    @Override
    protected void convert(RecyclerAdapterHelper helper, OtherTypesInteractEntity item) {
        tv_open_content = helper.getView(R.id.tv_open_content);
        tv_interact_item_item_content = helper.getView(R.id.tv_interact_item_item_content);
        if (item.user_show_data != null && item.user_show_data.id != null) {
            helper.setImageUrl(R.id.cv_interact_item_item_head, item.user_show_data.head_img, R.drawable.personal_head_portrait);
            helper.setText(R.id.tv_interact_item_item_name, item.user_show_data.nickname);
            int level = 0;
            try {
                level = Integer.parseInt(item.user_show_data.level) - 1;
            } catch (Exception e) {
                level = 0;
            }
            helper.setBackgroundRes(R.id.iv_interact_item_item_level, draw[level]);
        } else {
            helper.setBackgroundRes(R.id.cv_interact_item_item_head, R.drawable.personal_head_portrait);
            helper.setText(R.id.tv_interact_item_item_name, "该用户不存在");
            helper.setBackgroundRes(R.id.iv_interact_item_item_level, draw[0]);
        }
        String time = StringUtils.getTimes(item.c_time);
        helper.setText(R.id.tv_interact_item_item_time, time);
        String content = "<font color='#9b9b9b'>" + item.topic + " " + "</font>";
        String con = item.content;
        if (con.length() > 90) {
            con = con.substring(0, 90);
        }
        List<TextImgBean> ibean = new DrawPaser(context).getDrawBean(con + "");
        final TextBeans beans = new TextBeans(context);
        SpannableString bea = new TextBeans(context, content).init();
        SpannableString[] sp = beans.inits(ibean);
        tv_open_content.setText("查看全部");
        if (sp != null && sp.length > 0) {
            tv_interact_item_item_content.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
                @Override
                public void onGlobalLayout() {
                    if (!beans.isClick()) {
                        if (tv_interact_item_item_content.getLineCount() >= 4) {
                            tv_open_content.setVisibility(View.VISIBLE);
                            beans.contentLine = tv_interact_item_item_content.getLineCount();
                            tv_interact_item_item_content.setLines(4);
                        } else {
                            tv_open_content.setVisibility(View.GONE);
                            beans.contentLine = tv_interact_item_item_content.getLineCount();
                            tv_interact_item_item_content.setLines(beans.contentLine);
                        }
                    } else {
                        tv_interact_item_item_content.setLines(beans.contentLine);
                    }
                }
            });
            TextsUntil.setTextSpan(tv_interact_item_item_content, bea, sp);
        } else {
            tv_interact_item_item_content.setVisibility(View.GONE);
        }
        if (tv_interact_item_item_content.getLineCount() < 4) {
            tv_open_content.setVisibility(View.GONE);
        }

        //// TODO: 2016/12/31  RecyclerView加载图片
        if (item.is_like.equals("101"))
            helper.setBackgroundRes(R.id.iv_interact_item_item_good_img, R.drawable.announcement_interaction_good_selected);
        else
            helper.setBackgroundRes(R.id.iv_interact_item_item_good_img, R.drawable.personal_good);
        setTextViewColor((TextView) helper.getView(R.id.tv_interact_item_item_good), item.is_like.equals("101"));

        int read_times = Integer.parseInt(item.read_times);
        if (read_times > 0)
            helper.setText(R.id.tv_interact_item_item_look, item.read_times + "阅读");
        else
            helper.setText(R.id.tv_interact_item_item_look, "阅读");
        int like_times = Integer.parseInt(item.like_times);
        if (like_times > 0)
            helper.setText(R.id.tv_interact_item_item_good, like_times + "个赞");
        else
            helper.setText(R.id.tv_interact_item_item_good, "赞");
        int comment_times = Integer.parseInt(item.comment_times);
        if (comment_times > 0)
            helper.setText(R.id.tv__interact_item_item_write, comment_times + "条回复");
        else
            helper.setText(R.id.tv__interact_item_item_write, "回复");

    }

    private void setTextViewColor(TextView tv, boolean isTrue) {
        if (tv != null) {
            if (isTrue) {
                tv.setTextColor(Color.parseColor("#2c8dff"));
            } else {
                tv.setTextColor(Color.parseColor("#9e9e9e"));
            }
        }
    }
}
