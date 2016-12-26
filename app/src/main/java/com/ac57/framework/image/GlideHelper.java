package com.ac57.framework.image;

import android.app.Activity;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

/**
 * Created by Du_Li on 2016/10/27.
 * Desc:
 */
public class GlideHelper {
    private static GlideHelper mGlideHelper;

    private GlideHelper() {
    }

    public static GlideHelper getInstance() {
        if (mGlideHelper == null) {
            mGlideHelper = new GlideHelper();
        }
        return mGlideHelper;
    }

    public void displayImage(Activity activity, ImageView img, String imgUrl) {
        Glide.with(activity)
                .load(imgUrl)
                .into(img);
    }
}
