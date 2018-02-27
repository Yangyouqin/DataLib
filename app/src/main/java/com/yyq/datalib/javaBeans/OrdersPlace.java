package com.yyq.datalib.javaBeans;

import cn.bmob.v3.BmobObject;
import cn.bmob.v3.datatype.BmobDate;

/**
 * Created by YQ on 2017/12/20.
 * 预定场地详情
 */

public class OrdersPlace extends BmobObject {

    private MyUser myUser;

    private String placeId;
    /**
     * 订单金额
     */
    private double payment;
    /**
     * 订单状态 1 待付款 2 待使用 3 待评论 4 售后  5 退款 6 退款成功  7 交易关闭
     */
    private int oderState;

    //平台收取的费用
    private double charge;

    //商家盈利
    private double profit;

    //场地租用开始时间
    private BmobDate startTime;

    //场地租用结束时间
    private BmobDate endTime;

    public MyUser getMyUser() {
        return myUser;
    }

    public void setMyUser(MyUser myUser) {
        this.myUser = myUser;
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

    public double getCharge() {
        return charge;
    }

    public void setCharge(double charge) {
        this.charge = charge;
    }

    public double getProfit() {
        return profit;
    }

    public void setProfit(double profit) {
        this.profit = profit;
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
}
