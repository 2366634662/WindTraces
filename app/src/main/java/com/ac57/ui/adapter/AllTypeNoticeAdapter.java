package com.ac57.ui.adapter;

import android.support.v7.widget.RecyclerView;

import com.ac57.R;
import com.ac57.ui.entity.NoticeEntity;
import com.ac57.ui.view.MyListView;

import java.util.ArrayList;
import java.util.List;

import cn.bingoogolapple.androidcommon.adapter.BGARecyclerViewAdapter;
import cn.bingoogolapple.androidcommon.adapter.BGAViewHolderHelper;


/**
 * Created by Du_Li on 2017/1/2.
 */

public class AllTypeNoticeAdapter extends BGARecyclerViewAdapter<NoticeEntity> {
    private int id;

    public void setId(int id) {
        this.id = id;
    }

    public AllTypeNoticeAdapter(RecyclerView recyclerView) {
        super(recyclerView, R.layout.item_all_type_notice_time);
    }

    @Override
    protected void fillData(BGAViewHolderHelper helper, int position, NoticeEntity item) {
        helper.setText(R.id.tv_notice_time_title, item.time_show_str);
        List<NoticeEntity.ArtListBean> artListBeen = new ArrayList<>();
        artListBeen = item.art_list;
        AllTypeNoticeContentAdapter contentAdapter = new AllTypeNoticeContentAdapter(mContext, R.layout.item_all_type_notice_content);
        contentAdapter.setData(artListBeen);
        contentAdapter.setId(id);
        MyListView myListView = helper.getView(R.id.lv_notice_content);
        myListView.setAdapter(contentAdapter);
    }
}
