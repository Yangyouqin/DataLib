package com.yyq.datalib.query;

import com.yyq.datalib.javaBeans.CircleMessage;
import com.yyq.datalib.javaBeans.Comment;
import com.yyq.datalib.javaBeans.MakeDate;
import com.yyq.datalib.javaBeans.Match;
import com.yyq.datalib.javaBeans.MyUser;
import com.yyq.datalib.javaBeans.Orders;
import com.yyq.datalib.javaBeans.Place;
import com.yyq.datalib.javaBeans.Trains;
import com.yyq.datalib.models.CircleMessageModel;
import com.yyq.datalib.models.MakeDateModel;
import com.yyq.datalib.models.MatchModel;
import com.yyq.datalib.models.SortModel;
import com.yyq.datalib.models.TrainModel;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import cn.bmob.v3.BmobUser;
import cn.bmob.v3.datatype.BmobDate;
import cn.bmob.v3.datatype.BmobFile;

/**
 * Created by yangyouqin on 2018/1/4.
 */

public class DealDate {
    public List<SortModel> dealPlace(List<Place> places) {
        List<SortModel> list = new ArrayList<>();
        for (Place place : places) {
            SortModel sortModel = new SortModel();
            if (place.isAvailable())
                sortModel.setAvailable("可预订");
            else if (!place.isAvailable())
                sortModel.setAvailable("已预订");
            sortModel.setGrade(place.getGrade());
            sortModel.setLatitude(place.getLatitude());
            sortModel.setLongitude(place.getLongitude());
            sortModel.setName(place.getPlaceName());
            sortModel.setPrice(place.getPrice());
            sortModel.setType("场地");
            sortModel.setObjectId(place.getObjectId());
            sortModel.setPictureUrl(place.getPlaceImg1().get(0).getFileUrl());
            list.add(sortModel);
        }
        return list;
    }

    public List<SortModel> dealMatch(List<Match> matches) {
        List<SortModel> list = new ArrayList<>();
        for (Match match : matches) {
            SortModel sortModel = new SortModel();
            if (match.getPersonNum() > match.getApplyNum()) {
                sortModel.setAvailable("可报名");
            } else {
                sortModel.setAvailable("满员");
            }
            sortModel.setGrade(match.getGrade());
            sortModel.setLatitude(match.getLatitude());
            sortModel.setLongitude(match.getLongitude());
            sortModel.setName(match.getMatchName());
            sortModel.setPrice(match.getPrice());
            sortModel.setType("比赛");
            sortModel.setObjectId(match.getObjectId());
            sortModel.setPictureUrl(match.getMatchImg().getFileUrl());
            list.add(sortModel);
        }
        return list;
    }

    public List<SortModel> dealTrain(List<Trains> trains) {
        List<SortModel> list = new ArrayList<>();
        for (Trains train : trains) {
            SortModel sortModel = new SortModel();
            if (train.getPersonNum() > train.getApplyNum()) {
                sortModel.setAvailable("可报名");
            } else {
                sortModel.setAvailable("满员");
            }
            sortModel.setGrade(train.getGrade());
            sortModel.setLatitude(train.getLatitude());
            sortModel.setLongitude(train.getLongitude());
            sortModel.setName(train.getTrainName());
            sortModel.setPrice(train.getPrice());
            sortModel.setType("培训");
            sortModel.setObjectId(train.getObjectId());
            sortModel.setPictureUrl(train.getTrainImg1().get(0).getFileUrl());
            list.add(sortModel);
        }
        return list;
    }

    public List<MatchModel> dealMatchPage(List<Match> matches) {
        List<MatchModel> list = new ArrayList<>();
        for (Match match : matches) {
            MatchModel matchModel = new MatchModel();
            matchModel.setMatchName(match.getMatchName());
            matchModel.setPictureUrl(match.getMatchImg().getFileUrl());
            matchModel.setPrice(match.getPrice());
            matchModel.setType(match.getMatchType());
            matchModel.setMatchTitle(match.getMatchTitle());
            matchModel.setLongitude(match.getLongitude());
            matchModel.setLatitude(match.getLatitude());
            matchModel.setObjectId(match.getObjectId());
            if (match.getPersonNum() > match.getApplyNum())
                matchModel.setAvailable("可报名");
            else
                matchModel.setAvailable("满员");
            list.add(matchModel);
        }
        return list;
    }

