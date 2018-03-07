package com.yyq.datalib.service.impl;

import android.util.Log;

import com.yyq.datalib.javaBeans.HotProject;

import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.datatype.BmobQueryResult;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SQLQueryListener;

/**
 * Created by YQ on 2017/12/3.
 */

public class HotProjectsService implements IHotProjectsService {

    OnGetHotProjectDataListener mOnGetHotProjectDataListener;

    @Override
    public void getHotProjects() {
        String bql ="select * from HotProject";//查询所有的记录
        new BmobQuery<HotProject>().doSQLQuery(bql,new SQLQueryListener<HotProject>(){
            @Override
            public void done(BmobQueryResult<HotProject> result, BmobException e) {
                if(e ==null){
                    List<HotProject> list = (List<HotProject>) result.getResults();
                    if(list!=null && list.size()>0){
                        Log.i("smile", "查询成功，有数据返回");
                        list.get(0).getImg().getUrl();
                        if(mOnGetHotProjectDataListener!=null){
                            mOnGetHotProjectDataListener.getData(list);
                        }
                    }else{
                        Log.i("smile", "查询成功，无数据返回");
                    }
                }else{
                    Log.i("smile", "错误码："+e.getErrorCode()+"，错误描述："+e.getMessage());
                }
            }
        });
    }
    public void getByName(){
        String bql ="select * from HotProject where name = '篮球1'";//查询所有的记录
        new BmobQuery<HotProject>().doSQLQuery(bql,new SQLQueryListener<HotProject>(){
            @Override
            public void done(BmobQueryResult<HotProject> result, BmobException e) {
                if(e ==null){
                    List<HotProject> list = (List<HotProject>) result.getResults();
                    if(list!=null && list.size()>0){
                        Log.i("smile", "查询成功，有数据返回");
                        Log.i("MyTag",list.get(0).getName());
                        if(mOnGetHotProjectDataListener!=null){
                            mOnGetHotProjectDataListener.getData(list);
                        }
                    }else{
                        Log.i("smile", "查询成功，无数据返回");
                    }
                }else{
                    Log.i("smile", "错误码："+e.getErrorCode()+"，错误描述："+e.getMessage());
                }
            }
        });
    }

    //第三部：设置外部监听事件
    public void setOnGetHotProjectDataListener(HotProjectsService.OnGetHotProjectDataListener onGetHotProjectDataListener) {
        mOnGetHotProjectDataListener = onGetHotProjectDataListener;
    }
    //第一步：定义接口
    public interface OnGetHotProjectDataListener {
        void getData(List<HotProject> hotProjectlist);
    }
}
