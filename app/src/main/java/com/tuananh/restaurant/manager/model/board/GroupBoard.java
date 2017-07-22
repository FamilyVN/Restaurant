package com.tuananh.restaurant.manager.model.board;

/**
 * Created by framgia on 17/09/2016.
 */
public class GroupBoard {
    private int mIdGroupBoard;
    private String mNameGroupBoard;
    private boolean mIsSelected;

    public GroupBoard(int idGroupBoard, String nameGroupBoard) {
        mIdGroupBoard = idGroupBoard;
        mNameGroupBoard = nameGroupBoard;
    }

    public GroupBoard(String nameGroupBoard) {
        mNameGroupBoard = nameGroupBoard;
    }

    public int getIdGroupBoard() {
        return mIdGroupBoard;
    }

    public String getNameGroupBoard() {
        return mNameGroupBoard;
    }

    public boolean isSelected() {
        return mIsSelected;
    }

    public GroupBoard setSelected(boolean selected) {
        mIsSelected = selected;
        return this;
    }
}
