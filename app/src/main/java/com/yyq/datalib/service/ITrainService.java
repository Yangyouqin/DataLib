package com.yyq.datalib.service;

import android.content.Context;

import com.yyq.datalib.javaBeans.Trains;

import java.util.List;

/**
 * Created by YQ on 2017/12/20.
 */

public interface ITrainService {

    //TODO:查询全部培训数据
    void getAllTrain(Context context, int skip);

    //TODO:根据类型查询培训,返回的数据为 TrainModel，在models包里
    void getTrainByType(Context context, String type, int skip);

    //TODO:根据培训id查询，,返回的数据为 Trains，在javaBeans包里
    void getTrainByid(Context context, String id);

    //TODO:添加培训信息
    void insertTrain(Context context, Trains trains, List<String> trainImgUrl);

}
