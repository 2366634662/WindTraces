package com.ac57.ui.utils;

import com.ac57.framework.tools.SPHelper;
import com.ac57.ui.AppContext;

/**
 * Created by Du_Li on 2017/1/15.
 */

public class Tools {
    public static LoginType loginType() {
        String user_type = SPHelper.getInstence(AppContext.getAppContext()).getUserType();
        if (user_type.equals("101")) {//注册用户
            return LoginType.WINDTRACES;
        } else if (user_type.equals("103")) {//微信
            return LoginType.WEIXIN;
        } else if (user_type.equals("104")) {//QQ
            return LoginType.QQ;
        } else {
            return LoginType.VISTOR;
        }
    }
}
