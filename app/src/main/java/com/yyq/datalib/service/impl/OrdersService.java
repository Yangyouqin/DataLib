package com.yyq.datalib.service.impl;

import android.content.Context;
import android.util.Log;

import com.mj.datashow.utils.ToastUtil;
import com.yyq.datalib.javaBeans.Match;
import com.yyq.datalib.javaBeans.MyUser;
import com.yyq.datalib.javaBeans.Orders;
import com.yyq.datalib.javaBeans.Trains;
import com.yyq.datalib.query.DealDate;
import com.yyq.datalib.service.IOdersService;

import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.datatype.BmobDate;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.QueryListener;
import cn.bmob.v3.listener.SaveListener;
import cn.bmob.v3.listener.UpdateListener;

/**
 * Created by yangyouqin on 2018/2/26.
 */

public class OrdersService implements IOdersService {

    OnGetAllOrdersPlaceListener mOnGetAllOrdersPlaceListener;

    OnGetMyOrdersPlaceListener mOnGetMyOrdersPlaceListener;

    OnGetOrdersPlaceByStateListener mOnGetOrdersPlaceByStateListener;

    OnGetOneOrdersPlaceListener mOnGetOneOrdersPlaceListener;

    DealDate dealDate = new DealDate();

    @Override
    public void addOrder(final Context context, final Orders newOrder, BmobDate startTime, BmobDate endTime) {
        //如果是场地的订单，要判断下单的时间是否存在冲突，否则直接添加订单，只是前端需要判断一下，当前人数是否已经满员
        if(newOrder.getType() == 0){
            //如果新增的订单时间在场地规定时间以内
            if(newOrder.getStartTime().getDate().compareTo(startTime.getDate())>=0 && newOrder.getEndTime().getDate().compareTo(endTime.getDate())<=0){
                //先查询一下同一时间段的场地是否已预定
                BmobQuery<Orders> orderQuery = new BmobQuery<Orders>();
                //初始化收益和下单当前用户
                newOrder.setProfit(0.0);
                newOrder.setCharge(0.0);
                MyUser user = MyUser.getCurrentUser(MyUser.class);
                newOrder.setUser(user);

                //查询出所有当前场地的订单
                orderQuery.addWhereEqualTo("type",newOrder.getType()).addWhereEqualTo("typeId",newOrder.getTypeId());
//            //查询订单中是否已经存在此时间段的订单，存在，提示不可预订，不存在，则预订
//            orderQuery.addWhereLessThan("startTime", orders.getEndTime()).addWhereGreaterThan("endTime", orders.getStartTime());
                //执行查询方法
                orderQuery.findObjects(new FindListener<Orders>() {
                    @Override
                    public void done(List<Orders> orders1, BmobException e) {
                        if (e == null) {
                            if(orders1.size()==0){
                                newOrder.save(new SaveListener<String>() {
                                    @Override
                                    public void done(String objectId,BmobException e) {
                                        if(e==null){
                                            ToastUtil.showToast(context,"下单成功！");
                                        }else{
                                            ToastUtil.showToast(context,"下单失败" + e.getErrorCode()+e.getMessage());
                                        }
                                    }
                                });
                            }
                            else {
                                int flag = 1;
                                for (Orders order :orders1){
                                    //如果已存在的订单开始时间大于新增订单的结束时间，或者已存在订单的结束时间小于新增订单的开始时间，否则，不可预订
                                    if(!(order.getStartTime().getDate().compareTo(newOrder.getEndTime().getDate())>0 || order.getEndTime().getDate().compareTo(newOrder.getStartTime().getDate())<0)){
                                        Log.i("MyTag","此时间段不可预订");
                                        ToastUtil.showToast(context,"此时间段不可预订");
                                        flag = -1;
                                        break;
                                    }
                                }
                                //如果不存在冲突的订单，则添加订单
                                if(flag == 1){
                                    newOrder.save(new SaveListener<String>() {
                                        @Override
                                        public void done(String objectId,BmobException e) {
                                            if(e==null){
                                                ToastUtil.showToast(context,"下单成功！");
                                            }else{
                                                ToastUtil.showToast(context,"下单失败" + e.getErrorCode()+e.getMessage());
                                            }
                                        }
                                    });
                                }
                            }
                            Log.i("MyTag","此时间段不可预订");
                            ToastUtil.showToast(context,"此时间段不可预订");

                        } else {
                            Log.i("MyTag","查询订单失败"+e.getErrorCode()+e.getMessage());
                            if (e.getErrorCode() == 9016) {
                                ToastUtil.showToast(context, "网络连接失败，请检查网络状况");
                            }

                        }
                    }
                });
            }
            else {
                ToastUtil.showToast(context,"此时间段不可预订");
            }
        }
        //直接添加订单
        else {
            newOrder.save(new SaveListener<String>() {
                @Override
                public void done(String objectId,BmobException e) {
                    if(e==null){
                        ToastUtil.showToast(context,"下单成功！");
                    }else{
                        ToastUtil.showToast(context,"下单失败" + e.getErrorCode()+e.getMessage());
                    }
                }
            });
        }
    }

