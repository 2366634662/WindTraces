package com.ac57.ui.entity;

import java.util.ArrayList;

/**
 * Created on 2017/2/5.
 * Desc :
 */

public class MyReleaseAndInvoledEntity {


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
    public String img_ids;
    public String content;
    public String comment_id;
    public String is_like;
    public String like_times;
    public String comment_times;
    public String unread_comment_times;
    public LatestOneCommentEntity latest_one_comment;
    public String topic;
    public String c_time_show_str;
    public UserShowDataEntity user_show_data;
    public NewOneCommentEntity new_one_comment;
    public ArrayList<String> img_url_list;

    public class LatestOneCommentEntity {

        public String id;
        public String user_id;
        public String art_id;
        public String art_type;
        public String comment_type;
        public String reply_id;
        public String reply_user_id;
        public String content;
        public String c_time;
    }

    public class UserShowDataEntity {

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

    public class NewOneCommentEntity {

        public String id;
        public String user_id;
        public String art_id;
        public String art_type;
        public String comment_type;
        public String reply_id;
        public String reply_user_id;
        public String content;
        public String c_time;
    }
}
