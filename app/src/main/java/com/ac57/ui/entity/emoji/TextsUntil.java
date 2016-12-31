package com.ac57.ui.entity.emoji;

import android.graphics.Paint;
import android.text.SpannableString;
import android.text.TextPaint;
import android.text.TextUtils;
import android.text.method.LinkMovementMethod;
import android.widget.TextView;

import static com.ac57.framework.utils.sign.ProjectUntil.getWidth;

/**
 * Created by Administrator on 2016/9/13.
 */
public class TextsUntil {
    public static void setTextSpans(TextView tv, SpannableString... spans) {
        setTextSpan(tv, spans);
    }

    public static void setTextSpan(TextView tv, SpannableString... spans) {
        tv.setText("");
        if (TextUtils.isEmpty(tv.getText().toString())) {
            tv.setText(spans[0]);
        } else {
            tv.append(spans[0]);
        }
        for (int i = 1; i < spans.length; i++) {
            tv.append(spans[i]);
        }
        tv.setMovementMethod(LinkMovementMethod.getInstance());
    }

    public static void setTextSpan(TextView tv, SpannableString span, SpannableString... spans) {
        tv.setText(span);
        for (int i = 0; i < spans.length; i++) {
//            tv.append(spans[i]);
            tv.append(getSubString(tv, spans[i]));
        }
        tv.setMovementMethod(LinkMovementMethod.getInstance());
    }

    public static CharSequence getSubString(TextView tv, SpannableString content) {
//        float width = tv.getPaint().measureText(content.toString());
//        //这里只是为了方便，用屏幕宽度代替了textview控件宽度，如果需要精准控制，可以换成控件宽度
//        float tvWidth = tv.getWidth();
//        if (width / tvWidth > (4 + 0.5)) {
//            return content.subSequence(0, (int) (content.length() / (width / tvWidth) / (4 + 0.5)));
//        }

        int availableTextWidth = 3 * (getWidth()) - 150;
        Paint paint = tv.getPaint();
        paint.setTextSize(tv.getTextSize());

        // 根据长度截取出剪裁后的文字
        CharSequence ellipsizeStr = TextUtils.ellipsize(content.subSequence(0, content.length()), (TextPaint) paint, availableTextWidth, TextUtils.TruncateAt.END);
//        tv.setText(ellipsizeStr);

        return ellipsizeStr;
    }
}
