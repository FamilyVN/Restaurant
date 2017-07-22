package com.tuananh.restaurant.manager.model.editboard;

/**
 * Created by FRAMGIA\vu.tuan.anh on 22/07/2017.
 */
public class EditRoomBoard {
    private int mId;
    private String mNameEditBoard;

    public EditRoomBoard(int id, String nameEditBoard) {
        mId = id;
        mNameEditBoard = nameEditBoard;
    }

    public int getId() {
        return mId;
    }

    public EditRoomBoard setId(int id) {
        mId = id;
        return this;
    }

    public String getNameEditBoard() {
        return mNameEditBoard;
    }

    public EditRoomBoard setNameEditBoard(String nameEditBoard) {
        mNameEditBoard = nameEditBoard;
        return this;
    }
}
