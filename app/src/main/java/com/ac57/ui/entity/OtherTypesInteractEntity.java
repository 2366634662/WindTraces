package com.ac57.ui.entity;

import java.util.ArrayList;

/**
 * Created by Du_Li on 2016/12/31.
 */

public class OtherTypesInteractEntity {
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
    public String type_name;
    public String exc_name;
    public String img_ids;
    public String content;
    public UserShowDataBean user_show_data;
    public String is_like;
    public String like_times;
    public String comment_times;
    public String topic;
    public String c_time_show_str;
    public PublisherInfoBean publisher_info;
    public ArrayList<String> img_url_list;

    public class UserShowDataBean {
        public String id;
        public String nickname;
        public String level;
        public String exp;
        public String head_img;
        public String user_type;
        public String phone;
        public String user_role;
        public String role_name;
    }

    public class PublisherInfoBean {

        public String id;
        public String nickname;
        public String level;
        public String exp;
        public String head_img;
        public String user_type;
        public String phone;
        public String user_role;
        public String role_name;

    }
}
