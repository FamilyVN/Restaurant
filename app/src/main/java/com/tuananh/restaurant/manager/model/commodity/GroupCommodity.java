package com.tuananh.restaurant.manager.model.commodity;

/**
 * Created by framgia on 09/10/2016.
 */
public class GroupCommodity {
    private int mIdGroupCommodity;
    private String mNameGroupCommodity;
    private boolean mSelected;

    public GroupCommodity(int idGroupCommodity, String nameGroupCommodity) {
        mIdGroupCommodity = idGroupCommodity;
        mNameGroupCommodity = nameGroupCommodity;
    }

    public GroupCommodity(String nameGroupCommodity) {
        mNameGroupCommodity = nameGroupCommodity;
    }

    public int getIdGroupCommodity() {
        return mIdGroupCommodity;
    }

    public String getNameGroupCommodity() {
        return mNameGroupCommodity;
    }

    public boolean isSelected() {
        return mSelected;
    }

    public GroupCommodity setSelected(boolean selected) {
        mSelected = selected;
        return this;
    }
}
