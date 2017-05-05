package com.tuananh.restaurant.manager.view.activity.board;

import com.restaurant.tuananh.mvvm.BaseViewModel;
import com.tuananh.restaurant.manager.data.model.commodity.Commodity;
import com.tuananh.restaurant.manager.database.DatabaseManager;
import com.tuananh.restaurant.manager.databinding.ActivityBoardBinding;
import com.tuananh.restaurant.manager.model.Constant;
import com.tuananh.restaurant.manager.model.board.Board;
import com.tuananh.restaurant.manager.utils.CommonUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by framgia on 05/05/2017.
 */
public class BoardActivityViewModel extends BaseViewModel<ActivityBoardBinding, BoardActivity> {
    private List<Commodity> mCommoditySelectedList = new ArrayList<>();
    private Board mBoard;

    @Override
    public void onAttached(boolean isFirst) {
        super.onAttached(isFirst);
        int id = getIntent().getIntExtra(Constant.KEY_BOARD_ID, Constant.ID_BOARD_DEFAULT);
        mBoard = id != Constant.ID_BOARD_DEFAULT ?
            DatabaseManager.getInstance(getContext()).getBoardById(id) : new Board();
        getView().showCommoditySelectedList(mCommoditySelectedList);
    }

    public void updateTotalCost() {
        int totalCost = 0;
        for (Commodity commodity : mCommoditySelectedList) {
            totalCost += commodity.getTotalCost();
        }
        getView().showTotalCost(totalCost);
    }

    public boolean isSelectedCommodity() {
        return !CommonUtils.isEmptyList(mCommoditySelectedList);
    }

    public String getNameBoard() {
        return mBoard.getNameBoard();
    }

    public void saveData() {
        if (mBoard.getId() != Board.ID_DEFAULT) {
            for (Commodity commodity : mCommoditySelectedList) {
                DatabaseManager.getInstance(getContext())
                    .addBoardCommodity(mBoard.getId(), commodity.getId(), commodity.getNumber());
            }
            mBoard.setIsSave(Constant.TRUE);
        }
    }
}
