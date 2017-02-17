package com.ac57.ui.entity;

import java.util.List;

/**
 * Created by Du_Li on 2017/1/9.
 */

public class NewGoodsEntity {
    public String id;
    public String new_goods_id;
    public String art_id;
    public String user_id;
    public String art_type;
    public String exc_id;
    public String title;
    public String content_type;
    public String c_time;
    public String read_times;
    public String like_times;
    public String status;
    public String notice_type_id;
    public String num;
    public String unit;
    public String buy_money;
    public String buy_time;
    public String success_time;
    public String unit_name;
    public ArtExcInfoBean art_exc_info;
    public RemindInfoBean remind_info;
    public String time_show_str;
    public List<String> tag_arr;

    public class ArtExcInfoBean {

        public String id;
        public String name;
        public String icon_show_str;
        public String icon_show_color;
    }

    public class RemindInfoBean {

        public String target_art_id;
        public String time_show_str;
        public String can_click;
        public String btn_type;
    }
}
