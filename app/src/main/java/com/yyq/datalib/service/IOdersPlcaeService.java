package com.yyq.datalib.service;

import android.content.Context;

import com.yyq.datalib.javaBeans.OrdersPlace;

/**
 * Created by yangyouqin on 2018/2/26.
 */

public interface IOdersPlcaeService {

    //添加订单，判断是否可以下单
    void addOrder (Context context, OrdersPlace ordersPlace);

    //删除订单，需要订单id
    void deleteOrder(Context context,String ObjectId);

    //更新订单
    void updaterOrder(Context context,OrdersPlace ordersPlace,int state);

    //获取订单
    void getPlaceOrders(Context context,int skip);

    //获取当前用户订单
    void getMyPlaceOrders(Context context,int skip);

    //获取当前用户的不同状态的订单  1 待付款，2 待使用，3 待评价 ，4 已失效
    void getByState(Context context , int skip, int state);

}
