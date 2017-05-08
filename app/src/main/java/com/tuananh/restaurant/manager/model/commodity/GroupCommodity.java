package com.tuananh.restaurant.manager.model.commodity;

/**
 * Created by framgia on 09/10/2016.
 */
public class GroupCommodity {
    private int mId;
    private String mNameGroupCommodity;
    private boolean mSelected;

    public GroupCommodity(int id, String nameGroupCommodity) {
        mId = id;
        mNameGroupCommodity = nameGroupCommodity;
    }

    public GroupCommodity(String nameGroupCommodity) {
        mNameGroupCommodity = nameGroupCommodity;
    }

    public int getId() {
        return mId;
    }

    public String getNameGroupCommodity() {
        return mNameGroupCommodity;
    }

    public boolean isSelected() {
        return mSelected;
    }

    public void setSelected(boolean selected) {
        mSelected = selected;
    }
}
