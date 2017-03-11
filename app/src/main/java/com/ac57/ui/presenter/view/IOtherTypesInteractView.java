package com.ac57.ui.presenter.view;

import com.ac57.framework.base.IBaseStatusView;
import com.ac57.ui.entity.OtherTypesInteractEntity;

import java.util.List;

/**
 */

public interface IOtherTypesInteractView extends IBaseStatusView {

    void getOtherTypesInteractDatas(List<OtherTypesInteractEntity> entity);

}
