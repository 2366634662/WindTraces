package com.ac57.ui.presenter.view;

import com.ac57.framework.base.IBaseStatusView;
import com.ac57.ui.entity.SelfMessageEntity;

import java.util.List;

/**
 * Desc : 消息中心 接口view
 */

public interface ISelfMessageView extends IBaseStatusView {

    void getSelfMessageData(List<SelfMessageEntity> entities);

}
