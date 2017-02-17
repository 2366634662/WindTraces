package com.ac57.ui.main.fragment;

import android.os.Bundle;
import android.view.View;

import com.ac57.R;
import com.ac57.framework.base.BaseFragment;

/**
 * Created on 2017/2/16.
 * Desc :
 */

public class OtherThreeFragment extends BaseFragment {

    public static OtherThreeFragment newInstance(String id) {
        Bundle bundle = new Bundle();
        bundle.putString("id", id);
        OtherThreeFragment otherOneFragment = new OtherThreeFragment();
        otherOneFragment.setArguments(bundle);
        return otherOneFragment;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_other_three;
    }

    @Override
    protected void initView(View convertView, Bundle savedInstanceState) {

    }

    @Override
    protected void initData() {

    }

    @Override
    protected void getData() {

    }
}
