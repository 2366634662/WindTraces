package com.ac57.ui.main.fragment;

import android.os.Bundle;
import android.view.View;

import com.ac57.R;
import com.ac57.framework.base.MVPBaseFragment;
import com.ac57.ui.entity.ReleaseOrInvoledEntity;
import com.ac57.ui.presenter.ReleaseOrInvoledPresneter;
import com.ac57.ui.presenter.view.IReleaseOrInvoledView;

import java.util.List;

/**
 * Created on 2017/2/16.
 * Desc :
 */

public class OtherTwoFragment extends MVPBaseFragment<ReleaseOrInvoledPresneter, IReleaseOrInvoledView> implements IReleaseOrInvoledView  {

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

    }

    @Override
    protected void initData() {

    }

    @Override
    protected void getData() {
    }

    @Override
    public void getReleaseOrInvoledListData(List<ReleaseOrInvoledEntity> entities) {

    }

    @Override
    public void showDailog(String msg) {

    }

    @Override
    public void disDailog() {

    }

    @Override
    public void showError(String msg) {

    }

    @Override
    protected ReleaseOrInvoledPresneter initPresenter() {
        return null;
    }
}
