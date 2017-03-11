package com.ac57.ui.presenter;

import com.ac57.framework.base.BasePresenter;
import com.ac57.framework.retrofit.DefaultSubscriber;
import com.ac57.ui.entity.NewGoodsEntity;
import com.ac57.ui.presenter.view.INewGoodsContentView;
import com.ac57.ui.service.UserRepository;

import java.util.List;

/**
 */

public class NewGoodsContentPresenter extends BasePresenter<INewGoodsContentView> {

    public void getNewGoodsData(int page, String exc_id, String type) {
        UserRepository.getInstance().getNewGoodsData(page + "", exc_id, type).subscribe(new DefaultSubscriber<List<NewGoodsEntity>>() {
            @Override
            public void _onNext(List<NewGoodsEntity> entity) {
                if (getView() != null) {
                    getView().content();
                    getView().getNewGoodsData(entity);
                }
            }
            @Override
            public void _onError(String e) {
                if (getView() != null)
                    getView().error(e);
            }
        });
    }

}
