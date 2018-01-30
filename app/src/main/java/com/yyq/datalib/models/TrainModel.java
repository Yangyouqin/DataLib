package com.yyq.datalib.models;

/**
 * Created by yangyouqin on 2018/1/5.
 */

public class TrainModel {

    //爱运动游泳培训1
    private String trainName;

    //英郡俱乐部
    private String clubName;

    //游泳培训一对一课程
    private String className;

    //大图的utl
    private String pictureUrl;

    //报名费
    private double price;

    //用户头像url
    private String headImgUrl;

    //id用于查询详情
    private String objectId;

    //是否可报名
    private String available;

    private double longitude;

    private double latitude;

    public String getTrainName() {
        return trainName;
    }

    public void setTrainName(String trainName) {
        this.trainName = trainName;
    }

    public String getClubName() {
        return clubName;
    }

    public void setClubName(String clubName) {
        this.clubName = clubName;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getPictureUrl() {
        return pictureUrl;
    }

    public void setPictureUrl(String pictureUrl) {
        this.pictureUrl = pictureUrl;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getHeadImgUrl() {
        return headImgUrl;
    }

    public void setHeadImgUrl(String headImgUrl) {
        this.headImgUrl = headImgUrl;
    }

    public String getObjectId() {
        return objectId;
    }

    public void setObjectId(String objectId) {
        this.objectId = objectId;
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

    public String getAvailable() {
        return available;
    }

    public void setAvailable(String available) {
        this.available = available;
    }

    @Override
    public String toString() {
        return "TrainModel{" +
                "trainName='" + trainName + '\'' +
                ", clubName='" + clubName + '\'' +
                ", className='" + className + '\'' +
                ", pictureUrl='" + pictureUrl + '\'' +
                ", price=" + price +
                ", headImgUrl='" + headImgUrl + '\'' +
                ", objectId='" + objectId + '\'' +
                ", available='" + available + '\'' +
                ", longitude=" + longitude +
                ", latitude=" + latitude +
                '}';
    }
}
