package com.ac57.framework.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.ac57.R;
import com.ac57.framework.base.BaseActivity;
import com.ac57.framework.tools.AppManager;

/**
 * description: 跳转utils
 */
public class IntentUtils {

    /**
     * 通过类名启动Activity
     *
     * @param pClass
     */
    public static void startActivity(Context context, Class<?> pClass) {
        startActivity(context, pClass, null);
    }

    /**
     * 通过类名启动Activity，并且含有Bundle数据
     *
     * @param pClass
     * @param pBundle
     */
    public static void startActivity(Context context, Class<?> pClass, Bundle pBundle) {
        Intent intent = new Intent(context, pClass);
        if (pBundle != null) {
            intent.putExtras(pBundle);
        }
        context.startActivity(intent);
        ((Activity) context).overridePendingTransition(R.anim.enter_righttoleft,
                R.anim.exit_righttoleft);
    }


    public static void finishActivity(BaseActivity activity) {
        AppManager.getInstance().finishActivity(activity);
        activity.overridePendingTransition(R.anim.enter_lefttoright,
                R.anim.exit_lefttoright);
    }


    /**
     * 通过Action启动Activity
     *
     * @param pAction
     */
    public void startActivity(Context context, String pAction) {
        startActivity(context, pAction, null);
    }

    /**
     * 通过Action启动Activity，并且含有Bundle数据
     *
     * @param pAction
     * @param pBundle
     */
    public static void startActivity(Context context, String pAction, Bundle pBundle) {
        Intent intent = new Intent(pAction);
        if (pBundle != null) {
            /**
             * 新建任务
             */
            intent.addFlags(intent.FLAG_ACTIVITY_NEW_TASK);
            intent.putExtras(pBundle);
        }
        context.startActivity(intent);
    }

    /**
     * 通过Intent跳转
     *
     * @param context
     * @param Intent
     */
    public static void startActivity(Context context, Intent Intent) {
        if (Intent == null) {
            return;
        }
        context.startActivity(Intent);
    }
}
