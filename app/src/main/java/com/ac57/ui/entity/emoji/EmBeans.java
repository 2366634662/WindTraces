package com.ac57.ui.entity.emoji;

import com.ac57.R;

/**
 * Created by Du_Li on 2016/12/31.
 */

public enum EmBeans {
    Item1("[em_hehe]", R.drawable.em_hehe),
    Item2("[em_beishang]", R.drawable.em_beishang),
    Item3("[em_haha]", R.drawable.em_haha),
    Item4("[em_fennu]", R.drawable.em_fennu),
    Item5("[em_miyan]", R.drawable.em_miyan),
    Item6("[em_haqian]", R.drawable.em_haqian),
    Item7("[em_waizui]", R.drawable.em_waizui),
    Item8("[em_xingxingyan]", R.drawable.em_xingxingyan),
    Item9("[em_bugaoxing]", R.drawable.em_bugaoxing),
    Item10("[em_yanjing]", R.drawable.em_yanjing),
    Item11("[em_banyuanzui]", R.drawable.em_banyuanzui),
    Item12("[em_lengmo]", R.drawable.em_lengmo),
    Item13("[em_liulei]", R.drawable.em_liulei),
    Item14("[em_mimei]", R.drawable.em_mimei),
    Item15("[em_mojing]", R.drawable.em_mojing),
    Item16("[em_jingya]", R.drawable.em_jingya),
    Item17("[em_tushe]", R.drawable.em_tushe),
    Item18("[em_buxie]", R.drawable.em_buxie),
    Item19("[em_biyan]", R.drawable.em_biyan),
    Item20("[em_piezui]", R.drawable.em_piezui),
    Item21("[em_jidong]", R.drawable.em_jidong),
    Item22("[em_tiaopi]", R.drawable.em_tiaopi),
    Item23("[em_taoxin]", R.drawable.em_taoxin),
    Item24("[em_delet]", R.drawable.em_delet);
    public String name;
    public int draw;
    public boolean isSelect = false;

    EmBeans(String name, int draw) {
        this.name = name;
        this.draw = draw;
    }
}

