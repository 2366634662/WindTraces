package com.ac57.ui.entity;

import java.util.List;

/**
 * Created by Du_Li on 2016/12/31.
 */

public class OptionWatchTopEntity {
    public int follow_num;
    public List<DataListBean> data_list;

    public class DataListBean {
        public DataListBean(String name) {
            this.name = name;
        }

        public String id;
        public String name;
        public String icon_show_str;
        public String icon_show_color;
        public String price;
        public String rises;
        public String price_show_str;
        public String rises_show_str;
        public String is_up;

    }
}
