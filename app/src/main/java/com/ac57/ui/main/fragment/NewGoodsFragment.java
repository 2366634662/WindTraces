package com.ac57.ui.main.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.RadioGroup;

import com.ac57.R;
import com.ac57.framework.base.BaseFragment;
import com.ac57.ui.adapter.MyFragmentPageAdapter;
import com.ac57.ui.view.MyViewPager;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * A simple {@link Fragment} subclass.
 */
public class NewGoodsFragment extends BaseFragment implements RadioGroup.OnCheckedChangeListener {
    @BindView(R.id.rgoup_new_goods_title)
    RadioGroup rgoupNewGoodsTitle;
    @BindView(R.id.vp_new_goods_page)
    MyViewPager vpNewGoodsPage;

    private List<Fragment> fragments = new ArrayList<>();
    private ArrayList<String> ex_title;
    private ArrayList<String> ex_id;
    private MyFragmentPageAdapter adapter;

    public static NewGoodsFragment newInstance(ArrayList<String> ex_title, ArrayList<String> ex_id) {
        Bundle bundle = new Bundle();
        bundle.putStringArrayList("ex_title", ex_title);
        bundle.putStringArrayList("ex_id", ex_id);
        NewGoodsFragment fragment = new NewGoodsFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    public NewGoodsFragment() {
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_new_goods;
    }

    @Override
    protected void initView(View convertView, Bundle savedInstanceState) {

    }

    @Override
    protected void initData() {
        ex_title = getArguments().getStringArrayList("ex_title");
        ex_id = getArguments().getStringArrayList("ex_id");
        //新品申购
        fragments.add(NewGoodsPurchaseFragment.newInstance(ex_title, ex_id, "101"));
        //新品托管
        fragments.add(NewGoodsHostingFragment.newInstance(ex_title, ex_id, "102"));
        adapter = new MyFragmentPageAdapter(getFragmentManager());
        adapter.setmFragments(fragments);
        vpNewGoodsPage.setAdapter(adapter);
        vpNewGoodsPage.setCurrentItem(0);
        rgoupNewGoodsTitle.setOnCheckedChangeListener(this);
    }

    @Override
    protected void getData() {

    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch (group.getCheckedRadioButtonId()) {
            case R.id.rb_new_goods_purchase:
                vpNewGoodsPage.setCurrentItem(0);
                break;
            case R.id.rb_new_goods_hosting:
                vpNewGoodsPage.setCurrentItem(1);
                break;
        }
    }

}
