package com.yyq.datalib.service;

import android.content.Context;

import com.yyq.datalib.javaBeans.MakeDate;

/**
 * Created by YQ on 2017/12/24.
 * 约球信息
 */

public interface IMakeDateService {

    void getById(Context context, String id);

    void insert(Context context, MakeDate makeDate);
}
