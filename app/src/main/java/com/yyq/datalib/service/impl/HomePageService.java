package com.yyq.datalib.service.impl;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.yyq.datalib.javaBeans.HomePage;

import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.datatype.BmobFile;
import cn.bmob.v3.datatype.BmobQueryResult;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SQLQueryListener;
import cn.bmob.v3.listener.SaveListener;
import cn.bmob.v3.listener.UploadBatchListener;

/**
 * Created by YQ on 2017/12/3.
 */

public class HomePageService implements IHomePageService {

    OnGetHomePageDataListener mOnGetHomePageDataListener;
    private Context context;

    public HomePageService(Context context){
        this.context = context;
        getHomePage();
    }
    @Override
    public void getHomePage() {
        String bql ="select * from HomePage";//查询所有的记录
        new BmobQuery<HomePage>().doSQLQuery(bql,new SQLQueryListener<HomePage>(){
            @Override
            public void done(BmobQueryResult<HomePage> result, BmobException e) {
                if(e ==null){
                    List<HomePage> list = (List<HomePage>) result.getResults();
                    if(list!=null && list.size()>0){
                        Log.i("smile", "查询成功，有数据返回");
                        if(mOnGetHomePageDataListener!=null){
                            mOnGetHomePageDataListener.getData(list);
                        }
                    }else{
                        Toast.makeText(context,"查询成功，无数据返回",Toast.LENGTH_LONG).show();
                        Log.i("smile", "查询成功，无数据返回");
                    }
                }else{
                    Toast.makeText(context,"查询失败"+e.getMessage(),Toast.LENGTH_LONG).show();
                    Log.i("smile", "错误码："+e.getErrorCode()+"，错误描述："+e.getMessage());
                }
            }
        });
            }

    @Override
    public void insertHomePage() {
        final HomePage homePage = new HomePage();
        homePage.setHomepageId("hpage00001");
        homePage.setAddressId("0101");
        homePage.setAddressName("郫都红光镇");
        List<String> list = new ArrayList<>();
        list.add("游泳馆");
        list.add("体育馆");
        list.add("健身房");
        homePage.setSearchEdit(list);
        List<BmobFile> bmobFiles = new ArrayList<>();
        String picPath1 = "/mnt/sdcard/droid4xshare/kite.png";
        String picPath2 = "/mnt/sdcard/droid4xshare/add.png";
        final String[] filePaths = new String[2];
        filePaths[0] = picPath1;
        filePaths[1] = picPath2;
        BmobFile.uploadBatch(filePaths, new UploadBatchListener() {

            @Override
            public void onSuccess(List<BmobFile> files, List<String> urls) {
                //1、files-上传完成后的BmobFile集合，是为了方便大家对其上传后的数据进行操作，例如你可以将该文件保存到表中
                //2、urls-上传文件的完整url地址
                if(urls.size()==filePaths.length){//如果数量相等，则代表文件全部上传完成
                    //do something
                    homePage.setCgjx(files);
                    homePage.save(new SaveListener<String>() {

                        @Override
                        public void done(String objectId, BmobException e) {
                            if(e==null){
                                Log.i("MyTag","创建数据成功：" + objectId);
                            }else{
                                Log.i("bmob","失败："+e.getMessage()+","+e.getErrorCode());
                            }
                        }
                    });
                }
            }

            @Override
            public void onError(int statuscode, String errormsg) {
                Log.i("MyTag","错误码"+statuscode +",错误描述："+errormsg);
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


    //第三部：设置外部监听事件
    public void setOnGetHomePageDataListener(HomePageService.OnGetHomePageDataListener onGetHomePageDataListener) {
        mOnGetHomePageDataListener = onGetHomePageDataListener;
    }
    //第一步：定义接口
    public interface OnGetHomePageDataListener {
        void getData(List<HomePage> homePagelist);
    }

}
