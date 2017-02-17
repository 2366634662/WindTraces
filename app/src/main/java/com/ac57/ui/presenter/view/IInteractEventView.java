package com.ac57.ui.presenter.view;

import com.ac57.framework.base.BaseViewController;
import com.ac57.ui.entity.InteractEventEntity;

import java.util.List;

/**
 */

public interface IInteractEventView extends BaseViewController {

    void getInteractEventData(List<InteractEventEntity> entity);

}
