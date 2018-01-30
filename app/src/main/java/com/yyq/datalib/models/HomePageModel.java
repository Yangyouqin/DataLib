package com.yyq.datalib.models;

import com.yyq.datalib.javaBeans.HomePage;

import java.util.List;

/**
 * Created by YQ on 2017/12/27.
 */

public class HomePageModel {
    //轮播的五张图
    List<HomePage> gradeData;

    //下面的场馆精选
    List<HomePage> handpick;

    public List<HomePage> getGradeData() {
        return gradeData;
    }

    public void setGradeData(List<HomePage> gradeData) {
        this.gradeData = gradeData;
    }

    public List<HomePage> getHandpick() {
        return handpick;
    }

    public void setHandpick(List<HomePage> handpick) {
        this.handpick = handpick;
    }
}
