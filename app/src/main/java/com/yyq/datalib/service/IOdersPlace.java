package com.yyq.datalib.service;

import android.content.Context;

import com.yyq.datalib.javaBeans.OrdersPlace;

/**
 * Created by YQ on 2017/12/20.
 */

public interface IOdersPlace {

    //TODO:查询所预定场地名信息
    void getAllPlaceOders(Context context);

    //TODO:获取指定用户的场地订单信息
    void getPlaceOderByUserId(Context context, String userId);

    //TODO:添加场地订单
    void insertPlaceOder(Context context, OrdersPlace oders_Place);

    //TODO:更新场地订单
    void updatePlaceOder(Context context, String oderId, int state);

    //TODO:删除场地订单
    void deletePlaceOder(Context context, String oderId);
}
