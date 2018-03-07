package com.yyq.datalib.javaBeans;

import cn.bmob.v3.BmobObject;
import cn.bmob.v3.datatype.BmobDate;

/**
 * Created by YQ on 2017/12/20.
 * 预定场地详情
 */

public class Orders extends BmobObject {

    private MyUser user;

    //场地、培训、比赛的id
    private String typeId;

    //// 0  表示场地，1 表示培训， 2 表示比赛
    private int type;
    /**
     * 订单金额
     */
    private double payment;
    /**
     * 1 下单（待付款） 2 付款（待使用） 3 使用（待评论） 4 售后  5 申请退款 6 退款（退款成功）  7 交易关闭，
     */
    private int oderState;

    //平台收取的费用
    private double charge;

    //商家盈利
    private double profit;

    //场地租用开始时间（如果是培训和比赛的话，是直接获取培训信息的时间，不需要自己选择）
    private BmobDate startTime;

    //场地租用结束时间（如果是培训和比赛的话，是直接获取培训信息的时间，不需要自己选择）
    private BmobDate endTime;

    public Orders() {
    }

    //添加订单的构造函数
    public Orders(String typeId, int type, double payment, int oderState, BmobDate startTime, BmobDate endTime) {
        this.typeId = typeId;
        this.type = type;
        this.payment = payment;
        this.oderState = oderState;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public MyUser getUser() {
        return user;
    }

    public void setUser(MyUser user) {
        this.user = user;
    }

    public String getTypeId() {
        return typeId;
    }

    public void setTypeId(String typeId) {
        this.typeId = typeId;
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

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
