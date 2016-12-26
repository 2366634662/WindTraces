package com.ac57.ui.view;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import com.ac57.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/11/14.
 */

public class RabbitsView extends View {

    public float SCALE = 0.1f;
    public int aph = 0;
    private List<Animator> animators;
    private Bitmap downbp;
    private Bitmap[] runbp;
    private int[] draw;
    private float maxHeiht = 40;
    private float height = 1;
    private int current_index = 0;
    private Paint mPaint;
    private int mColor = Color.RED;
    private int stat = 1;
    private int run_count;
    private int rabbits_arr = R.array.rabbits_arr;
    private float max_scale = 1.5f;

    public RabbitsView(Context context) {
        super(context);
    }

    public RabbitsView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public RabbitsView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    private void init() {
        int dimens50 = (int) getResources().getDimension(R.dimen.DIMEN_50PX);
        int dimens26 = (int) getResources().getDimension(R.dimen.DIMEN_26PX);
        int dimens70 = (int) getResources().getDimension(R.dimen.DIMEN_70PX);
        int dimens37 = (int) getResources().getDimension(R.dimen.DIMEN_37PX);
        TypedArray typedArray = getResources().obtainTypedArray(rabbits_arr);
        if (typedArray != null) {
            run_count = typedArray.length();
            draw = new int[run_count];
            runbp = new Bitmap[run_count - 1];
            for (int i = 0; i < typedArray.length(); i++) {
                draw[i] = typedArray.getResourceId(i, 0);
                if (i == 0) {
                    downbp = BitmapFactory.decodeResource(getResources(), draw[i]);
                    downbp = Bitmap.createScaledBitmap(downbp, dimens50, dimens26, false);
                } else {
                    Bitmap bp = BitmapFactory.decodeResource(getResources(), draw[i]);
                    runbp[i - 1] = Bitmap.createScaledBitmap(bp, dimens70, dimens37, false);
                }
            }
            typedArray.recycle();
        }
        mPaint = new Paint();
        mPaint.setColor(mColor);
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setAntiAlias(true);
        createAnimation();
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (stat == 1) {
            int w = (int) (downbp.getWidth() * SCALE);
            int h = (int) (downbp.getHeight() * SCALE);
            if (w <= 0) {
                w = 1;
            }
            if (h <= 0) {
                h = 1;
            }
            Bitmap bp = Bitmap.createScaledBitmap(downbp, w, h, true);
            canvas.drawBitmap(bp, (getWidth() - w) / 2, (getHeight() - h) / 2, mPaint);
        } else if (stat == 2) {
            Bitmap currentbp = runbp[current_index];
            int w = currentbp.getWidth();
            int h = currentbp.getHeight();
            canvas.drawBitmap(currentbp, (getWidth() - w) / 2, (getHeight() - h) / 2, mPaint);
        }
    }


    public void setCurrentHeight(float height) {
        this.height = height;
        if (height / maxHeiht < max_scale) {
            SCALE = height / maxHeiht;
        } else {
            SCALE = max_scale;
        }
        int aph = (int) (255 / maxHeiht * height);
        if (aph > 255) {
            aph = 255;
        }
        mPaint.setAlpha(aph);
        postInvalidate();
    }

    public void setStat(int stat) {
        this.stat = stat;
        postInvalidate();
    }


    public List<Animator> createAnimation() {
        animators = new ArrayList<>();
        int delay = 120;
        for (int i = 0; i < 2; i++) {
            final int index = i;

            ValueAnimator scaleAnim = ValueAnimator.ofInt(0, run_count - 1);

            scaleAnim.setDuration(750);
            scaleAnim.setRepeatCount(-1);
            scaleAnim.setStartDelay(delay);

            scaleAnim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                @Override
                public void onAnimationUpdate(ValueAnimator animation) {
                    current_index = (int) animation.getAnimatedValue();
                    postInvalidate();

                }
            });
            scaleAnim.start();
            animators.add(scaleAnim);
        }
        return animators;
    }

    public void setAnimationStatus(AnimStatus animStatus) {
        if (animators == null) {
            return;
        }
        int count = animators.size();
        for (int i = 0; i < count; i++) {
            Animator animator = animators.get(i);
            boolean isRunning = animator.isRunning();
            switch (animStatus) {
                case START:
                    if (!isRunning) {
                        animator.start();
                    }
                    break;
                case END:
                    if (isRunning) {
                        animator.end();
                    }
                    break;
                case CANCEL:
                    if (isRunning) {
                        animator.cancel();
                    }
                    break;
            }
        }
    }

    public void setArrId(int rabbits_arr) {
        this.rabbits_arr = rabbits_arr;
        init();
    }

    public enum AnimStatus {
        START, END, CANCEL
    }
}
