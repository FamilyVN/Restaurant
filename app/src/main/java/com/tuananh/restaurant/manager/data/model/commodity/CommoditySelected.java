package com.tuananh.restaurant.manager.data.model.commodity;

/**
 * Created by framgia on 11/09/2016.
 */
public class CommoditySelected extends Commodity {
    private int mNumber;

    public CommoditySelected(int id, String name, int cost, int idGroupCommodity, boolean isCommon,
                             int number) {
        super(id, name, cost, idGroupCommodity, isCommon);
        mNumber = number;
    }

    public CommoditySelected(String name, int cost, int idGroupCommodity, boolean isCommon,
                             int number) {
        super(name, cost, idGroupCommodity, isCommon);
        mNumber = number;
    }

    public int getNumber() {
        return mNumber;
    }
}
