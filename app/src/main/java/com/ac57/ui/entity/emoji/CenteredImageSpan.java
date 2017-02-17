package com.ac57.ui.entity.emoji;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.text.style.ImageSpan;

import java.lang.ref.WeakReference;

/**
 * <br/>
 * 作者：lzhs <br/>
 * 时间： 2017/1/10 0010 15:25<br/>
 * 邮箱：1050629507@qq.com
 */
public class CenteredImageSpan extends ImageSpan {
    private WeakReference<Drawable> mDrawableRef;

    public CenteredImageSpan(Drawable drawableRes) {
        super(drawableRes);
    }

    @Override
    public int getSize(Paint paint, CharSequence text, int start, int end, Paint.FontMetricsInt
            fm) {
        Drawable d = getCachedDrawable();
        Rect rect = d.getBounds();

        if (fm != null) {
            Paint.FontMetricsInt pfm = paint.getFontMetricsInt();
            // keep it the same as paint's fm
            fm.ascent = pfm.ascent;
            fm.descent = pfm.descent;
            fm.top = pfm.top;
            fm.bottom = pfm.bottom;
        }

        return rect.right;
    }

    @Override
    public void draw(Canvas canvas, CharSequence text, int start, int end, float x, int top, int
            y, int bottom, Paint paint) {
        Drawable b = getCachedDrawable();
        canvas.save();
        canvas.translate(x, (Math.abs(paint.getFontMetricsInt().top) -
                Math.abs(paint.getFontMetricsInt().ascent)));
        b.draw(canvas);
        canvas.restore();
    }

    // Redefined locally because it is a private member from
    private Drawable getCachedDrawable() {
        WeakReference<Drawable> wr = mDrawableRef;
        Drawable d = null;

        if (wr != null)
            d = wr.get();

        if (d == null) {
            d = getDrawable();
            mDrawableRef = new WeakReference<Drawable>(d);
        }

        return d;
    }
}