    @Override
    public void deleteOrder(final Context context, String ObjectId) {
        Orders orders = new Orders();
        orders.setObjectId(ObjectId);
        orders.delete(new UpdateListener() {
            @Override
            public void done(BmobException e) {
                if(e==null){
                    Log.i("MyTag","删除场地订单成功");
                }else{
                    Log.i("MyTag","删除场地订单失败："+e.getMessage()+","+e.getErrorCode());
                    if (e.getErrorCode() == 9016) {
                        ToastUtil.showToast(context, "网络连接失败，请检查网络状况");
                    }
                }
            }
        });
    }

    //更新订单,state    1 下单（待付款） 2 付款（待使用） 3 使用（待评论） 4 售后  5 申请退款 6 退款（退款成功）  7 交易关闭，
    // 前端直接将操作以后的state传过来，比如用户付款操作，前端直接传 2 过来
    @Override
    public void updateOrder(final Context context, Orders orders, final int state) {
        //修改订单状态
        orders.setOderState(state);
        //如果用户申请退款，提示将扣取的费用，并不更新数据，如果用户确认，再调用此方法更新。
        if(state == 5) {
            //如果是比赛，取消只是更新参加人数，不扣费
            if (orders.getType() == 2) {
                MatchService matchService = new MatchService();
                matchService.getMatchById(context, orders.getTypeId());
                matchService.setOnGetMatchByIdDataListener(new MatchService.OnGetMatchByIdListener() {
                    @Override
                    public void getMatchById(Match matchs, boolean state) {
                        Match match = new Match();
                        match.setApplyNum(matchs.getApplyNum() - 1);
                        match.update(matchs.getObjectId(), new UpdateListener() {
                            @Override
                            public void done(BmobException e) {
                                if (e == null) {
                                    Log.i("MyTag", "比赛更新申请人数成功！");
                                } else {
                                    Log.i("MyTag", "比赛更新申请人数失败！" + e.getErrorCode() + e.getMessage());
                                }
                            }
                        });
                    }
                });
            }
            //场地和培训取消订单要收取手续费
            else {
                //取消订单收取的手续费
                double money = dealDate.caculateCharge2(orders);
                ToastUtil.showToast(context, "现在退订将会收取  " + money + "  元手续费");
                //从手续费中获取平台利益
                double charge = dealDate.caculateCharge1(money);
                orders.setCharge(charge);
                //从手续费中获取商户利益
                orders.setProfit(money - charge);
                //如果是培训，修改培训的申请报名人数
                if (orders.getType() == 1) {
                    TrainService trainService = new TrainService();
                    trainService.getTrainByid(context, orders.getTypeId());
                    trainService.setOnGetTrainDataByIdListener(new TrainService.OnGetTrainDataByIdListener() {
                        @Override
                        public void getTrainDataById(Trains trainses, boolean state) {
                            Trains trains = new Trains();
                            trains.setApplyNum(trainses.getApplyNum() - 1);
                            trains.update(trainses.getObjectId(), new UpdateListener() {
                                @Override
                                public void done(BmobException e) {
                                    if (e == null) {
                                        Log.i("MyTag", "培训更新申请人数成功！");
                                    } else {
                                        Log.i("MyTag", "培训更新申请人数失败！" + e.getErrorCode() + e.getMessage());
                                    }
                                }
                            });
                        }
                    });
                }
            }
        }
        //用户使用的时候
        else if (state == 3) {
            //平台收费
            double charge = dealDate.caculateCharge1(orders.getPayment());
            orders.setCharge(charge);
            orders.setProfit(orders.getPayment() - charge);
        }
        //更新订单
        orders.update(orders.getObjectId(), new UpdateListener() {
            @Override
            public void done(BmobException e) {
                if (e == null) {
                    Log.i("MyTag", "更新成功");
                    if (state == 2) {
                        ToastUtil.showToast(context, "付款成功！");
                    } else if (state == 3) {
                        ToastUtil.showToast(context, "交易成功！");

                    } else if (state == 4) {
                        ToastUtil.showToast(context, "评价成功！");
                    } else if (state == 6) {
                        ToastUtil.showToast(context, "退款成功！");
                    }
                } else {
                    Log.i("MyTag", "更新失败：" + e.getMessage() + "," + e.getErrorCode());
                    if (e.getErrorCode() == 9016) {
                        ToastUtil.showToast(context, "网络连接失败，请检查网络状况");
                    }
                }
            }
        });
    }

