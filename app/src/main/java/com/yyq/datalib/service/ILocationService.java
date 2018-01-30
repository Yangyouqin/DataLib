package com.yyq.datalib.service;

import android.content.Context;

import com.yyq.datalib.javaBeans.Location;

/**
 * Created by YQ on 2017/12/24.
 * 地址
 */

public interface ILocationService {

    //TODO:根据地址id获取地址信息
    void getById(Context context, String id);

    //TODO:根据经纬度获取地址
    void getByLongititudeAndLatitude(Context context, double longititude, double latitude);

    //TODO:插入地址
    void insert(Context context, Location location);

}
