package com.ac57.ui.entity.emoji;

import android.graphics.drawable.Drawable;

import java.io.Serializable;

/**
 * Created by Du_Li on 2016/12/31.
 */

public class TextImgBean implements Serializable {
    public int type;//1文字2图片
    public String str;
    public Drawable draw;

    public TextImgBean(int type, String str, Drawable draw) {
        this.type = type;
        this.str = str;
        this.draw = draw;
    }
}
