package com.ac57.ui.adapter;

import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.ac57.R;
import com.ac57.framework.utils.StringUtils;
import com.ac57.framework.utils.Untils;
import com.ac57.ui.entity.HomeInfoListEntity;
import com.bumptech.glide.Glide;

import cn.bingoogolapple.androidcommon.adapter.BGARecyclerViewAdapter;
import cn.bingoogolapple.androidcommon.adapter.BGAViewHolderHelper;

/**
 * Created by Du_Li on 2016/12/25.
 */
public class HomeListInfoAdapter extends BGARecyclerViewAdapter<HomeInfoListEntity> {

    public HomeListInfoAdapter(RecyclerView recyclerView) {
        super(recyclerView);
    }

    @Override
    public int getItemViewType(int position) {
        if (getItem(position).is_big_img.equals("102")) {
            return R.layout.item_home_collication;
        } else {
            return R.layout.item_information_list;
        }
    }

    int pos = 1;

    @Override
    protected void fillData(BGAViewHolderHelper helper, int pos, HomeInfoListEntity item) {
        if (item.is_big_img.equals("102")) {
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
                helper.setText(R.id.tv_collectinfoms_item_read, read_count + "万阅读");
            } else {
                helper.setText(R.id.tv_collectinfoms_item_read, read_count + "阅读");
            }
//            helper.setImageUrl(R.id.iv_collectinformas_item_img, item.cover_img, R.drawable.group1);
            Glide.with(mContext).load(item.cover_img).placeholder(R.drawable.group1).into(helper.getImageView(R.id.iv_collectinformas_item_img));
            String content_type = item.content_type;
            if (content_type.equals("103")) {
                helper.getView(R.id.tv_collectinformas_item_img_stat).setVisibility(View.VISIBLE);
            } else {
                helper.getView(R.id.tv_collectinformas_item_img_stat).setVisibility(View.GONE);
            }
            if (content_type.equals("104")) {
                helper.getView(R.id.tv_collectinfoms_item_read).setVisibility(View.GONE);
                helper.getView(R.id.tv_colllectinfoms_item_time).setVisibility(View.GONE);
            }
        } else if (item.is_big_img.equals("101")) {
            helper.setText(R.id.tv_information_list_item_title, item.title);
//            helper.setImageUrl(R.id.iv_information_list_item, item.cover_img, R.drawable.group3);
            Glide.with(mContext).load(item.cover_img).placeholder(R.drawable.group3).into(helper.getImageView(R.id.iv_information_list_item));
            if (!item.content_type.equals("104")) {
                helper.setVisibility(R.id.rlayout_information_list_item_linear, View.VISIBLE);
                helper.setVisibility(R.id.tv_information_list_item_img_stat, View.GONE);
                helper.setText(R.id.tv_information_list_item_name, item.type_name);
                helper.setText(R.id.tv_information_list_item_time, StringUtils.getTimeStr(item.c_time));
                if (item.is_read.equals("101")) {
                    helper.setTextColor(R.id.tv_information_list_item_title, Color.parseColor("#9b9b9b"));
                } else {
                    helper.setTextColor(R.id.tv_information_list_item_title, Color.parseColor("#202c3b"));
                }
                int read_count = Integer.parseInt(item.read_times);
                if (read_count > 10000) {
                    read_count = read_count / 10000;
                    helper.setText(R.id.tv_information_list_item_read, read_count + "万阅读");
                } else {
                    helper.setText(R.id.tv_information_list_item_read, read_count + "万阅读");
                }
            } else {
                helper.setVisibility(R.id.rlayout_information_list_item_linear, View.GONE);
                helper.setVisibility(R.id.tv_information_list_item_img_stat, View.VISIBLE);
            }
            if (item.content_type.equals("103")) {
                helper.setVisibility(R.id.tv_information_list_item_img_all, View.VISIBLE);
            } else {
                helper.setVisibility(R.id.tv_information_list_item_img_all, View.GONE);
            }
            if (item.content_type.equals("104")) {
                helper.setVisibility(R.id.tv_information_list_item_read, View.GONE);
                helper.setVisibility(R.id.tv_information_list_item_time, View.GONE);
            }
//            helper.seton(R.id.tv_information_list_item_name, view -> {
//                Log.e("tag", "position" + pos);
//                Log.e("tag", "position");
//                if (item.content_type.equals("104")) {
//
//                } else {
////                    Intent intent = new Intent(context, InfomationItemActivity.class);//子列表
////                    intent.putExtra("art_type", item.art_type);
////                    intent.putExtra("name", item.type_name);
////                    intent.putExtra("content_type", item.content_type);
////                    context.startActivity(intent);
//                }
//            });
//            helper.getItemView().setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    Log.e("tag", "position" + pos);
//                }
//            });
//            helper.getView(R.id.llayout_all_view).setOnClickListener(view -> {
//                Log.e("tag", "position" + pos);
//            });
//            helper.setOnClickListener(R.id.llayout_all_view, view -> {
//                Log.e("tag", "position" + pos);
//                if (!item.content_type.equals("104")) {//不是广告
//                    if (!item.content_type.equals("103")) {
////                        Intent intent = new Intent(context, InteractItemInfosActivity.class);//文本+图片,或者h5  详情
////                        intent.putExtra("art_type", item.art_type);
////                        intent.putExtra("name", item.type_name);
////                        intent.putExtra("art_id", item.id);
////                        intent.putExtra("content_type", item.content_type);
////                        intent.putExtra("type", 3);//根据这个判断详情显示的标题
////                        context.startActivity(intent);
//                    } else {
////                        Intent in = new Intent(context, OtherInfomationItemActivity.class);//多图集
////                        in.putExtra("art_type",item.art_type);
////                        in.putExtra("art_id", item.id);
////                        in.putExtra("content_type",item.content_type);
////                        context.startActivity(in);
//                    }
//                } else {
//                    //// TODO: 2017/1/13    跳广告页面
//                }
//            });
        }
    }

    private void toBrower(int position) {
        if (getItem(position).advert_info.advert_type.equals("101")) {//内部浏览器
//            Intent intent = new Intent(context, MyJiFengActivity.class);
//            intent.putExtra("type", 4);
//            intent.putExtra("url", advert_info.get("advert_url") + "");
//            context.startActivity(intent);
        } else if (getItem(position).advert_info.advert_type.equals("102")) {//外部浏览器
//            Intent intent = new Intent();
//            intent.setAction("android.intent.action.VIEW");
//            Uri content_url = Uri.parse(advert_info.get("advert_url") + "");
//            intent.setData(content_url);
//            context.startActivity(intent);
        }
    }

}
