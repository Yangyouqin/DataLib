package com.yyq.datalib.javaBeans;

import cn.bmob.v3.BmobObject;
import cn.bmob.v3.datatype.BmobDate;
import cn.bmob.v3.datatype.BmobFile;

/**
 * Created by YQ on 2017/12/20.
 * 约球
 */

public class MakeDate extends BmobObject {
    //发布的用户
    private MyUser user;

    //约球标题
    private String title;

    //约球的正文
    private String content;

    //约球的那张大图
    private BmobFile makeDateImg;

    //经度
    private double longitude;

    //纬度
    private double latitude;

    //地点名称（详情里面的地点）
    private String addressName;

    //运动项目
    private String object;

    //约球人数容量
    private int personNum;

    //开始时间
    private String phone;

    //结束时间
    private BmobDate endTime;

    //电话
    private BmobDate startTime;

    //申请的人数
    private int applyNum;

    //喜欢的数
    private int like;

    public MakeDate() {
    }

    public MakeDate(String title, String content, double longitude, double latitude, String addressName, String object, int personNum, BmobDate startTime, BmobDate endTime, String phone) {
        this.title = title;
        this.content = content;
        this.longitude = longitude;
        this.latitude = latitude;
        this.addressName = addressName;
        this.object = object;
        this.personNum = personNum;
        this.phone = phone;
        this.endTime = endTime;
        this.startTime = startTime;
    }

    public MyUser getUser() {
        return user;
    }

    public void setUser(MyUser user) {
        this.user = user;
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

    public BmobFile getMakeDateImg() {
        return makeDateImg;
    }

    public void setMakeDateImg(BmobFile makeDateImg) {
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

    public String getAddressName() {
        return addressName;
    }

    public void setAddressName(String addressName) {
        this.addressName = addressName;
    }

    public String getObject() {
        return object;
    }

    public void setObject(String object) {
        this.object = object;
    }

    public int getPersonNum() {
        return personNum;
    }

    public void setPersonNum(int personNum) {
        this.personNum = personNum;
    }

    public BmobDate getStartTime() {
        return startTime;
    }

    public void setStartTime(BmobDate startTime) {
        this.startTime = startTime;
    }

    public BmobDate getEndTime() {
        return endTime;
    }

    public void setEndTime(BmobDate endTime) {
        this.endTime = endTime;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
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
}
