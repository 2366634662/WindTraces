package com.ac57.ui.entity;

/**
 * Created by Du_Li on 2017/1/15.
 */

public class CoustomCollectionEntity {

    public String id;
    public String trade_code;
    public String trade_name;
    public String price;
    public String rises;
    public String exc_id;
    public ExcInfoBean exc_info;
    public String is_up;
    public String content_url;

    @Override
    public String toString() {
        return "CoustomCollectionEntity{" +
                "id='" + id + '\'' +
                ", trade_code='" + trade_code + '\'' +
                ", trade_name='" + trade_name + '\'' +
                ", price='" + price + '\'' +
                ", rises='" + rises + '\'' +
                ", exc_id='" + exc_id + '\'' +
                ", exc_info=" + exc_info +
                ", is_up='" + is_up + '\'' +
                ", content_url='" + content_url + '\'' +
                '}';
    }

    public class ExcInfoBean {

        public String id;
        public String name;
        public String icon_show_str;
        public String icon_show_color;

        @Override
        public String toString() {
            return "ExcInfoBean{" +
                    "id='" + id + '\'' +
                    ", name='" + name + '\'' +
                    ", icon_show_str='" + icon_show_str + '\'' +
                    ", icon_show_color='" + icon_show_color + '\'' +
                    '}';
        }
    }
}
