package com.ac57.ui.presenter;

import com.ac57.framework.base.BaseViewController;
import com.ac57.ui.entity.UserInfoData;
import com.ac57.ui.view.MyToast;

/**
 * Created by Du_Li on 2016/12/17.
 */

public interface LoginActivityViewController extends BaseViewController {
    /**
     * 登陆成功跳转到其他界面
     *
     * @param bean
     */
    /**
     * 显示信息提示对话框
     *
     * @param msg message
     */
    void showDailog(String msg, MyToast.Types types, UserInfoData infoData);

}
