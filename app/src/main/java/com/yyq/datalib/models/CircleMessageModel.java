package com.yyq.datalib.models;

import java.util.List;

/**
 * Created by YQ on 2018/1/6.
 */

public class CircleMessageModel {
    //用户头像图片网址
    private String headImgUrl;

    //用户名
    private String userName;

    //用户发布的内容
    private String content;

    //用户发布的图片的网址List
    private List<String> imagsUrl;

    //点赞数量
    private int like;

    //当前用户是否点赞，用于判断那个点赞是否亮着
    private boolean currentlike;

    //评论的集合
    private List<String> comments;

    //评论的数量
    private int commentCount;

    //用户发布动态的时间
    private String sendTime;

    //数据的id，方便点击进去的时候用id查询
    private String objectId;

    public String getObjectId() {
        return objectId;
    }

    public void setObjectId(String objectId) {
        this.objectId = objectId;
    }

    public int getCommentCount() {
        return commentCount;
    }

    public void setCommentCount(int commentCount) {
        this.commentCount = commentCount;
    }

    public String getHeadImgUrl() {
        return headImgUrl;
    }

    public void setHeadImgUrl(String headImgUrl) {
        this.headImgUrl = headImgUrl;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public List<String> getImagsUrl() {
        return imagsUrl;
    }

    public void setImagsUrl(List<String> imagsUrl) {
        this.imagsUrl = imagsUrl;
    }

    public int getLike() {
        return like;
    }

    public void setLike(int like) {
        this.like = like;
    }

    public boolean isCurrentlike() {
        return currentlike;
    }

    public void setCurrentlike(boolean currentlike) {
        this.currentlike = currentlike;
    }

    public List<String> getComments() {
        return comments;
    }

    public void setComments(List<String> comments) {
        this.comments = comments;
    }

    public String getSendTime() {
        return sendTime;
    }

    public void setSendTime(String sendTime) {
        this.sendTime = sendTime;
    }

    @Override
    public String toString() {
        return "CircleMessageModel{" +
                "headImgUrl='" + headImgUrl + '\'' +
                ", userName='" + userName + '\'' +
                ", content='" + content + '\'' +
                ", imagsUrl=" + imagsUrl +
                ", like=" + like +
                ", currentlike=" + currentlike +
                ", comments=" + comments +
                ", sendTime=" + sendTime +
                '}';
    }
}
