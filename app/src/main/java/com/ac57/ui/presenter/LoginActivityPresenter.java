package com.ac57.ui.presenter;

import com.ac57.framework.base.BasePresenter;
import com.ac57.framework.retrofit.DefaultSubscriber;
import com.ac57.framework.retrofit.UserRepository;
import com.ac57.framework.utils.sign.ProUntil;
import com.ac57.framework.utils.sign.ProjectUntil;
import com.ac57.ui.entity.UserInfoData;
import com.ac57.ui.view.MyToast;

/**
 * Created by Du_Li on 2016/12/17.
 */

public class LoginActivityPresenter extends BasePresenter<LoginActivityViewController> {
    /**
     * 在子类的构造函数中，设定参数为model，这时候可以presenter调用接口来实现对界面的操作。
     *
     * @param model
     */
    public LoginActivityPresenter(LoginActivityViewController model) {
        super(model);
    }

    @Override
    public void initData() {

    }

    public void doLogin(String phone, String password) {
        String rand = ProjectUntil.randString(8);
        String login_sign = ProUntil.getLoginSign(rand, phone, password);
        UserRepository.getInstance().getUserInfoData(phone, rand, login_sign)
                .subscribe(new DefaultSubscriber<UserInfoData>() {
                    @Override
                    public void _onNext(UserInfoData entity) {
                        if (null != entity) {
//                            model.openHome(entity);
                            model.showDailog("登录成功", MyToast.Types.OK, entity);
                        } else {
                            model.showDailog("用户名或密码错误", MyToast.Types.ERREY, null);
                        }
                    }

                    @Override
                    public void _onError(String e) {
                        model.showDailog("用户名或密码错误", MyToast.Types.ERREY, null);
                    }
                });
    }

}
