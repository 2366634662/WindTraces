package com.ac57.ui.main.fragment;


import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.ac57.R;
import com.ac57.framework.base.BaseFragment;
import com.ac57.ui.adapter.MyFragmentPageAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * A simple {@link Fragment} subclass.
 */
public class NoticeFragment extends BaseFragment {

    @BindView(R.id.tlayout_notice)
    TabLayout tlayoutInteractive;
    @BindView(R.id.vp_notice)
    ViewPager vpInteract;

    private List<Fragment> fragments = new ArrayList<>();
    private List<String> ex_title;
    private List<String> ex_id;
    private MyFragmentPageAdapter pageAdapter;

    public static NoticeFragment newInstance(ArrayList<String> ex_title, ArrayList<String> ex_id) {
        Bundle bundle = new Bundle();
        bundle.putStringArrayList("ex_title", ex_title);
        bundle.putStringArrayList("ex_id", ex_id);
        NoticeFragment fragment = new NoticeFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    public NoticeFragment() {
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_notice;
    }

    @Override
    protected void initView(View convertView, Bundle savedInstanceState) {
    }

    @Override
    protected void initData() {
        ex_title = getArguments().getStringArrayList("ex_title");
        ex_id = getArguments().getStringArrayList("ex_id");

        for (int i = 0; i < ex_title.size(); i++) {
            //公告页面内容
            fragments.add(AllTypeNoticeFragment.newInstance(ex_id.get(i)));
        }
        pageAdapter = new MyFragmentPageAdapter(getFragmentManager());
        pageAdapter.setmFragments(fragments);
        pageAdapter.setTitle(ex_title);
        vpInteract.setAdapter(pageAdapter);
        tlayoutInteractive.setupWithViewPager(vpInteract);
        tlayoutInteractive.setTabMode(TabLayout.MODE_SCROLLABLE);
    }

    @Override
    protected void getData() {

    }
}
