package com.ac57.ui.presenter;

import android.app.Activity;
import android.content.Intent;

import com.ac57.framework.base.BasePresenter;
import com.ac57.framework.retrofit.DefaultSubscriber;
import com.ac57.framework.utils.sign.ProUntil;
import com.ac57.framework.utils.sign.ProjectUntil;
import com.ac57.ui.entity.UserInfoData;
import com.ac57.ui.presenter.view.ILoginActivityView;
import com.ac57.ui.service.UserRepository;
import com.umeng.socialize.UMAuthListener;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.bean.SHARE_MEDIA;

import java.util.Map;

/**
 */

public class LoginPresenter extends BasePresenter<ILoginActivityView> {
    /**
     * 在子类的构造函数中，设定参数为model，这时候可以presenter调用接口来实现对界面的操作。
     *
     * @param model
     */
    private Activity activity;
    private UMShareAPI mShareAPI = null;

    public LoginPresenter(Activity activity) {
        this.activity = activity;
        mShareAPI = UMShareAPI.get(activity);
    }

    public void doLogin(String phone, String password) {
        String rand = ProjectUntil.randString(8);
        String login_sign = ProUntil.getLoginSign(rand, phone, password);
        UserRepository.getInstance().getUserInfoData(phone, rand, login_sign)
                .subscribe(new DefaultSubscriber<UserInfoData>() {
                    @Override
                    public void _onNext(UserInfoData entity) {
                        if (getView() != null) {
                            if (null != entity) {
                                getView().openHome(entity);
                                getView().content();
                            } else {
                                getView().error("用户名或密码错误");
                            }
                        }
                    }

                    @Override
                    public void _onError(String e) {
                        if (getView() != null)
                            getView().error(e);
                    }
                });
//
    }

    /**
     * 第三方登陆
     *
     * @param type 登陆类型
     */
    public void doThreeLogin(SHARE_MEDIA type) {
        mShareAPI.doOauthVerify(activity, type, doAuthListener);
    }

    public void doVisitorLogin() {
        UserRepository.getInstance().getVisitorUserInfoData().subscribe(new DefaultSubscriber<UserInfoData>() {
            @Override
            public void _onNext(UserInfoData entity) {
                if (getView() != null) {
                    getView().content();
                    getView().openHome(entity);
                }
            }

            @Override
            public void _onError(String e) {
                if (getView() != null)
                    getView().error(e);
            }
        });
    }

    private UMAuthListener doAuthListener = new UMAuthListener() {
        @Override
        public void onComplete(SHARE_MEDIA share_media, int i, Map<String, String> map) {
            mShareAPI.getPlatformInfo(activity, share_media, umAuthListener);
        }

        @Override
        public void onError(SHARE_MEDIA share_media, int i, Throwable throwable) {
            if (getView() != null)
                getView().error(share_media + "第三方登陆失败");
        }

        @Override
        public void onCancel(SHARE_MEDIA share_media, int i) {
            if (getView() != null)
                getView().error(share_media + "登陆授权取消");
        }
    };
    private UMAuthListener umAuthListener = new UMAuthListener() {
        @Override
        public void onComplete(SHARE_MEDIA platform, int action, Map<String, String> data) {
//            model.getThreeLoginInfo(platform, data);
            String login_type = "";
            String name = "";
            String icon = "";
            String gender = "";
            String uid = "";
            if (platform == SHARE_MEDIA.WEIXIN) {
                login_type = "103";
                uid = data.get("unionid");
            } else if (platform == SHARE_MEDIA.QQ) {
                login_type = "104";
                uid = data.get("openid");
            }
            name = data.get("screen_name");
            icon = data.get("profile_image_url");
            gender = data.get("gender");
            UserRepository.getInstance().getThreeUserInfoData(login_type, name, icon, ProjectUntil.toMd5String(uid), gender)
                    .subscribe(new DefaultSubscriber<UserInfoData>() {
                        @Override
                        public void _onNext(UserInfoData entity) {
                            if (getView() != null){
                                getView().content();
                            getView().openHome(entity);
                        }}

                        @Override
                        public void _onError(String e) {
                            if (getView() != null)
                                getView().error(e);
                        }
                    });
        }

        @Override
        public void onError(SHARE_MEDIA platform, int action, Throwable t) {
            if (getView() != null)
                getView().error(platform + "第三方登陆失败");
        }

        @Override
        public void onCancel(SHARE_MEDIA platform, int action) {
            if (getView() != null)
                getView().error(platform + "登陆授权取消");
        }
    };

    public void threeLoginResult(int requestCode, int resultCode, Intent data) {
        UMShareAPI.get(activity).HandleQQError(activity, requestCode, umAuthListener);
        mShareAPI.onActivityResult(requestCode, resultCode, data);
    }

}
