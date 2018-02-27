package com.yyq.datalib.service.impl;

import android.content.Context;
import android.util.Log;

import com.mj.datashow.utils.ToastUtil;
import com.yyq.datalib.javaBeans.OrdersPlace;
import com.yyq.datalib.query.DealDate;
import com.yyq.datalib.service.IOdersPlcaeService;

import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.SaveListener;
import cn.bmob.v3.listener.UpdateListener;

/**
 * Created by yangyouqin on 2018/2/26.
 */

public class OrdersPlaceService implements IOdersPlcaeService {
    @Override
    public void addOrder(final Context context, final OrdersPlace ordersPlace) {
        //先查询一下同一时间段的场地是否已预定
        BmobQuery<OrdersPlace> orderQuery = new BmobQuery<OrdersPlace>();
        orderQuery.addWhereLessThan("startTime",ordersPlace.getEndTime()).addWhereGreaterThan("endTime",ordersPlace.getStartTime());
        //执行查询方法
        orderQuery.findObjects(new FindListener<OrdersPlace>() {
            @Override
            public void done(List<OrdersPlace> makeDates, BmobException e) {
                if (e == null) {
                    ordersPlace.save(new SaveListener<String>() {
                        @Override
                        public void done(String objectId,BmobException e) {
                            if(e==null){
                                ToastUtil.showToast(context,"下单成功！");
                            }else{
                                ToastUtil.showToast(context,"下单失败" + e.getErrorCode()+e.getMessage());
                            }
                        }
                    });
                } else {
                    Log.i("MyTag","此时间段不可预订");
                    ToastUtil.showToast(context,"此时间段不可预订");
                    if (e.getErrorCode() == 9016) {
                        ToastUtil.showToast(context, "网络连接失败，请检查网络状况");
                    }

                }
            }
        });
    }

    @Override
    public void deleteOrder(Context context, String ObjectId) {
        OrdersPlace ordersPlace = new OrdersPlace();
        ordersPlace.setObjectId(ObjectId);
        ordersPlace.delete(new UpdateListener() {
            @Override
            public void done(BmobException e) {
                if(e==null){
                    Log.i("MyTag","删除场地订单成功");
                }else{
                    Log.i("MyTag","删除场地订单失败："+e.getMessage()+","+e.getErrorCode());
                }
            }
        });
    }

    @Override
    public void updaterOrder(final Context context, OrdersPlace ordersPlace, final int state) {
        ordersPlace.setOderState(state);
        if(state == 3){
            DealDate dealDate = new DealDate();
            double profit = dealDate.caculateProfit(ordersPlace.getPayment());
            ordersPlace.setProfit(profit);
        }
        ordersPlace.update(ordersPlace.getObjectId(), new UpdateListener() {
            @Override
            public void done(BmobException e) {
                if(e==null){
                    Log.i("MyTag","更新成功");
                    if(state == 2){
                        ToastUtil.showToast(context,"付款成功！");
                    }
                    else if(state == 3){
                        ToastUtil.showToast(context,"交易成功！");

                    }
                    else if(state == 4){
                        ToastUtil.showToast(context,"评价成功！");
                    }
                    else if(state == 6){
                        ToastUtil.showToast(context,"退款成功！");
                    }
                }else{
                    Log.i("MyTag","更新失败："+e.getMessage()+","+e.getErrorCode());
                }
            }
        });
    }

    @Override
    public void getPlaceOrders(Context context, int skip) {

    }

    @Override
    public void getMyPlaceOrders(Context context, int skip) {

    }

    @Override
    public void getByState(Context context, int skip, int state) {

    }
}
