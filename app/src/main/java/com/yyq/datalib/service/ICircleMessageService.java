package com.yyq.datalib.service;

import android.content.Context;

import com.yyq.datalib.javaBeans.MakeDate;

/**
 * Created by YQ on 2018/1/6.
 */

public interface ICircleMessageService {

    //获取全部动态，每5条返回一次，skip传5的倍数，初始传为0
    void getAllMessage(Context context, int skip);

    //点击获取一条动态
    void getOneCircleMessage(Context context, String objectId);

    //content动态的文字内容，picUrls动态图片的地址数组
    void insertCircleMessage(Context context, String content, String[] picUrls);

    //根据objectId动态删除
    void deleteByObjectId(Context context, String id);

    //添加约球。makeDate动态的构造方法那些参数，大图的url
    void insertMakeDate(Context context, MakeDate makeDate, String picUrl);

    //获取全部约球，每5条返回一次，skip传5的倍数，初始传为0
    void getAllMakeDate(Context context, int skip);

    //获取一条约球详情
    void getOneMakeDateDetail(Context context, String objectId);

    //点赞喜欢,现有的喜欢的数量
    void UpdateMakeDate1(Context context, String objectId, int like);

    //约球里面申请报名，当前的报名数量，前端用apply和personNum判断一下，如果apply已经等于personNum了，则提示人数已满
    void UpdateMakeDate2(Context context, String objectId, int aplly);
}
