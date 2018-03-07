package com.yyq.datalib.service.impl;

import android.content.Context;
import android.util.Log;

import com.yyq.datalib.javaBeans.Match;
import com.yyq.datalib.javaBeans.Place;
import com.yyq.datalib.javaBeans.Trains;
import com.yyq.datalib.models.SortModel;
import com.yyq.datalib.query.DealDate;
import com.yyq.datalib.query.SortQuery;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.QueryListener;


/**
 * Created by yangyouqin on 2018/1/4.
 */

public class AllListService implements IAllListService {

    OnGetDataListener mOnGetDataListener;

    OnGetDetailListener mOnGetDetailListener;

    //TODO:type为类型，篮球，足球。。。，sort是排序方式，具体到SortQuery里面看用法
    @Override
    public void getData(Context context, final SortQuery sortQuery) {
        final List<SortModel> list = new ArrayList<>();
        BmobQuery<Place> query = new BmobQuery<Place>();
        if (sortQuery.getType() != null) {
            query.addWhereEqualTo("placeType", sortQuery.getType());
        }
        if (sortQuery.getConfigures() != null) {
            final List<String> lists = new ArrayList<>();
            for (String s : sortQuery.getConfigures()) {
                if (!s.equals("培训") && !s.equals("比赛"))
                    lists.add(s);
            }
            if (lists.size() != 0) {
                query.addWhereContainsAll("configures", lists);
            }
        }
        if (sortQuery.getMaxPrice() != 0) {
            query.addWhereGreaterThanOrEqualTo("price", sortQuery.getMinPrice()).addWhereLessThanOrEqualTo("price", sortQuery.getMaxPrice());
        }
        //        query.setSkip(sortQuery.getSkip());
        query.findObjects(new FindListener<Place>() {

            @Override
            public void done(final List<Place> places, BmobException e) {
                if (e == null) {
                    //                    Log.i("MyTag","查询个数："+places.size());
                    BmobQuery<Match> query = new BmobQuery<Match>();

                    if (sortQuery.getType() != null) {
                        query.addWhereEqualTo("matchType", sortQuery.getType());
                    }
                    if (sortQuery.getConfigures() != null) {
                        final List<String> lists = new ArrayList<>();
                        for (String s : sortQuery.getConfigures()) {
                            if (!s.equals("培训") && !s.equals("比赛"))
                                lists.add(s);
                        }
                        if (lists.size() != 0) {
                            query.addWhereContainsAll("configures", lists);
                        }
                    }
                    if (sortQuery.getMaxPrice() != 0) {
                        query.addWhereGreaterThanOrEqualTo("price", sortQuery.getMinPrice()).addWhereLessThanOrEqualTo("price", sortQuery.getMaxPrice());
                    }
                    //                    query.setSkip(sortQuery.getSkip());
                    //                    query.setLimit(2);
                    query.findObjects(new FindListener<Match>() {

                        @Override
                        public void done(final List<Match> matchs, BmobException e) {
                            if (e == null) {
                                //                                Log.i("MyTag","查询个数："+matchs.size());
                                BmobQuery<Trains> query = new BmobQuery<Trains>();

                                if (sortQuery.getType() != null) {
                                    query.addWhereEqualTo("trainType", sortQuery.getType());
                                }
                                if (sortQuery.getConfigures() != null) {
                                    final List<String> lists = new ArrayList<>();
                                    for (String s : sortQuery.getConfigures()) {
                                        if (!s.equals("培训") && !s.equals("比赛"))
                                            lists.add(s);
                                    }
                                    if (lists.size() != 0) {
                                        query.addWhereContainsAll("configures", lists);
                                    }
                                }
                                if (sortQuery.getMaxPrice() != 0) {
                                    query.addWhereGreaterThanOrEqualTo("price", sortQuery.getMinPrice()).addWhereLessThanOrEqualTo("price", sortQuery.getMaxPrice());
                                }
                                //                                query.setSkip(sortQuery.getSkip());
                                //                                query.setLimit(2);
                                query.findObjects(new FindListener<Trains>() {
                                    @Override
                                    public void done(List<Trains> trains, BmobException e) {
                                        if (e == null) {
                                            // Log.i("MyTag","查询个数："+trains.size());
                                            for (Place place : places) {
                                                Log.i("MyTag", place.getConfigures().toString());
                                            }
                                            if (mOnGetDataListener != null) {
                                                DealDate dealDate = new DealDate();
                                                if (sortQuery.getConfigures() != null) {
                                                    if (!sortQuery.getConfigures().contains("培训") && !sortQuery.getConfigures().contains("比赛")) {
                                                        list.addAll(dealDate.dealTrain(trains));
                                                        list.addAll(dealDate.dealMatch(matchs));
                                                        list.addAll(dealDate.dealPlace(places));
                                                    } else {
                                                        if (sortQuery.getConfigures().contains("培训"))
                                                            list.addAll(dealDate.dealTrain(trains));
                                                        if (sortQuery.getConfigures().contains("比赛"))
                                                            list.addAll(dealDate.dealMatch(matchs));
                                                    }
                                                } else {
                                                    list.addAll(dealDate.dealTrain(trains));
                                                    list.addAll(dealDate.dealMatch(matchs));
                                                    list.addAll(dealDate.dealPlace(places));
                                                }

                                                //1表示离我最近，2表示评分最高，3.表示价格最低 4.表示价格最高，不需要就写0
                                                if (sortQuery.getSort() != 0) {
                                                    if (sortQuery.getSort() == 2) {
                                                        Collections.sort(list, new Comparator<SortModel>() {
                                                            public int compare(SortModel o1, SortModel o2) {

                                                                //按照评分降序
                                                                if (o1.getGrade() < o2.getGrade()) {
                                                                    return 1;
                                                                }
                                                                if (o1.getGrade() == o2.getGrade()) {
                                                                    return 0;
                                                                }
                                                                return -1;
                                                            }
                                                        });
                                                    } else if (sortQuery.getSort() == 4) {
                                                        Collections.sort(list, new Comparator<SortModel>() {
                                                            public int compare(SortModel o1, SortModel o2) {

                                                                //按照价格降序
                                                                if (o1.getPrice() < o2.getPrice()) {
                                                                    return 1;
                                                                }
                                                                if (o1.getPrice() == o2.getPrice()) {
                                                                    return 0;
                                                                }
                                                                return -1;
                                                            }
                                                        });
                                                    } else if (sortQuery.getSort() == 3) {
                                                        Collections.sort(list, new Comparator<SortModel>() {
                                                            public int compare(SortModel o1, SortModel o2) {

                                                                //按照价格升序
                                                                if (o1.getPrice() > o2.getPrice()) {
                                                                    return 1;
                                                                }
                                                                if (o1.getPrice() == o2.getPrice()) {
                                                                    return 0;
                                                                }
                                                                return -1;
                                                            }
                                                        });
                                                    }
                                                }
                                                int skip1 = 5 * sortQuery.getSkip();
                                                int skip2 = (sortQuery.getSkip() + 1) * 5;
                                                if (skip2 <= list.size())
                                                    mOnGetDataListener.getData(list.subList(skip1, skip2));
                                                else if (skip1 < list.size() && skip2 > list.size())
                                                    mOnGetDataListener.getData(list.subList(skip1, list.size()));
                                                else {
                                                    mOnGetDataListener.getData(new ArrayList<SortModel>());
                                                    Log.i("MyTag", "加载完毕");
                                                }
                                            }

                                        } else {
                                            Log.i("MyTag", "失败：" + e.getErrorCode() + e.getMessage());
                                        }
                                    }

                                });

                            } else {
                                Log.i("MyTag", "失败：" + e.getErrorCode() + e.getMessage());
                            }
                        }

                    });
                } else {
                    Log.i("MyTag", "失败：" + e.getErrorCode() + e.getMessage());
                }
            }

        });

    }

