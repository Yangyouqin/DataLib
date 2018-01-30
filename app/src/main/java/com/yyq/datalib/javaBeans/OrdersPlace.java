package com.yyq.datalib.javaBeans;

import cn.bmob.v3.BmobObject;

/**
 * Created by YQ on 2017/12/20.
 * 预定场地详情
 */

public class OrdersPlace extends BmobObject {
    private String userId;

    private String placeId;
    /**
     * 订单金额
     */
    private double payment;
    /**
     * 订单状态
     */
    private int oderState;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPlaceId() {
        return placeId;
    }

    public void setPlaceId(String placeId) {
        this.placeId = placeId;
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
        return "OrdersPlace{" +
                "userId='" + userId + '\'' +
                ", placeId='" + placeId + '\'' +
                ", payment=" + payment +
                ", oderState=" + oderState +
                '}';
    }
}
