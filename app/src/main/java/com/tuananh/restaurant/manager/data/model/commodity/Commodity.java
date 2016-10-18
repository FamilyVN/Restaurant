package com.tuananh.restaurant.manager.data.model.commodity;

/**
 * Created by framgia on 09/10/2016.
 */
public class Commodity {
    private int mId;
    private String mName;
    private int mCost;
    private int mIdGroupCommodity;
    private int mIsCommon;

    public Commodity(int id, String name, int cost, int idGroupCommodity, int isCommon) {
        mId = id;
        mName = name;
        mCost = cost;
        mIdGroupCommodity = idGroupCommodity;
        mIsCommon = isCommon;
    }

    public Commodity(String name, int cost, int idGroupCommodity, int isCommon) {
        mName = name;
        mCost = cost;
        mIdGroupCommodity = idGroupCommodity;
        mIsCommon = isCommon;
    }

    public int getId() {
        return mId;
    }

    public String getName() {
        return mName;
    }

    public int getCost() {
        return mCost;
    }

    public int getIdGroupCommodity() {
        return mIdGroupCommodity;
    }

    public int getIsCommon() {
        return mIsCommon;
    }
}
