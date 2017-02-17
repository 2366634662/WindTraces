package com.ac57.framework.base;

/**
 */

public interface BaseViewController {
    //这里面添加实现类需要实现的方法即可
    /**
     * 显示信息提示对话框
     *
     * @param msg message
     */
    void showDailog(String msg);

    /**
     * 关闭对话框
     */
    void disDailog();

    /**
     * 显示错误页面
     */
    void showError(String msg);

}
