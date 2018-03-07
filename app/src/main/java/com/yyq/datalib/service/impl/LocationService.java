package com.yyq.datalib.service.impl;

import android.content.Context;

import com.mj.datashow.utils.ToastUtil;
import com.yyq.datalib.javaBeans.Location;

import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.SaveListener;

/**
 * Created by YQ on 2017/12/24.
 */

public class LocationService implements ILocationService {

    //根据id获取
    OnGetLocationDataByIdListener mOnGetLocationDataByIdListener;

    //根据经纬度获取地址
    OnGetLocationDataByLongititudeAndLatitudeListener mOnGetLocationDataByLongititudeAndLatitudeListener;

    //TODO:根据地址id获取地址信息
    @Override
    public void getById(final Context context, String id) {
        BmobQuery<Location> query = new BmobQuery<Location>();
        query.addWhereEqualTo("ObjectId",id);
        query.setLimit(5);
        //执行查询方法
        query.findObjects(new FindListener<Location>() {
            @Override
            public void done(List<Location> object, BmobException e) {
                if(e==null){
                    ToastUtil.showToast(context,"查询成功：共"+object.size()+"条数据。");
                    if(mOnGetLocationDataByIdListener!=null){
                        mOnGetLocationDataByIdListener.getLocationDataById(object.get(0),true);
                    }
                }else{
                    mOnGetLocationDataByIdListener.getLocationDataById(null,false);
                    ToastUtil.showToast(context,"失败："+e.getMessage()+","+e.getErrorCode());
                }
            }
        });
    }


    //TODO:根据经纬度获取地址
    @Override
    public void getByLongititudeAndLatitude(final Context context, double longititude, double latitude) {
        BmobQuery<Location> query = new BmobQuery<Location>();
        query.addWhereEqualTo("longititude",longititude).addWhereEqualTo("latitude",latitude);
        query.setLimit(5);
        //执行查询方法
        query.findObjects(new FindListener<Location>() {
            @Override
            public void done(List<Location> object, BmobException e) {
                if(e==null){
                    ToastUtil.showToast(context,"查询成功：共"+object.size()+"条数据。");
                    if(mOnGetLocationDataByIdListener!=null){
                        mOnGetLocationDataByIdListener.getLocationDataById(object.get(0),true);
                    }
                }else{
                    mOnGetLocationDataByIdListener.getLocationDataById(null,false);
                    ToastUtil.showToast(context,"失败："+e.getMessage()+","+e.getErrorCode());
                }
            }
        });
    }


    //TODO:插入地址
    @Override
    public void insert(final Context context, Location location) {
        location.save(new SaveListener<String>() {

            @Override
            public void done(String objectId, BmobException e) {
                if(e==null){
                    ToastUtil.showToast(context,"添加地址成功！");
                }else{
                    ToastUtil.showToast(context,"添加地址失败！");
                }
            }
        });
    }

    //TODO:根据id
    public void setOnGetLocationDataByIdListener(OnGetLocationDataByIdListener onGetLocationDataByIdListener){
        mOnGetLocationDataByIdListener = onGetLocationDataByIdListener;
    }
    //TODO:接口   根据id
    public interface OnGetLocationDataByIdListener{
        void getLocationDataById(Location Locations, boolean state);
    }

    //TODO:根据id
    public void setOnGetLocationDataByLongititudeAndLatitudeListener(OnGetLocationDataByLongititudeAndLatitudeListener onGetLocationDataByLongititudeAndLatitudeListener){
        mOnGetLocationDataByLongititudeAndLatitudeListener = onGetLocationDataByLongititudeAndLatitudeListener;
    }
    //TODO:接口   根据id
    public interface OnGetLocationDataByLongititudeAndLatitudeListener{
        void getLocationDataByLongititudeAndLatitude(Location Locations, boolean state);
    }
}
