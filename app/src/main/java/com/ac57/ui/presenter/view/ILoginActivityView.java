package com.ac57.ui.presenter.view;

import com.ac57.framework.base.BaseViewController;
import com.ac57.ui.entity.UserInfoData;

/**
 * Created by Du_Li on 2016/12/17.
 */

public interface ILoginActivityView extends BaseViewController {
    /**
     * 登陆成功跳转到其他界面
     *
     * @param bean
     */
    void openHome(UserInfoData bean);
}
