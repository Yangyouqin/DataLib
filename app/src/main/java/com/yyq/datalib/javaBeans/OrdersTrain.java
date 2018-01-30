package com.yyq.datalib.javaBeans;

import cn.bmob.v3.BmobObject;

/**
 * Created by YQ on 2017/12/20.
 * 报名培训报名信息
 */

public class OrdersTrain extends BmobObject {
    private String userId;

    private String trainId;

    private double payment;

    private int oderState;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getTrainId() {
        return trainId;
    }

    public void setTrainId(String trainId) {
        this.trainId = trainId;
    }

    public double getPayment() {
        return payment;
    }

    public void setPayment(double payment) {
        this.payment = payment;
    }

    public int getOderState() {
        return oderState;
    }

    public void setOderState(int oderState) {
        this.oderState = oderState;
    }

    @Override
    public String toString() {
        return "OrdersTrain{" +
                "userId='" + userId + '\'' +
                ", trainId='" + trainId + '\'' +
                ", payment=" + payment +
                ", oderState=" + oderState +
                '}';
    }
}
