package com.yyq.datalib.service;

import android.content.Context;

import com.yyq.datalib.javaBeans.OrdersTrain;

/**
 * Created by YQ on 2017/12/20.
 */

public interface IOdersTrain {

    //TODO:查询所有培训报名信息
    void getAllTrainOders(Context context);

    //TODO:获取指定用户的培训报名信息
    void getTrainOderByUserId(Context context, String userId);

    //TODO:添加培训报名
    void insertTrainOder(Context context, OrdersTrain oders_Train);

    //TODO:更新培训报名
    void updateTrainOder(Context context, String oderId, int state);

    //TODO:删除培训报名
    void deleteTrainOder(Context context, String oderId);
}
