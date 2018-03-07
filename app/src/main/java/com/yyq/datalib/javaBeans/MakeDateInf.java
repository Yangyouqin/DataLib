package com.yyq.datalib.javaBeans;

import cn.bmob.v3.BmobObject;

/**
 * Created by yangyouqin on 2018/2/1.
 * 约球的申请实体类
 */

public class MakeDateInf extends BmobObject {

    //申请约球的一个动态id
    private String objectId;

    //是否喜欢
    private boolean like;

    //是否报名
    private boolean apply;

    //约球id
    private String makeDateId;

    //申请或点赞的用户
    private MyUser user;

    public String getObjectId() {
        return objectId;
    }

    public void setObjectId(String objectId) {
        this.objectId = objectId;
    }

    public boolean isLike() {
        return like;
    }

    public void setLike(boolean like) {
        this.like = like;
    }

    public boolean isApply() {
        return apply;
    }

    public void setApply(boolean apply) {
        this.apply = apply;
    }

    public String getMakeDateId() {
        return makeDateId;
    }

    public void setMakeDateId(String makeDateId) {
        this.makeDateId = makeDateId;
    }

    public MyUser getUser() {
        return user;
    }

    public void setUser(MyUser user) {
        this.user = user;
    }
}
