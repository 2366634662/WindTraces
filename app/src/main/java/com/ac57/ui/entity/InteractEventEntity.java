package com.ac57.ui.entity;

/**
 * Created by Du_Li on 2016/12/31.
 */

public class InteractEventEntity {
    /**
     * id : 11
     * user_id : 1
     * art_type : 110
     * exc_id : 0
     * title : 测试活动
     * content_type : 101
     * c_time : 1476845151
     * read_times : 0
     * status : 101
     * notice_type_id : 0
     * main_img_url : http://127.0.0.1/wind/Public/xxxxxx.png
     * s_time : 123321331
     * e_time : 1232132131
     * art_exc_info : {"id":"1","name":"测试1","icon_show_str":"测","icon_show_color":"ef6666"}
     * is_over : 101
     * e_time_show_str : 结束时间: 10-19 12:33
     */

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
        /**
         * id : 1
         * name : 测试1
         * icon_show_str : 测
         * icon_show_color : ef6666
         */

        public String id;
        public String name;
        public String icon_show_str;
        public String icon_show_color;
    }
}
