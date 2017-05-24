package com.tuananh.restaurant.manager.model.board;

import com.tuananh.restaurant.manager.model.Constant;

/**
 * Created by framgia on 16/09/2016.
 */
public class Board {
    public final static int ID_BOARD_DEFAULT = -69;
    private int mIdBoard;
    private int mIdForGroupBoard;
    private String mNameBoard;
    private int mIsSave;
    private int mIsPaid;

    public Board() {
        mIdBoard = ID_BOARD_DEFAULT;
    }

    public Board(int idBoard, int idForGroupBoard, String nameBoard, int isSave, int isPaid) {
        mIdBoard = idBoard;
        mIdForGroupBoard = idForGroupBoard;
        mNameBoard = nameBoard;
        mIsSave = isSave;
        mIsPaid = isPaid;
    }

    public Board(int idForGroupBoard, String nameBoard, int isSave, int isPaid) {
        mIdForGroupBoard = idForGroupBoard;
        mNameBoard = nameBoard;
        mIsSave = isSave;
        mIsPaid = isPaid;
    }

    public int getIdBoard() {
        return mIdBoard;
    }

    public String getNameBoard() {
        return mNameBoard;
    }

    public int getIdForGroupBoard() {
        return mIdForGroupBoard;
    }

    public boolean isSave() {
        return mIsSave == Constant.TRUE;
    }

    public void setIsSave(int isSave) {
        mIsSave = isSave;
    }

    public boolean isPaid() {
        return mIsPaid == Constant.TRUE;
    }

    public void setIsPaid(int isPaid) {
        mIsPaid = isPaid;
    }
}
