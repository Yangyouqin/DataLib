package com.yyq.datalib.service.impl;

import android.content.Context;
import android.util.Log;

import com.mj.datashow.utils.ToastUtil;
import com.yyq.datalib.javaBeans.OrdersMatch;
import com.yyq.datalib.service.IOdersMatch;

import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.SaveListener;
import cn.bmob.v3.listener.UpdateListener;

/**
 * Created by YQ on 2017/12/20.
 */

public class OdersMatchService implements IOdersMatch {

    //获取比赛全部数据
    OnGetAllOdersMatchListener mOnGetAllOdersMatchListener;
    //获取用户的报名情况
    OnGetOdersMatchByuserIdListener mOnGetOdersMatchByUserIdListener;
    //添加报名信息
    OnApplyMatchListener mOnApplyMatchListener;
    //删除报名信息
    OnDeleteOderMatchListener mOnDeleteOderMatchListener;

    //TODO:查询所有比赛报名信息
    @Override
    public void getAllMatchOders(final Context context) {
        BmobQuery<OrdersMatch> query = new BmobQuery<OrdersMatch>();
        query.setLimit(5);
        //执行查询方法
        query.findObjects(new FindListener<OrdersMatch>() {
            @Override
            public void done(List<OrdersMatch> object, BmobException e) {
                if(e==null){
                    ToastUtil.showToast(context,"查询成功：共"+object.size()+"条数据。");
                    if(mOnGetAllOdersMatchListener!=null){
                        mOnGetAllOdersMatchListener.getAllOdersMatch(object,true);
                    }
                }else{
                    mOnGetAllOdersMatchListener.getAllOdersMatch(null,false);
                    ToastUtil.showToast(context,"失败："+e.getMessage()+","+e.getErrorCode());
                }
            }
        });
    }

    //TODO:获取制定用户的报名信息
    @Override
    public void getMatchOderByUserId(final Context context, String userId) {
        BmobQuery<OrdersMatch> query = new BmobQuery<OrdersMatch>();
        query.addWhereEqualTo("userId",userId);
        query.setLimit(5);
        //执行查询方法
        query.findObjects(new FindListener<OrdersMatch>() {
            @Override
            public void done(List<OrdersMatch> object, BmobException e) {
                if(e==null){
                    ToastUtil.showToast(context,"查询成功：共"+object.size()+"条数据。");
                    if(mOnGetOdersMatchByUserIdListener!=null){
                        mOnGetOdersMatchByUserIdListener.getOdersMatchByuserId(object,true);
                    }
                }else{
                    mOnGetOdersMatchByUserIdListener.getOdersMatchByuserId(null,false);
                    ToastUtil.showToast(context,"失败："+e.getMessage()+","+e.getErrorCode());
                }
            }
        });
    }

    //TODO:添加报名信息
    @Override
    public void insertMatchOder(final Context context, OrdersMatch odersmatch) {
        odersmatch.save(new SaveListener<String>() {
            @Override
            public void done(String objectId, BmobException e) {
                if(e==null){
                    ToastUtil.showToast(context,"发布比赛信息成功！");
                    if(mOnApplyMatchListener!=null){
                        mOnApplyMatchListener.isApply(1);
                    }
                }else{
                    ToastUtil.showToast(context,"发布比赛信息失败！");
                    mOnApplyMatchListener.isApply(e.getErrorCode());
                }
            }
        });

    }

    //TODO:更新报名信息
    @Override
    public void updateMatchOder(Context context,String oderId, int state) {
        OrdersMatch ordersMatch = new OrdersMatch();
        ordersMatch.setOderState(state);
        ordersMatch.update(oderId, new UpdateListener() {
            @Override
            public void done(BmobException e) {
                if (e == null) {
                    Log.i("MyTag", "用户更新成功");
                } else {
                    Log.i("MyTag", "用户更新失败+错误码" + e.getErrorCode() + ",错误信息" + e.getMessage());
                }
            }
        });

    }

    //TODO:删除报名信息
    @Override
    public void deleteMatchOder(Context context, String oderId) {
        OrdersMatch ordersMatch = new OrdersMatch();
        ordersMatch.setObjectId(oderId);
        ordersMatch.delete(new UpdateListener() {
            @Override
            public void done(BmobException e) {
                if(e==null){
                    Log.i("MyTag","删除成功");
                    if(mOnDeleteOderMatchListener!=null){
                        mOnDeleteOderMatchListener.isDelete(1);
                    }
                }else{
                    Log.i("MyTag","删除失败："+e.getMessage()+","+e.getErrorCode());
                    mOnDeleteOderMatchListener.isDelete(e.getErrorCode());
                }
            }
        });

    }

    //TODO:获取全部比赛报名数据
    public void setOnGetAllOdersMatchListener(OnGetAllOdersMatchListener onGetAllOdersMatchListener){
        mOnGetAllOdersMatchListener = onGetAllOdersMatchListener;
    }
    //TODO:接口   获取全部比赛报名数据
    public interface OnGetAllOdersMatchListener{
        void getAllOdersMatch(List<OrdersMatch> ordersMatches, boolean state);
    }

    //TODO:获取用户订单
    public void setOnGetOdersMatchByTypeListener(OnGetOdersMatchByuserIdListener onGetOdersMatchByuserIdListener){
        mOnGetOdersMatchByUserIdListener = onGetOdersMatchByuserIdListener;
    }
    //TODO:接口   获取用户订单
    public interface OnGetOdersMatchByuserIdListener{
        void getOdersMatchByuserId(List<OrdersMatch> ordersMatches, boolean state);
    }

    //TODO:添加比赛报名监听
    public void setOnApplyMatchListener(OnApplyMatchListener onApplyMatchListener){
        mOnApplyMatchListener = onApplyMatchListener;
    }
    //TODO:接口   添加比赛报名
    public interface OnApplyMatchListener{
        void isApply(int code);
    }

    //TODO:删除比赛报名监听
    public void setOnDeleteOderMatchListener(OnDeleteOderMatchListener onDeleteOderMatchListener){
        mOnDeleteOderMatchListener = onDeleteOderMatchListener;
    }
    //TODO:接口   删除比赛报名
    public interface OnDeleteOderMatchListener{
        void isDelete(int code);
    }
}
