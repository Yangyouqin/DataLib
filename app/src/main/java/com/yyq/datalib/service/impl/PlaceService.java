package com.yyq.datalib.service.impl;

import android.content.Context;
import android.util.Log;

import com.mj.datashow.utils.ToastUtil;
import com.yyq.datalib.javaBeans.MyUser;
import com.yyq.datalib.javaBeans.Place;
import com.yyq.datalib.service.IPlaceService;

import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.datatype.BmobFile;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.SaveListener;
import cn.bmob.v3.listener.UpdateListener;
import cn.bmob.v3.listener.UploadBatchListener;


/**
 * Created by YQ on 2017/12/14.
 */

public class PlaceService implements IPlaceService {
    //获取场地信息监听
    OnGetAllPlaceDataListener mOnGetAllPlaceDataListener;
    //根据类型  篮球，足球等获取
    OnGetPlaceDataByTypeListener mOnGetPlaceDataByTypeListener;
    //发布场地信息
    OnSendListener mOnSendListener;

    //TODO:获取场地信息，每次取5条
    @Override
    public void getPlaces(final Context context,int skip) {
        BmobQuery<Place> query = new BmobQuery<Place>();
        query.order("-createdAt");
//返回50条数据，如果不加上这条语句，默认返回10条数据
        int count = 5;
        query.setLimit(count);
        query.setSkip(skip);
        query.addWhereEqualTo("state",1);
//执行查询方法
        query.findObjects(new FindListener<Place>() {
            @Override
            public void done(List<Place> object, BmobException e) {
                if(e==null){
                    ToastUtil.showToast(context,"查询成功：共"+object.size()+"条数据。");
                    if(mOnGetAllPlaceDataListener!=null){
                        mOnGetAllPlaceDataListener.getAllPlaceData(object,true);
                    }
                }else{
                    Log.i("MyTag","失败："+e.getMessage()+","+e.getErrorCode());
                    ToastUtil.showToast(context,"失败："+e.getMessage()+","+e.getErrorCode());
                    if (e.getErrorCode() == 9016) {
                        ToastUtil.showToast(context, "网络连接失败，请检查网络状况");
                    }
                }
            }
        });
    }
    //TODO:分类型查询场地信息，篮球，足球，羽毛球等
    @Override
    public void getPlacesByType(final Context context, String type,int skip) {
        BmobQuery<Place> query = new BmobQuery<Place>();
        query.addWhereEqualTo("placeType",type);
        query.setLimit(5);
        query.addWhereEqualTo("state",1);
        //执行查询方法
        query.findObjects(new FindListener<Place>() {
            @Override
            public void done(List<Place> object, BmobException e) {
                if(e==null){
                    ToastUtil.showToast(context,"查询成功：共"+object.size()+"条数据。");
                    if(mOnGetPlaceDataByTypeListener!=null){
                        mOnGetPlaceDataByTypeListener.getPlaceDataByType(object,true);
                    }
                    Log.i("MyTag","查询成功：共"+object.size()+"条数据。");
                }else{
                    Log.i("MyTag","失败："+e.getMessage()+","+e.getErrorCode());
                    ToastUtil.showToast(context,"失败："+e.getMessage()+","+e.getErrorCode());
                    if (e.getErrorCode() == 9016) {
                        ToastUtil.showToast(context, "网络连接失败，请检查网络状况");
                    }

                }
            }
        });
    }


    //TODO:添加场地信息
    @Override
    public void insertPlace(final Context context, final Place places, List<String>placeImgsUrl) {
        final String[] filePaths = new String[placeImgsUrl.size()];
        for (int i = 0; i <placeImgsUrl.size(); i++) {
            filePaths[i] = placeImgsUrl.get(i);
        }
        BmobFile.uploadBatch(filePaths, new UploadBatchListener() {

            @Override
            public void onSuccess(List<BmobFile> files, List<String> urls) {
                //1、files-上传完成后的BmobFile集合，是为了方便大家对其上传后的数据进行操作，例如你可以将该文件保存到表中
                //2、urls-上传文件的完整url地址
                if(urls.size()==filePaths.length){//如果数量相等，则代表文件全部上传完成
                    //do something
                    places.setPlaceImg1(files);
                    places.setUser(MyUser.getCurrentUser(MyUser.class));
                    places.setState(1);
                    places.setAvailable(true);
                    places.save(new SaveListener<String>() {

                        @Override
                        public void done(String objectId, BmobException e) {
                            if(e==null){
                                ToastUtil.showToast(context,"发布场地信息成功！");
                                if(mOnSendListener!=null){
                                    mOnSendListener.Send(1);
                                }
                            }else{
                                ToastUtil.showToast(context,"发布场地信息失败！");
                                if (e.getErrorCode() == 9016) {
                                    ToastUtil.showToast(context, "网络连接失败，请检查网络状况");
                                }
                            }
                        }
                    });
                }
            }

            @Override
            public void onError(int statuscode, String errormsg) {
                ToastUtil.showToast(context,"错误码"+statuscode +",错误描述："+errormsg);
            }

            @Override
            public void onProgress(int curIndex, int curPercent, int total,int totalPercent) {
                //1、curIndex--表示当前第几个文件正在上传
                //2、curPercent--表示当前上传文件的进度值（百分比）
                //3、total--表示总的上传文件数
                //4、totalPercent--表示总的上传进度（百分比）
            }
        });
    }

    //TODO:更新场地是否可利用状态
    @Override
    public void updatePlace(final Context context, Place oldPlace, boolean available) {
        oldPlace.setAvailable(available);
        oldPlace.update(oldPlace.getObjectId(), new UpdateListener() {
            @Override
            public void done(BmobException e) {
                if (e == null) {
                    ToastUtil.showToast(context,"更新状态成功！");
                } else {
                    ToastUtil.showToast(context,"更新失败+错误码" + e.getErrorCode() + ",错误信息" + e.getMessage());
                    if (e.getErrorCode() == 9016) {
                        ToastUtil.showToast(context, "网络连接失败，请检查网络状况");
                    }
                }
            }
        });
    }

    //TODO:根据类型查询 篮球，足球，游泳等
    public void setOnGetPlaceDataByTypeListener(OnGetPlaceDataByTypeListener onGetPlaceDataByTypeListener){
        mOnGetPlaceDataByTypeListener = onGetPlaceDataByTypeListener;
    }
    //TODO:接口   根据类型查询 篮球，足球，游泳等
    public interface OnGetPlaceDataByTypeListener{
        void getPlaceDataByType(List<Place> places, boolean state);
    }

    //TODO:获取所有场地信息监听，第一参数表示查询结果，第二个表示查询状态
    public void setOnGetAllPlaceDataListener(OnGetAllPlaceDataListener onGetAllPlaceDataListener){
        mOnGetAllPlaceDataListener = onGetAllPlaceDataListener;
    }
    //TODO:接口   获取所有场地信息监听，第一参数表示查询结果，第二个表示查询状态
    public interface OnGetAllPlaceDataListener{
        void getAllPlaceData(List<Place> places, boolean state);
    }

    //TODO:发布场地监听
    public void setOnSendListener(OnSendListener onSendListener){
        mOnSendListener = onSendListener;
    }
    //TODO:接口    发布场地信息
    public interface OnSendListener{
        void Send(int code);
    }
}
