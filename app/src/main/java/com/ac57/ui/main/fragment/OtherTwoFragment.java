package com.ac57.ui.main.fragment;

import android.os.Bundle;
import android.view.View;

import com.ac57.R;
import com.ac57.framework.base.BaseMVPFragment;
import com.ac57.ui.entity.ReleaseOrInvoledEntity;
import com.ac57.ui.presenter.ReleaseOrInvoledPresneter;
import com.ac57.ui.presenter.view.IReleaseOrInvoledView;

import java.util.List;

/**
 * Created on 2017/2/16.
 * Desc :
 */

public class OtherTwoFragment extends BaseMVPFragment<IReleaseOrInvoledView, ReleaseOrInvoledPresneter> implements IReleaseOrInvoledView {

    public static OtherTwoFragment newInstance(String id) {
        Bundle bundle = new Bundle();
        bundle.putString("id", id);
        OtherTwoFragment otherOneFragment = new OtherTwoFragment();
        otherOneFragment.setArguments(bundle);
        return otherOneFragment;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_other_two;
    }

    @Override
    protected void initView(View convertView, Bundle savedInstanceState) {
        setEasyStatusView(easyStatusView);
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void getData() {
        loading();
    }

    @Override
    public void getReleaseOrInvoledListData(List<ReleaseOrInvoledEntity> entities) {

    }

    @Override
    protected ReleaseOrInvoledPresneter initPresenter() {
        return new ReleaseOrInvoledPresneter();
    }
}
