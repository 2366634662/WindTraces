package com.ac57.ui.entity;

import java.util.List;

/**
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

    public  class AdvertInfoBean {

        public String id;
        public String art_id;
        public String advert_type;
        public String advert_name;
        public String advert_url;
        public String advert_img_url;

    }
}
