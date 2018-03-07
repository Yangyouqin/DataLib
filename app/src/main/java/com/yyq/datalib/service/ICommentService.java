package com.yyq.datalib.service;

import android.content.Context;

import com.yyq.datalib.javaBeans.Comment;
import com.yyq.datalib.javaBeans.Orders;

/**
 * Created by YQ on 2017/12/21.
 * 场地评论、圈子动态评论
 */

public interface ICommentService {

    //TODO:获取评论，type 0，1,2,3  0表示场地评论，1表示培训，2表示圈子，3表示比赛
    void getComment(Context context, String id, int type, int skip);

    //TODO:添加场地/培训/比赛评论  type的值分别为0,1,2
    void insertComment1(Context context, Comment comment, Orders orders);

    //TODO:添加圈子动态评论
    void insertComment2(Context context, Comment comment);

    //TODO:删除评论
    void deleteComment(Context context, String commentId);

}
