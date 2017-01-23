package com.ac57.ui.view.edittext;

/**
 * Created by Du_Li on 2017/1/23.
 * Desc:
 */

public interface EditTextType {

    //手机校验类型
    int TYPE_OF_MOBILE = 0xb0;
    //座机校验类型
    int TYPE_OF_TEL = 0xb1;
    //邮箱校验类型
    int TYPE_OF_EMAIL = 0xb2;
    //url校验类型
    int TYPE_OF_URL = 0xb3;
    //汉字校验类型
    int TYPE_OF_CHZ = 0xb4;
    //用户名校验类型
    int TYPE_OF_USERNAME = 0xb5;
    //用户自定义
    int TYPE_OF_USER_DEFINE = 0xbb;
}
