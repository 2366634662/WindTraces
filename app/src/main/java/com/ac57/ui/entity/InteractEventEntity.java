package com.ac57.ui.entity;

/**
 * Created by Du_Li on 2016/12/31.
 */

public class InteractEventEntity {

    public String id;
    public String user_id;
    public String art_type;
    public String exc_id;
    public String title;
    public String content_type;
    public String c_time;
    public String read_times;
    public String status;
    public String notice_type_id;
    public String main_img_url;
    public String s_time;
    public String e_time;
    public ArtExcInfoBean art_exc_info;
    public String is_over;
    public String e_time_show_str;

    public class ArtExcInfoBean {
        public String id;
        public String name;
        public String icon_show_str;
        public String icon_show_color;
    }
}
