package com.ac57.ui.adapter;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ac57.R;
import com.ac57.ui.entity.SelectExchangeEntity;
import com.ac57.ui.view.CircleView;
import com.ac57.ui.view.stickyheaders.SectioningAdapter;

import java.util.ArrayList;

/**
 * Created by Du_Li on 2017/2/4.
 * Desc:
 */

public class SelectExchangeAdapter extends SectioningAdapter {

    ArrayList<Section> sections = new ArrayList<>();
    char alpha = 0;
    Section currentSection = null;

    private class Section {
        String alpha;
        ArrayList<SelectExchangeEntity.ExcListBean.ExcSectionListBean> indexData = new ArrayList<>();
    }



    public SelectExchangeAdapter() {
    }

    public void setIndexData(ArrayList<SelectExchangeEntity.ExcListBean.ExcSectionListBean> listBeen) {
        sections.clear();
        // sort indexData into buckets by the first letter of last name

//        Observable.from(listBeen).subscribe(excSectionListBean -> {
//            if (excSectionListBean.pinyin.charAt(0) != alpha) {
//                if (currentSection != null) {
//                    sections.add(currentSection);
//                }
//                currentSection = new Section();
//                alpha = excSectionListBean.pinyin.charAt(0);
//                currentSection.alpha = String.valueOf(alpha);
//                if (currentSection != null) {
//                    currentSection.indexData.add(excSectionListBean);
//                }
//            }
//        });
        for (SelectExchangeEntity.ExcListBean.ExcSectionListBean itemData : listBeen) {
            if (itemData.pinyin.charAt(0) != alpha) {
                if (currentSection != null) {
                    sections.add(currentSection);
                }

                currentSection = new Section();
                alpha = itemData.pinyin.charAt(0);
                currentSection.alpha = String.valueOf(alpha);
            }

            if (currentSection != null) {
                currentSection.indexData.add(itemData);
            }
        }
        sections.add(currentSection);
        notifyAllSectionsDataSetChanged();
    }

    @Override
    public int getNumberOfSections() {
        return sections.size();
    }

    @Override
    public int getNumberOfItemsInSection(int sectionIndex) {
        return sections.get(sectionIndex).indexData.size();
    }

    @Override
    public boolean doesSectionHaveHeader(int sectionIndex) {
        return true;
    }

    @Override
    public boolean doesSectionHaveFooter(int sectionIndex) {
        return false;
    }

    @Override
    public ItemViewHolder onCreateItemViewHolder(ViewGroup parent, int itemType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View v = inflater.inflate(R.layout.item_select_exchange_list, parent, false);
        return new ItemViewHolder(v);
    }

    @Override
    public HeaderViewHolder onCreateHeaderViewHolder(ViewGroup parent, int headerType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View v = inflater.inflate(R.layout.item_select_exchange_list_header, parent, false);
        return new HeaderViewHolder(v);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindItemViewHolder(SectioningAdapter.ItemViewHolder viewHolder, int sectionIndex, int itemIndex, int itemType) {
        Section s = sections.get(sectionIndex);
        ItemViewHolder ivh = (ItemViewHolder) viewHolder;
        SelectExchangeEntity.ExcListBean.ExcSectionListBean itemData = s.indexData.get(itemIndex);
        ivh.cv_select_exchange_type.setText(itemData.icon_show_str);
        try {
            ivh.cv_select_exchange_type.setBackgroundColor(Color.parseColor("#" + itemData.icon_show_color));
        } catch (Exception e) {
            ivh.cv_select_exchange_type.setBackgroundColor(Color.BLACK);
        }
        ivh.tv_select_exchange_name.setText(itemData.name);

        ivh.itemView.setOnClickListener(view -> {
            Log.e("tag", "item 信息" + itemData.toString());
        });

    }

    public int getPosition(int index) {
        for (int i = 0; i < sections.size(); i++) {
            char firstChar = sections.get(i).alpha.toUpperCase().charAt(0);
            if (firstChar == index) {
                return getAdapterPositionForSectionHeader(i);
            }
        }
        return -1;
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindHeaderViewHolder(SectioningAdapter.HeaderViewHolder viewHolder, int sectionIndex, int headerType) {
        Section s = sections.get(sectionIndex);
        HeaderViewHolder hvh = (HeaderViewHolder) viewHolder;
        hvh.titleTextView.setText(s.alpha);
    }

    public class ItemViewHolder extends SectioningAdapter.ItemViewHolder {
        TextView tv_select_exchange_name;
        CircleView cv_select_exchange_type;

        public ItemViewHolder(View itemView) {
            super(itemView);
            tv_select_exchange_name = (TextView) itemView.findViewById(R.id.tv_select_exchange_name);
            cv_select_exchange_type = (CircleView) itemView.findViewById(R.id.cv_select_exchange_type);
        }
    }

    public class HeaderViewHolder extends SectioningAdapter.HeaderViewHolder {
        TextView titleTextView;
        public HeaderViewHolder(View itemView) {
            super(itemView);
            titleTextView = (TextView) itemView.findViewById(R.id.tv_title_header);
        }
    }

}
