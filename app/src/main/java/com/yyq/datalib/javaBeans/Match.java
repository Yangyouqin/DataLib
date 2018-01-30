package com.yyq.datalib.javaBeans;

import java.util.List;

import cn.bmob.v3.BmobObject;
import cn.bmob.v3.datatype.BmobDate;
import cn.bmob.v3.datatype.BmobFile;

/**
 * Created by YQ on 2017/12/19.
 */

public class Match extends BmobObject {
    //比赛名称  西华杯
    private String matchName;

    //西华大学本部
    private String name;

    //西华大学2017女子足球赛1
    private String matchTitle;

    //类型，足球1
    private String matchType;

    private String phone;

    private double price;

    //详细介绍  一大串
    private String matchMess;

    private BmobFile matchImg;

    private double grade;

    private List<String> configures;

    private int personNum;

    private int applyNum;

    private double longitude;

    private double latitude;

    private BmobDate startTime;

    private BmobDate endTIme;

    private MyUser user;

    //TODO：用于标志信息是否可显示，有0,1,2三个状态，0表示临时，刚发布未审核通过的，1表示审核通过可以显示的数据，2表示用户删除的历史数据。
    // TODO：老师需求是需要审核一下，历史状态的用于后台统计，状态为1的用于app显示
    private int state;

    private String available;

    public Match() {
    }

    public Match(String name, String matchName, String matchTitle, String matchType, String phone, double price, String matchMess, double grade, List<String> configures, int personNum, int applyNum, double longitude, double latitude, BmobDate startTime, BmobDate endTIme, MyUser user) {
        this.name = name;
        this.matchName = matchName;
        this.matchTitle = matchTitle;
        this.matchType = matchType;
        this.phone = phone;
        this.price = price;
        this.matchMess = matchMess;
        this.grade = grade;
        this.configures = configures;
        this.personNum = personNum;
        this.applyNum = applyNum;
        this.longitude = longitude;
        this.latitude = latitude;
        this.startTime = startTime;
        this.endTIme = endTIme;
        this.user = user;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMatchName() {
        return matchName;
    }

    public void setMatchName(String matchName) {
        this.matchName = matchName;
    }

    public String getMatchType() {
        return matchType;
    }

    public void setMatchType(String matchType) {
        this.matchType = matchType;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getMatchMess() {
        return matchMess;
    }

    public void setMatchMess(String matchMess) {
        this.matchMess = matchMess;
    }

    public BmobFile getMatchImg() {
        return matchImg;
    }

    public void setMatchImg(BmobFile matchImg) {
        this.matchImg = matchImg;
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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getMatchTitle() {
        return matchTitle;
    }

    public void setMatchTitle(String matchTitle) {
        this.matchTitle = matchTitle;
    }

    public BmobDate getStartTime() {
        return startTime;
    }

    public void setStartTime(BmobDate startTime) {
        this.startTime = startTime;
    }

    public BmobDate getEndTime() {
        return endTIme;
    }

    public void setEndTime(BmobDate endTime) {
        this.endTIme = endTime;
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

    public String getAvailable() {
        return available;
    }

    public void setAvailable(String available) {
        this.available = available;
    }


}
