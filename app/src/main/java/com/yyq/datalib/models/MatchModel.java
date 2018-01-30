package com.yyq.datalib.models;

/**
 * Created by yangyouqin on 2018/1/5.
 */

public class MatchModel {

    //西华杯
    private String matchName;

    //女子800米决赛
    private String matchTitle;

    //篮球，足球。。。
    private String type;

    //大图的url
    private String pictureUrl;

    //报名费
    private double price;

    //item的id，用于跳转到详情页面的查询
    private String objectId;

    private double longitude;

    private double latitude;

    private String available;

    public String getMatchName() {
        return matchName;
    }

    public void setMatchName(String matchName) {
        this.matchName = matchName;
    }

    public String getMatchTitle() {
        return matchTitle;
    }

    public void setMatchTitle(String matchTitle) {
        this.matchTitle = matchTitle;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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
        return "MatchModel{" +
                "matchName='" + matchName + '\'' +
                ", matchTitle='" + matchTitle + '\'' +
                ", type='" + type + '\'' +
                ", pictureUrl='" + pictureUrl + '\'' +
                ", price=" + price +
                ", objectId='" + objectId + '\'' +
                ", longitude=" + longitude +
                ", latitude=" + latitude +
                ", available='" + available + '\'' +
                '}';
    }
}
