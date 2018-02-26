package com.yyq.datalib;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Button;
import android.widget.ListView;

import com.yyq.datalib.javaBeans.MakeDate;
import com.yyq.datalib.javaBeans.MyUser;
import com.yyq.datalib.service.impl.CircleMessageService;
import com.yyq.datalib.service.impl.MyUserService;
import com.yyq.datalib.service.impl.PlaceService;

import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import cn.bmob.v3.Bmob;
import cn.bmob.v3.datatype.BmobDate;
import cn.bmob.v3.datatype.BmobFile;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;
import cn.bmob.v3.listener.UploadBatchListener;

public class MainActivity extends AppCompatActivity {

    private Button b,b2;
    private ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        b = (Button) findViewById(R.id.button);
        b2 = (Button) findViewById(R.id.button2);
        listView = (ListView) findViewById(R.id.list1);
        //第一：默认初始化
        Bmob.initialize(this, "47cd947fb9cda932d30e2a3fc9560660");

//        TrainService trainService = new TrainService();
//        trainService.getTrainByType(MainActivity.this,"篮球",0);
//        trainService.setOnGetTrainDataByTypeListener(new TrainService.OnGetTrainDataByTypeListener() {
//            @Override
//            public void getTrainDataByType(List<TrainModel> trainses, boolean state) {
//                for (TrainModel trains : trainses) {
//                    Log.i("MyTag",trains.toString());
//                }
//            }
//        });
//
//        trainService.getTrainByid(MainActivity.this,"JnbdZZZn");
//        trainService.setOnGetTrainDataByIdListener(new TrainService.OnGetTrainDataByIdListener() {
//            @Override
//            public void getTrainDataById(Trains trainses, boolean state) {
//                Log.i("MyTag",trainses.toString());
//            }
//        });


//        gl("排球","EK6Q555I");
//        gl("乒乓球","NCpI6668");
//        gl("游泳","ponT3336");
//        gl("羽毛球","s0zk333l");
//        gl("其他","xaIWKKKa");
//        gl("篮球","asKz888I");
//        gl("足球","5WhR333P");

//        BmobQuery<Trains> query = new BmobQuery<Trains>();
//        query.addWhereEqualTo("trainType","篮球");
//        query.include("user.username");
//        query.setLimit(5);
//        //执行查询方法
//        query.findObjects(new FindListener<Trains>() {
//            @Override
//            public void done(List<Trains> object, BmobException e) {
//                if(e==null){
//                    ToastUtil.showToast(MainActivity.this,"查询成功：共"+object.size()+"条数据。");
//                    Log.i("MyTag",object.get(0).getUser().getObjectId());
//                    Log.i("MyTag","查询成功：共"+object.size()+"条数据。");
//                }else{
//                    Log.i("MyTag","失败："+e.getMessage()+","+e.getErrorCode());
//                    ToastUtil.showToast(MainActivity.this,"失败："+e.getMessage()+","+e.getErrorCode());
//
//                }
//            }
//        });

        //TODO:条件筛选查询
//        final SortQuery sortQuery = new SortQuery();
//        sortQuery.setType("篮球");
//        sortQuery.setSort(0);
//        List<String> conf = new ArrayList<>();
//        conf.add("WIFI");
//        conf.add("停车场");
//        conf.add("淋浴");
//        conf.add("更衣室");
//        sortQuery.setConfigures(conf);
//        sortQuery.setMinPrice(20.1);
//        sortQuery.setMaxPrice(30.0);
////        final SortQuery sortQuery = new SortQuery("篮球",0,0,0,null,0);
//        final AllListService allListService = new AllListService();
//        allListService.getData(MainActivity.this,sortQuery);
//        allListService.setOnGetDataListener(new AllListService.OnGetDataListener() {
//            @Override
//            public void getData(List<SortModel> list) {
//                for (SortModel sortModel : list) {
//                    Log.i("MyTag","按评分最高"+sortModel.toString());
//                }
//            }
//        });
//        final int[] i = {1};
//        //点击加载第二次
//        b.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                sortQuery.setSkip(i[0]);
//                allListService.getData(MainActivity.this,sortQuery);
//                allListService.setOnGetDataListener(new AllListService.OnGetDataListener() {
//                    @Override
//                    public void getData(List<SortModel> list) {
//
//                        for (SortModel sortModel : list) {
//                            Log.i("MyTag","按评分最高"+sortModel.toString());
//                        }
//                    }
//                });
//                i[0] +=1;
//            }
//        });




        //TODO:插入评论
        MyUserService myUserService = new MyUserService();
        myUserService.loginByPhone("13584523695","123456",0,MainActivity.this);
//        CommentService commentService = new CommentService();
//        Comment comment = new Comment();
//        comment.setCircleMessageId("nyZ9MMMb");
//        comment.setContent("好冷哦！");
//        commentService.insertComment(MainActivity.this,comment);

