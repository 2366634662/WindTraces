package com.ac57.ui.adapter;

import android.support.v7.widget.RecyclerView;
import android.text.SpannableString;
import android.text.TextUtils;
import android.view.View;

import com.ac57.R;
import com.ac57.ui.entity.SelfMessageEntity;
import com.ac57.ui.entity.emoji.TextBeans;
import com.ac57.ui.entity.emoji.TextsUntil;

import cn.bingoogolapple.androidcommon.adapter.BGARecyclerViewAdapter;
import cn.bingoogolapple.androidcommon.adapter.BGAViewHolderHelper;

/**
 * Desc :
 */

public class SelfMessageAdapter extends BGARecyclerViewAdapter<SelfMessageEntity> {
    public SelfMessageAdapter(RecyclerView recyclerView) {
        super(recyclerView, R.layout.item_self_message);
    }

    @Override
    protected void fillData(BGAViewHolderHelper helper, int position, SelfMessageEntity model) {
        String session_type = model.session_type;
        if (session_type.equals("110")) {
            helper.setText(R.id.tv_self_message_title, "风迹活动");
            helper.setImageResource(R.id.iv_self_message_img, R.drawable.activity);
        } else if (session_type.equals("108")) {
            helper.setText(R.id.tv_self_message_title, "申购提醒");
            helper.setImageResource(R.id.iv_self_message_img, R.drawable.news_purchase);
            helper.setVisibility(R.id.iv_self_message_st, View.VISIBLE);
        } else if (session_type.equals("109")) {
            helper.setText(R.id.tv_self_message_title, "托管提醒");
            helper.setImageResource(R.id.iv_self_message_img, R.drawable.clock);
            helper.setVisibility(R.id.iv_self_message_st, View.VISIBLE);
        } else if (session_type.equals("170")) {
            helper.setText(R.id.tv_self_message_title, "话题动态");
            helper.setImageResource(R.id.iv_self_message_img, R.drawable.tab_interaction);
        } else if (session_type.equals("140")) {
            helper.setText(R.id.tv_self_message_title, "评论动态");
            helper.setImageResource(R.id.iv_self_message_img, R.drawable.personal_comment);
        } else if (session_type.equals("150")) {
            helper.setText(R.id.tv_self_message_title, "点赞动态");
            helper.setImageResource(R.id.iv_self_message_img, R.drawable.personal_good);
        } else if (session_type.equals("160")) {
            helper.setText(R.id.tv_self_message_title, "关注动态");
            helper.setImageResource(R.id.iv_self_message_img, R.drawable.personal_follow);
        }

        helper.setText(R.id.tv_self_message_date, model.time_show_str);
        helper.setText(R.id.tv_self_message_more, model.btn_str);

        String mess1 = "你发布的";
        String mess2 = "有了新的回复";
        if (session_type.equals("170")) {
            String cons = model.content;
            if (!TextUtils.isEmpty(cons) && !cons.equals("null")) {
                if (cons.length() > 14) {
                    cons = cons.substring(0, 11);
                    cons = cons + "...";
                }
            }
            SpannableString beans1 = new TextBeans(mContext, R.color.self_select2, mess1, 2, null).init();
            SpannableString beans2 = new TextBeans(mContext, R.color.self_select2, mess2, 2, null).init();
            SpannableString beans3 = new TextBeans(mContext, R.color.main_tab_blue_cor, true, cons + "", 2, null).init();
            TextsUntil.setTextSpans(helper.getTextView(R.id.tv_self_message_content), beans1, beans3, beans2);
        } else {
            helper.setText(R.id.tv_self_message_content, model.content);
        }
        helper.getView(R.id.tv_self_message_more).setOnClickListener(view -> {
//            setToTwing(model);


                }
        );
    }
//    private void setToTwing(SelfMessageEntity model) {
//        if (!model.session_type.equals("103")) {
//            if (model.is_list.equals("101")) {//是列表
//                int count = Integer.parseInt(model.total_content_num + "");
//                if (model.session_type.equals("160")) {//关注
//                    toWing(MyConcernActivity.class, model.id, model.session_type, count);
//                } else if (model.session_type.equals("150")) {//点赞
//                    toWing(MyLikeActivity.class, model.id, model.session_type, count);
//                } else if (model.session_type.equals("140")) {//评论
//                    toWing(MessPinglunActivity.class, model.id, model.session_type, count);
//                } else if (model.session_type.equals("108")) {//108申购提醒
//                    toWing(MessToastActivity.class, model.id, model.session_type, count);
//                } else if (model.session_type.equals("109")) {//109托管提醒
//                    toWing(MessToastActivity.class, model.id, model.session_type, count);
//                }
//            } else if (model.is_list.equals("102")) {
//                if (model.session_type.equals("110")) { //活动
//                    Bundle bundle = new Bundle();
//                    bundle.putInt("type", 5);
//                    bundle.putString("content_url", model.content_url);
//                    IntentUtils.startActivity(mContext, WebViewActivity.class, bundle);
//                } else {
//                    Bundle bundle = new Bundle();
//                    bundle.putString("art_type", model.art_type);
//                    bundle.putString("art_id", model.art_id);
//                    bundle.putString("content_type", model.content_type);
//                    bundle.putInt("type", 1);
//                    IntentUtils.startActivity(mContext, InteractItemInfosActivity.class, bundle);
//                }
//            }
//        } else {
//            Bundle bundle = new Bundle();
//            bundle.putString("art_type", model.art_type);
//            bundle.putString("art_id", model.art_id);
//            bundle.putString("content_type", model.content_type);
//            IntentUtils.startActivity(mContext, OtherInfomationItemActivity.class, bundle);
//        }
//    }
//
//    private void toWing(Class cla, String session_id, String msg_list_type, int count) {
//        Bundle bundle = new Bundle();
//        bundle.putString("session_id", session_id);
//        bundle.putString("msg_list_type", msg_list_type);
//        bundle.putInt("count", count);
//        IntentUtils.startActivity(mContext, cla, bundle);
//    }
}
