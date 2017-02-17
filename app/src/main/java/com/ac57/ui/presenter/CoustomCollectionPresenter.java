package com.ac57.ui.presenter;

import android.util.Log;

import com.ac57.framework.base.BasePresenter;
import com.ac57.framework.retrofit.DefaultSubscriber;
import com.ac57.ui.entity.CoustomCollectionEntity;
import com.ac57.ui.entity.DeleteCoustomCollectionData;
import com.ac57.ui.presenter.view.ICoustomCollectionView;
import com.ac57.ui.service.UserRepository;

import java.util.List;

/**
 */

public class CoustomCollectionPresenter extends BasePresenter<ICoustomCollectionView> {
    public CoustomCollectionPresenter(ICoustomCollectionView model) {
        super(model);
    }

    public void getCoustomCollectionData(int page, String is_desc) {
        model.showDailog("加载中。。。");
        UserRepository.getInstance().getCoustomCollectionData(page, is_desc).subscribe(new DefaultSubscriber<List<CoustomCollectionEntity>>() {
            @Override
            public void _onNext(List<CoustomCollectionEntity> entity) {
                model.disDailog();
                model.getCoustomCollectionData(entity);
            }

            @Override
            public void _onError(String e) {
                model.disDailog();
                model.showError("" + e);
            }
        });
    }

    public void deleteData(String trade_id) {
        UserRepository.getInstance().deleteCoustomCollectionData(trade_id, "102").subscribe(new DefaultSubscriber<DeleteCoustomCollectionData>() {
            @Override
            public void _onNext(DeleteCoustomCollectionData entity) {
                model.deleteCoustomCollectionData(entity);
            }

            @Override
            public void _onError(String e) {
                Log.e("tag", "'" + e);
                model.showError("删除失败");
            }
        });
    }

}
