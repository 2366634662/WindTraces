package com.ac57.ui.presenter.view;

import com.ac57.framework.base.BaseViewController;
import com.ac57.ui.entity.NewGoodsEntity;

import java.util.List;

/**
 */

public interface INewGoodsContentView extends BaseViewController {

    void getNewGoodsData(List<NewGoodsEntity> newGoodsEntities);
}
