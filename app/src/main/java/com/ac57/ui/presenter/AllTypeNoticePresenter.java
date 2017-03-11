package com.ac57.ui.presenter;

import com.ac57.framework.base.BasePresenter;
import com.ac57.framework.retrofit.DefaultSubscriber;
import com.ac57.ui.entity.NoticeEntity;
import com.ac57.ui.presenter.view.IAllTypeNoticeView;
import com.ac57.ui.service.UserRepository;

import java.util.List;

/**
 */

public class AllTypeNoticePresenter extends BasePresenter<IAllTypeNoticeView> {

    public void getAllTypeNoticeData(int page, String exc_id) {
        UserRepository.getInstance().getAllTypeNoticeData(page + "", exc_id).subscribe(new DefaultSubscriber<List<NoticeEntity>>() {
            @Override
            public void _onNext(List<NoticeEntity> entity) {
                if (getView() != null) {
                    getView().content();
                    getView().getAllTypeNoticeData(entity);
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
