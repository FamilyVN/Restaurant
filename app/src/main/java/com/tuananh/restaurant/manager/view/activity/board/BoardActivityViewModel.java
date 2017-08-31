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
    private static final String TAG = "TAG: " + BoardActivityViewModel.class.getSimpleName();
    private List<Commodity> mCommoditySelectedList = new ArrayList<>();
    private List<GroupCommodity> mGroupCommodityList = new ArrayList<>();
    private List<Commodity> mCommodityList = new ArrayList<>();
    private Board mBoard;
    private boolean mIsQuickPay;

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
        mIsQuickPay = idBoard == Constant.ID_BOARD_DEFAULT;
        Log.d(TAG, "idBoard = " + idBoard);
        mBoard = !mIsQuickPay ?
            DatabaseManager.getInstance(getContext()).getBoardById(idBoard) : new Board();
        getView().updateButton(!mBoard.isSave());
        if (mIsQuickPay) {
            getView().updateButton(false);
        }
        List<GroupCommodity> groupCommodityList =
            DatabaseManager.getInstance(getContext()).getGroupCommodityAll();
        if (!CommonUtils.isEmptyList(groupCommodityList)) {
            mGroupCommodityList.addAll(groupCommodityList);
        }
        loadDataGroupCommodityList(Constant.ID_GROUP_COMMODITY);
        List<Commodity> commodityList =
            DatabaseManager.getInstance(getContext()).getCommoditySelectedInBoardList(idBoard);
        if (!CommonUtils.isEmptyList(commodityList)) {
            mCommoditySelectedList.addAll(commodityList);
        }
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

    public Board getBoard() {
        return mBoard;
    }

    public Long getTotalCost() {
        Long totalCost = 0L;
        for (Commodity commodity : mCommoditySelectedList) {
            totalCost += commodity.getTotalCost();
        }
        return totalCost;
    }

    public boolean isQuickPay() {
        return mIsQuickPay;
    }

    public void saveData() {
        if (mBoard.getIdBoard() != Board.ID_BOARD_DEFAULT) {
            Log.d("TAG", "isSave = " + mBoard.isSave());
            if (!mBoard.isSave()) {
                for (Commodity commodity : mCommoditySelectedList) {
                    DatabaseManager.getInstance(getContext()).insertBoardCommodity(
                        mBoard.getIdBoard(), commodity.getIdCommodity(),
                        commodity.getNumberCommodity());
                }
                mBoard.setIsSave(Constant.TRUE);
                DatabaseManager.getInstance(getContext()).updateBoard(mBoard);
            } else {
                for (Commodity commodity : mCommoditySelectedList) {
                    DatabaseManager.getInstance(getContext()).updateBoardCommodity(
                        mBoard.getIdBoard(), commodity.getIdCommodity(),
                        commodity.getNumberCommodity());
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
            List<Commodity> commodityList = DatabaseManager.getInstance(getContext())
                .getCommodityAllByIdGroupCommodity(groupCommodity.getIdGroupCommodity());
            if (!CommonUtils.isEmptyList(commodityList)) {
                mCommodityList.addAll(commodityList);
            }
        } else {
            List<Commodity> commodityCommonList =
                DatabaseManager.getInstance(getContext()).getCommodityAllCommon();
            if (!CommonUtils.isEmptyList(commodityCommonList)) {
                mCommodityList.addAll(commodityCommonList);
            }
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
            if (commodity.getIdCommodity() == commoditySearch.getIdCommodity()) {
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
            commoditySelected
                .setNumberCommodity(commoditySelected.getNumberCommodity() + Constant.ADD_NUMBER);
        } else {
            mCommoditySelectedList.add(commodity);
        }
        getView().showCommoditySelectedList(mCommoditySelectedList);
    }
}
