package com.ac57.ui.entity;

import java.util.List;

/**
 * Created by Du_Li on 2016/12/25.
 */

public class HomeInfoListEntity {
    /**
     * id : 22
     * user_id : 0
     * art_type : 103
     * exc_id : 0
     * title_show_str : 测试22132113212
     * title : 测试22132113212
     * is_big_img : 102
     * content_type : 101
     * c_time : 0
     * read_times : 0
     * status : 101
     * notice_type_id : 0
     * type_name : 文交所动态
     * exc_name :
     * img_ids :
     * content : 测试测试
     * c_time_show_str : 01-01 08:00
     * img_url_list : []
     * cover_img : http://upload.news.cecb2b.com/2016/0412/1460442200606.jpg
     * is_read : 101
     * advert_info : {"id":"1","art_id":"20","advert_type":"101","advert_name":"测试,外部浏览器打开的广告1","advert_url":"http://wap.news.cecb2b.com/","advert_img_url":"http://upload.news.cecb2b.com/2016/0412/1460442200606.jpg"}
     */

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

    public static class AdvertInfoBean {
        /**
         * id : 1
         * art_id : 20
         * advert_type : 101
         * advert_name : 测试,外部浏览器打开的广告1
         * advert_url : http://wap.news.cecb2b.com/
         * advert_img_url : http://upload.news.cecb2b.com/2016/0412/1460442200606.jpg
         */

        public String id;
        public String art_id;
        public String advert_type;
        public String advert_name;
        public String advert_url;
        public String advert_img_url;
    }
}
