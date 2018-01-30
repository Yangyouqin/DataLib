package com.yyq.datalib.javaBeans;

import java.util.List;

import cn.bmob.v3.BmobObject;
import cn.bmob.v3.datatype.BmobFile;

/**
 * Created by YQ on 2017/12/19.
 */

public class Collection extends BmobObject {
    private String userId;

    private String placeId;

    private String trainId;

    private String matchId;

    private List<BmobFile> pictures;

    private List<BmobFile> vedio;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPlaceId() {
        return placeId;
    }

    public void setPlaceId(String placeId) {
        this.placeId = placeId;
    }

    public String getTrainId() {
        return trainId;
    }

    public void setTrainId(String trainId) {
        this.trainId = trainId;
    }

    public String getMatchId() {
        return matchId;
    }

    public void setMatchId(String matchId) {
        this.matchId = matchId;
    }

    public List<BmobFile> getPictures() {
        return pictures;
    }

    public void setPictures(List<BmobFile> pictures) {
        this.pictures = pictures;
    }

    public List<BmobFile> getVedio() {
        return vedio;
    }

    public void setVedio(List<BmobFile> vedio) {
        this.vedio = vedio;
    }

    @Override
    public String toString() {
        return "Collection{" +
                "userId='" + userId + '\'' +
                ", placeId='" + placeId + '\'' +
                ", trainId='" + trainId + '\'' +
                ", matchId='" + matchId + '\'' +
                ", pictures=" + pictures +
                ", vedio=" + vedio +
                '}';
    }
}
