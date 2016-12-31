package com.ac57.ui.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PaintFlagsDrawFilter;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * Created by Du_Li on 2016/12/31.
 */

public class CircleView extends TextView {
    private Paint mBgPaint = new Paint();
    private Paint mBgPaints = new Paint();
    private boolean isTrue = false;
    private int w;
    PaintFlagsDrawFilter pfd = new PaintFlagsDrawFilter(0, Paint.ANTI_ALIAS_FLAG | Paint.FILTER_BITMAP_FLAG);

    public CircleView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public CircleView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mBgPaint.setColor(Color.TRANSPARENT);
        mBgPaint.setAntiAlias(true);
        mBgPaints.setColor(Color.parseColor("#2c8dff"));
        mBgPaints.setAntiAlias(true);
        mBgPaints.setStyle(Paint.Style.STROKE);//设置空心
        w = 1;
        mBgPaints.setStrokeWidth(1);

    }

    public CircleView(Context context) {
        super(context);
        mBgPaint.setColor(Color.WHITE);
        mBgPaint.setAntiAlias(true);

        mBgPaints.setColor(Color.parseColor("#2c8dff"));
        mBgPaints.setAntiAlias(true);
        mBgPaints.setStyle(Paint.Style.STROKE);//设置空心
        w = 1;
        mBgPaints.setStrokeWidth(1);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int measuredWidth = getMeasuredWidth();
        int measuredHeight = getMeasuredHeight();
        int max = Math.max(measuredWidth, measuredHeight);
        setMeasuredDimension(max, max);
    }

    @Override
    public void setBackgroundColor(int color) {
        mBgPaint.setColor(color);
        invalidate();
    }

    public void setWid(int w) {
        this.w = w;
        mBgPaints.setStrokeWidth(w);
        invalidate();
    }

    public void setBackgroundColor(int color, boolean istrue) {
        mBgPaint.setColor(color);
        this.isTrue = istrue;
        invalidate();
    }

    /**
     * 设置通知个数显示
     *
     * @param text
     */
    public void setNotifiText(String text) {
        setText(text + "");
    }

    @Override
    public void draw(Canvas canvas) {
        canvas.setDrawFilter(pfd);//给Canvas加上抗锯齿标志
        canvas.drawCircle(getWidth() / 2, getHeight() / 2, Math.max(getWidth(), getHeight()) / 2, mBgPaint);
        if (isTrue) {
            int ra = Math.max(getWidth(), getHeight()) / 2 - w;
            canvas.drawCircle(getWidth() / 2, getHeight() / 2, ra, mBgPaints);
        }
        super.draw(canvas);
    }
}

