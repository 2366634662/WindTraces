package com.ac57.ui.entity;

import java.util.List;

/**
 * Created by Du_Li on 2016/12/17.
 */

public class UserInfoData {
    public UserModelBean user_model;
    public List<ExchangeDataBean> exchange_data;
    public List<String> attendance_list;

    @Override
    public String toString() {
        return "UserInfoData{" +
                "user_model=" + user_model +
                ", exchange_data=" + exchange_data +
                ", attendance_list=" + attendance_list +
                '}';
    }

    public class UserModelBean {
        public String id;
        public String nickname;
        public String birth_day;
        public String level;
        public String exp;
        public String head_img;
        public String user_type;
        public String user_role;
        public String phone;
        public String total_exp;
        public String profession_id;
        public String profession_name;
        public String auth_id;
        public String auth_status;
        public String only_sign;
        public String role_name;
        public String register_time;
        public String register_data;
        public String exp_percent_show_str;
        public String exp_lack_show_str;
        public String birth_day_show_str;
        public String fans_num;
        public String sign_num;
        public String exp_desc_url;
        public String is_first_login;
        public String is_sign_today;

        @Override
        public String toString() {
            return "UserModelBean{" +
                    "id='" + id + '\'' +
                    ", nickname='" + nickname + '\'' +
                    ", birth_day='" + birth_day + '\'' +
                    ", level='" + level + '\'' +
                    ", exp='" + exp + '\'' +
                    ", head_img='" + head_img + '\'' +
                    ", user_type='" + user_type + '\'' +
                    ", user_role='" + user_role + '\'' +
                    ", phone='" + phone + '\'' +
                    ", total_exp='" + total_exp + '\'' +
                    ", profession_id='" + profession_id + '\'' +
                    ", profession_name='" + profession_name + '\'' +
                    ", auth_id='" + auth_id + '\'' +
                    ", auth_status='" + auth_status + '\'' +
                    ", only_sign='" + only_sign + '\'' +
                    ", role_name='" + role_name + '\'' +
                    ", register_time='" + register_time + '\'' +
                    ", register_data='" + register_data + '\'' +
                    ", exp_percent_show_str='" + exp_percent_show_str + '\'' +
                    ", exp_lack_show_str='" + exp_lack_show_str + '\'' +
                    ", birth_day_show_str='" + birth_day_show_str + '\'' +
                    ", fans_num='" + fans_num + '\'' +
                    ", sign_num='" + sign_num + '\'' +
                    ", exp_desc_url='" + exp_desc_url + '\'' +
                    ", is_first_login='" + is_first_login + '\'' +
                    ", is_sign_today='" + is_sign_today + '\'' +
                    '}';
        }
    }

    public class ExchangeDataBean {

        public String id;
        public String name;
        public String icon_show_str;
        public String icon_show_color;
        public String is_follow;

        //第三方登陆用的
        public String icon_img_id;
        public String icon_img;

        @Override
        public String toString() {
            return "ExchangeDataBean{" +
                    "id='" + id + '\'' +
                    ", name='" + name + '\'' +
                    ", icon_show_str='" + icon_show_str + '\'' +
                    ", icon_show_color='" + icon_show_color + '\'' +
                    ", is_follow='" + is_follow + '\'' +
                    '}';
        }
    }
}
