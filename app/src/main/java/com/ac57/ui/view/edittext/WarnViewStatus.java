package com.ac57.ui.view.edittext;

/**
 * Created by Du_Li on 2017/1/23.
 * Desc:
 */

/**
 * Created by caihan on 2016/9/16.
 * 警告语显示监听
 */
public interface WarnViewStatus {
    /**
     * 展示警告语
     *
     * @param msgs
     */
    void show(String... msgs);

    /**
     * 隐藏警告语
     */
    void hide();
}