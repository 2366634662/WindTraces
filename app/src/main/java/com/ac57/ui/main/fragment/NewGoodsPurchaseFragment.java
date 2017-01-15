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
 * 新品申购
 */
public class NewGoodsPurchaseFragment extends BaseFragment {
    @BindView(R.id.tlayout_ngp_content)
    TabLayout tlayoutNgpContent;
    @BindView(R.id.vp_ngp_content)
    ViewPager vpNgpContent;
    private ArrayList<String> ex_title;
    private ArrayList<String> ex_id;
    private List<Fragment> fragments = new ArrayList<>();
    private MyFragmentPageAdapter pageAdapter;
    private String type;

    public static NewGoodsPurchaseFragment newInstance(ArrayList<String> ex_title, ArrayList<String> ex_id, String type) {
        Bundle bundle = new Bundle();
        bundle.putStringArrayList("ex_title", ex_title);
        bundle.putStringArrayList("ex_id", ex_id);
        bundle.putString("type", type);
        NewGoodsPurchaseFragment fragment = new NewGoodsPurchaseFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    public NewGoodsPurchaseFragment() {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_new_goods_purchase;
    }

    @Override
    protected void initView(View convertView, Bundle savedInstanceState) {

    }
    @Override
    protected void initData() {
        ex_title = getArguments().getStringArrayList("ex_title");
        ex_id = getArguments().getStringArrayList("ex_id");
        type = getArguments().getString("type");
        pageAdapter = new MyFragmentPageAdapter(getFragmentManager());
        for (int i = 0; i < ex_id.size(); i++) {
            fragments.add(NewGoodsContentFragment.newInstance(ex_id.get(i), type));
        }
        pageAdapter.setmFragments(fragments);
        pageAdapter.setTitle(ex_title);
        vpNgpContent.setAdapter(pageAdapter);
        tlayoutNgpContent.setupWithViewPager(vpNgpContent);
        tlayoutNgpContent.setTabMode(TabLayout.MODE_SCROLLABLE);
        pageAdapter.notifyDataSetChanged();
    }

    @Override
    protected void getData() {

    }
}
