package com.ac57.ui.adapter;

import android.support.v7.widget.RecyclerView;
import android.text.SpannableString;
import android.view.View;

import com.ac57.R;
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

import static com.umeng.socialize.utils.DeviceConfig.context;

/**
 * Created on 2017/2/5.
 * Desc :
 */

public class MyReleaseAdapter extends BGARecyclerViewAdapter<MyReleaseAndInvoledEntity> implements BGANinePhotoLayout.Delegate {
    private int draw[];

    public MyReleaseAdapter(RecyclerView recyclerView) {
        super(recyclerView, R.layout.item_my_release);
        draw = new int[]{R.drawable.level1,
                R.drawable.level2,
                R.drawable.level3,
                R.drawable.level4,
                R.drawable.level5,
                R.drawable.level6};
    }

    @Override
    protected void fillData(BGAViewHolderHelper helper, int position, MyReleaseAndInvoledEntity model) {
        Glide.with(mContext).load(model.user_show_data.head_img).placeholder(R.drawable.personal_head_portrait).into(helper.getImageView(R.id.civ_release_item_head));
        helper.setText(R.id.tv_myrelease_item_name, model.user_show_data.nickname);
        String time = Untils.getTimes(model.c_time);
        helper.setText(R.id.tv_myrelease_item_time, time);
        int read_time = Integer.parseInt(model.read_times);
        if (read_time > 0) {
            helper.setText(R.id.tv_myrelease_item_look, read_time + "阅读");
        } else {
            helper.setText(R.id.tv_myrelease_item_look, "阅读");
        }
        int like_times = Integer.parseInt(model.like_times);
        if (like_times > 0) {
            helper.setText(R.id.tv_myrelease_item_good, model.like_times + "个赞");
        } else {
            helper.setText(R.id.tv_myrelease_item_good, "赞");
        }
        int comment_times = Integer.parseInt(model.comment_times);
        if (comment_times > 0) {
            helper.setText(R.id.tv_myrelease_item_write, model.comment_times + "条回复");
        } else {
            helper.setText(R.id.tv_myrelease_item_write, "回复");
        }

        String contents = "最新回复:";
        if (model.latest_one_comment != null && model.latest_one_comment.content != null) {
            String con1 = model.latest_one_comment.content;
            if (con1.length() > 90) {
                con1 = con1.substring(0, 90);
            }
            contents = contents + con1 + "";
            List<TextImgBean> ibean1 = new DrawPaser(context).getDrawBean(contents);
            TextBeans beans1 = new TextBeans(context);
            SpannableString[] sp1 = beans1.inits(ibean1);
            TextsUntil.setTextSpan(helper.getTextView(R.id.tv_myrelease_item_new), sp1);
        } else {
            contents = contents + "暂无";
            helper.setText(R.id.tv_myrelease_item_new, contents);
        }
        helper.setText(R.id.tv_myrelease_item_not, model.unread_comment_times + "条未读评论");
        String conten = "<font color='#8F8F8F'>" + model.topic + " " + "</font>";
        String con2 = model.content;
        if (con2.length() > 90) {
            con2 = con2.substring(0, 90);
        }
        List<TextImgBean> ibean = new DrawPaser(mContext).getDrawBean(con2 + "");
        TextBeans beans = new TextBeans(mContext);
        SpannableString bea = new TextBeans(mContext, conten).init();
        SpannableString[] sp = beans.inits(ibean);
        TextsUntil.setTextSpan(helper.getTextView(R.id.tv_myrelease_item_content), bea, sp);

        int level;
        try {
            //用户不为空的时候  正常显示等级
            level = Integer.parseInt(model.user_show_data.level) - 1;
        } catch (NumberFormatException n) {
            //用户不存在，服务器返回等级为空的时候设置个默认的值  默认等级为 1级
            level = 0;
        }
        helper.setImageResource(R.id.iv_myrelease_item_level, draw[level]);

        if (model.img_url_list != null) {
            BGANinePhotoLayout recyclerView = (BGANinePhotoLayout) (helper.getView(R.id.nine_myrelease_item_item_img));
            recyclerView.setDelegate(this);
            recyclerView.setData(model.img_url_list);
        }
        if ((model.img_url_list != null && model.img_url_list.size() > 0) || (model != null && model.content != null)) {
            helper.setVisibility(R.id.llayout_myrelease_item_content_linear, View.VISIBLE);
        } else {
            helper.setVisibility(R.id.llayout_myrelease_item_content_linear, View.GONE);
        }
    }

    @Override
    public void onClickNinePhotoItem(BGANinePhotoLayout ninePhotoLayout, View view, int position, String model, List<String> models) {

    }
}
