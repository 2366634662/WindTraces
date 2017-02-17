package com.ac57.ui.presenter.view;

import com.ac57.framework.base.BaseViewController;
import com.ac57.ui.entity.OptionDetailListEntity;
import com.ac57.ui.entity.OptionDetailListTopEntity;

import java.util.List;

/**
 * Created by Du_Li on 2017/2/13 13:35.
 * Function:
 * Desc:
 */

public interface IOptionDetailListView extends BaseViewController {
    void getTopData(OptionDetailListTopEntity entity);

    void getListData(List<OptionDetailListEntity> entities);
}
