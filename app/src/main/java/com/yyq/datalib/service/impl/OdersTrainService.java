package com.yyq.datalib.service.impl;

import android.content.Context;
import android.util.Log;

import com.mj.datashow.utils.ToastUtil;
import com.yyq.datalib.javaBeans.OrdersTrain;
import com.yyq.datalib.service.IOdersTrain;

import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.SaveListener;
import cn.bmob.v3.listener.UpdateListener;

/**
 * Created by YQ on 2017/12/20.
 */

public class OdersTrainService implements IOdersTrain {

    //获取培训报名全部数据
    OnGetAllOdersTrainListener mOnGetAllOdersTrainListener;
    //获取用户培训报名
    OnGetOdersTrainByuserIdListener mOnGetOdersTrainByUserIdListener;
    //添加订单
    OnApplyTrainListener mOnApplyTrainListener;
    //删除订单
    OnDeleteOderTrainListener mOnDeleteOderTrainListener;

    //TODO:查询所有培训报名
    @Override
    public void getAllTrainOders(final Context context) {
        BmobQuery<OrdersTrain> query = new BmobQuery<OrdersTrain>();
        query.setLimit(5);
        //执行查询方法
        query.findObjects(new FindListener<OrdersTrain>() {
            @Override
            public void done(List<OrdersTrain> object, BmobException e) {
                if(e==null){
                    ToastUtil.showToast(context,"查询成功：共"+object.size()+"条数据。");
                    if(mOnGetAllOdersTrainListener!=null){
                        mOnGetAllOdersTrainListener.getAllOdersTrain(object,true);
                    }
                }else{
                    mOnGetAllOdersTrainListener.getAllOdersTrain(null,false);
                    ToastUtil.showToast(context,"失败："+e.getMessage()+","+e.getErrorCode());
                }
            }
        });
    }

    //TODO:获取指定用户的培训报名
    @Override
    public void getTrainOderByUserId(final Context context, String userId) {
        BmobQuery<OrdersTrain> query = new BmobQuery<OrdersTrain>();
        query.addWhereEqualTo("userId",userId);
        query.setLimit(5);
        //执行查询方法
        query.findObjects(new FindListener<OrdersTrain>() {
            @Override
            public void done(List<OrdersTrain> object, BmobException e) {
                if(e==null){
                    ToastUtil.showToast(context,"查询成功：共"+object.size()+"条数据。");
                    if(mOnGetOdersTrainByUserIdListener!=null){
                        mOnGetOdersTrainByUserIdListener.getOdersTrainByuserId(object,true);
                    }
                }else{
                    mOnGetOdersTrainByUserIdListener.getOdersTrainByuserId(null,false);
                    ToastUtil.showToast(context,"失败："+e.getMessage()+","+e.getErrorCode());
                }
            }
        });
    }

    //TODO:添加培训报名
    @Override
    public void insertTrainOder(final Context context, OrdersTrain ordersTrain) {
        ordersTrain.save(new SaveListener<String>() {
            @Override
            public void done(String objectId, BmobException e) {
                if(e==null){
                    ToastUtil.showToast(context,"添加培训报名成功！");
                    if(mOnApplyTrainListener!=null){
                        mOnApplyTrainListener.isApply(1);
                    }
                }else{
                    ToastUtil.showToast(context,"添加培训报名失败！");
                    mOnApplyTrainListener.isApply(e.getErrorCode());
                }
            }
        });

    }

    //TODO:更新培训报名
    @Override
    public void updateTrainOder(Context context,String oderId, int state) {
        OrdersTrain ordersTrain = new OrdersTrain();
        ordersTrain.setOderState(state);
        ordersTrain.update(oderId, new UpdateListener() {
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

    //TODO:删除培训报名
    @Override
    public void deleteTrainOder(Context context, String oderId) {
        OrdersTrain ordersTrain = new OrdersTrain();
        ordersTrain.setObjectId(oderId);
        ordersTrain.delete(new UpdateListener() {
            @Override
            public void done(BmobException e) {
                if(e==null){
                    Log.i("MyTag","删除成功");
                    if(mOnDeleteOderTrainListener!=null){
                        mOnDeleteOderTrainListener.isDelete(1);
                    }
                }else{
                    Log.i("MyTag","删除失败："+e.getMessage()+","+e.getErrorCode());
                    mOnDeleteOderTrainListener.isDelete(e.getErrorCode());
                }
            }
        });

    }

    //TODO:获取全部培训报名数据
    public void setOnGetAllOdersTrainListener(OnGetAllOdersTrainListener onGetAllOdersTrainListener){
        mOnGetAllOdersTrainListener = onGetAllOdersTrainListener;
    }
    //TODO:接口   获取全部培训报名数据
    public interface OnGetAllOdersTrainListener{
        void getAllOdersTrain(List<OrdersTrain> ordersTraines, boolean state);
    }

    //TODO:获取用户的订单
    public void setOnGetOdersTrainByTypeListener(OnGetOdersTrainByuserIdListener onGetOdersTrainByuserIdListener){
        mOnGetOdersTrainByUserIdListener = onGetOdersTrainByuserIdListener;
    }
    //TODO:接口   获取用户的订单
    public interface OnGetOdersTrainByuserIdListener{
        void getOdersTrainByuserId(List<OrdersTrain> ordersTraines, boolean state);
    }

    //TODO:添加培训报名监听
    public void setOnApplyTrainListener(OnApplyTrainListener onApplyTrainListener){
        mOnApplyTrainListener = onApplyTrainListener;
    }
    //TODO:接口   添加培训报名
    public interface OnApplyTrainListener{
        void isApply(int code);
    }

    //TODO:删除培训报名监听
    public void setOnDeleteOderTrainListener(OnDeleteOderTrainListener onDeleteOderTrainListener){
        mOnDeleteOderTrainListener = onDeleteOderTrainListener;
    }
    //TODO:接口   删除培训报名
    public interface OnDeleteOderTrainListener{
        void isDelete(int code);
    }
}
