package com.ac57.framework.base;

/**
 * Created by Du_Li on 2016/10/12.
 */

public class BaseEntity<T> {
    public String code;
    public String desc;
    public T data;

    @Override
    public String toString() {
        return "BaseEntity{" +
                "code=" + code +
                ", datas=" + desc +
                ", data=" + data +
                '}';
    }
}
