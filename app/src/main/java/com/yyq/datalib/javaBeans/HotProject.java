package com.yyq.datalib.javaBeans;

import cn.bmob.v3.BmobObject;
import cn.bmob.v3.datatype.BmobFile;

/**
 * Created by YQ on 2017/11/30.
 * 主页的热门项目，风筝，篮球····
 */

public class HotProject extends BmobObject {
    private BmobFile img;
    private String name;

    public BmobFile getImg() {
        return img;
    }

    public void setImg(BmobFile img) {
        this.img = img;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
