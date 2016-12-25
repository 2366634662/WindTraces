package com.ac57.ui.view.customtoast;

import android.content.Context;
import android.graphics.Color;
import android.view.Gravity;

import com.ac57.ui.AppContext;

/**
 * Created by Du_Li on 2016/12/20.
 * desc:自定义土司通知显示
 */

public class ToastUtils {

    private static final Context mContext = AppContext.getAppContext();

    private static UTCustomToast setToast(Context mContext, String content) {
        return UTCustomToast.makeToast(mContext, content)
                .setTextColor(Color.WHITE)
                .setTextSize(18)
                .setPadding(64, 32, 64, 32)
//                .setTextImage(R.drawable.ic_success_small)
//                .setTextImageSize(50, 50)
//                .setTextImageLocation(imagLocation)
                .setImagePadding(10)
                .setBackgroundColor(Color.parseColor("#" + "000000"))
                .setBackgroundAlpha(178)
                .setBackgroundRadius(10)
                .setToastGravity(Gravity.CENTER, 0, 50);
    }

    public static void success(String content) {
        setToast(mContext, content).showMToast();
    }

    public static void fail(String content) {
        setToast(mContext, content).showMToast();
    }

    public static void remind(String content) {
        setToast(mContext, content).showMToast();
    }

    public static void warn(String content) {
        setToast(mContext, content).showMToast();
    }

}
