package com.ac57.ui.presenter.view;

import com.ac57.framework.base.BaseViewController;
import com.ac57.ui.entity.OtherTypesInteractEntity;

import java.util.List;

/**
 * Created by Du_Li on 2016/12/31.
 */

public interface IOtherTypesInteractView extends BaseViewController {

    void getOtherTypesInteractDatas(List<OtherTypesInteractEntity> entity);

}
