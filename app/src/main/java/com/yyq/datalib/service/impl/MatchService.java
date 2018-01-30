package com.yyq.datalib.service.impl;

import android.content.Context;
import android.util.Log;

import com.mj.datashow.utils.ToastUtil;
import com.yyq.datalib.javaBeans.Match;
import com.yyq.datalib.javaBeans.MyUser;
import com.yyq.datalib.models.MatchModel;
import com.yyq.datalib.query.DealDate;
import com.yyq.datalib.service.IMatchService;

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

public class MatchService implements IMatchService {

    //获取培训全部数据
    OnGetAllMatchListener mOnGetAllMatchListener;
    //根据类型  篮球，足球等获取
    //TODO:根据类型查询培训,返回的数据为 MatchModel，在models包里
    OnGetMatchDataByTypeListener mOnGetMatchDataByTypeListener;
    //根据id查询比赛
    //TODO:根据id 查询数据 ,返回的数据为 Match，在javaBeans包里
    OnGetMatchByIdListener mOnGetMatchByIdListener;

    //发送是否成功回调方法
    OnSendListener mOnSendListener;

    //TODO:查询全部培训数据
    @Override
    public void getAllMatch(final Context context,int skip) {
        BmobQuery<Match> query = new BmobQuery<Match>();
        query.setLimit(5);
        query.setSkip(skip);
        query.addWhereEqualTo("state",1);
        //执行查询方法
        query.findObjects(new FindListener<Match>() {
            @Override
            public void done(List<Match> object, BmobException e) {
                if(e==null){
                    ToastUtil.showToast(context,"查询成功：共"+object.size()+"条数据。");
                    if(mOnGetAllMatchListener!=null){
                        mOnGetAllMatchListener.getAllMatch(object,true);
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

    //TODO:根据类型查询培训,返回的数据为 MatchModel，在models包里
    @Override
    public void getMatchByType(final Context context, String type,int skip) {
        BmobQuery<Match> query = new BmobQuery<Match>();
        query.addWhereEqualTo("matchType",type).addWhereEqualTo("state",1);
        query.setLimit(5);
        query.setSkip(skip);
        //执行查询方法
        query.findObjects(new FindListener<Match>() {
            @Override
            public void done(List<Match> object, BmobException e) {
                if(e==null){
                    ToastUtil.showToast(context,"查询成功：共"+object.size()+"条数据。");
                   Log.i("MyTag","查询成功：共"+object.size()+"条数据。");
                    DealDate dealDate = new DealDate();
                    if(mOnGetMatchDataByTypeListener!=null){
                        mOnGetMatchDataByTypeListener.getMatchDataByType(dealDate.dealMatchPage(object),true);
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

    //TODO:添加培训信息,需要传入一个对象，和那些图片的地址
    @Override
    public void insertMatch(final Context context, final Match match, String pics) {
        final String[] filePaths = new String[1];
            filePaths[0] = pics;
        BmobFile.uploadBatch(filePaths, new UploadBatchListener() {

            @Override
            public void onSuccess(List<BmobFile> files, List<String> urls) {
                //1、files-上传完成后的BmobFile集合，是为了方便大家对其上传后的数据进行操作，例如你可以将该文件保存到表中
                //2、urls-上传文件的完整url地址
                if(urls.size()==filePaths.length){//如果数量相等，则代表文件全部上传完成
                    //do something
                    match.setMatchImg(files.get(0));
                    match.setAvailable("可报名");
                    match.setState(1);
                    match.setUser(MyUser.getCurrentUser(MyUser.class));
                    match.save(new SaveListener<String>() {

                        @Override
                        public void done(String objectId, BmobException e) {
                            if(e==null){
                                ToastUtil.showToast(context,"发布比赛信息成功！");
                                if(mOnSendListener!=null)
                                    mOnSendListener.send(true);
                            }else{
                                ToastUtil.showToast(context,"发布比赛信息失败！");
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
    //TODO:根据id 查询数据 ,返回的数据为 Match，在javaBeans包里
    @Override
    public void getMatchById(final Context context, String objectId) {
        BmobQuery<Match> bmobQuery = new BmobQuery<Match>();
        bmobQuery.addWhereEqualTo("state",1);
        bmobQuery.getObject(objectId, new QueryListener<Match>() {
            @Override
            public void done(Match object, BmobException e) {
                if(e==null){
                    ToastUtil.showToast(context,"查询成功：");
                    if(object.getPersonNum()>object.getApplyNum())
                        object.setAvailable("可报名");
                    else
                        object.setAvailable("满员");
                    if(mOnGetMatchByIdListener!=null){
                        mOnGetMatchByIdListener.getMatchById(object,true);
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

    //TODO:发送数据
    public void setOnSendDataListener(OnSendListener onSendListener){
        mOnSendListener = onSendListener;
    }
    //TODO:接口   发送数据
    public interface OnSendListener{
        void send(boolean isSend);
    }
    //TODO:根据id获取比赛数据
    public void setOnGetMatchByIdDataListener(OnGetMatchByIdListener onGetMatchByIdListener){
        mOnGetMatchByIdListener = onGetMatchByIdListener;
    }
    //TODO:接口   根据id获取比赛数据
    public interface OnGetMatchByIdListener{
        void getMatchById(Match matchs, boolean state);
    }

    //TODO:获取全部比赛数据
    public void setOnGetAllMatchDataListener(OnGetAllMatchListener onGetAllMatchListener){
        mOnGetAllMatchListener = onGetAllMatchListener;
    }
    //TODO:接口   获取全部比赛数据
    public interface OnGetAllMatchListener{
        void getAllMatch(List<Match> Matchs, boolean state);
    }

    //TODO:根据类型查询 篮球，足球，游泳等
    public void setOnGetMatchDataByTypeListener(OnGetMatchDataByTypeListener onGetMatchDataByTypeListener){
        mOnGetMatchDataByTypeListener = onGetMatchDataByTypeListener;
    }
    //TODO:接口   根据类型查询 篮球，足球，游泳等
    public interface OnGetMatchDataByTypeListener{
        void getMatchDataByType(List<MatchModel> matchs, boolean state);
    }
}
