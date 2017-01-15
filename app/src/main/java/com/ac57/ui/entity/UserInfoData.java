package com.ac57.ui.entity;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Du_Li on 2016/12/17.
 */

public class UserInfoData implements Parcelable {
    public UserModelBean user_model;//用户信息
    public List<ExchangeDataBean> exchange_data;//关注的文交所
    public List<String> attendance_list;//签到时间集合

    @Override
    public String toString() {
        return "UserInfoData{" +
                "user_model=" + user_model +
                ", exchange_data=" + exchange_data +
                ", attendance_list=" + attendance_list +
                '}';
    }

    public class UserModelBean implements Parcelable {
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
                    ", CREATOR=" + CREATOR +
                    '}';
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(this.id);
            dest.writeString(this.nickname);
            dest.writeString(this.birth_day);
            dest.writeString(this.level);
            dest.writeString(this.exp);
            dest.writeString(this.head_img);
            dest.writeString(this.user_type);
            dest.writeString(this.user_role);
            dest.writeString(this.phone);
            dest.writeString(this.total_exp);
            dest.writeString(this.profession_id);
            dest.writeString(this.profession_name);
            dest.writeString(this.auth_id);
            dest.writeString(this.auth_status);
            dest.writeString(this.only_sign);
            dest.writeString(this.role_name);
            dest.writeString(this.register_time);
            dest.writeString(this.register_data);
            dest.writeString(this.exp_percent_show_str);
            dest.writeString(this.exp_lack_show_str);
            dest.writeString(this.birth_day_show_str);
            dest.writeString(this.fans_num);
            dest.writeString(this.sign_num);
            dest.writeString(this.exp_desc_url);
            dest.writeString(this.is_first_login);
            dest.writeString(this.is_sign_today);
        }

        public UserModelBean() {
        }

        protected UserModelBean(Parcel in) {
            this.id = in.readString();
            this.nickname = in.readString();
            this.birth_day = in.readString();
            this.level = in.readString();
            this.exp = in.readString();
            this.head_img = in.readString();
            this.user_type = in.readString();
            this.user_role = in.readString();
            this.phone = in.readString();
            this.total_exp = in.readString();
            this.profession_id = in.readString();
            this.profession_name = in.readString();
            this.auth_id = in.readString();
            this.auth_status = in.readString();
            this.only_sign = in.readString();
            this.role_name = in.readString();
            this.register_time = in.readString();
            this.register_data = in.readString();
            this.exp_percent_show_str = in.readString();
            this.exp_lack_show_str = in.readString();
            this.birth_day_show_str = in.readString();
            this.fans_num = in.readString();
            this.sign_num = in.readString();
            this.exp_desc_url = in.readString();
            this.is_first_login = in.readString();
            this.is_sign_today = in.readString();
        }

        public final Creator<UserModelBean> CREATOR = new Creator<UserModelBean>() {
            @Override
            public UserModelBean createFromParcel(Parcel source) {
                return new UserModelBean(source);
            }

            @Override
            public UserModelBean[] newArray(int size) {
                return new UserModelBean[size];
            }
        };
    }

    public class ExchangeDataBean implements Parcelable {

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
                    ", icon_img_id='" + icon_img_id + '\'' +
                    ", icon_img='" + icon_img + '\'' +
                    ", CREATOR=" + CREATOR +
                    '}';
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(this.id);
            dest.writeString(this.name);
            dest.writeString(this.icon_show_str);
            dest.writeString(this.icon_show_color);
            dest.writeString(this.is_follow);
            dest.writeString(this.icon_img_id);
            dest.writeString(this.icon_img);
        }

        public ExchangeDataBean() {
        }

        protected ExchangeDataBean(Parcel in) {
            this.id = in.readString();
            this.name = in.readString();
            this.icon_show_str = in.readString();
            this.icon_show_color = in.readString();
            this.is_follow = in.readString();
            this.icon_img_id = in.readString();
            this.icon_img = in.readString();
        }

        public final Creator<ExchangeDataBean> CREATOR = new Creator<ExchangeDataBean>() {
            @Override
            public ExchangeDataBean createFromParcel(Parcel source) {
                return new ExchangeDataBean(source);
            }

            @Override
            public ExchangeDataBean[] newArray(int size) {
                return new ExchangeDataBean[size];
            }
        };
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(this.user_model, flags);
        dest.writeList(this.exchange_data);
        dest.writeStringList(this.attendance_list);
    }

    public UserInfoData() {
    }

    protected UserInfoData(Parcel in) {
        this.user_model = in.readParcelable(UserModelBean.class.getClassLoader());
        this.exchange_data = new ArrayList<ExchangeDataBean>();
        in.readList(this.exchange_data, ExchangeDataBean.class.getClassLoader());
        this.attendance_list = in.createStringArrayList();
    }

    public static final Creator<UserInfoData> CREATOR = new Creator<UserInfoData>() {
        @Override
        public UserInfoData createFromParcel(Parcel source) {
            return new UserInfoData(source);
        }

        @Override
        public UserInfoData[] newArray(int size) {
            return new UserInfoData[size];
        }
    };
}
