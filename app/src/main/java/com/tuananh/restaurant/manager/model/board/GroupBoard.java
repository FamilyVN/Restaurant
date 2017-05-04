package com.tuananh.restaurant.manager.model.board;

/**
 * Created by framgia on 17/09/2016.
 */
public class GroupBoard {
    private int mId;
    private String mNameGroupBoard;
    private boolean mIsSelected;

    public GroupBoard(int id, String nameGroupBoard) {
        mId = id;
        mNameGroupBoard = nameGroupBoard;
    }

    public GroupBoard(String nameGroupBoard) {
        mNameGroupBoard = nameGroupBoard;
    }

    public int getId() {
        return mId;
    }

    public String getNameGroupBoard() {
        return mNameGroupBoard;
    }

    public boolean isSelected() {
        return mIsSelected;
    }

    public void setSelected(boolean selected) {
        mIsSelected = selected;
    }
}
