package com.ac57.ui.entity.emoji;

import android.content.Context;
import android.graphics.drawable.Drawable;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Du_Li on 2016/12/31.
 */

public class DrawPaser {
    private List<TextImgBean> formlist;
    private Context context;

    public DrawPaser(Context context) {
        this.context = context;
        formlist = new ArrayList<>();
    }
    public static String stringFilter(String str) {
        str = str.replaceAll("【", "[").replaceAll("】", "]")
                .replaceAll("！", "!").replaceAll("：", ":");// 替换中文标号
        String regEx = "[『』]"; // 清除掉特殊字符
        Pattern p = Pattern.compile(regEx);
        Matcher m = p.matcher(str);
        return m.replaceAll("").trim();
    }


    public List<TextImgBean> getDrawBean(String content) {

        String contentStr = stringFilter(content);
        int length = contentStr.length();
        int start = 0, end = 0;

        while (length > 0) {
            if (contentStr.contains("[em_") && contentStr.contains("]")) {
                start = contentStr.indexOf("[em_");
                end = contentStr.indexOf("]") + 1;
                String items = contentStr.substring(0, end);
                // Log.i("user","items>>"+items);
                getTextImgBean(items, start);
                contentStr = contentStr.substring(end, contentStr.length());
                // Log.i("user","contentStr>>"+contentStr);
                length = contentStr.length();
            }
            else {
                formlist.add(new TextImgBean(1, contentStr, null));
                break;
            }
        }
        return formlist;
    }

    private void getTextImgBean(String itemstr, int s) {
        String firstItem = itemstr.substring(0, s);
        //  Log.i("user","firstItem>>"+firstItem);
        String twoItem = itemstr.substring(s, itemstr.length());
        //  Log.i("user","twoItem>>"+twoItem);
        if (firstItem != null && firstItem.length() > 0) {
            formlist.add(new TextImgBean(1, firstItem, null));
        }
        int draw = getDrawId(twoItem);
        if(draw==-1){
            formlist.add(new TextImgBean(1, twoItem, null));
        }else {
            Drawable d = context.getResources().getDrawable(draw);
            formlist.add(new TextImgBean(2, twoItem, d));
        }

    }

    private int getDrawId(String twoItem) {
        int draw = -1;
        String content = twoItem.substring(twoItem.indexOf("[em_"), twoItem.indexOf("]")+1);
        for (EmBeans bean:EmBeans.values()) {
            if (bean.name.equals(content)) {
                draw = bean.draw;
                break;
            }
            else {
                continue;
            }
        }
        return draw;
    }

    public String toString(int draws) {
        for (EmBeans bean : EmBeans.values()) {
            if (bean.draw == draws) {
                return bean.name;
            }
            else {
                continue;
            }
        }
        return "";
    }
}
