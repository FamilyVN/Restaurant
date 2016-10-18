package com.tuananh.restaurant.manager.data.model.board;

/**
 * Created by framgia on 16/09/2016.
 */
public class Board {
    private int mId;
    private int mIdGroup;
    private String mNameBoard;
    private int mIsSave;

    public Board(int id, int idGroup, String nameBoard, int isSave) {
        mId = id;
        mIdGroup = idGroup;
        mNameBoard = nameBoard;
        mIsSave = isSave;
    }

    public Board(int idGroup, String nameBoard, int isSave) {
        mIdGroup = idGroup;
        mNameBoard = nameBoard;
        mIsSave = isSave;
    }

    public int getId() {
        return mId;
    }

    public String getNameBoard() {
        return mNameBoard;
    }

    public int getIdGroup() {
        return mIdGroup;
    }

    public int getIsSave() {
        return mIsSave;
    }
}
