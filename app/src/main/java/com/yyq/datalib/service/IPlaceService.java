package com.yyq.datalib.service;

import android.content.Context;

import com.yyq.datalib.javaBeans.Place;

/**
 * Created by YQ on 2017/12/14.
 * 场地service
 */

public interface IPlaceService {

    //TODO:获取场地数据
    public void getPlaces(Context context, int skip);

    //TODO:分类型查询场地信息，篮球，足球，羽毛球等
    public void getPlacesByType(Context context, String type, int skip);

    //TODO:添加场地信息
    public void insertPlace(Context context, Place places,String[] placeImgsUrl);

    //TODO:更新场地是否可利用状态
    public void updatePlace(Context context, Place oldPlace, boolean available);

}
