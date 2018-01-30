package com.yyq.datalib.javaBeans;

import java.util.List;

import cn.bmob.v3.BmobObject;

/**
 * Created by YQ on 2017/12/3.
 */

public class HotSearch extends BmobObject {
    List<String> hotSearchContent;

    public List<String> getHotSearchContent() {
        return hotSearchContent;
    }

    public void setHotSearchContent(List<String> hotSearchContent) {
        this.hotSearchContent = hotSearchContent;
    }
}
