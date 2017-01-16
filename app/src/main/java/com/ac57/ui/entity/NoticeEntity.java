package com.ac57.ui.entity;

import java.util.List;

/**
 * Created by Du_Li on 2017/1/2.
 */

public class NoticeEntity {

    public String id;
    public String art_id;
    public String time_show_str;
    public List<ArtListBean> art_list;

    @Override
    public String toString() {
        return "NoticeEntity{" +
                "id='" + id + '\'' +
                ", art_id='" + art_id + '\'' +
                ", time_show_str='" + time_show_str + '\'' +
                ", art_list=" + art_list +
                '}';
    }

    public class ArtListBean {

        public String id;
        public String title;
        public String art_type;
        public String content_type;
        public ArtExcInfoBean art_exc_info;
        public String art_cate_name;
        public String art_cate_color;

        public class ArtExcInfoBean {

            public String id;
            public String name;
            public String icon_show_str;
            public String icon_show_color;
        }
    }
}
