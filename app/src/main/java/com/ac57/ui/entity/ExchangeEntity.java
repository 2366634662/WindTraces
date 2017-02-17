package com.ac57.ui.entity;

/**
 * Created on 2017/2/5.
 * Desc :
 */

public class ExchangeEntity {
    public String id;
    public String name;
    public String icon_show_str;
    public String icon_show_color;
    public String is_follow;

    @Override
    public String toString() {
        return "ExchangeEntity{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", icon_show_str='" + icon_show_str + '\'' +
                ", icon_show_color='" + icon_show_color + '\'' +
                ", is_follow='" + is_follow + '\'' +
                '}';
    }
}
