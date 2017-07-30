package com.tuananh.restaurant.manager.model.board;

import com.tuananh.restaurant.manager.model.Constant;
import com.tuananh.restaurant.manager.model.editboard.EditRoomBoard;

/**
 * Created by framgia on 16/09/2016.
 */
public class Board {
    public static final int ID_BOARD_DEFAULT = -69;
    private int mIdBoard;
    private int mIdForGroupBoard;
    private String mNameBoard;
    private int mIsSave;
    private int mIsUsed;
    private EditRoomBoard mEditRoomBoard;

    public Board() {
        mIdBoard = ID_BOARD_DEFAULT;
    }

    public Board(int idBoard, int idForGroupBoard, String nameBoard, int isSave, int isUsed) {
        mIdBoard = idBoard;
        mIdForGroupBoard = idForGroupBoard;
        mNameBoard = nameBoard;
        mIsSave = isSave;
        mIsUsed = isUsed;
    }

    public Board(int idForGroupBoard, String nameBoard, int isSave, int isUsed) {
        mIdForGroupBoard = idForGroupBoard;
        mNameBoard = nameBoard;
        mIsSave = isSave;
        mIsUsed = isUsed;
    }

    public int getIdBoard() {
        return mIdBoard;
    }

    public Board setIdBoard(int idBoard) {
        mIdBoard = idBoard;
        return this;
    }

    public String getNameBoard() {
        return mNameBoard;
    }

    public Board setNameBoard(String nameBoard) {
        mNameBoard = nameBoard;
        return this;
    }

    public int getIdForGroupBoard() {
        return mIdForGroupBoard;
    }

    public Board setIdForGroupBoard(int idForGroupBoard) {
        mIdForGroupBoard = idForGroupBoard;
        return this;
    }

    public boolean isSave() {
        return mIsSave == Constant.TRUE;
    }

    public Board setIsSave(int isSave) {
        mIsSave = isSave;
        return this;
    }

    public boolean isUsed() {
        return mIsUsed == Constant.TRUE;
    }

    public Board setUsed(int used) {
        mIsUsed = used;
        return this;
    }

    public EditRoomBoard getEditRoomBoard() {
        return mEditRoomBoard;
    }

    public Board setEditRoomBoard(EditRoomBoard editRoomBoard) {
        mEditRoomBoard = editRoomBoard;
        return this;
    }
}
