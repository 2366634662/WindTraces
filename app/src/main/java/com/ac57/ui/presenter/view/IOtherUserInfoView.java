package com.ac57.ui.presenter.view;

import com.ac57.framework.base.IBaseStatusView;
import com.ac57.ui.entity.OtherUserInfoEntity;

/**
 * Created on 2017/2/16.
 * Desc :
 */

public interface IOtherUserInfoView extends IBaseStatusView {

    void getOtherUserInfoData(OtherUserInfoEntity entity);

}
