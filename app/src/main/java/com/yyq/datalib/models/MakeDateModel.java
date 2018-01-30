package com.yyq.datalib.models;

/**
 * Created by yangyouqin on 2018/1/19.
 */

public class MakeDateModel {

    //发布的用户头像
    private String headImg;

    //约球标题
    private String title;

    //约球的正文
    private String content;

    //约球的那张大图
    private String makeDateImg;

    //经度
    private double longitude;

    //纬度
    private double latitude;

    //申请的人数
    private int applyNum;

    //喜欢的数
    private int like;

    //objectId
    private String objectId;

    public String getHeadImg() {
        return headImg;
    }

    public void setHeadImg(String headImg) {
        this.headImg = headImg;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getMakeDateImg() {
        return makeDateImg;
    }

    public void setMakeDateImg(String makeDateImg) {
        this.makeDateImg = makeDateImg;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public int getApplyNum() {
        return applyNum;
    }

    public void setApplyNum(int applyNum) {
        this.applyNum = applyNum;
    }

    public int getLike() {
        return like;
    }

    public void setLike(int like) {
        this.like = like;
    }

    public String getObjectId() {
        return objectId;
    }

    public void setObjectId(String objectId) {
        this.objectId = objectId;
    }
}
