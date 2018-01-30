package com.yyq.datalib.query;

import java.util.List;

/**
 * Created by yangyouqin on 2018/1/4.
 */

public class SortQuery {

    //篮球，游泳····，从主页进入时主需要填这个参数
    String type;

    //  1表示离我最近，2表示评分最高，3.表示价格最低 4.表示价格最高，不需要就写0
    int sort;

    // 分类筛选中的价格区间的最低价
    double minPrice;

    // 分类筛选中的价格区间的最高价
    double maxPrice;

    //更多条件，如果是选的培训和比赛，请将值传到type中
    List<String> configures;

    //分页的参数
    int skip;

    public SortQuery() {
    }

    public SortQuery(String type, int sort, double minPrice, double maxPrice, List<String> configures, int skip) {
        this.type = type;
        this.sort = sort;
        this.minPrice = minPrice;
        this.maxPrice = maxPrice;
        this.configures = configures;
        this.skip = skip;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
    //  1表示离我最近，2表示评分最高，3.表示价格最低 4.表示价格最高，不需要就写0
    public int getSort() {
        return sort;
    }

    public void setSort(int sort) {
        this.sort = sort;
    }

    public double getMinPrice() {
        return minPrice;
    }

    public void setMinPrice(double minPrice) {
        this.minPrice = minPrice;
    }

    public double getMaxPrice() {
        return maxPrice;
    }

    public void setMaxPrice(double maxPrice) {
        this.maxPrice = maxPrice;
    }

    public List<String> getConfigures() {
        return configures;
    }

    public void setConfigures(List<String> configures) {
        this.configures = configures;
    }

    public int getSkip() {
        return skip;
    }

    public void setSkip(int skip) {
        this.skip = skip;
    }
}
