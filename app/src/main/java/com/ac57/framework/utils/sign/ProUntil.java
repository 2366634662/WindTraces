package com.ac57.framework.utils.sign;


import com.ac57.framework.tools.SPHelper;
import com.ac57.ui.AppContext;

/**
 * Created by Administrator on 2016/8/29.
 */
public class ProUntil {
    public static String getLoginSign(String rand, String phone, String pass) {
        String passwd = ProjectUntil.toMd5String("ShiDian_" + pass);
        String decrypt = ProjectUntil.SHA1(new String[]{phone, passwd, rand});
        return decrypt;
    }

    public static String getUuid() {
        return ProjectUntil.getUuid(AppContext.getMyAppContext());
    }

    //
    public static String getUserid() {
        return SPHelper.getInstence(AppContext.getAppContext()).getUserId();
    }

}
