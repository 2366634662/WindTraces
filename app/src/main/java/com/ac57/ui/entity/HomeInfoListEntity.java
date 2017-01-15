package com.ac57.ui.entity;

import java.util.List;

/**
 * Created by Du_Li on 2016/12/25.
 */

public class HomeInfoListEntity {

    public String id;
    public String user_id;
    public String art_type;
    public String exc_id;
    public String title_show_str;
    public String title;
    public String is_big_img;
    public String content_type;
    public String c_time;
    public String read_times;
    public String status;
    public String notice_type_id;
    public String type_name;
    public String exc_name;
    public String img_ids;
    public String content;
    public String c_time_show_str;
    public String cover_img;
    public String is_read;
    public AdvertInfoBean advert_info;
    public List<?> img_url_list;

    @Override
    public String toString() {
        return "HomeInfoListEntity{" +
                "id='" + id + '\'' +
                ", user_id='" + user_id + '\'' +
                ", art_type='" + art_type + '\'' +
                ", exc_id='" + exc_id + '\'' +
                ", title_show_str='" + title_show_str + '\'' +
                ", title='" + title + '\'' +
                ", is_big_img='" + is_big_img + '\'' +
                ", content_type='" + content_type + '\'' +
                ", c_time='" + c_time + '\'' +
                ", read_times='" + read_times + '\'' +
                ", status='" + status + '\'' +
                ", notice_type_id='" + notice_type_id + '\'' +
                ", type_name='" + type_name + '\'' +
                ", exc_name='" + exc_name + '\'' +
                ", img_ids='" + img_ids + '\'' +
                ", content='" + content + '\'' +
                ", c_time_show_str='" + c_time_show_str + '\'' +
                ", cover_img='" + cover_img + '\'' +
                ", is_read='" + is_read + '\'' +
                ", advert_info=" + advert_info +
                ", img_url_list=" + img_url_list +
                '}';
    }

    public static class AdvertInfoBean {

        public String id;
        public String art_id;
        public String advert_type;
        public String advert_name;
        public String advert_url;
        public String advert_img_url;

        @Override
        public String toString() {
            return "AdvertInfoBean{" +
                    "id='" + id + '\'' +
                    ", art_id='" + art_id + '\'' +
                    ", advert_type='" + advert_type + '\'' +
                    ", advert_name='" + advert_name + '\'' +
                    ", advert_url='" + advert_url + '\'' +
                    ", advert_img_url='" + advert_img_url + '\'' +
                    '}';
        }
    }
}
