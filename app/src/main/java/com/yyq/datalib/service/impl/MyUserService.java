package com.yyq.datalib.service.impl;
//package com.mj.datalib.service.impl;

import android.content.Context;
import android.util.Log;

import com.mj.datashow.utils.ToastUtil;
import com.yyq.datalib.javaBeans.MyUser;

import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.BmobSMS;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.datatype.BmobFile;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.LogInListener;
import cn.bmob.v3.listener.QueryListener;
import cn.bmob.v3.listener.SaveListener;
import cn.bmob.v3.listener.UpdateListener;
import cn.bmob.v3.listener.UploadBatchListener;

/**
 * Created by yangyouqin on 2017/11/28.
 */

public class MyUserService implements IMyUserService {
    private OnGetMyUserDataListener mOnGetMyUserDataListener;
    private OnGetRegsterStatusListener mOnGetRegsterStatusListener;
    private OnModifyUserNameListener mOnModifyUserNameListener;
    private OnModifyUserHeadListener mOnModifyUserHeadListener;
    private OnCheckPhoneNumListener mOnCheckPhoneNumListener;

    //用户注册
    @Override
    public void insert(final MyUser bu, final Context context) {
        //final MyUser myUser = new MyUser();

        bu.signUp(new SaveListener<MyUser>() {
            @Override
            public void done(MyUser s, BmobException e) {
                if (e == null) {
                    Log.i("MyTag", "注册成功！");
                    if (mOnGetRegsterStatusListener != null) {
                        mOnGetRegsterStatusListener.isRegster(-1);
                    }
                } else {
                    Log.i("MyTag", "注册失败！" + e);
                    if (e.getErrorCode() == 9016) {
                        ToastUtil.showToast(context, "网络连接失败，请检查网络状况");
                    } else if (mOnGetRegsterStatusListener != null) {
                        mOnGetRegsterStatusListener.isRegster(e.getErrorCode());
                    }
                }
            }
        });
    }

    @Override
    public void updateHeadImg(String picUrl, final Context context) {
        final MyUser myUser = new MyUser();
        final MyUser oldUser = MyUser.getCurrentUser(MyUser.class);
        final String[] filePaths = new String[1];
        filePaths[0] = picUrl;
        BmobFile.uploadBatch(filePaths, new UploadBatchListener() {

            @Override
            public void onSuccess(List<BmobFile> files, List<String> urls) {
                //1、files-上传完成后的BmobFile集合，是为了方便大家对其上传后的数据进行操作，例如你可以将该文件保存到表中
                //2、urls-上传文件的完整url地址
                if (urls.size() == filePaths.length) {//如果数量相等，则代表文件全部上传完成
                    //do something
                    myUser.setHeadImg(files.get(0));
                    myUser.update(oldUser.getObjectId(), new UpdateListener() {
                        @Override
                        public void done(BmobException e) {
                            if (e == null) {
                                Log.i("MyTag", "用户更新成功");
                                if (mOnModifyUserHeadListener != null) {
                                    mOnModifyUserHeadListener.isModify(-1);
                                }
                                //toast("更新用户信息成功");
                            } else {
                                Log.i("MyTag", "用户更新失败+错误码" + e.getErrorCode() + ",错误信息" + e.getMessage());
                                if (mOnModifyUserHeadListener != null) {
                                    mOnModifyUserHeadListener.isModify(e.getErrorCode());
                                }
                            }
                        }
                    });
                }
            }

            @Override
            public void onError(int statuscode, String errormsg) {
                Log.i("MyTag", "错误码" + statuscode + ",错误描述：" + errormsg);
                if (statuscode == 9016) {
                    ToastUtil.showToast(context, "网络连接失败，请检查网络状况");
                }
            }

            @Override
            public void onProgress(int curIndex, int curPercent, int total, int totalPercent) {
                //1、curIndex--表示当前第几个文件正在上传
                //2、curPercent--表示当前上传文件的进度值（百分比）
                //3、total--表示总的上传文件数
                //4、totalPercent--表示总的上传进度（百分比）
            }
        });
        //newUser.setEmail("xxx@163.com");
        //获取当前用户
        //BmobUser bmobUser = BmobUser.getCurrentUser(context);


    }

