package com.ac57.ui.entity.emoji;

import android.content.Context;
import android.text.TextPaint;
import android.text.style.ClickableSpan;
import android.view.View;

/**
 * Created by Du_Li on 2016/12/31.
 */
public class MySimpleClickText extends ClickableSpan {
    private Context context;
    private int cor;
    private int po = -1;
    private Object obj;

    public MySimpleClickText(Context context, int cor, int po, Object obj) {
        super();
        this.context = context;
        this.cor = cor;
        this.po = po;
        this.obj = obj;
    }

    @Override
    public void updateDrawState(TextPaint ds) {
        super.updateDrawState(ds);
        //设置文本的颜色
        ds.setColor(context.getResources().getColor(cor));
        //超链接形式的下划线，false 表示不显示下划线，true表示显示下划线
        ds.setUnderlineText(false);
    }

    @Override
    public void onClick(View widget) {
        // Toast.makeText(context, "发生了点击效果", Toast.LENGTH_SHORT).show();
//        if(listener!=null) {
//            listener.callBack(po, 1, obj, null);
//        }
    }
}

