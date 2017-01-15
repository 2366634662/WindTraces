package com.ac57.ui.presenter.view;

import com.ac57.framework.base.BaseViewController;
import com.ac57.ui.entity.CoustomCollectionEntity;
import com.ac57.ui.entity.DeleteCoustomCollectionData;

import java.util.List;

/**
 * Created by Du_Li on 2017/1/11.
 */

public interface ICoustomCollectionView extends BaseViewController {

    void getCoustomCollectionData(List<CoustomCollectionEntity> entity);

    void deleteCoustomCollectionData(DeleteCoustomCollectionData entity);

}
