package com.yyq.datalib.models;

/**
 * Created by yangyouqin on 2018/1/4.
 */

public class SortModel {

    //你要显示的那张图片的地址，注意直接是地址了，不需要转了
    private String pictureUrl;

    //评分
    private double grade;

    //需要显示的名称  比如西华大学篮球场
    private String name;

    //经度
    private double longitude;

    //纬度
    private double latitude;

    //是否已预订,是否可报名
    private String available;

    //价格
    private double price;

    //这不是篮球那些，是场地，比赛，培训，方便点击进去的时候传参数
    private String type;

    //数据的id，方便点击进去的时候用id查询
    private String objectId;

    public String getPictureUrl() {
        return pictureUrl;
    }

    public void setPictureUrl(String pictureUrl) {
        this.pictureUrl = pictureUrl;
    }

    public double getGrade() {
        return grade;
    }

    public void setGrade(double grade) {
        this.grade = grade;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String isAvailable() {
        return available;
    }

    public void setAvailable(String available) {
        this.available = available;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getObjectId() {
        return objectId;
    }

    public void setObjectId(String objectId) {
        this.objectId = objectId;
    }

    @Override
    public String toString() {
        return "SortModel{" +
                "pictureUrl='" + pictureUrl + '\'' +
                ", grade=" + grade +
                ", name='" + name + '\'' +
                ", longitude=" + longitude +
                ", latitude=" + latitude +
                ", available=" + available +
                ", price=" + price +
                ", type='" + type + '\'' +
                ", objectId='" + objectId + '\'' +
                '}';
    }
}
