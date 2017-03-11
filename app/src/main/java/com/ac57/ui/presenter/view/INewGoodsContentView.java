package com.ac57.ui.presenter.view;

import com.ac57.framework.base.IBaseStatusView;
import com.ac57.ui.entity.NewGoodsEntity;

import java.util.List;

/**
 */

public interface INewGoodsContentView extends IBaseStatusView {

    void getNewGoodsData(List<NewGoodsEntity> newGoodsEntities);
}
