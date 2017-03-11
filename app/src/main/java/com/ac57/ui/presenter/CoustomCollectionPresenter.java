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

    public void getCoustomCollectionData(int page, String is_desc) {
        getView().loading();
        UserRepository.getInstance().getCoustomCollectionData(page, is_desc).subscribe(new DefaultSubscriber<List<CoustomCollectionEntity>>() {
            @Override
            public void _onNext(List<CoustomCollectionEntity> entity) {
                if (getView() != null) {
                    getView().content();
                    getView().getCoustomCollectionData(entity);
                }
            }

            @Override
            public void _onError(String e) {
                if (getView() != null)
                    getView().error(e);
            }
        });
    }

    public void deleteData(String trade_id) {
        UserRepository.getInstance().deleteCoustomCollectionData(trade_id, "102").subscribe(new DefaultSubscriber<DeleteCoustomCollectionData>() {
            @Override
            public void _onNext(DeleteCoustomCollectionData entity) {
                if (getView() != null) {
                    getView().deleteCoustomCollectionData(entity);
                    getView().content();
                }
            }

            @Override
            public void _onError(String e) {
                Log.e("tag", "'" + e);
                getView().error(e);
            }
        });
    }

}
