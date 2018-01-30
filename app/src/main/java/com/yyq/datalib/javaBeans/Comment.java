package com.yyq.datalib.javaBeans;

import cn.bmob.v3.BmobObject;

/**
 * Created by YQ on 2017/12/19.
 */

public class Comment extends BmobObject {
    private String trainId;

    private String placeId;

    private String matchId;

    private MyUser user;

    private String content;

    private Double stars;

    private boolean like;

    private String circleMessageId;

    public String getTrainId() {
        return trainId;
    }

    public void setTrainId(String trainId) {
        this.trainId = trainId;
    }

    public String getPlaceId() {
        return placeId;
    }

    public void setPlaceId(String placeId) {
        this.placeId = placeId;
    }



    public String getMatchId() {
        return matchId;
    }

    public void setMatchId(String matchId) {
        this.matchId = matchId;
    }

    public MyUser getUser() {
        return user;
    }

    public void setUser(MyUser user) {
        this.user = user;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Double getStars() {
        return stars;
    }

    public void setStars(Double stars) {
        this.stars = stars;
    }

    public boolean isLike() {
        return like;
    }

    public void setLike(boolean like) {
        this.like = like;
    }

    public String getCircleMessageId() {
        return circleMessageId;
    }

    public void setCircleMessageId(String circleMessageId) {
        this.circleMessageId = circleMessageId;
    }

    public Comment() {
    }

    //圈子动态的构造方法
    public Comment(String content, boolean like, String circleMessageId) {
        this.content = content;
        this.like = like;
        this.circleMessageId = circleMessageId;
    }
    //培训，场地，比赛的评论的构造，需要再添加一个id，如果是place的评论就setPlaceId
    public Comment(String content, Double stars, boolean like) {
        this.content = content;
        this.stars = stars;
        this.like = like;
    }


    @Override
    public String toString() {
        return "Comment{" +
                "trainId='" + trainId + '\'' +
                ", placeId='" + placeId + '\'' +
                ", matchId='" + matchId + '\'' +
                ", user=" + user +
                ", content='" + content + '\'' +
                ", stars=" + stars +
                ", like=" + like +
                ", circleMessageId='" + circleMessageId + '\'' +
                '}';
    }
}
