package com.tuananh.restaurant.manager.data.model.commodity;

/**
 * Created by framgia on 09/10/2016.
 */
public class Commodity {
    private int mId;
    private int mName;
    private int mCost;
    private int mIdGroupCommodity;

    public Commodity(int id, int name, int cost, int idGroupCommodity) {
        mId = id;
        mName = name;
        mCost = cost;
        mIdGroupCommodity = idGroupCommodity;
    }

    public Commodity(int name, int cost, int idGroupCommodity) {
        mName = name;
        mCost = cost;
        mIdGroupCommodity = idGroupCommodity;
    }

    public int getId() {
        return mId;
    }

    public int getName() {
        return mName;
    }

    public int getCost() {
        return mCost;
    }

    public int getIdGroupCommodity() {
        return mIdGroupCommodity;
    }
}
