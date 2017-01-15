package com.ac57.ui.presenter.view;

import com.ac57.framework.base.BaseViewController;
import com.ac57.ui.entity.NoticeEntity;

import java.util.List;

/**
 * Created by Du_Li on 2017/1/2.
 */

public interface IAllTypeNoticeView extends BaseViewController {

    void getAllTypeNoticeData(List<NoticeEntity> noticeEntities);

}