        //TODO:动态查询
//        final int[] i = {0};
//        b.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Log.i("MyTag","点击点击");
//                CircleMessageService messageService = new CircleMessageService();
//                messageService.getAllMessage(MainActivity.this, i[0]*5);
//                messageService.setOnGetAllMessageListener(new CircleMessageService.OnGetAllMessageListener() {
//                    @Override
//                    public void getAllMessage(CircleMessageModel messages, boolean state) {
//                        String [] strs = new String[]{messages.getContent()};
////                        listView.setAdapter(new ArrayAdapter<String>(MainActivity.this,
////                                android.R.layout.simple_list_item_1, strs));
//                        Log.i("MyTag",messages.toString());
//                        if(state==false)
//                            Log.i("MyTag","加载完毕");
//                    }
//                });
//                i[0]++;
//            }
//        });
//        CircleMessageService messageService = new CircleMessageService();
//        messageService.getOneCIrcleMessage(MainActivity.this);
//
//
//        myUserService.phoneIsExistence(MainActivity.this,"13555555555");
//        myUserService.phoneIsExistence(MainActivity.this,"13880362644");

        //TODO:添加动态
//        MyUserService myUserService = new MyUserService();
//        myUserService.loginByPhone("13604539538","123456",0,MainActivity.this);

//        b.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                CircleMessageService messageService = new CircleMessageService();
//                String[] picUrls = {"/mnt/sdcard/droid4xshare/1.jpg","/mnt/sdcard/droid4xshare/2.JPG","/mnt/sdcard/droid4xshare/3.jpg"};
//                messageService.insert(MainActivity.this,"天气太冷了，进入冬眠",picUrls);
//            }
//        });
//        b2.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//            }
//        });

//        PlaceService placeService = new PlaceService();
//        placeService.getPlacesByType(MainActivity.this,"篮球",0);
//        AllListService allListService = new AllListService();
//        allListService.getDetails(MainActivity.this,"场地","6Tx3DDDu");
//        allListService.setOnGetDetailListener(new AllListService.OnGetDetailListener() {
//            @Override
//            public void getDetail(Object object) {
//                Log.i("MyTag",object.toString());
//            }
//        });
       // insertmakedate();

    }

    //TODO:插入约球
    private void insertmakedate(){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String strDate = "2018-1-22 09:00";
        String str = "2018-1-23 18:00";
        Date ss = new Date();
        try {
            ss=sdf.parse(strDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Date s = new Date();
        try {
            s=sdf.parse(str);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        BmobDate start = new BmobDate(ss);
        BmobDate end = new BmobDate(s);
         MakeDate makeDate = new MakeDate("一起篮球","虽然都是陌路人，但是能跑到最后的人，就是这个计划最温暖的瞬间2",104.1002270000,30.6757020000
         ,"电子科技大学沙河校区","篮球",100,start,end,"028-7542136");

        String picUrl = "/mnt/sdcard/droid4xshare/3.JPG";
        MyUserService myUserService = new MyUserService();
        myUserService.loginByPhone("13604539537","123456",0,MainActivity.this);
        CircleMessageService circleMessageService = new CircleMessageService();
        circleMessageService.insertMakeDate(MainActivity.this,makeDate,picUrl);

    }
    private void singUp() {
        final MyUserService myUserService = new MyUserService();
        String picPath = "/mnt/sdcard/droid4xshare/kite.png";
        final BmobFile bmobFile = new BmobFile(new File(picPath));
        final MyUser myUser = new MyUser();
        myUser.setUsername("tom12");
        myUser.setPassword("tom12311");
        myUser.setMobilePhoneNumber("13604539538");

        PlaceService placeService = new PlaceService();
        final String[] filePaths = new String[1];
        filePaths[0] = picPath;
        BmobFile.uploadBatch(filePaths, new UploadBatchListener() {

            @Override
            public void onSuccess(List<BmobFile> files, List<String> urls) {
                //1、files-上传完成后的BmobFile集合，是为了方便对其上传后的数据进行操作，例如你可以将该文件保存到表中
                //2、urls-上传文件的完整url地址
                if(urls.size()==filePaths.length){//如果数量相等，则代表文件全部上传完成
                    //do something
                    myUser.setHeadImg(files.get(0));
                    myUser.signUp(new SaveListener<MyUser>() {
                        @Override
                        public void done(MyUser s, BmobException e) {
                            if(e==null){
                                Log.i("MyTag","注册成功！");
                            }else{
                                Log.i("MyTag","注册失败！"+e);
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
}
