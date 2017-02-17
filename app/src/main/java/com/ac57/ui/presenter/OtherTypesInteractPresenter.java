package com.ac57.ui.presenter;

import android.util.Log;

import com.ac57.framework.base.BasePresenter;
import com.ac57.framework.retrofit.DefaultSubscriber;
import com.ac57.ui.entity.OtherTypesInteractEntity;
import com.ac57.ui.presenter.view.IOtherTypesInteractView;
import com.ac57.ui.service.UserRepository;

import java.util.List;

/**
 */

public class OtherTypesInteractPresenter extends BasePresenter<IOtherTypesInteractView> {
    public OtherTypesInteractPresenter(IOtherTypesInteractView model) {
        super(model);
    }

    public void getOtherTypeInteractData(int page, String type) {
        model.showDailog("正在加载中");
        UserRepository.getInstance().getOtherTypesInteractListData("" + page, type).subscribe(new DefaultSubscriber<List<OtherTypesInteractEntity>>() {
            @Override
            public void _onNext(List<OtherTypesInteractEntity> entity) {
                model.disDailog();
                model.getOtherTypesInteractDatas(entity);
                Log.e("tag", "" + entity.toString());
            }

            @Override
            public void _onError(String e) {
                Log.e("tag", "" + e.toString());
                model.disDailog();
                model.showError("加载失败");
            }
        });
    }

}
