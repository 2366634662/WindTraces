package com.ac57.ui.entity;

import java.io.Serializable;

/**
 * Created by Du_Li on 2017/2/13 13:41.
 * Function:
 * Desc:
 */

public class OptionDetailListEntity implements Serializable {

    public String id;
    public String price;
    public String rises;
    public String is_up;
    public String trade_code;
    public String trade_name;
    public String content_url;
    public ExcInfoBean exc_info;
    public String time;
    public String num;

    public class ExcInfoBean implements Serializable {

        public String id;
        public String name;
        public String icon_show_str;
        public String icon_show_color;
    }
}
