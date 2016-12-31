package com.ac57.ui.entity;

import java.util.List;

/**
 * Created by Du_Li on 2016/12/31.
 */

public class OtherTypesInteractEntity {
    /**
     * id : 9
     * user_id : 3
     * art_type : 103
     * exc_id : 0
     * title : 测试文交所动态哈哈哈
     * content_type : 101
     * c_time : 1476844912
     * read_times : 0
     * status : 101
     * notice_type_id : 0
     * type_name : 文交所动态
     * exc_name :
     * img_ids : 1
     * content : 测试文交所动态哈哈哈:我是内容23333333333333
     * user_show_data : {"id":"3","nickname":"围观群众","level":"1","exp":"0","head_img":"http://127.0.0.1/wind/Public/upload/2016-10-8/123.png","user_type":"101","phone":"119","user_role":"1","role_name":"普通用户"}
     * img_url_list : ["http://127.0.0.1/wind/Public/upload/test.png"]
     * is_like : 102
     * like_times : 0
     * comment_times : 0
     * topic : #文交所动态#
     * c_time_show_str : 1小时前
     * publisher_info : {"id":"3","nickname":"围观群众","level":"1","exp":"0","head_img":"http://127.0.0.1/wind/Public/upload/2016-10-8/123.png","user_type":"101","phone":"119","user_role":"1","role_name":"普通用户"}
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
    public List<String> img_url_list;

    @Override
    public String toString() {
        return "OtherTypesInteractEntity{" +
                "id='" + id + '\'' +
                ", user_id='" + user_id + '\'' +
                ", art_type='" + art_type + '\'' +
                ", exc_id='" + exc_id + '\'' +
                ", title='" + title + '\'' +
                ", content_type='" + content_type + '\'' +
                ", c_time='" + c_time + '\'' +
                ", read_times='" + read_times + '\'' +
                ", status='" + status + '\'' +
                ", notice_type_id='" + notice_type_id + '\'' +
                ", type_name='" + type_name + '\'' +
                ", exc_name='" + exc_name + '\'' +
                ", img_ids='" + img_ids + '\'' +
                ", content='" + content + '\'' +
                ", user_show_data=" + user_show_data +
                ", is_like='" + is_like + '\'' +
                ", like_times='" + like_times + '\'' +
                ", comment_times='" + comment_times + '\'' +
                ", topic='" + topic + '\'' +
                ", c_time_show_str='" + c_time_show_str + '\'' +
                ", publisher_info=" + publisher_info +
                ", img_url_list=" + img_url_list +
                '}';
    }

    public class UserShowDataBean {
        /**
         * id : 3
         * nickname : 围观群众
         * level : 1
         * exp : 0
         * head_img : http://127.0.0.1/wind/Public/upload/2016-10-8/123.png
         * user_type : 101
         * phone : 119
         * user_role : 1
         * role_name : 普通用户
         */

        public String id;
        public String nickname;
        public String level;
        public String exp;
        public String head_img;
        public String user_type;
        public String phone;
        public String user_role;
        public String role_name;

        @Override
        public String toString() {
            return "UserShowDataBean{" +
                    "id='" + id + '\'' +
                    ", nickname='" + nickname + '\'' +
                    ", level='" + level + '\'' +
                    ", exp='" + exp + '\'' +
                    ", head_img='" + head_img + '\'' +
                    ", user_type='" + user_type + '\'' +
                    ", phone='" + phone + '\'' +
                    ", user_role='" + user_role + '\'' +
                    ", role_name='" + role_name + '\'' +
                    '}';
        }
    }

    public class PublisherInfoBean {
        /**
         * id : 3
         * nickname : 围观群众
         * level : 1
         * exp : 0
         * head_img : http://127.0.0.1/wind/Public/upload/2016-10-8/123.png
         * user_type : 101
         * phone : 119
         * user_role : 1
         * role_name : 普通用户
         */

        public String id;
        public String nickname;
        public String level;
        public String exp;
        public String head_img;
        public String user_type;
        public String phone;
        public String user_role;
        public String role_name;

        @Override
        public String toString() {
            return "PublisherInfoBean{" +
                    "id='" + id + '\'' +
                    ", nickname='" + nickname + '\'' +
                    ", level='" + level + '\'' +
                    ", exp='" + exp + '\'' +
                    ", head_img='" + head_img + '\'' +
                    ", user_type='" + user_type + '\'' +
                    ", phone='" + phone + '\'' +
                    ", user_role='" + user_role + '\'' +
                    ", role_name='" + role_name + '\'' +
                    '}';
        }
    }
}
