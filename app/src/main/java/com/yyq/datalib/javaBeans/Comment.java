package com.yyq.datalib.javaBeans;

import cn.bmob.v3.BmobObject;

/**
 * Created by YQ on 2017/12/19.
 */

public class Comment extends BmobObject {

    private MyUser user;

    private String content;

    private Double stars;

    private boolean like;

    //场地/培训/比赛/圈子的id
    private String typeId;

    // 0  表示场地，1 表示培训， 2 表示比赛， 3 表示圈子
    private int type;

    private String orderId;

    public MyUser getUser() {
        return user;
    }

    public void setUser(MyUser user) {
        this.user = user;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Double getStars() {
        return stars;
    }

    public void setStars(Double stars) {
        this.stars = stars;
    }

    public boolean isLike() {
        return like;
    }

    public void setLike(boolean like) {
        this.like = like;
    }

    public Comment() {
    }

    public String getTypeId() {
        return typeId;
    }

    public void setTypeId(String typeId) {
        this.typeId = typeId;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }
}