    //修改用户其他信息
    @Override
    public void update(final MyUser myUser, final Context context) {
        MyUser oldUser = MyUser.getCurrentUser(MyUser.class);
        myUser.update(oldUser.getObjectId(), new UpdateListener() {
            @Override
            public void done(BmobException e) {
                if (e == null) {
                    Log.i("MyTag", "用户更新成功");
                    if (mOnModifyUserNameListener != null) {
                        mOnModifyUserNameListener.isModify(-1);
                    }
                    //toast("更新用户信息成功");
                } else {
                    Log.i("MyTag", "用户更新失败+错误码" + e.getErrorCode() + ",错误信息" + e.getMessage());
                    if (e.getErrorCode() == 9016) {
                        ToastUtil.showToast(context, "网络连接失败，请检查网络状况");
                    } else if (mOnModifyUserNameListener != null) {
                        mOnModifyUserNameListener.isModify(e.getErrorCode());
                    }
                }
            }
        });
    }

    @Override
    public void loginByPhone(String phoneNum, String password, Integer type, final Context context) {
        if (type == 0) {
            BmobUser.loginByAccount(phoneNum, password, new LogInListener<MyUser>() {

                @Override
                public void done(MyUser user, BmobException e) {
                    boolean isMatch = false;
                    if (user != null) {
                        Log.i("MyTag", "用户登陆成功");
                        if (mOnGetMyUserDataListener != null) {
                            isMatch = true;
                            mOnGetMyUserDataListener.isMatch(-1);
                        }
                    } else {
                        if (e.getErrorCode() == 9016) {
                            ToastUtil.showToast(context, "网络连接失败，请检查网络状况");
                        } else if (mOnGetMyUserDataListener != null) {
                            isMatch = false;
                            mOnGetMyUserDataListener.isMatch(e.getErrorCode());
                        }
                        Log.i("MyTag", "用户登陆失败，错误码：" + e.getErrorCode() + "错误信息" + e.getMessage());
                    }
                }
            });
        } else if (type == 1) {
            BmobSMS.requestSMSCode(phoneNum, "模板名称", new QueryListener<Integer>() {

                @Override
                public void done(Integer smsId, BmobException ex) {
                    if (ex == null) {//验证码发送成功
                        Log.i("smile", "短信id：" + smsId);//用于后续的查询本次短信发送状态
                    }
                }
            });
        }
    }


    @Override
    public void getById(String id) {

    }

    @Override
    public void phoneIsExistence(final Context context, String phoneNum) {
        BmobQuery<MyUser> query = new BmobQuery<MyUser>();
        query.addWhereEqualTo("mobilePhoneNumber",phoneNum);
        query.findObjects(new FindListener<MyUser>() {
            @Override
            public void done(List<MyUser> object, BmobException e) {
                if(e==null){
                    if(object.size()!=0){
                        if(mOnCheckPhoneNumListener!=null){
                            mOnCheckPhoneNumListener.isChecked(true);
                        }
                    }
                    else {
                        mOnCheckPhoneNumListener.isChecked(false);
                    }
                }else{
                    mOnCheckPhoneNumListener.isChecked(false);
                    Log.i("MyTag","失败："+e.getMessage()+","+e.getErrorCode());
                    if (e.getErrorCode() == 9016) {
                        ToastUtil.showToast(context, "网络连接失败，请检查网络状况");
                    }
                }
            }
        });
    }

    //第三部：设置外部监听事件
    public void setOnGetMyUserDataListener(OnGetMyUserDataListener onGetMyUserDataListener) {
        mOnGetMyUserDataListener = onGetMyUserDataListener;
    }

    //第三部：设置外部监听事件
    public void setOnGetRegsterStatusListener(OnGetRegsterStatusListener onGetRegsterStatusListener) {
        mOnGetRegsterStatusListener = onGetRegsterStatusListener;
    }

    //第三部：设置外部监听事件
    public void setOnModifyUserNameListener(OnModifyUserNameListener onModifyUserNameListener) {
        mOnModifyUserNameListener = onModifyUserNameListener;
    }

    //第三部：设置外部监听事件
    public void setOnModifyUserHeadListener(OnModifyUserHeadListener onModifyUserHeadListener) {
        mOnModifyUserHeadListener = onModifyUserHeadListener;
    }

    //第一步：定义接口
    public interface OnGetMyUserDataListener {
        //是否登录成功
        void isMatch(int b);
    }

    //第一步：定义接口
    public interface OnGetRegsterStatusListener {
        //是否注册成功
        void isRegster(int r);
    }

    public interface OnModifyUserNameListener {
        //修改名字
        void isModify(int m);
    }

    public interface OnModifyUserHeadListener {
        //修改头像
        void isModify(int m);
    }

    public void setOnCheckPhoneNumListener(OnCheckPhoneNumListener onCheckPhoneNumListener) {
        mOnCheckPhoneNumListener = onCheckPhoneNumListener;
    }
    public interface OnCheckPhoneNumListener {
        //修改头像
        void isChecked(boolean c);
    }

}
