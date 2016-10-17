package com.tuananh.restaurant.manager.data.model.commodity;

/**
 * Created by framgia on 09/10/2016.
 */
public class Commodity {
    private int mId;
    private String mName;
    private int mCost;
    private int mIdGroupCommodity;
    private boolean mIsCommon;

    public Commodity(int id, String name, int cost, int idGroupCommodity, boolean isCommon) {
        mId = id;
        mName = name;
        mCost = cost;
        mIdGroupCommodity = idGroupCommodity;
        mIsCommon = isCommon;
    }

    public Commodity(String name, int cost, int idGroupCommodity, boolean isCommon) {
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

    public boolean isCommon() {
        return mIsCommon;
    }
}
