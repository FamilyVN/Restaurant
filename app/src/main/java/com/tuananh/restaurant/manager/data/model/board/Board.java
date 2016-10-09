package com.tuananh.restaurant.manager.data.model.board;

/**
 * Created by framgia on 16/09/2016.
 */
public class Board {
    private int mId;
    private int mIdGroup;
    private String mNameBoard;

    public Board(int id, int idGroup, String nameBoard) {
        mId = id;
        mIdGroup = idGroup;
        mNameBoard = nameBoard;
    }

    public Board(int idGroup, String nameBoard) {
        mIdGroup = idGroup;
        mNameBoard = nameBoard;
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
}
