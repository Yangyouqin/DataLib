package com.yyq.datalib.service.impl;

import android.content.Context;
import android.util.Log;

import com.mj.datashow.utils.ToastUtil;
import com.yyq.datalib.javaBeans.Comment;
import com.yyq.datalib.javaBeans.MyUser;
import com.yyq.datalib.service.ICommentService;

import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.SaveListener;
import cn.bmob.v3.listener.UpdateListener;

/**
 * Created by YQ on 2017/12/21.
 */

public class CommentService implements ICommentService {

    //获取评论
    OnGetCommentListener mOnGetCommentListener;

    //添加评论
    OnAddCommentListener mOnAddCommentListener;

    //删除评论
    OnDeleteCommentListener mOnDeleteCommentListener;

    //TODO:获取评论，type 1,2,3  1表示场地评论，2表示培训，3表示圈子，4表示比赛
    @Override
    public void getComment(final Context context, String id,int type) {
        BmobQuery<Comment> query = new BmobQuery<Comment>();
        if(type==1){
            query.addWhereEqualTo("placeId",id);
        }
        else if(type==2){
            query.addWhereEqualTo("trainId",id);
        }
        else if(type==3){
            query.addWhereEqualTo("messageId",id);
        }
        else if(type==4){
            query.addWhereEqualTo("matchId",id);
        }
        query.setLimit(5);
        //执行查询方法
        query.findObjects(new FindListener<Comment>() {
            @Override
            public void done(List<Comment> object, BmobException e) {
                if(e==null){
                    ToastUtil.showToast(context,"查询成功：共"+object.size()+"条数据。");
                    if(mOnGetCommentListener!=null){
                        mOnGetCommentListener.getComment(object,true);
                    }
                }else{
                    mOnGetCommentListener.getComment(null,false);
                    ToastUtil.showToast(context,"失败："+e.getMessage()+","+e.getErrorCode());
                }
            }
        });
    }


    @Override
    public void insertComment(final Context context, Comment comment) {
        MyUser user = MyUser.getCurrentUser(MyUser.class);
        comment.setUser(user);
        comment.save(new SaveListener<String>() {
            @Override
            public void done(String objectId, BmobException e) {
                if(e==null){
                    ToastUtil.showToast(context,"添加评论成功！");
                    Log.i("MyTag","添加评论成功！");
                    if(mOnAddCommentListener!=null){
                        mOnAddCommentListener.isApply(1);
                    }
                }else{
                    ToastUtil.showToast(context,"添加评论失败！");
                    Log.i("MyTag","添加失败"+e.getErrorCode()+e.getMessage());
                    mOnAddCommentListener.isApply(e.getErrorCode());
                }
            }
        });
    }

    @Override
    public void deleteComment(Context context, String commentId) {
        Comment comment = new Comment();
        comment.setObjectId(commentId);
        comment.delete(new UpdateListener() {
            @Override
            public void done(BmobException e) {
                if(e==null){
                    Log.i("MyTag","删除成功");
                    if(mOnDeleteCommentListener!=null){
                        mOnDeleteCommentListener.isDelete(1);
                    }
                }else{
                    Log.i("MyTag","删除失败："+e.getMessage()+","+e.getErrorCode());
                    mOnDeleteCommentListener.isDelete(e.getErrorCode());
                }
            }
        });

    }

    //TODO:获取获取培训评论
    public void setOnGetCommentByTypeListener(CommentService.OnGetCommentListener onGetCommentListener){
        mOnGetCommentListener = onGetCommentListener;
    }
    //TODO:接口   获取培训评论
    public interface OnGetCommentListener{
        void getComment(List<Comment> Commentes, boolean state);
    }

    //TODO:添加培训报名监听
    public void setOnAddCommentListener(OnAddCommentListener onAddCommentListener){
        mOnAddCommentListener = onAddCommentListener;
    }
    //TODO:接口   添加培训报名
    public interface OnAddCommentListener{
        void isApply(int code);
    }

    //TODO:删除培训报名监听
    public void setOnDeleteCommentListener(OnDeleteCommentListener onDeleteCommentListener){
        mOnDeleteCommentListener = onDeleteCommentListener;
    }
    //TODO:接口   删除培训报名
    public interface OnDeleteCommentListener{
        void isDelete(int code);
    }
}
