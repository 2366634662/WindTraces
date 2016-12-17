package com.ac57.framework.tools;

import android.content.Context;

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


    public void setCityId(String cityId) {
        putString("cityId", cityId);
    }

    public String cityId() {
        return getString("cityId", "");
    }

}
