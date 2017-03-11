package com.ac57.ui.presenter;

import com.ac57.framework.base.BasePresenter;
import com.ac57.framework.retrofit.DefaultSubscriber;
import com.ac57.ui.entity.OptionDetailListEntity;
import com.ac57.ui.entity.OptionDetailListTopEntity;
import com.ac57.ui.presenter.view.IOptionDetailListView;
import com.ac57.ui.service.UserRepository;

import java.util.List;

/**
 * Created by Du_Li on 2017/2/13 13:35.
 * Function:
 * Desc:
 */

public class OptionDetailListPresenter extends BasePresenter<IOptionDetailListView> {

    public void getTopData(String id) {

        UserRepository.getInstance().getOptionDetailTopData(id).subscribe(new DefaultSubscriber<OptionDetailListTopEntity>() {
            @Override
            public void _onNext(OptionDetailListTopEntity entity) {
                if (getView() != null) {
                    getView().content();
                    getView().getTopData(entity);
                }
            }
            @Override
            public void _onError(String e) {
                if (getView() != null)
                    getView().error(e);
            }
        });

    }

    public void getListData(int page, String id, int stats) {
        UserRepository.getInstance().getOptionDetailListData(page, id, stats).subscribe(new DefaultSubscriber<List<OptionDetailListEntity>>() {
            @Override
            public void _onNext(List<OptionDetailListEntity> entity) {
                getView().content();
                getView().getListData(entity);
            }

            @Override
            public void _onError(String e) {
                getView().error(e);
            }
        });
    }

}
