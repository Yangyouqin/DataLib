package com.yyq.datalib.service;

import android.content.Context;

import com.yyq.datalib.javaBeans.Match;

/**
 * Created by YQ on 2017/12/20.
 */

public interface IMatchService {
    //TODO:查询全部培训数据
    void getAllMatch(final Context context, int skip);

    //TODO:根据类型查询培训,返回的数据为 MatchModel，在models包里
    void getMatchByType(final Context context, String type, int skip);

    //TODO:添加培训信息,需要传入一个对象，和那些图片的地址
    void insertMatch(final Context context, Match match, String pics);

    //TODO:根据id 查询数据 ,返回的数据为 Match，在javaBeans包里
    void getMatchById(Context context, String objectId);
}
