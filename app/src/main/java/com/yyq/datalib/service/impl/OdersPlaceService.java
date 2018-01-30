package com.yyq.datalib.service.impl;

import android.content.Context;
import android.util.Log;

import com.mj.datashow.utils.ToastUtil;
import com.yyq.datalib.javaBeans.OrdersPlace;
import com.yyq.datalib.service.IOdersPlace;

import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.SaveListener;
import cn.bmob.v3.listener.UpdateListener;

/**
 * Created by YQ on 2017/12/20.
 */

public class OdersPlaceService implements IOdersPlace {

    //获取场地订单全部数据
    OnGetAllOdersPlaceListener mOnGetAllOdersPlaceListener;
    //获取用户场地订单
    OnGetOdersPlaceByuserIdListener mOnGetOdersPlaceByUserIdListener;
    //添加订单
    OnApplyPlaceListener mOnApplyPlaceListener;
    //删除订单
    OnDeleteOderPlaceListener mOnDeleteOderPlaceListener;

    //TODO:查询所有场地订单
    @Override
    public void getAllPlaceOders(final Context context) {
        BmobQuery<OrdersPlace> query = new BmobQuery<OrdersPlace>();
        query.setLimit(5);
        //执行查询方法
        query.findObjects(new FindListener<OrdersPlace>() {
            @Override
            public void done(List<OrdersPlace> object, BmobException e) {
                if(e==null){
                    ToastUtil.showToast(context,"查询成功：共"+object.size()+"条数据。");
                    if(mOnGetAllOdersPlaceListener!=null){
                        mOnGetAllOdersPlaceListener.getAllOdersPlace(object,true);
                    }
                }else{
                    mOnGetAllOdersPlaceListener.getAllOdersPlace(null,false);
                    ToastUtil.showToast(context,"失败："+e.getMessage()+","+e.getErrorCode());
                }
            }
        });
    }

    //TODO:获取制定用户的场地订单
    @Override
    public void getPlaceOderByUserId(final Context context, String userId) {
        BmobQuery<OrdersPlace> query = new BmobQuery<OrdersPlace>();
        query.addWhereEqualTo("userId",userId);
        query.setLimit(5);
        //执行查询方法
        query.findObjects(new FindListener<OrdersPlace>() {
            @Override
            public void done(List<OrdersPlace> object, BmobException e) {
                if(e==null){
                    ToastUtil.showToast(context,"查询成功：共"+object.size()+"条数据。");
                    if(mOnGetOdersPlaceByUserIdListener!=null){
                        mOnGetOdersPlaceByUserIdListener.getOdersPlaceByuserId(object,true);
                    }
                }else{
                    mOnGetOdersPlaceByUserIdListener.getOdersPlaceByuserId(null,false);
                    ToastUtil.showToast(context,"失败："+e.getMessage()+","+e.getErrorCode());
                }
            }
        });
    }

    //TODO:添加场地订单
    @Override
    public void insertPlaceOder(final Context context, OrdersPlace ordersPlace) {
        ordersPlace.save(new SaveListener<String>() {
            @Override
            public void done(String objectId, BmobException e) {
                if(e==null){
                    ToastUtil.showToast(context,"添加场地订单成功！");
                    if(mOnApplyPlaceListener!=null){
                        mOnApplyPlaceListener.isApply(1);
                    }
                }else{
                    ToastUtil.showToast(context,"添加场地订单失败！");
                    mOnApplyPlaceListener.isApply(e.getErrorCode());
                }
            }
        });

    }

    //TODO:更新场地订单
    @Override
    public void updatePlaceOder(Context context,String oderId, int state) {
        OrdersPlace ordersPlace = new OrdersPlace();
        ordersPlace.setOderState(state);
        ordersPlace.update(oderId, new UpdateListener() {
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

    //TODO:删除场地订单
    @Override
    public void deletePlaceOder(Context context, String oderId) {
        OrdersPlace ordersPlace = new OrdersPlace();
        ordersPlace.setObjectId(oderId);
        ordersPlace.delete(new UpdateListener() {
            @Override
            public void done(BmobException e) {
                if(e==null){
                    Log.i("MyTag","删除成功");
                    if(mOnDeleteOderPlaceListener!=null){
                        mOnDeleteOderPlaceListener.isDelete(1);
                    }
                }else{
                    Log.i("MyTag","删除失败："+e.getMessage()+","+e.getErrorCode());
                    mOnDeleteOderPlaceListener.isDelete(e.getErrorCode());
                }
            }
        });

    }

    //TODO:获取全部场地订单数据
    public void setOnGetAllOdersPlaceListener(OnGetAllOdersPlaceListener onGetAllOdersPlaceListener){
        mOnGetAllOdersPlaceListener = onGetAllOdersPlaceListener;
    }
    //TODO:接口   获取全部场地订单数据
    public interface OnGetAllOdersPlaceListener{
        void getAllOdersPlace(List<OrdersPlace> ordersPlacees, boolean state);
    }

    //TODO:获取用户订单
    public void setOnGetOdersPlaceByTypeListener(OnGetOdersPlaceByuserIdListener onGetOdersPlaceByuserIdListener){
        mOnGetOdersPlaceByUserIdListener = onGetOdersPlaceByuserIdListener;
    }
    //TODO:接口   获取用户订单
    public interface OnGetOdersPlaceByuserIdListener{
        void getOdersPlaceByuserId(List<OrdersPlace> ordersPlacees, boolean state);
    }

    //TODO:添加场地订单监听
    public void setOnApplyPlaceListener(OnApplyPlaceListener onApplyPlaceListener){
        mOnApplyPlaceListener = onApplyPlaceListener;
    }
    //TODO:接口   添加场地订单
    public interface OnApplyPlaceListener{
        void isApply(int code);
    }

    //TODO:删除场地订单监听
    public void setOnDeleteOderPlaceListener(OnDeleteOderPlaceListener onDeleteOderPlaceListener){
        mOnDeleteOderPlaceListener = onDeleteOderPlaceListener;
    }
    //TODO:接口   删除场地订单
    public interface OnDeleteOderPlaceListener{
        void isDelete(int code);
    }
}
