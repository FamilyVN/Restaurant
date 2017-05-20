package com.tuananh.restaurant.manager.model.commodity;

/**
 * Created by framgia on 20/05/2017.
 */
public class BoardCommodity {
    private int mIdBoardCommodity;
    private int mIdBoard;
    private int mIdCommodity;
    private int mNumberCommodityInBoard;

    public BoardCommodity(int idBoardCommodity, int idBoard, int idCommodity,
                          int numberCommodityInBoard) {
        mIdBoardCommodity = idBoardCommodity;
        mIdBoard = idBoard;
        mIdCommodity = idCommodity;
        mNumberCommodityInBoard = numberCommodityInBoard;
    }

    public int getIdBoardCommodity() {
        return mIdBoardCommodity;
    }

    public void setIdBoardCommodity(int idBoardCommodity) {
        mIdBoardCommodity = idBoardCommodity;
    }

    public int getIdBoard() {
        return mIdBoard;
    }

    public void setIdBoard(int idBoard) {
        mIdBoard = idBoard;
    }

    public int getIdCommodity() {
        return mIdCommodity;
    }

    public void setIdCommodity(int idCommodity) {
        mIdCommodity = idCommodity;
    }

    public int getNumberCommodityInBoard() {
        return mNumberCommodityInBoard;
    }

    public void setNumberCommodityInBoard(int numberCommodityInBoard) {
        mNumberCommodityInBoard = numberCommodityInBoard;
    }
}
