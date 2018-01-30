package com.yyq.datalib.javaBeans;

import java.util.List;

import cn.bmob.v3.BmobObject;
import cn.bmob.v3.datatype.BmobFile;

/**
 * Created by YQ on 2017/11/30.
 * 主页显示数据
 */

public class HomePage extends BmobObject {
    private String homePageId;
    //搜索框的热搜词
    private List<String> searchEdit;
    //定位地址id
    private String addressCode;
    //定位地址名称
    private String addressName;
    //轮播图
    private List<BmobFile> hotPics;
    //热门项目，风筝，篮球····
    private List<HotProject> hotProjects;
    //场馆精选
    private List<BmobFile> cgjx;

    public HomePage() {
    }

    public HomePage(String homePageId, List<String> searchEdit, String addressCode, String addressName, List<BmobFile> hotPics, List<HotProject> hotProjects, List<BmobFile> cgjx) {
        this.homePageId = homePageId;
        this.searchEdit = searchEdit;
        this.addressCode = addressCode;
        this.addressName = addressName;
        this.hotPics = hotPics;
        this.hotProjects = hotProjects;
        this.cgjx = cgjx;
    }

    public String getHomepageId() {
        return homePageId;
    }

    public void setHomepageId(String homePageId) {
        this.homePageId = homePageId;
    }

    public List<String> getSearchEdit() {
        return searchEdit;
    }

    public void setSearchEdit(List<String> searchEdit) {
        this.searchEdit = searchEdit;
    }

    public String getAddressId() {
        return addressCode;
    }

    public void setAddressId(String addressCode) {
        this.addressCode = addressCode;
    }

    public String getAddressName() {
        return addressName;
    }

    public void setAddressName(String addressName) {
        this.addressName = addressName;
    }

    public List<BmobFile> getHotPic() {
        return hotPics;
    }

    public void setHotPic(List<BmobFile> hotPics) {
        this.hotPics = hotPics;
    }

    public List<HotProject> getHotProjects() {
        return hotProjects;
    }

    public void setHotProjects(List<HotProject> hotProjects) {
        this.hotProjects = hotProjects;
    }

    public List<BmobFile> getCgjx() {
        return cgjx;
    }

    public void setCgjx(List<BmobFile> cgjx) {
        this.cgjx = cgjx;
    }
}
