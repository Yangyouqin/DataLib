package com.yyq.datalib.service.impl;

import android.content.Context;
import android.util.Log;

import com.mj.datashow.utils.ToastUtil;
import com.yyq.datalib.javaBeans.CircleMessage;
import com.yyq.datalib.javaBeans.Comment;
import com.yyq.datalib.javaBeans.MakeDate;
import com.yyq.datalib.javaBeans.MakeDateInf;
import com.yyq.datalib.javaBeans.MyUser;
import com.yyq.datalib.models.CircleMessageModel;
import com.yyq.datalib.models.MakeDateModel;
import com.yyq.datalib.query.DealDate;
import com.yyq.datalib.service.ICircleMessageService;

import java.io.File;
import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.datatype.BmobFile;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.QueryListener;
import cn.bmob.v3.listener.SaveListener;
import cn.bmob.v3.listener.UpdateListener;
import cn.bmob.v3.listener.UploadBatchListener;
import cn.bmob.v3.listener.UploadFileListener;

/**
 * Created by yangyouqin on 2018/1/15.
 */

public class CircleMessageService implements ICircleMessageService {

    //获取全部动态，用MessageModel来接收
    //TODO:这里回调函数返回的不是集合，。
    CircleMessageService.OnGetAllMessageListener mOnGetAllMessageListener;

    //获取一个动态的评论，后台调用，界面不需要管这个回调
    OnGetCommentsListener mOnGetCommentsListener;

    //获取一条动态及评论
    OnGetOneMessageListener mOnGetOneMessageListener;

    //获取全部约球
    OnGetAllMakeDateListener mOnGetAllMakeDateListener;

    //获取一条约球详情
    OnGetOneMakeDateListener mOnGetOneMakeDateListener;

    //TODO:注意调用的时候是一次循环的方式返回5调数据，skip需要传5的倍数。
    @Override
    public void getAllMessage(final Context context, int skip) {
        final CircleMessageModel[] list = {new CircleMessageModel()};
        BmobQuery<CircleMessage> query = new BmobQuery<CircleMessage>();
        query.order("-createdAt");
        query.setLimit(5);
        query.setSkip(skip);
        query.include("user");
        //执行查询方法
        query.findObjects(new FindListener<CircleMessage>() {
            @Override
            public void done(final List<CircleMessage> messageList, BmobException e) {
                if (e == null) {
                    final int[] i = {0};
                    final boolean[] flag = {true};
                    for (final CircleMessage c : messageList) {
                        BmobQuery<Comment> commentquery = new BmobQuery<Comment>();
//                        commentquery.include("circleMessage");
                        commentquery.include("user");
                        commentquery.addWhereEqualTo("circleMessageId", c.getObjectId());
                        //执行查询方法
                        commentquery.findObjects(new FindListener<Comment>() {
                            @Override
                            public void done(List<Comment> commentList, BmobException e) {
                                if (e == null) {
//                                    ToastUtil.showToast(context,"查询成功：共"+commentList.size()+"条数据。");
                                    DealDate dealDate = new DealDate();
                                    list[0] = dealDate.dealCircleMessage(commentList, c);
//                                    Log.i("MyTag",dealDate.dealCircleMessage(commentList,c).toString());
                                    if (i[0] == messageList.size() - 1)
                                        flag[0] = false;
                                    if (mOnGetAllMessageListener != null) {
                                        mOnGetAllMessageListener.getAllMessage(list[0], flag[0]);

                                    } else {
                                        Log.i("MyTag", "查询成功了，只是赋值失败了");
                                    }

//                                    Log.i("MyTag","comment查询成功：共"+commentList.size()+"条数据。");
                                } else {
                                    Log.i("MyTag", "comment查询失败：" + c.getContent() + e.getMessage() + "," + e.getErrorCode());
                                    ToastUtil.showToast(context, "查询评论失败：" + e.getMessage() + "," + e.getErrorCode());
                                    if (e.getErrorCode() == 9016) {
                                        ToastUtil.showToast(context, "网络连接失败，请检查网络状况");
                                    }

                                }
                                i[0]++;
                            }
                        });

                    }
                } else {
                    Log.i("MyTag", "查询动态失败：" + e.getMessage() + "," + e.getErrorCode());
                    ToastUtil.showToast(context, "查询动态失败：" + e.getMessage() + "," + e.getErrorCode());
                    if (e.getErrorCode() == 9016) {
                        ToastUtil.showToast(context, "网络连接失败，请检查网络状况");
                    }
                }
            }
        });
    }


