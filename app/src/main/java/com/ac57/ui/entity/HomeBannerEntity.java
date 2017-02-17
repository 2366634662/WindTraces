package com.ac57.ui.entity;

/**
 * Created by Du_Li on 2016/12/25.
 */

public class HomeBannerEntity {
    public String id;
    public String art_id;
    public String title;
    public String art_type;
    public String exc_name;
    public String cover_img;
    public AdvertInfoBean advert_info;

    public class AdvertInfoBean {

        public String id;
        public String art_id;
        public String advert_type;
        public String advert_name;
        public String advert_url;
        public String advert_img_url;


    }
}
