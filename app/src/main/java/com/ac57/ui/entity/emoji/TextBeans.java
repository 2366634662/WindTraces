package com.ac57.ui.entity.emoji;

import android.content.Context;
import android.graphics.Color;
import android.text.Html;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;

import com.ac57.R;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Du_Li on 2016/12/31.
 */

public class TextBeans implements Serializable {
    private int po;
    private String str;
    private boolean isClick = false;
    private int cor;
    private Context context;
    private Object obj;

    public int contentLine;

    public int getPo() {
        return po;
    }

    public void setPo(int po) {
        this.po = po;
    }

    public String getStr() {
        return str;
    }

    public void setStr(String str) {
        this.str = str;
    }

    public int getCor() {
        return cor;
    }

    public void setCor(int cor) {
        this.cor = cor;
    }

    public boolean isClick() {
        return isClick;
    }

    public void setClick(boolean click) {
        isClick = click;
    }


    public TextBeans() {
    }

    public TextBeans(Context context) {
        this.context = context;
    }

    public TextBeans(Context context, String str) {
        this(context,  R.color.actionsheet_gray, false, str, -2, null);
    }

    public TextBeans(Context context, String str, int po) {
        this(context,  R.color.actionsheet_gray, false, str, po, null);
    }

    public TextBeans(Context context, int cor, String str, int po, Object obj) {
        this(context,  cor, false, str, po, obj);
    }

    public TextBeans(Context context,  int cor, boolean isClick, String str, int po, Object obj) {

        this.cor = cor;
        this.isClick = isClick;
        this.str = str;
        this.po = po;
        this.context = context;
        this.obj = obj;
    }

    public SpannableString init() {
        SpannableString spans = null;
        if (!isClick) {
            spans = new SpannableString(Html.fromHtml(str));
        } else {
            spans = new SpannableString(str);
            spans.setSpan(new MySimpleClickText(context, cor,  po, obj), 0, str.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        }
        return spans;
    }

    public SpannableString[] inits(List<TextImgBean> li) {
        List<SpannableString> list = new ArrayList<>();
        if (li != null) {
            for (TextImgBean bean : li) {
                String str = bean.str;
                SpannableString spans = new SpannableString(str);
                if (bean.type == 1) {//文字
                    spans.setSpan(new ForegroundColorSpan(Color.parseColor("#73717a")), 0, str.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                } else if (bean.type == 2) {//，表情
                    spans.setSpan(new MyImageSpan(context, bean.draw, R.dimen.DIMEN_20PX), 0, str.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                }
                list.add(spans);
            }
        }
        return (SpannableString[]) list.toArray(new SpannableString[list.size()]);
    }
}

