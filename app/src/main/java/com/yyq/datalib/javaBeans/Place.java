package com.yyq.datalib.javaBeans;

import java.util.List;

import cn.bmob.v3.BmobObject;
import cn.bmob.v3.datatype.BmobDate;
import cn.bmob.v3.datatype.BmobFile;

/**
 * Created by YQ on 2017/12/14.
 *场地相关信息，对应表Place
 */

public class Place extends BmobObject {
    //场地名称
    private String placeName;

    //西华大学本部
    private String name;

    //联系电话
    private String phone;

    //场地价格
    private double price;

    //场地数量
    private int placeNum;

    //价格单位
    private String priceDW;

    //场地材质
    private String placeCz;

    //场地类型
    private String placeType;

    //场地图片，放在顶部的5 张
    private List<BmobFile> placeImg1;

    //评分 首页热门项目点进去的列表需要显示
    private double grade;

    //场地配置 淋浴，停车场等
    private List<String> configures;

    //是否室内
    private boolean sfsn;

    //是否可预订
    private boolean available;

    //经度
    private double longitude;

    //纬度
    private double latitude;

    //发布的用户
    private MyUser user;

    //场地使用的开始时间
    private BmobDate startTime;

    //场地使用的结束时间
    private BmobDate endTime;

    //TODO：用于标志信息是否可显示，有0,1,2三个状态，0表示临时，刚发布未审核通过的，1表示审核通过可以显示的数据，2表示用户删除的历史数据。
    // TODO：老师需求是需要审核一下，历史状态的用于后台统计，状态为1的用于app显示
    private int state;

    public Place() {
    }

    //这些就是添加一条信息必填的
    public Place(String name, String placeName, String phone, double price, int placeNum, String priceDW, String placeCz, String placeType, double grade, List<String> configures, boolean sfsn, double longitude, double latitude, BmobDate startTime, BmobDate endTime) {
        this.name = name;
        this.placeName = placeName;
        this.phone = phone;
        this.price = price;
        this.placeNum = placeNum;
        this.priceDW = priceDW;
        this.placeCz = placeCz;
        this.placeType = placeType;
        this.grade = grade;
        this.configures = configures;
        this.sfsn = sfsn;
        this.longitude = longitude;
        this.latitude = latitude;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPlaceName() {
        return placeName;
    }

    public void setPlaceName(String placeName) {
        this.placeName = placeName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getPlaceNum() {
        return placeNum;
    }

    public void setPlaceNum(int placeNum) {
        this.placeNum = placeNum;
    }

    public String getPriceDW() {
        return priceDW;
    }

    public void setPriceDW(String priceDW) {
        this.priceDW = priceDW;
    }

    public String getPlaceCz() {
        return placeCz;
    }

    public void setPlaceCz(String placeCz) {
        this.placeCz = placeCz;
    }

    public String getPlaceType() {
        return placeType;
    }

    public void setPlaceType(String placeType) {
        this.placeType = placeType;
    }

    public List<BmobFile> getPlaceImg1() {
        return placeImg1;
    }

    public void setPlaceImg1(List<BmobFile> placeImg1) {
        this.placeImg1 = placeImg1;
    }

    public double getGrade() {
        return grade;
    }

    public void setGrade(double grade) {
        this.grade = grade;
    }

    public List<String> getConfigures() {
        return configures;
    }

    public void setConfigures(List<String> configures) {
        this.configures = configures;
    }

    public boolean isSfsn() {
        return sfsn;
    }

    public void setSfsn(boolean sfsn) {
        this.sfsn = sfsn;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
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

    public MyUser getUser() {
        return user;
    }

    public void setUser(MyUser user) {
        this.user = user;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    @Override
    public String toString() {
        return "Place{" +
                "placeName='" + placeName + '\'' +
                ", name='" + name + '\'' +
                ", phone='" + phone + '\'' +
                ", price=" + price +
                ", placeNum=" + placeNum +
                ", priceDW='" + priceDW + '\'' +
                ", placeCz='" + placeCz + '\'' +
                ", placeType='" + placeType + '\'' +
                ", placeImg1=" + placeImg1 +
                ", grade=" + grade +
                ", configures=" + configures +
                ", sfsn=" + sfsn +
                ", available=" + available +
                ", longitude=" + longitude +
                ", latitude=" + latitude +
                ", user=" + user +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                ", state=" + state +
                '}';
    }
}
