package com.yyq.datalib.service.impl;

import android.content.Context;
import android.util.Log;

import com.mj.datashow.utils.ToastUtil;
import com.yyq.datalib.javaBeans.MyUser;
import com.yyq.datalib.javaBeans.Trains;
import com.yyq.datalib.models.TrainModel;
import com.yyq.datalib.query.DealDate;
import com.yyq.datalib.service.ITrainService;

import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.datatype.BmobFile;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.QueryListener;
import cn.bmob.v3.listener.SaveListener;
import cn.bmob.v3.listener.UploadBatchListener;

/**
 * Created by YQ on 2017/12/20.
 */

public class TrainService implements ITrainService {
    
    //获取培训全部数据
    OnGetAllTrainListener mOnGetAllTrainListener;
    //TODO:根据类型查询培训,返回的数据为 TrainModel，在models包里
    OnGetTrainDataByTypeListener mOnGetTrainDataByTypeListener;
    //TODO:根据培训id查询,返回的数据为 Trains，在javaBeans包里
    OnGetTrainDataByIdListener mOnGetTrainDataByIdListener;

    //发送数据,成功返回true
    OnSendListener mOnSendListener;
    
    //TODO:查询全部培训数据
    @Override
    public void getAllTrain(final Context context,int skip) {
        BmobQuery<Trains> query = new BmobQuery<Trains>();
        query.setLimit(5);
        query.setSkip(skip);
        query.addWhereEqualTo("state",1);
        //执行查询方法
        query.findObjects(new FindListener<Trains>() {
            @Override
            public void done(List<Trains> object, BmobException e) {
                if(e==null){
                    ToastUtil.showToast(context,"查询成功：共"+object.size()+"条数据。");
                    for (Trains trains : object) {
                        if(trains.getPersonNum()>trains.getApplyNum())
                            trains.setAvailable("可报名");
                        else
                            trains.setAvailable("满员");
                    }
                    if(mOnGetAllTrainListener!=null){
                        mOnGetAllTrainListener.getAllTrain(object,true);
                    }
                }else{
                    ToastUtil.showToast(context,"失败："+e.getMessage()+","+e.getErrorCode());
                    if (e.getErrorCode() == 9016) {
                        ToastUtil.showToast(context, "网络连接失败，请检查网络状况");
                    }
                }
            }
        });
    }

    //TODO:根据类型查询培训,返回的数据为 TrainModel，在models包里
    @Override
    public void getTrainByType(final Context context, String type,int skip) {
        BmobQuery<Trains> query = new BmobQuery<Trains>();
        query.addWhereEqualTo("trainType",type);
        query.include("user.headImg");
        query.setLimit(5);
        query.setSkip(skip);
        query.addWhereEqualTo("state",1);
        //执行查询方法
        query.findObjects(new FindListener<Trains>() {
            @Override
            public void done(List<Trains> object, BmobException e) {
                if(e==null){
                    ToastUtil.showToast(context,"查询成功：共"+object.size()+"条数据。");
                    DealDate dealDate = new DealDate();
                    Log.i("MyTag",object.get(0).toString());
                    if(mOnGetTrainDataByTypeListener!=null){
                        mOnGetTrainDataByTypeListener.getTrainDataByType(dealDate.dealTrainPage(object),true);
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

    //TODO:根据培训id查询,返回的数据为 Trains，在javaBeans包里
    @Override
    public void getTrainByid(final Context context, String id) {
        BmobQuery<Trains> bmobQuery = new BmobQuery<Trains>();
        bmobQuery.addWhereEqualTo("state",1);
        bmobQuery.getObject(id, new QueryListener<Trains>() {
            @Override
            public void done(Trains object, BmobException e) {
                if(e==null){
                    ToastUtil.showToast(context,"查询成功：");
                    if(object.getPersonNum()>object.getApplyNum())
                        object.setAvailable("可报名");
                    else
                        object.setAvailable("满员");
                        if(mOnGetTrainDataByIdListener!=null){
                            mOnGetTrainDataByIdListener.getTrainDataById(object,true);
                        }
                }else{
                    ToastUtil.showToast(context,"失败："+e.getMessage()+","+e.getErrorCode());
                    if (e.getErrorCode() == 9016) {
                        ToastUtil.showToast(context, "网络连接失败，请检查网络状况");
                    }
                }
            }
        });
    }

    //TODO:添加培训信息
    @Override
    public void insertTrain(final Context context, final Trains trains, List<String> trainImgsUrl) {
        final String[] filePaths = new String[trainImgsUrl.size()];
        for (int i = 0; i <trainImgsUrl.size(); i++) {
            filePaths[i] = trainImgsUrl.get(i);
        }
        BmobFile.uploadBatch(filePaths, new UploadBatchListener() {

            @Override
            public void onSuccess(List<BmobFile> files, List<String> urls) {
                //1、files-上传完成后的BmobFile集合，是为了方便大家对其上传后的数据进行操作，例如你可以将该文件保存到表中
                //2、urls-上传文件的完整url地址
                if(urls.size()==filePaths.length){//如果数量相等，则代表文件全部上传完成
                    //do something
                    trains.setTrainImg1(files);
                    trains.setState(1);
                    trains.setAvailable("可报名");
                    trains.setUser(MyUser.getCurrentUser(MyUser.class));
                    trains.save(new SaveListener<String>() {

                        @Override
                        public void done(String objectId, BmobException e) {
                            if(e==null){
                                ToastUtil.showToast(context,"发布培训信息成功！");
                                if(mOnSendListener!=null)
                                    mOnSendListener.send(true);
                            }else{
                                ToastUtil.showToast(context,"发布培训信息失败！");
                                mOnSendListener.send(false);
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

    //TODO:发送数据
    public void setOnSendDataListener(OnSendListener onSendListener){
        mOnSendListener = onSendListener;
    }
    //TODO:接口   发送数据
    public interface OnSendListener{
        void send(boolean isSend);
    }
    //TODO:获取全部培训数据
    public void setOnGetAllTrainDataListener(OnGetAllTrainListener onGetAllTrainListener){
        mOnGetAllTrainListener = onGetAllTrainListener;
    }
    //TODO:接口   获取全部培训数据
    public interface OnGetAllTrainListener{
        void getAllTrain(List<Trains> trainses, boolean state);
    }

    //TODO:根据类型查询 篮球，足球，游泳等
    public void setOnGetTrainDataByTypeListener(OnGetTrainDataByTypeListener onGetTrainDataByTypeListener){
        mOnGetTrainDataByTypeListener = onGetTrainDataByTypeListener;
    }
    //TODO:接口   根据类型查询 篮球，足球，游泳等
    public interface OnGetTrainDataByTypeListener{
        void getTrainDataByType(List<TrainModel> trainses, boolean state);
    }

    //TODO:根据类型查询 篮球，足球，游泳等
    public void setOnGetTrainDataByIdListener(OnGetTrainDataByIdListener onGetTrainDataByIdListener){
        mOnGetTrainDataByIdListener = onGetTrainDataByIdListener;
    }
    //TODO:接口   根据类型查询 篮球，足球，游泳等
    public interface OnGetTrainDataByIdListener{
        void getTrainDataById(Trains trainses, boolean state);
    }
}
