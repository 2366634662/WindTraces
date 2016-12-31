package com.ac57.ui.entity;

/**
 * Created by Du_Li on 2016/12/31.
 */

public class OptionWatchBottomEntity {

    public String id;
    public String name;
    public String icon_show_str;
    public String icon_show_color;
    public String price;
    public String rises;
    public String volume;
    public String turn_volume;
    public String price_show_str;
    public String rises_show_str;
    public String volume_show_str;
    public String turn_volume_show_str;
    public String is_up;

    @Override
    public String toString() {
        return "OptionWatchBottomEntity{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", icon_show_str='" + icon_show_str + '\'' +
                ", icon_show_color='" + icon_show_color + '\'' +
                ", price='" + price + '\'' +
                ", rises='" + rises + '\'' +
                ", volume='" + volume + '\'' +
                ", turn_volume='" + turn_volume + '\'' +
                ", price_show_str='" + price_show_str + '\'' +
                ", rises_show_str='" + rises_show_str + '\'' +
                ", volume_show_str='" + volume_show_str + '\'' +
                ", turn_volume_show_str='" + turn_volume_show_str + '\'' +
                ", is_up='" + is_up + '\'' +
                '}';
    }
}
