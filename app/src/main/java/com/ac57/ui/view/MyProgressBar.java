package com.ac57.ui.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

import com.ac57.R;
import com.ac57.framework.utils.sign.ProjectUntil;

/**
 * Created by Administrator on 2016/10/28.
 */
public class MyProgressBar extends View {
    private String str = "0", str1 = "100";
    private int max_count = 100;
    private int min_count = 0;
    private int current_count = 0;
    private String persent_str = "0%";
    private String textStr = "还有100分升级";
    int w, h;
    private int p_width = 300;
    private int p_height = 15;

    private int pr_w = 40;
    private int h_margin = 5;
    private int p_w = 10;
    private int cor_blue;
    private int cor_gre_blue;
    private Paint bgPaint, mPaint, tvPaint;
    private int corner = 8;
    private Bitmap bp;
    private int ds = 18;
    private float cou = 20.0f;

    public MyProgressBar(Context context) {
        this(context, null);
    }

    public MyProgressBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        initvalues(context);
        init();
    }

    private void initvalues(Context context) {
        p_width = (int) context.getResources().getDimension(R.dimen.DIMEN_300PX);
        p_height = (int) context.getResources().getDimension(R.dimen.DIMEN_15PX);
        pr_w = (int) context.getResources().getDimension(R.dimen.DIMEN_40PX);
        h_margin = (int) context.getResources().getDimension(R.dimen.DIMEN_5PX);
        p_w = (int) context.getResources().getDimension(R.dimen.DIMEN_10PX);
        corner = (int) context.getResources().getDimension(R.dimen.DIMEN_8PX);
        ds = (int) context.getResources().getDimension(R.dimen.DIMEN_18PX);
    }

    private void init() {
        w = ProjectUntil.getWidth();
        h = ProjectUntil.getHeight();
        cor_blue = Color.parseColor("#2c8dff");
        cor_gre_blue = Color.parseColor("#cce8ff");
        bgPaint = new Paint();
        mPaint = new Paint();
        tvPaint = new Paint();
        bgPaint.setStyle(Paint.Style.FILL);
        mPaint.setStyle(Paint.Style.FILL);
        bgPaint.setColor(cor_gre_blue);
        bgPaint.setAntiAlias(true);// 设置画笔的锯齿效果
        mPaint.setColor(cor_blue);
        mPaint.setAntiAlias(true);// 设置画笔的锯齿效果

        tvPaint.setColor(Color.WHITE);
        tvPaint.setAntiAlias(true);// 设置画笔的锯齿效果
        int size = (int) this.getResources().getDimension(R.dimen.DIMEN_14PX);
        tvPaint.setTextSize(size);
        mPaint.setTextSize(size);
        bp = BitmapFactory.decodeResource(getResources(), R.drawable.personal_schedule);
        bp = Bitmap.createScaledBitmap(bp, pr_w, pr_w / 2, true);
    }

    public void setValues(int total, int min, int pre, String pres, String mess) {
        this.max_count = total;
        this.min_count = min;
        this.current_count = pre;
        //   current_count=1;
        this.str1 = total + "";
        this.str = current_count + "";
        this.persent_str = pres;
        this.textStr = mess;
        invalidate();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        int left = (w - p_width) / 2;

        try {
            p_w = current_count * p_width / (max_count - min_count);
        } catch (ArithmeticException e) {
        }


        RectF ovalBg = new RectF(left, 0, left + p_width, p_height);// 设置个新的长方形
        canvas.drawRoundRect(ovalBg, corner, corner, bgPaint);//第二个参数是x半径，第三个参数是y半径
        int lss = 0;
        if (current_count > 9) {
            p_w = p_w;
        } else {
            p_w = (int) ((current_count / cou) * p_width);
        }
        lss = p_w + left;
        RectF ovalPre = new RectF(left, 0, lss, p_height);// 设置个新的长方形
        canvas.drawRoundRect(ovalPre, corner, corner, mPaint);//第二个参数是x半径，第三个参数是y半径

        Rect min = new Rect();
        tvPaint.getTextBounds(str, 0, str.length(), min);
        int top = (p_height + min.height()) / 2;

        int ls = left + (p_w - min.width()) / 2;
        canvas.drawText(str, ls, top, tvPaint);

        Rect max = new Rect();
        tvPaint.getTextBounds(str1, 0, str1.length(), max);
        canvas.drawText(str1, w - left - max.width() - p_height, top, tvPaint);


        int bp_l = left + p_w - bp.getWidth() / 2;
        int bp_t = p_height + h_margin;
        canvas.drawBitmap(bp, bp_l, bp_t, tvPaint);

        Rect pre = new Rect();
        tvPaint.getTextBounds(persent_str, 0, persent_str.length(), pre);
        int pre_l = left + p_w - pre.width() / 2;
        int pre_t = p_height + h_margin + ds;
        canvas.drawText(persent_str, pre_l, pre_t, tvPaint);

        Rect tv = new Rect();
        tvPaint.getTextBounds(textStr, 0, textStr.length(), tv);
        int l = pre_l + pr_w;
        if (pre_l + pr_w + tv.width() >= w) {
            l = pre_l - pr_w - tv.width() / 2;
        }
        canvas.drawText(textStr, l, pre_t, mPaint);
    }
}
