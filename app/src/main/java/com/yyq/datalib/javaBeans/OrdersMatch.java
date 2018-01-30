package com.yyq.datalib.javaBeans;

import cn.bmob.v3.BmobObject;

/**
 * Created by YQ on 2017/12/20.
 * 比赛报名信息
 */

public class OrdersMatch extends BmobObject {
    private String userId;

    private String matchId;

    private double payment;

    private int oderState;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getMatchId() {
        return matchId;
    }

    public void setMatchId(String matchId) {
        this.matchId = matchId;
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
        return "OrdersMatch{" +
                "userId='" + userId + '\'' +
                ", matchId='" + matchId + '\'' +
                ", payment=" + payment +
                ", oderState=" + oderState +
                '}';
    }
}
