package com.ac57.ui;

import android.app.Application;
import android.content.Context;

/**
 * Created by Du_Li on 2016/12/17.
 */

public class AppContext extends Application {

    private static AppContext context;

    @Override
    public void onCreate() {
        super.onCreate();
        context = this;
    }

    public static AppContext getMyAppContext() {
        return context;
    }

    public static Context getAppContext() {
        return context.getApplicationContext();
    }
}
