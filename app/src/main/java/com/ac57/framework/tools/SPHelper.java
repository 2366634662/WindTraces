package com.ac57.framework.tools;

import android.content.Context;

import com.ac57.ui.entity.UserInfoData;

/**
 * Created by Du_Li on 2016/12/17.
 */

public class SPHelper extends SharePrefrenceUtils {
    private static SPHelper sharePrefreceHelper;

    private SPHelper(Context context) {
        super(context);
    }

    public static SPHelper getInstence(Context context) {
        if (sharePrefreceHelper == null)
            sharePrefreceHelper = new SPHelper(context);
        return sharePrefreceHelper;
    }

    public void setIsFirst(boolean isFirst) {
        setBoolean("isFirst", isFirst);
    }

    public boolean isFirst() {
        return getBoolean("isFirst", true);
    }

    public void setIsLogin(boolean isFirst) {
        setBoolean("isLogin", isFirst);
    }

    public boolean isLogin() {
        return getBoolean("isLogin", false);
    }

    public void setPassWord(String password) {
        putString("password", password);
    }

    public void setUserType(String password) {
        putString("user_type", password);
    }

    public String getUserType() {
        return getString("user_type", "102");
    }
    public String getAnyString(String type) {
        return getString("type", "102");
    }

    /**
     * 保存用户信息
     *
     * @param infoData
     */
    public void saveUserData(UserInfoData.UserModelBean infoData) {
        putString("id", infoData.id);
        putString("nickname", infoData.nickname);
        putString("level", infoData.level);
        putString("head_img", infoData.head_img);
        putString("exp", infoData.exp);
        putString("user_type", infoData.user_type);
        putString("user_role", infoData.user_role);
        putString("phone", infoData.phone);
//        putString("passwd", infoData.passwd);
        putString("only_sign", infoData.only_sign);
        putString("is_first_login", infoData.is_first_login);
        putString("is_sign_today", infoData.is_sign_today);
        putString("exp_desc_url", infoData.exp_desc_url);
        putString("register_time", infoData.register_time);
        putString("profession_id", infoData.profession_id);
        putString("auth_status", infoData.auth_status);
        putString("register_data", infoData.register_data);
        putString("user_type", infoData.user_type);
    }

    public String getUserId() {
        return getString("id", "");
    }

}
