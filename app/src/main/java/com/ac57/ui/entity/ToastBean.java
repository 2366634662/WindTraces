package com.ac57.ui.entity;


import com.ac57.ui.view.MyToast;

import java.io.Serializable;

public class ToastBean implements Serializable {
    private String title;
    private MyToast.Types type;
    private int flag;
    private Object obj;
    private String url;

    public int getFlag() {
        return flag;
    }

    public void setFlag(int flag) {
        this.flag = flag;
    }

    public Object getObj() {
        return obj;
    }

    public void setObj(Object obj) {
        this.obj = obj;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public MyToast.Types getType() {
        return type;
    }

    public void setType(MyToast.Types type) {
        this.type = type;
    }


    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public ToastBean(String title, MyToast.Types type, int flag, Object obj) {
        this.title = title;
        this.type = type;
        this.flag = flag;
        this.obj = obj;
    }

    public ToastBean(String title, MyToast.Types type, int flag) {
        this.title = title;
        this.type = type;
        this.flag = flag;
        this.obj = null;
        this.url = null;
    }

    public ToastBean(String title, MyToast.Types type) {
        this.title = title;
        this.type = type;
        this.flag = -1;
        this.obj = null;
        this.url = null;
    }
}
