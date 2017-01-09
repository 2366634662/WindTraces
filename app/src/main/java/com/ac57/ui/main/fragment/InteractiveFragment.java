package com.ac57.ui.main.fragment;


import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.ac57.R;
import com.ac57.framework.base.BaseFragment;
import com.ac57.ui.adapter.TabAndVPAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * A simple {@link Fragment} subclass.
 */
public class InteractiveFragment extends BaseFragment {
    @BindView(R.id.tlayout_interactive)
    TabLayout tlayoutInteractive;
    @BindView(R.id.vp_interact)
    ViewPager vpInteract;
    private List<String> titles;
    private String[] types;
    List<Fragment> fragments;

    private TabAndVPAdapter tabAndVPAdapter;

    public static InteractiveFragment newInstance() {
        InteractiveFragment fragment = new InteractiveFragment();
        return fragment;
    }

    public InteractiveFragment() {
        // Required empty public constructor
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_interactive;
    }

    @Override
    protected void initView(View convertView, Bundle savedInstanceState) {

    }

    @Override
    protected void initData() {
        titles = new ArrayList<>();
        titles.add("全部");
        titles.add("综述集锦");
        titles.add("文交所动态");
        titles.add("活动专区");
        titles.add("随便说说");
        titles.add("现货看点");
        types = new String[]{"all", "104", "103", "0", "101", "102"};
        fragments = new ArrayList<>();
        for (int i = 0; i < titles.size(); i++) {
            if (i == 3) {
                //活动专区页面
                fragments.add(InteractEventFragment.getInstance(i));
            } else {
                //全部  综述集锦  文交所动态  随便说说  现货看点
                fragments.add(OtherTypesInteractFragment.getInstance(types[i]));//其他
            }
        }
        tabAndVPAdapter = new TabAndVPAdapter(getFragmentManager(), fragments, titles);
        vpInteract.setAdapter(tabAndVPAdapter);
        tlayoutInteractive.setupWithViewPager(vpInteract);
        tlayoutInteractive.setTabMode(TabLayout.MODE_SCROLLABLE);


    }

    @Override
    protected void getData() {

    }

}
