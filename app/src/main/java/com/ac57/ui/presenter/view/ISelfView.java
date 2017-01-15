package com.ac57.ui.presenter.view;

import com.ac57.framework.base.BaseViewController;
import com.ac57.ui.entity.UserInfoDetailEntity;

/**
 * Created by Du_Li on 2017/1/15.
 */

public interface ISelfView extends BaseViewController {
    void getUserInfoDetail(UserInfoDetailEntity entity);
}
