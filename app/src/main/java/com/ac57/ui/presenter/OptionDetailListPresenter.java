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
    public OptionDetailListPresenter(IOptionDetailListView model) {
        super(model);
    }

    public void getTopData(String id) {

        UserRepository.getInstance().getOptionDetailTopData(id).subscribe(new DefaultSubscriber<OptionDetailListTopEntity>() {
            @Override
            public void _onNext(OptionDetailListTopEntity entity) {
                model.getTopData(entity);
            }

            @Override
            public void _onError(String e) {
                model.showError(e);
            }
        });

    }

    public void getListData(int page, String id, int stats) {
        model.showDailog("");
        UserRepository.getInstance().getOptionDetailListData(page, id, stats).subscribe(new DefaultSubscriber<List<OptionDetailListEntity>>() {
            @Override
            public void _onNext(List<OptionDetailListEntity> entity) {
                model.disDailog();
                model.getListData(entity);
            }

            @Override
            public void _onError(String e) {
                model.disDailog();
                model.showError(e);
            }
        });
    }

}
