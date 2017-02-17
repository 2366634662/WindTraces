package com.ac57.ui.utils;

import com.google.gson.Gson;

/**
 * Created by Du_Li on 2017/2/6.
 * Desc:
 */
public class GsonUtils {
    private static volatile Gson mGsonUtils;

    private GsonUtils() {
    }

    public static Gson getInstance() {
        if (mGsonUtils == null) {
            synchronized (GsonUtils.class) {
                if (mGsonUtils == null) {
                    mGsonUtils = new Gson();
                }
            }
        }
        return mGsonUtils;
    }
}
