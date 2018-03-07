package com.yyq.datalib.service.impl;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.BmobBatch;
import cn.bmob.v3.BmobObject;
import cn.bmob.v3.datatype.BatchResult;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.QueryListListener;

/**
 * Created by YQ on 2017/12/3.
 */

public class HotSearchService implements IHotSearchService {
    @Override
    public void gerHotSearch() {
        List<BmobObject> hotSearches = new ArrayList<BmobObject>();

        new BmobBatch().insertBatch(hotSearches).doBatch(new QueryListListener<BatchResult>() {
            @Override
            public void done(List<BatchResult> o, BmobException e) {
                if(e==null){
                    for(int i=0;i<o.size();i++){
                        BatchResult result = o.get(i);
                        BmobException ex =result.getError();
                        if(ex==null){
                            Log.i("MyTag","第"+i+"个数据批量添加成功："+result.getCreatedAt()+","+result.getObjectId()+","+result.getUpdatedAt());
                        }else{
                            Log.i("MyTag","第"+i+"个数据批量添加失败："+ex.getMessage()+","+ex.getErrorCode());
                        }
                    }
                }else{
                    Log.i("bmob","失败："+e.getMessage()+","+e.getErrorCode());
                }
            }
        });
    }
}
