package com.ac57.ui.entity;

import java.util.List;

/**
 * Desc:
 */

public class SelectExchangeEntity {

    public List<HotExcListBean> hot_exc_list;
    public List<ExcListBean> exc_list;

    public class HotExcListBean {
        public String id;
        public String name;
        public String icon_show_str;
        public String icon_show_color;
    }

    public class ExcListBean {
        public String exc_section_name;
        public List<ExcSectionListBean> exc_section_list;

        public class ExcSectionListBean {

            public String id;
            public String name;
            public String icon_show_str;
            public String icon_show_color;
            public String pinyin;

        }
    }
}
