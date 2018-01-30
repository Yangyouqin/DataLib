package com.yyq.datalib.javaBeans;

import cn.bmob.v3.BmobUser;
import cn.bmob.v3.datatype.BmobFile;

/**
 * Created by YQ on 2017/11/27.
 * 用户信息
 */

public class MyUser extends BmobUser {
    //用户头像
    private BmobFile headImg;

    //用户类型 0表示普通用户,1表示商户
    private int userType;

    public MyUser() {
    }

    //用户名和密码必填
    public MyUser(BmobFile headImg, String userName, String password, String mobilePhoneNumber, int userType) {
        this.headImg = headImg;
        this.setPassword(password);
        this.setMobilePhoneNumber(mobilePhoneNumber);
        this.setUsername(userName);
        this.userType = userType;
    }

    public BmobFile getHeadImg() {
        return headImg;
    }

    public void setHeadImg(BmobFile headImg) {
        this.headImg = headImg;
    }

    @Override
    public String toString() {
        return "MyUser{" +
                "headImg=" + headImg +
                '}';
    }

    public int getUserType() {
        return userType;
    }

    public void setUserType(int userType) {
        this.userType = userType;
    }
}
