package com.tuananh.restaurant.manager.view.activity.board;

import android.util.Log;

import com.restaurant.tuananh.mvvm.BaseViewModel;
import com.tuananh.restaurant.manager.database.DatabaseManager;
import com.tuananh.restaurant.manager.databinding.ActivityBoardBinding;
import com.tuananh.restaurant.manager.model.Constant;
import com.tuananh.restaurant.manager.model.board.Board;
import com.tuananh.restaurant.manager.model.commodity.Commodity;
import com.tuananh.restaurant.manager.model.commodity.GroupCommodity;
import com.tuananh.restaurant.manager.utils.CommonUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by framgia on 05/05/2017.
 */
public class BoardActivityViewModel extends BaseViewModel<ActivityBoardBinding, BoardActivity> {
    private List<Commodity> mCommoditySelectedList = new ArrayList<>();
    private List<GroupCommodity> mGroupCommodityList = new ArrayList<>();
    private List<Commodity> mCommodityList = new ArrayList<>();
    private Board mBoard;

    @Override
    public void onAttached(boolean isFirst) {
        super.onAttached(isFirst);
        loadData();
        getView().showCommoditySelectedList(mCommoditySelectedList);
        getView().showGroupCommodityList(mGroupCommodityList);
        updateTotalCost();
    }

    private void loadData() {
        int idBoard = getIntent().getIntExtra(Constant.KEY_BOARD_ID, Constant.ID_BOARD_DEFAULT);
        mBoard = idBoard != Constant.ID_BOARD_DEFAULT ?
            DatabaseManager.getInstance(getContext()).getBoardById(idBoard) : new Board();
        mGroupCommodityList = DatabaseManager.getInstance(getContext()).getGroupCommodityAll();
        loadDataGroupCommodityList(Constant.ID_GROUP_COMMODITY);
        mCommoditySelectedList.addAll(
            DatabaseManager.getInstance(getContext()).getCommoditySelectedInBoardList(idBoard));
    }

    public void updateTotalCost() {
        getView().showTotalCost(getTotalCost());
    }

    public boolean isSelectedCommodity() {
        return !CommonUtils.isEmptyList(mCommoditySelectedList);
    }

    public String getNameBoard() {
        return mBoard.getNameBoard();
    }

    public Long getTotalCost() {
        Long totalCost = 0L;
        for (Commodity commodity : mCommoditySelectedList) {
            totalCost += commodity.getTotalCost();
        }
        return totalCost;
    }

    public void saveData() {
        if (mBoard.getId() != Board.ID_DEFAULT) {
            Log.d("TAG:", "isSave = " + mBoard.getIsSave());
            if (mBoard.getIsSave() == Constant.FALSE) {
                for (Commodity commodity : mCommoditySelectedList) {
                    DatabaseManager.getInstance(getContext())
                        .addBoardCommodity(mBoard.getId(), commodity.getId(),
                            commodity.getNumber());
                }
                mBoard.setIsSave(Constant.TRUE);
            } else {
                for (Commodity commodity : mCommoditySelectedList) {
                    DatabaseManager.getInstance(getContext())
                        .updateBoardCommodity(mBoard.getId(), commodity.getId(),
                            commodity.getNumber());
                }
            }
        }
    }

    public void loadDataGroupCommodityList(int position) {
        unSelectedGroupCommodity();
        GroupCommodity groupCommodity = mGroupCommodityList.get(position);
        groupCommodity.setSelected(true);
        mCommodityList.clear();
        if (position != 0) {
            mCommodityList.addAll(DatabaseManager.getInstance(getContext())
                .getCommodityAllByIdGroupCommodity(groupCommodity.getId()));
        } else {
            mCommodityList.addAll(DatabaseManager.getInstance(getContext())
                .getCommodityAllCommon());
        }
        getView().showCommodityList(mCommodityList);
    }

    public void unSelectedGroupCommodity() {
        for (GroupCommodity groupCommodity : mGroupCommodityList) {
            groupCommodity.setSelected(false);
        }
    }

    public int searchCommodity(Commodity commoditySearch, List<Commodity> commodityList) {
        int index = Constant.KEY_NOT_FOUND;
        for (Commodity commodity : commodityList) {
            if (commodity.getId() == commoditySearch.getId()) {
                index = commodityList.indexOf(commodity);
            }
        }
        return index;
    }

    public void removeSelectedList(int position) {
        mCommoditySelectedList.remove(position);
        getView().showCommoditySelectedList(mCommoditySelectedList);
    }

    public void updateSelectedList(int position) {
        Commodity commodity = mCommodityList.get(position);
        int index = searchCommodity(commodity, mCommoditySelectedList);
        if (index != Constant.KEY_NOT_FOUND) {
            Commodity commoditySelected = mCommoditySelectedList.get(index);
            commoditySelected.setNumber(commoditySelected.getNumber() + Constant.ADD_NUMBER);
        } else {
            mCommoditySelectedList.add(commodity);
        }
        getView().showCommoditySelectedList(mCommoditySelectedList);
    }
}