    @Override
    public void getPlaceOrders(final Context context, int skip) {
        BmobQuery<Orders> query = new BmobQuery<Orders>();
        query.setLimit(5);
        query.setSkip(skip);
        //执行查询方法
        query.findObjects(new FindListener<Orders>() {
            @Override
            public void done(List<Orders> object, BmobException e) {
                if(e==null){
                    ToastUtil.showToast(context,"查询成功：共"+object.size()+"条数据。");
                    if(mOnGetAllOrdersPlaceListener!=null){
                        mOnGetAllOrdersPlaceListener.getAllOrdersPlace(object);
                    }
                }else{
                    Log.i("MyTag","失败："+e.getMessage()+","+e.getErrorCode());
                    if (e.getErrorCode() == 9016) {
                        ToastUtil.showToast(context, "网络连接失败，请检查网络状况");
                    }
                }
            }
        });
    }

    //获取当前用户订单
    @Override
    public void getMyOrders(final Context context, int skip) {
        BmobQuery<Orders> query = new BmobQuery<Orders>();
        query.setLimit(5);
        query.setSkip(skip);
        query.include("user");
        MyUser myUser = MyUser.getCurrentUser(MyUser.class);
        query.addWhereEqualTo("user",myUser);
        //执行查询方法
        query.findObjects(new FindListener<Orders>() {
            @Override
            public void done(List<Orders> object, BmobException e) {
                if(e==null){
                    ToastUtil.showToast(context,"查询成功：共"+object.size()+"条数据。");
                    if(mOnGetMyOrdersPlaceListener!=null){
                        mOnGetMyOrdersPlaceListener.getMyOrdersPlace(object);
                    }
                }else{
                    Log.i("MyTag","失败："+e.getMessage()+","+e.getErrorCode());
                    if (e.getErrorCode() == 9016) {
                        ToastUtil.showToast(context, "网络连接失败，请检查网络状况");
                    }
                }
            }
        });
    }

    //获取当前用户的不同状态的订单  1 待付款，2 待使用，3 待评价 ，4 已失效
    @Override
    public void getByState(final Context context, int skip, int state) {
        BmobQuery<Orders> query = new BmobQuery<Orders>();
        query.setLimit(5);
        query.setSkip(skip);
        query.addWhereEqualTo("state",state);
        //执行查询方法
        query.findObjects(new FindListener<Orders>() {
            @Override
            public void done(List<Orders> object, BmobException e) {
                if(e==null){
                    ToastUtil.showToast(context,"查询成功：共"+object.size()+"条数据。");
                    if(mOnGetOrdersPlaceByStateListener!=null){
                        mOnGetOrdersPlaceByStateListener.getOrdersPlaceByState(object);
                    }
                }else{
                    Log.i("MyTag","失败："+e.getMessage()+","+e.getErrorCode());
                    if (e.getErrorCode() == 9016) {
                        ToastUtil.showToast(context, "网络连接失败，请检查网络状况");
                    }
                }
            }
        });
    }

    @Override
    public void getById(final Context context, String objectId) {
        BmobQuery<Orders> query = new BmobQuery<Orders>();
        query.getObject(objectId, new QueryListener<Orders>() {
            @Override
            public void done(Orders object, BmobException e) {
                if(e==null){
                    Log.i("MyTag","查询成功！");
                    if(mOnGetOneOrdersPlaceListener != null){
                        mOnGetOneOrdersPlaceListener.getOneOrdersPlace(object);
                    }
                }else{
                    Log.i("bmob","失败："+e.getMessage()+","+e.getErrorCode());
                    if (e.getErrorCode() == 9016) {
                        ToastUtil.showToast(context, "网络连接失败，请检查网络状况");
                    }
                }
            }

        });
    }

    public void setOnGetAllOrdersPlaceListener(OnGetAllOrdersPlaceListener onGetAllOrdersPlaceListener){
        mOnGetAllOrdersPlaceListener = onGetAllOrdersPlaceListener;
    }
    //获取全部场地订单
    public interface OnGetAllOrdersPlaceListener{
        void getAllOrdersPlace(List<Orders> orders);
    }

    public void setOnGetMyOrdersPlaceListener(OnGetMyOrdersPlaceListener onGetMyOrdersPlaceListener){
        mOnGetMyOrdersPlaceListener = onGetMyOrdersPlaceListener;
    }
    //获取当前用户的订单
    public interface OnGetMyOrdersPlaceListener{
        void getMyOrdersPlace(List<Orders> orders);
    }
    public void setOnGetOrdersPlaceByStateListener(OnGetOrdersPlaceByStateListener onGetOrdersPlaceByStateListener){
        mOnGetOrdersPlaceByStateListener = onGetOrdersPlaceByStateListener;
    }
    //根据订单状态获取场地订单，待付款，待使用
    public interface OnGetOrdersPlaceByStateListener{
        void getOrdersPlaceByState(List<Orders> orders);
    }
    public void setOnGetOneOrdersPlaceListener(OnGetOneOrdersPlaceListener onGetOneOrdersPlaceListener){
        mOnGetOneOrdersPlaceListener = onGetOneOrdersPlaceListener;
    }
    //根据id获取一条场地订单
    public interface OnGetOneOrdersPlaceListener{
        void getOneOrdersPlace(Orders orders);
    }

}
