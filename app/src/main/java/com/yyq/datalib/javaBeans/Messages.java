package com.yyq.datalib.javaBeans;

import java.util.List;

import cn.bmob.v3.BmobObject;
import cn.bmob.v3.datatype.BmobFile;

/**
 * 运动圈动态
 * Created by YQ on 2017/12/20.
 */

public class Messages extends BmobObject {
    private String userId;

    private String content;

    private List<BmobFile> messageImg;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public List<BmobFile> getMessageImg() {
        return messageImg;
    }

    public void setMessageImg(List<BmobFile> messageImg) {
        this.messageImg = messageImg;
    }

    @Override
    public String toString() {
        return "Messages{" +
                "userId='" + userId + '\'' +
                ", content='" + content + '\'' +
                ", messageImg=" + messageImg +
                '}';
    }
}