    public void getComments(final Context context, String objectId) {
        BmobQuery<Comment> query = new BmobQuery<Comment>();
        query.include("user");
        query.addWhereEqualTo("circleMessageId", objectId);
        //执行查询方法
        query.findObjects(new FindListener<Comment>() {
            @Override
            public void done(List<Comment> commentList, BmobException e) {
                if (e == null) {
                    Log.i("MyTag", "comment查询成功：共" + commentList.size() + "条数据。");
                } else {
                    ToastUtil.showToast(context, "失败：" + e.getMessage() + "," + e.getErrorCode());
                    Log.i("MyTag", "失败：" + e.getMessage() + "," + e.getErrorCode());
                    if (e.getErrorCode() == 9016) {
                        ToastUtil.showToast(context, "网络连接失败，请检查网络状况");
                    }

                }
            }
        });
    }

    @Override
    public void insertCircleMessage(final Context context, String content, final String[] picUrls) {
        final MyUser user = BmobUser.getCurrentUser(MyUser.class);
        final CircleMessage circleMessage = new CircleMessage();
        circleMessage.setContent(content);
        circleMessage.setUser(BmobUser.getCurrentUser(MyUser.class));
        BmobFile.uploadBatch(picUrls, new UploadBatchListener() {
            @Override
            public void onSuccess(List<BmobFile> files, List<String> urls) {
                //1、files-上传完成后的BmobFile集合，是为了方便大家对其上传后的数据进行操作，例如你可以将该文件保存到表中
                //2、urls-上传文件的完整url地址
                if (urls.size() == picUrls.length) {//如果数量相等，则代表文件全部上传完成
                    //do something
                    circleMessage.setImages(files);
                    circleMessage.setUser(user);
                    circleMessage.save(new SaveListener<String>() {
                        @Override
                        public void done(String objectId, BmobException e) {
                            if (e == null) {
                                ToastUtil.showToast(context, "添加数据成功，返回objectId为：" + objectId);
                                Log.i("MyTag", "添加数据成功，返回objectId为：" + objectId);
                            } else {
                                ToastUtil.showToast(context, "创建数据失败：" + e.getMessage());
                                Log.i("MyTag", "创建数据失败：" + e.getErrorCode() + e.getMessage());
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
                ToastUtil.showToast(context, "错误码" + statuscode + ",错误描述：" + errormsg);
            }

            @Override
            public void onProgress(int curIndex, int curPercent, int total, int totalPercent) {
                //1、curIndex--表示当前第几个文件正在上传
                //2、curPercent--表示当前上传文件的进度值（百分比）
                //3、total--表示总的上传文件数
                //4、totalPercent--表示总的上传进度（百分比）
            }
        });
    }

    //根据objectId删除动态
    @Override
    public void deleteByObjectId(final Context context, String id) {
        CircleMessage circleMessage = new CircleMessage();
        circleMessage.setObjectId(id);
        circleMessage.delete(new UpdateListener() {
            @Override
            public void done(BmobException e) {
                if (e == null) {
                    Log.i("MyTag", "删除动态成功");
                    ToastUtil.showToast(context, "删除动态成功！");
                } else {
                    Log.i("MyTag", "删除动态失败：" + e.getMessage() + "," + e.getErrorCode());
                    ToastUtil.showToast(context, "删除动态失败！");
                    if (e.getErrorCode() == 9016) {
                        ToastUtil.showToast(context, "网络连接失败，请检查网络状况");
                    }
                }
            }
        });
    }

    //点击获取一条动态
    @Override
    public void getOneCircleMessage(final Context context, final String objectId) {
        BmobQuery<CircleMessage> bmobQuery = new BmobQuery<CircleMessage>();
        bmobQuery.getObject(objectId, new QueryListener<CircleMessage>() {
            @Override
            public void done(final CircleMessage object, BmobException e) {
                if(e==null){
                    ToastUtil.showToast(context, "查询动态成功！");
                    BmobQuery<Comment> commentquery = new BmobQuery<Comment>();
//                        commentquery.include("circleMessage");
                    commentquery.include("user");
                    commentquery.addWhereEqualTo("circleMessageId", objectId);
                    //执行查询方法
                    commentquery.findObjects(new FindListener<Comment>() {
                        @Override
                        public void done(List<Comment> commentList, BmobException e) {
                            if (e == null) {
//                                    ToastUtil.showToast(context,"查询成功：共"+commentList.size()+"条数据。");
                                DealDate dealDate = new DealDate();
                                CircleMessageModel messageModel= dealDate.dealCircleMessage(commentList, object);
//                                    Log.i("MyTag",dealDate.dealCircleMessage(commentList,c).toString());
                                if (mOnGetOneMessageListener != null) {
                                    mOnGetOneMessageListener.getOneMessage(messageModel,true);

                                } else {
                                    Log.i("MyTag", "查询成功了，只是赋值失败了");
                                }
//                                    Log.i("MyTag","comment查询成功：共"+commentList.size()+"条数据。");
                            } else {
                                Log.i("MyTag", "comment查询失败：" + object.getContent() + e.getMessage() + "," + e.getErrorCode());
                                ToastUtil.showToast(context, "查询评论失败：" + e.getMessage() + "," + e.getErrorCode());
                                if (e.getErrorCode() == 9016) {
                                    ToastUtil.showToast(context, "网络连接失败，请检查网络状况");
                                }

                            }
                        }
                    });

                }else{
                    ToastUtil.showToast(context, "查询动态失败！");
                }
            }
        });
    }

    //添加约球。makeDate动态的构造方法那些参数，大图的url,请在登录成功后进行添加
    @Override
    public void insertMakeDate(final Context context, final MakeDate makeDate, final String picUrl) {
        final MyUser user = BmobUser.getCurrentUser(MyUser.class);
        makeDate.setUser(BmobUser.getCurrentUser(MyUser.class));
        final BmobFile bmobFile = new BmobFile(new File(picUrl));
        bmobFile.uploadblock(new UploadFileListener() {
            @Override
            public void done(final BmobException e) {
                if (e == null) {
                    makeDate.setMakeDateImg(bmobFile);
                    makeDate.setUser(user);
                    makeDate.save(new SaveListener<String>() {
                        @Override
                        public void done(String objectId, BmobException e) {
                            if (e == null) {
                                ToastUtil.showToast(context, "添加约球数据成功，返回objectId为：" + objectId);
                                Log.i("MyTag", "添加约球数据成功，返回objectId为：" + objectId);
                            } else {
                                ToastUtil.showToast(context, "创建约球数据失败：" + e.getMessage());
                                Log.i("MyTag", "创建约球数据失败：" + e.getErrorCode() + e.getMessage());
                                if (e.getErrorCode() == 9016) {
                                    ToastUtil.showToast(context, "网络连接失败，请检查网络状况");
                                }
                            }
                        }

                    });
                } else {
                    ToastUtil.showToast(context, "上传文件失败：" + e.getMessage());
                }
            }
        });
    }

    //获取全部约球，每5条返回一次，skip传5的倍数，初始传为0
    @Override
    public void getAllMakeDate(final Context context, int skip) {
        BmobQuery<MakeDate> commentquery = new BmobQuery<MakeDate>();
        commentquery.include("user");
        //执行查询方法
        commentquery.findObjects(new FindListener<MakeDate>() {
            @Override
            public void done(List<MakeDate> makeDates, BmobException e) {
                if (e == null) {
//                                    ToastUtil.showToast(context,"查询成功：共"+commentList.size()+"条数据。");
                    DealDate dealDate = new DealDate();
                    List<MakeDateModel> models= dealDate.dealMakeDate(makeDates);
//                                    Log.i("MyTag",dealDate.dealCircleMessage(commentList,c).toString());
                    if (mOnGetAllMakeDateListener != null) {
                        mOnGetAllMakeDateListener.getAllMakeDate(models,true);

                    } else {
                        Log.i("MyTag", "查询成功了，只是赋值失败了");
                    }

//                                    Log.i("MyTag","comment查询成功：共"+commentList.size()+"条数据。");
                } else {
                    Log.i("MyTag", "makeDate查询失败：" + e.getMessage() + "," + e.getErrorCode());
                    ToastUtil.showToast(context, "查询评论失败：" + e.getMessage() + "," + e.getErrorCode());
                    if (e.getErrorCode() == 9016) {
                        ToastUtil.showToast(context, "网络连接失败，请检查网络状况");
                    }

                }
            }
        });

    }

    //获取一条约球详情
    @Override
    public void getOneMakeDateDetail(final Context context, String objectId) {
        BmobQuery<MakeDate> bmobQuery = new BmobQuery<MakeDate>();
        bmobQuery.getObject(objectId, new QueryListener<MakeDate>() {
            @Override
            public void done(final MakeDate object, BmobException e) {
                            if (e == null) {
                                if (mOnGetOneMakeDateListener != null) {
                                    mOnGetOneMakeDateListener.getOneMakeDate(object, true);
                                } else {
                                    Log.i("MyTag", "查询成功了，只是赋值失败了");
                                }
                            } else {
                                Log.i("MyTag", "comment查询失败：" + object.getContent() + e.getMessage() + "," + e.getErrorCode());
                                ToastUtil.showToast(context, "查询评论失败：" + e.getMessage() + "," + e.getErrorCode());
                                if (e.getErrorCode() == 9016) {
                                    ToastUtil.showToast(context, "网络连接失败，请检查网络状况");
                                }

                            }
                        }
                    });

            }

    //点赞喜欢
    @Override
    public void UpdateMakeDate1(final Context context, String objectId, final int like) {
        MakeDateInf m = new MakeDateInf();
        m.setMakeDateId(objectId);
        m.setLike(true);
        m.save(new SaveListener<String>() {

            @Override
            public void done(String objectId, BmobException e) {
                if(e==null){
                    ToastUtil.showToast(context,"喜欢：" + objectId);
                    //同时将约球表里的喜欢数量加一
                    MakeDate makeDate = new MakeDate();
                    makeDate.setLike(like+1);
                    makeDate.update(objectId, new UpdateListener() {
                        @Override
                        public void done(BmobException e) {
                            if(e==null){
                                Log.i("MyTag" ,"更新成功");
                            }else{
                                Log.i("MyTag","更新失败："+e.getMessage()+","+e.getErrorCode());
                            }
                        }
                    });
                }else{
                    Log.i("MyTag","失败："+e.getMessage()+","+e.getErrorCode());
                }
            }
        });
    }

    //报名
    @Override
    public void UpdateMakeDate2(final Context context, String objectId, final int apply) {
        MakeDateInf m = new MakeDateInf();
        m.setMakeDateId(objectId);
        m.setApply(true);
        m.save(new SaveListener<String>() {
            @Override
            public void done(String objectId, BmobException e) {
                if(e==null){
                    ToastUtil.showToast(context,"报名：" + objectId);
                    //同时将约球表里的喜欢数量加一
                    MakeDate makeDate = new MakeDate();
                    makeDate.setLike(apply+1);
                    makeDate.update(objectId, new UpdateListener() {
                        @Override
                        public void done(BmobException e) {
                            if(e==null){
                                Log.i("MyTag" ,"更新成功");
                            }else{
                                Log.i("MyTag","更新失败："+e.getMessage()+","+e.getErrorCode());
                            }
                        }
                    });
                }else{
                    Log.i("MyTag","失败："+e.getMessage()+","+e.getErrorCode());
                }
            }
        });
    }

    //TODO:获取一条约球
    public void setOnGetOneMakeDateListener(OnGetOneMakeDateListener onGetOneMakeDateListener) {
        mOnGetOneMakeDateListener = onGetOneMakeDateListener;
    }

    //TODO:接口   获取一条约球
    public interface OnGetOneMakeDateListener {
        void getOneMakeDate(MakeDate makeDate, boolean state);
    }

    //TODO:获取全部约球
    public void setOnGetAllMakeDateListener(OnGetAllMakeDateListener onGetAllMakeDateListener) {
        mOnGetAllMakeDateListener = onGetAllMakeDateListener;
    }

    //TODO:接口   获取全部约球
    public interface OnGetAllMakeDateListener {
        void getAllMakeDate(List<MakeDateModel> MakeDates, boolean state);
    }

    //TODO:获取全部动态
    public void setOnGetAllMessageListener(OnGetAllMessageListener onGetAllMessageListener) {
        mOnGetAllMessageListener = onGetAllMessageListener;
    }

    //TODO:接口   获取全部动态
    public interface OnGetAllMessageListener {
        void getAllMessage(CircleMessageModel messages, boolean state);
    }

    //TODO:获取一条动态
    public void setOnGetOneMessageListener(OnGetOneMessageListener onGetOneMessageListener) {
        mOnGetOneMessageListener = onGetOneMessageListener;
    }

    //TODO:接口   获取一条动态
    public interface OnGetOneMessageListener {
        void getOneMessage(CircleMessageModel messages, boolean state);
    }

    //TODO:获取全部评论
    public void setOnGetCommentsListener(OnGetCommentsListener onGetCommentsListener) {
        mOnGetCommentsListener = onGetCommentsListener;
    }

    //TODO:接口   获取全部评论
    public interface OnGetCommentsListener {
        void getComments(List<Comment> comments, boolean state);
    }
}
