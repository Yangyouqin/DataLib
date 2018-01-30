package com.yyq.datalib.service;

import android.content.Context;

import com.yyq.datalib.javaBeans.OrdersMatch;

/**
 * Created by YQ on 2017/12/20.
 */

public interface IOdersMatch {

    //TODO:查询所有比赛报名信息
    void getAllMatchOders(Context context);

    //TODO:获取制定用户的报名信息
    void getMatchOderByUserId(Context context, String userId);

    //TODO:添加报名信息
    void insertMatchOder(Context context, OrdersMatch oders_match);

    //TODO:更新报名信息
    void updateMatchOder(Context context, String oderId, int state);

    //TODO:删除报名信息
    void deleteMatchOder(Context context, String oderId);
}
