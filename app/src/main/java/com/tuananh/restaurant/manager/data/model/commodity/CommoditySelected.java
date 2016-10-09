package com.tuananh.restaurant.manager.data.model.commodity;

/**
 * Created by framgia on 11/09/2016.
 */
public class CommoditySelected extends Commodity {
    private int mNumber;

    public CommoditySelected(int id, int name, int cost, int idGroupCommodity, int number) {
        super(id, name, cost, idGroupCommodity);
        mNumber = number;
    }

    public CommoditySelected(int name, int cost, int idGroupCommodity, int number) {
        super(name, cost, idGroupCommodity);
        mNumber = number;
    }

    public int getNumber() {
        return mNumber;
    }
}
