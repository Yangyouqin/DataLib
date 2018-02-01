package com.yyq.datalib.service;

import android.content.Context;

import com.yyq.datalib.javaBeans.Comment;

/**
 * Created by YQ on 2017/12/21.
 * 场地评论、圈子动态评论
 */

public interface ICommentService {

    //TODO:获取评论，type 1,2,3  1表示场地评论，2表示培训，3表示圈子，4表示比赛
    void getComment(Context context, String id, int type,int skip);

    //TODO:添加圈子动态评论
    void insertComment(Context context, Comment comment);

    //TODO:删除评论
    void deleteComment(Context context, String commentId);

}
