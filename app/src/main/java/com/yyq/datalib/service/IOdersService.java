package com.yyq.datalib.service;

import android.content.Context;

import com.yyq.datalib.javaBeans.Orders;

import cn.bmob.v3.datatype.BmobDate;

/**
 * Created by yangyouqin on 2018/2/26.
 */

public interface IOdersService {

    //添加订单，判断是否可以下单
    void addOrder (Context context, Orders orders, BmobDate startTime, BmobDate endTime);

    //删除订单，需要订单id
    void deleteOrder(Context context, String ObjectId);

    //更新订单,state    1 下单（待付款） 2 付款（待使用） 3 使用（待评论） 4 售后  5 申请退款 6 退款（退款成功）  7 交易关闭，
    void updateOrder(Context context, Orders orders, int state);

    //获取订单,skip传5的倍数
    void getPlaceOrders(Context context, int skip);

    //获取当前用户订单
    void getMyOrders(Context context, int skip);

    //获取当前用户的不同状态的订单  1 待付款，2 待使用，3 待评价 ，4 已失效
    void getByState(Context context, int skip, int state);

    //根据订单id获取一条订单
    void getById(Context context, String objectId);

}
