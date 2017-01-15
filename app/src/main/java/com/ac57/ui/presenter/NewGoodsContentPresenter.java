package com.ac57.ui.presenter;

import com.ac57.framework.base.BasePresenter;
import com.ac57.framework.retrofit.DefaultSubscriber;
import com.ac57.ui.entity.NewGoodsEntity;
import com.ac57.ui.presenter.view.INewGoodsContentView;
import com.ac57.ui.service.UserRepository;

import java.util.List;

/**
 * Created by Du_Li on 2017/1/9.
 */

public class NewGoodsContentPresenter extends BasePresenter<INewGoodsContentView> {
    public NewGoodsContentPresenter(INewGoodsContentView model) {
        super(model);
    }

    public void getNewGoodsData(int page, String exc_id, String type) {
        model.showDailog("加载中");
        UserRepository.getInstance().getNewGoodsData(page + "", exc_id, type).subscribe(new DefaultSubscriber<List<NewGoodsEntity>>() {
            @Override
            public void _onNext(List<NewGoodsEntity> entity) {
                model.disDailog();
                model.getNewGoodsData(entity);
            }

            @Override
            public void _onError(String e) {
                model.disDailog();
                model.showError("加载错误了");
            }
        });
    }

}
