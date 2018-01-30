package com.yyq.datalib.javaBeans;

import java.util.List;

import cn.bmob.v3.BmobObject;
import cn.bmob.v3.datatype.BmobFile;

/**
 * 运动圈动态
 * Created by YQ on 2017/12/20.
 */

public class CircleMessage extends BmobObject {
    //发布动态的用户
    private MyUser user;

    //动态的文字内容
    private String content;

    //发布的图片
    private List<BmobFile> images;

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

    public List<BmobFile> getImages() {
        return images;
    }

    public void setImages(List<BmobFile> images) {
        this.images = images;
    }

    @Override
    public String toString() {
        return "CircleMessage{" +
                "user=" + user +
                ", content='" + content + '\'' +
                ", images=" + images +
                '}';
    }
}
