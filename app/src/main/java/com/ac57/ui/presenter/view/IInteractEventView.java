package com.ac57.ui.presenter.view;

import com.ac57.framework.base.IBaseStatusView;
import com.ac57.ui.entity.InteractEventEntity;

import java.util.List;

/**
 */

public interface IInteractEventView extends IBaseStatusView {

    void getInteractEventData(List<InteractEventEntity> entity);

}
