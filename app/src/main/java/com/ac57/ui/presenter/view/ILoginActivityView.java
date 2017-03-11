package com.ac57.ui.presenter.view;

import com.ac57.framework.base.IBaseStatusView;
import com.ac57.ui.entity.UserInfoData;

/**
 */

public interface ILoginActivityView extends IBaseStatusView {
    /**
     * 登陆成功跳转到其他界面
     *
     * @param bean
     */
    void openHome(UserInfoData bean);
}
