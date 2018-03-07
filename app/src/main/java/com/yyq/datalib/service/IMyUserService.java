package com.yyq.datalib.service;

import android.content.Context;

import com.yyq.datalib.javaBeans.MyUser;

/**
 * Created by yangyouqin on 2017/11/28.
 */

public interface IMyUserService {

    //注册
    void insert(MyUser bu, Context context);

    /**
     * type 0为手机密码登陆，1  为手机验证码登陆
     *
     * @param type
     */
    void loginByPhone(String phoneNum, String password, Integer type, Context context);

    //修改用户头像
    void updateHeadImg(String picUrl, Context context);

    //修改用户其他信息
    void update(MyUser myUser, Context context);

    //根据id获取
    void getById(String id);

    void phoneIsExistence(Context context, String phoneNum);
}
