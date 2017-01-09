package com.ac57.ui.entity.emoji;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.text.style.DynamicDrawableSpan;

/**
 * Created by Du_Li on 2016/12/31.
 */

public class MyImageSpan extends DynamicDrawableSpan {

    private Drawable mDrawable;
    private int draw_id;
    private Context context;
    private int dimens;

    public MyImageSpan(Context context, Drawable d, int dimen) {
        super();
        this.context = context;
        dimens = (int) context.getResources().getDimension(dimen);
        mDrawable = d;
    }

    public MyImageSpan(Context context, int draw_id, int dimen) {
        super();
        this.context = context;
        dimens = (int) context.getResources().getDimension(dimen);

        mDrawable = context.getResources().getDrawable(draw_id);
    }

    @Override
    public Drawable getDrawable() {
        mDrawable.setBounds(0, 0, dimens, dimens);
        return mDrawable;
    }
}
