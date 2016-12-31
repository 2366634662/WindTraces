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

import butterknife.BindView;

/**
 * A simple {@link Fragment} subclass.
 */
public class ReadingTypeFragment extends BaseFragment implements RadioGroup.OnCheckedChangeListener {
    @BindView(R.id.read_title)
    RadioGroup read_title;
    @BindView(R.id.vp_radingtype)
    MyViewPager vpRadingtype;
    private ArrayList<Fragment> mFragments = new ArrayList<>();
    private MyFragmentPageAdapter adapter;

    public static ReadingTypeFragment newInstance() {
        ReadingTypeFragment fragment = new ReadingTypeFragment();
        return fragment;
    }

    public ReadingTypeFragment() {
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_reading_type;
    }

    @Override
    protected void initView(View convertView, Bundle savedInstanceState) {
        mFragments.add(OptionWatchFragment.newInstance());//行情看点
        mFragments.add(CoustomCollectionFragment.newInstance());//自选藏品
        adapter = new MyFragmentPageAdapter(getFragmentManager());
        adapter.setmFragments(mFragments);
        vpRadingtype.setAdapter(adapter);
        vpRadingtype.setCurrentItem(0);
        read_title.setOnCheckedChangeListener(this);
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void getData() {

    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        int cid = group.getCheckedRadioButtonId();
        switch (group.getCheckedRadioButtonId()) {
            case R.id.rb_read_option:
                vpRadingtype.setCurrentItem(0);
                break;
            case R.id.rb_read_collection:
                vpRadingtype.setCurrentItem(1);
                break;
        }
    }
}
