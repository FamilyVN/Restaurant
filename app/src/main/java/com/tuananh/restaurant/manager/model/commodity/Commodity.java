package com.tuananh.restaurant.manager.model.commodity;

import com.tuananh.restaurant.manager.model.Constant;
import com.tuananh.restaurant.manager.utils.CommonUtils;

import java.io.Serializable;

/**
 * Created by framgia on 09/10/2016.
 */
public class Commodity implements Serializable {
    private int mIdCommodity;
    private String mNameCommodity;
    private int mCostCommodity;
    private int mIdForGroupCommodity;
    private int mIsCommonCommodity;
    private int mNumberCommodity = 1;
    private int mIsPaid;

    public Commodity(int idCommodity, String nameCommodity, int costCommodity,
                     int idForGroupCommodity, int isCommonCommodity, int numberCommodity,
                     int isPaid) {
        mIdCommodity = idCommodity;
        mNameCommodity = nameCommodity;
        mCostCommodity = costCommodity;
        mIdForGroupCommodity = idForGroupCommodity;
        mIsCommonCommodity = isCommonCommodity;
        mNumberCommodity = numberCommodity;
        mIsPaid = isPaid;
    }

    public Commodity(int idCommodity, String nameCommodity, int costCommodity,
                     int idForGroupCommodity, int isCommonCommodity, int isPaid) {
        mIdCommodity = idCommodity;
        mNameCommodity = nameCommodity;
        mCostCommodity = costCommodity;
        mIdForGroupCommodity = idForGroupCommodity;
        mIsCommonCommodity = isCommonCommodity;
        mIsPaid = isPaid;
    }

    public Commodity(String nameCommodity, int costCommodity, int idForGroupCommodity,
                     int isCommonCommodity) {
        mNameCommodity = nameCommodity;
        mCostCommodity = costCommodity;
        mIdForGroupCommodity = idForGroupCommodity;
        mIsCommonCommodity = isCommonCommodity;
    }

    public Commodity(String nameCommodity, int costCommodity, int idForGroupCommodity,
                     int isCommonCommodity, int numberCommodity) {
        mNameCommodity = nameCommodity;
        mCostCommodity = costCommodity;
        mIdForGroupCommodity = idForGroupCommodity;
        mIsCommonCommodity = isCommonCommodity;
        mNumberCommodity = numberCommodity;
    }

    public int getIdCommodity() {
        return mIdCommodity;
    }

    public String getNameCommodity() {
        return mNameCommodity;
    }

    public int getCostCommodity() {
        return mCostCommodity;
    }

    public String getStringCost() {
        return String
            .format("%s đ", CommonUtils.convertToMoney(String.valueOf(getCostCommodity())));
    }

    public int getIdForGroupCommodity() {
        return mIdForGroupCommodity;
    }

    public boolean isCommonCommodity() {
        return mIsCommonCommodity == Constant.COMMON;
    }

    public int getNumberCommodity() {
        return mNumberCommodity;
    }

    public Commodity setNumberCommodity(int numberCommodity) {
        mNumberCommodity = numberCommodity;
        return this;
    }

    public Long getTotalCost() {
        return (long) (getCostCommodity() * getNumberCommodity());
    }

    public String getStringTotalCost() {
        return CommonUtils.convertToMoney(getTotalCost().toString()) + " đ";
    }

    public Commodity setIsPaid(int isPaid) {
        mIsPaid = isPaid;
        return this;
    }

    public boolean isPaid() {
        return mIsPaid == Constant.TRUE;
    }
}