    public List<TrainModel> dealTrainPage(List<Trains> trains) {
        List<TrainModel> list = new ArrayList<>();
        for (Trains train : trains) {
            TrainModel trainModel = new TrainModel();
            trainModel.setTrainName(train.getTrainName());
            trainModel.setClassName(train.getClassName());
            trainModel.setClubName(train.getClubName());
            trainModel.setHeadImgUrl(train.getUser().getHeadImg().getFileUrl());
            trainModel.setLongitude(train.getLongitude());
            trainModel.setLatitude(train.getLatitude());
            trainModel.setPictureUrl(train.getTrainImg1().get(0).getFileUrl());
            trainModel.setPrice(train.getPrice());
            trainModel.setObjectId(train.getObjectId());
            if (train.getPersonNum() > train.getApplyNum())
                trainModel.setAvailable("可报名");
            else
                trainModel.setAvailable("满员");
            list.add(trainModel);
        }
        return list;
    }

    public CircleMessageModel dealCircleMessage(List<Comment> commentList, CircleMessage circleMessage) {
        CircleMessageModel cc = new CircleMessageModel();
        final MyUser user = BmobUser.getCurrentUser(MyUser.class);
        final List<String> imgsUrl = new ArrayList<String>();
        for (BmobFile bmobFile : circleMessage.getImages()) {
            imgsUrl.add(bmobFile.getFileUrl());
        }
        List<String> ss = new ArrayList<String>();
        boolean isliked = false;
        int like = 0;
        if (commentList != null) {
            for (Comment comment : commentList) {
                ss.add(comment.getContent());
                String id1 = comment.getUser().getObjectId();
                String id2 = user.getObjectId();
                if (id1.equals(id2))
                    isliked = true;
                if (comment.isLike() == true)
                    like += 1;
            }
            cc.setComments(ss);
            cc.setCurrentlike(isliked);
            cc.setLike(like);
        }
        cc.setCommentCount(commentList.size());
        cc.setObjectId(circleMessage.getObjectId());
        cc.setContent(circleMessage.getContent());
        String dd = circleMessage.getCreatedAt().substring(0,10);
        Date d2 = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_YEAR,-2);
        Date d3 = calendar.getTime();
        Calendar calendar2 = Calendar.getInstance();
        calendar2.add(Calendar.DAY_OF_YEAR,-1);
        Date d4 = calendar2.getTime();
        String nowDay = sdf.format(d2);
        String oldDay = sdf.format(d3);
        String yesterday = sdf.format(d4);
        if(yesterday.equals(dd)){
            cc.setSendTime("昨天");
        }
        else if(dd.equals(oldDay)){
            cc.setSendTime("前天");
        }
        else if(nowDay.equals(dd)){
            cc.setSendTime(circleMessage.getCreatedAt().substring(11,15));
        }
        else {
            cc.setSendTime(dd);
        }
        cc.setHeadImgUrl(circleMessage.getUser().getHeadImg().getFileUrl());
        cc.setUserName(circleMessage.getUser().getUsername());
        cc.setImagsUrl(imgsUrl);
        return cc;
    }

    public List<MakeDateModel> dealMakeDate(List<MakeDate> makeDates){
        List<MakeDateModel> list = new ArrayList<>();
        MakeDateModel model = new MakeDateModel();
        for (MakeDate makeDate : makeDates) {
            model.setHeadImg(makeDate.getUser().getHeadImg().getFileUrl());
            model.setMakeDateImg(makeDate.getMakeDateImg().getFileUrl());
            model.setApplyNum(makeDate.getApplyNum());
            model.setContent(makeDate.getContent());
            model.setLatitude(makeDate.getLatitude());
            model.setLongitude(makeDate.getLongitude());
            model.setTitle(makeDate.getTitle());
            model.setLike(makeDate.getLike());
            model.setObjectId(makeDate.getObjectId());
            list.add(model);
        }
        return list;
    }
    public Double caculateCharge1(double payment){
        if(payment<50){
            return 5.00;
        }
        else if(payment<=100){
            return payment*0.1;
        }
        else if(payment<=500){
            return payment * 0.2;
        }
        else{
            return 50.00;
        }
    }
    public double caculateCharge2(Orders orders){
       Date currentDate = new Date();
        BmobDate startTime = orders.getStartTime();
        SimpleDateFormat formatter = new SimpleDateFormat( "yyyy-MM-dd HH:mm:ss");
        String s = startTime.getDate();
        //当前时间与订单开始时间相距几个小时
        double hours = 0;
        try {
            Date date =  formatter.parse(s);
            hours = (double) ((currentDate.getTime() - date.getTime()) / (1000*3600));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        //取消，退款0元，按正常订单，计算平台和商家的利益
        if(hours<2.0){
            return orders.getPayment();
        }
        else if(hours<6.0){
            //取消订单，扣去的费用，扣取30%
            double returnProfit = orders.getPayment()*0.3;
            return returnProfit;
        }
        else if(hours<12.0){
            //取消订单，扣去的费用，扣取5%
            double returnProfit = orders.getPayment()*0.05;
            return returnProfit;
        }
        //不扣费用，用户获得全额退款
        else{
            return 0;
        }
    }
}
