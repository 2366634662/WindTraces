package com.ac57.ui.presenter.view;

import com.ac57.framework.base.IBaseStatusView;
import com.ac57.ui.entity.CoustomCollectionEntity;
import com.ac57.ui.entity.DeleteCoustomCollectionData;

import java.util.List;

/**
 */

public interface ICoustomCollectionView extends IBaseStatusView {

    void getCoustomCollectionData(List<CoustomCollectionEntity> entity);

    void deleteCoustomCollectionData(DeleteCoustomCollectionData entity);

}
