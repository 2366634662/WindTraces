package com.ac57.ui.presenter.view;

import com.ac57.framework.base.IBaseStatusView;
import com.ac57.ui.entity.NoticeEntity;

import java.util.List;

/**
 */

public interface IAllTypeNoticeView extends IBaseStatusView {

    void getAllTypeNoticeData(List<NoticeEntity> noticeEntities);

}
