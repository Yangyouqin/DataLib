package com.yyq.datalib.javaBeans;

import java.util.List;

import cn.bmob.v3.BmobObject;
import cn.bmob.v3.datatype.BmobDate;
import cn.bmob.v3.datatype.BmobFile;

/**
 * Created by YQ on 2017/12/19.
 */

public class Trains extends BmobObject {
    //培训名称
    private String trainName;

    //培训类型  游泳，篮球，足球。。。。
    private String trainType;
    //联系电话
    private String phone;

    //收费信息
    private String payInf;

    //一大串的培训详情
    private String trainMess;

    //轮播图
    private List<BmobFile> trainImg1;

    //评分
    private double grade;

    //配置条件
    private List<String> configures;

    //最多容纳人数
    private int personNum;

    //申请的人数
    private int applyNum;

    private double longitude;

    private double latitude;

    private double price;


    //课程名称
    private String className;

    //俱乐部名称
    private String clubName;

    private MyUser user;

    private BmobDate startTime;

    private BmobDate endTime;

    //TODO：用于标志信息是否可显示，有0,1,2三个状态，0表示临时，刚发布未审核通过的，1表示审核通过可以显示的数据，2表示用户删除的历史数据。
    // TODO：老师需求是需要审核一下，历史状态的用于后台统计，状态为1的用于app显示
    private int state;

    private String available;

    public Trains() {
    }

    public Trains(String trainName, String trainType, String phone, String payInf, String trainMess, List<BmobFile> trainImg1, double grade, int personNum, int applyNum, double longitude, double latitude, double price, String className, String clubName, MyUser user, BmobDate startTime, BmobDate endTime) {
        this.trainName = trainName;
        this.trainType = trainType;
        this.phone = phone;
        this.payInf = payInf;
        this.trainMess = trainMess;
        this.trainImg1 = trainImg1;
        this.grade = grade;
        this.personNum = personNum;
        this.applyNum = applyNum;
        this.longitude = longitude;
        this.latitude = latitude;
        this.price = price;
        this.className = className;
        this.clubName = clubName;
        this.user = user;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public String getTrainName() {
        return trainName;
    }

    public void setTrainName(String trainName) {
        this.trainName = trainName;
    }

    public String getTrainType() {
        return trainType;
    }

    public void setTrainType(String trainType) {
        this.trainType = trainType;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPayInf() {
        return payInf;
    }

    public void setPayInf(String payInf) {
        this.payInf = payInf;
    }

    public String getTrainMess() {
        return trainMess;
    }

    public void setTrainMess(String trainMess) {
        this.trainMess = trainMess;
    }

    public List<BmobFile> getTrainImg1() {
        return trainImg1;
    }

    public void setTrainImg1(List<BmobFile> trainImg1) {
        this.trainImg1 = trainImg1;
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

    public int getPersonNum() {
        return personNum;
    }

    public void setPersonNum(int personNum) {
        this.personNum = personNum;
    }

    public int getApplyNum() {
        return applyNum;
    }

    public void setApplyNum(int applyNum) {
        this.applyNum = applyNum;
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

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getClubName() {
        return clubName;
    }

    public void setClubName(String clubName) {
        this.clubName = clubName;
    }

    public MyUser getUser() {
        return user;
    }

    public void setUser(MyUser user) {
        this.user = user;
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

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public String getAvailable() {
        return available;
    }

    public void setAvailable(String available) {
        this.available = available;
    }

    public Trains(String tableName, String trainName, String trainType, String phone, String payInf, String trainMess, double grade, List<String> configures, int personNum, int applyNum, double longitude, double latitude, double price, String className, String clubName, BmobDate startTime, BmobDate endTime) {
        super(tableName);
        this.trainName = trainName;
        this.trainType = trainType;
        this.phone = phone;
        this.payInf = payInf;
        this.trainMess = trainMess;
        this.grade = grade;
        this.configures = configures;
        this.personNum = personNum;
        this.applyNum = applyNum;
        this.longitude = longitude;
        this.latitude = latitude;
        this.price = price;
        this.className = className;
        this.clubName = clubName;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    @Override
    public String toString() {
        return "Trains{" +
                "trainName='" + trainName + '\'' +
                ", trainType='" + trainType + '\'' +
                ", phone='" + phone + '\'' +
                ", payInf='" + payInf + '\'' +
                ", trainMess='" + trainMess + '\'' +
                ", trainImg1=" + trainImg1 +
                ", grade=" + grade +
                ", configures=" + configures +
                ", personNum=" + personNum +
                ", applyNum=" + applyNum +
                ", longitude=" + longitude +
                ", latitude=" + latitude +
                ", price=" + price +
                ", className='" + className + '\'' +
                ", clubName='" + clubName + '\'' +
                ", user=" + user +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                '}';
    }
}
