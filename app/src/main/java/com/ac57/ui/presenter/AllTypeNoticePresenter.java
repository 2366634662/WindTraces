package com.ac57.ui.presenter;

import com.ac57.framework.base.BasePresenter;
import com.ac57.framework.retrofit.DefaultSubscriber;
import com.ac57.ui.entity.NoticeEntity;
import com.ac57.ui.presenter.view.IAllTypeNoticeView;
import com.ac57.ui.service.UserRepository;

import java.util.List;

/**
 * Created by Du_Li on 2017/1/2.
 */

public class AllTypeNoticePresenter extends BasePresenter<IAllTypeNoticeView> {
    public AllTypeNoticePresenter(IAllTypeNoticeView model) {
        super(model);
    }

    public void getAllTypeNoticeData(int page, String exc_id) {
        model.showDailog("加载中");
        UserRepository.getInstance().getAllTypeNoticeData(page + "", exc_id).subscribe(new DefaultSubscriber<List<NoticeEntity>>() {
            @Override
            public void _onNext(List<NoticeEntity> entity) {
                model.disDailog();
                model.getAllTypeNoticeData(entity);
            }

            @Override
            public void _onError(String e) {
                model.disDailog();
                model.showError(e);
            }
        });
    }
}
