package com.ac57.framework.base;

import com.ac57.ui.view.MyToast;

/**
 * Created by Du_Li on 2016/12/17.
 */

public interface BaseViewController {
    //这里面添加实现类需要实现的方法即可

    /**
     * 显示信息提示对话框
     *
     * @param msg message
     */
    void showDailog(String msg, MyToast.Types types);

//    /**
//     * 关闭对话框
//     */
//    void disDailog();
}
