package com.tuananh.restaurant.manager.model.commodity;

/**
 * Created by framgia on 09/10/2016.
 */
public class Commodity {
    private int mId;
    private String mName;
    private int mCost;
    private int mIdGroupCommodity;
    private int mIsCommon;
    private int mNumber;

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

    public Commodity(int id, String name, int cost, int idGroupCommodity, int isCommon,
                     int number) {
        mId = id;
        mName = name;
        mCost = cost;
        mIdGroupCommodity = idGroupCommodity;
        mIsCommon = isCommon;
        mNumber = number;
    }

    public Commodity(String name, int cost, int idGroupCommodity, int isCommon, int number) {
        mName = name;
        mCost = cost;
        mIdGroupCommodity = idGroupCommodity;
        mIsCommon = isCommon;
        mNumber = number;
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

    public String getStringCost() {
        return String.format("%d đ", mCost);
    }

    public int getIdGroupCommodity() {
        return mIdGroupCommodity;
    }

    public int getIsCommon() {
        return mIsCommon;
    }

    public int getNumber() {
        return mNumber;
    }

    public void setNumber(int number) {
        mNumber = number;
    }

    public int getTotalCost() {
        return mCost * mNumber;
    }

    public String getStringTotalCost() {
        return String.format("%d đ", getTotalCost());
    }
}
