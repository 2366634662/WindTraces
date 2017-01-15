package com.ac57.ui.presenter.view;

import com.ac57.framework.base.BaseViewController;
import com.ac57.ui.entity.NewGoodsEntity;

import java.util.List;

/**
 * Created by Du_Li on 2017/1/9.
 */

public interface INewGoodsContentView extends BaseViewController {

    void getNewGoodsData(List<NewGoodsEntity> newGoodsEntities);
}
