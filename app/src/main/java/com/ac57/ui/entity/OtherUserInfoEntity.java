package com.ac57.ui.entity;

/**
 * Created on 2017/2/16.
 * Desc :
 */

public class OtherUserInfoEntity {
    public String is_follow;
    public UserDataEntity user_data;

    public class UserDataEntity {
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
        public String exp_percent_show_str;
        public String exp_lack_show_str;
        public String birth_day_show_str;
        public String fans_num;
        public String sign_num;
        public String exp_desc_url;
        public FollowInfoEntity follow_info;

        public class FollowInfoEntity {

            public String follow_num;
            public String is_follow;
        }
    }
}
