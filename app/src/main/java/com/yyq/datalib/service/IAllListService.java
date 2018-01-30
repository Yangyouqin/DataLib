package com.yyq.datalib.service;

import android.content.Context;

import com.yyq.datalib.query.SortQuery;

/**
 * Created by yangyouqin on 2018/1/4.
 */

public interface IAllListService {

    //TODO:type为类型，篮球，足球。。。，sort是排序方式，具体到SortQuery里面看用法
    void getData(Context context, SortQuery sortQuery);


    //TODO:type为场地，培训，比赛  即当前点击item的getType，objecId是当前点击的item的objectId
    void getDetails(Context context, String type, String objectId);
}