    //TODO:type为场地，培训，比赛  即当前点击item的getType，objecId是当前点击的item的objectId
    @Override
    public void getDetails(final Context context, String type, final String objectId) {
        if (type.equals("场地")) {
            BmobQuery<Place> query = new BmobQuery<Place>();
            query.getObject(objectId, new QueryListener<Place>() {

                @Override
                public void done(Place object, BmobException e) {
                    if (e == null) {
                        Log.i("MyTag", "获取详情成功：" + object.toString());
                        if (mOnGetDetailListener != null) {
                            mOnGetDetailListener.getDetail(object);
                        }
                    } else {
                        Log.i("MyTag", "失败：" + e.getMessage() + "," + e.getErrorCode());
                    }
                }

            });
        } else if (type.equals("培训")) {
            BmobQuery<Trains> query = new BmobQuery<Trains>();
            query.getObject(objectId, new QueryListener<Trains>() {

                @Override
                public void done(Trains object, BmobException e) {
                    if (e == null) {
                        if (mOnGetDetailListener != null) {
                            mOnGetDetailListener.getDetail(object);
                        }
                    } else {
                        Log.i("MyTag", "失败：" + e.getMessage() + "," + e.getErrorCode());
                    }
                }

            });
        } else if (type.equals("比赛")) {
            BmobQuery<Match> query = new BmobQuery<Match>();
            query.getObject(objectId, new QueryListener<Match>() {

                @Override
                public void done(Match object, BmobException e) {
                    if (e == null) {
                        if (mOnGetDetailListener != null) {
                            mOnGetDetailListener.getDetail(object);
                        }
                    } else {
                        Log.i("MyTag", "失败：" + e.getMessage() + "," + e.getErrorCode());
                    }
                }

            });
        }
    }

    //TODO:获取详情监听
    public void setOnGetDetailListener(OnGetDetailListener onGetDetailListener) {
        mOnGetDetailListener = onGetDetailListener;
    }

    //TODO:接口   获取详情查询
    public interface OnGetDetailListener {
        void getDetail(Object object);
    }

    //TODO:条件查询监听
    public void setOnGetDataListener(OnGetDataListener onGetDataListener) {
        mOnGetDataListener = onGetDataListener;
    }

    //TODO:接口   条件查询
    public interface OnGetDataListener {
        void getData(List<SortModel> list);
    }
}
