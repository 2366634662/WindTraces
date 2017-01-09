package com.ac57.ui.presenter;

import com.ac57.framework.base.BasePresenter;
import com.ac57.ui.presenter.view.IRegisterView;

/**
 * Created by Du_Li on 2016/12/20.
 */

public class RegisterPresenter extends BasePresenter<IRegisterView> {
    /**
     * 在子类的构造函数中，设定参数为model，这时候可以presenter调用接口来实现对界面的操作。
     *
     * @param model
     */
    public RegisterPresenter(IRegisterView model) {
        super(model);
    }

}
