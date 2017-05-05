package com.tuananh.restaurant.manager.view.activity.sell;

import com.restaurant.tuananh.mvvm.BaseViewModel;
import com.tuananh.restaurant.manager.database.DatabaseManager;
import com.tuananh.restaurant.manager.databinding.ActivitySellBinding;
import com.tuananh.restaurant.manager.model.Constant;
import com.tuananh.restaurant.manager.model.board.Board;
import com.tuananh.restaurant.manager.model.board.GroupBoard;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by framgia on 04/05/2017.
 */
public class SellActivityViewModel extends BaseViewModel<ActivitySellBinding, SellActivity> {
    private List<GroupBoard> mGroupBoardList = new ArrayList<>();
    private List<Board> mBoardList = new ArrayList<>();

    @Override
    public void onAttached(boolean isFirst) {
        super.onAttached(isFirst);
        loadData();
        loadDataBoardList(Constant.ID_GROUP_DEFAULT);
    }

    private void loadData() {
        mGroupBoardList =
            DatabaseManager.getInstance(getContext()).getGroupBoardAll();
        if (!mGroupBoardList.isEmpty()) {
            mGroupBoardList.get(0).setSelected(true);
        }
        getView().showGroupBoardList(mGroupBoardList);
    }

    private void unSelectedGroupBoard() {
        for (GroupBoard groupBoard : mGroupBoardList) {
            groupBoard.setSelected(false);
        }
    }

    public void loadDataBoardList(int position) {
        unSelectedGroupBoard();
        GroupBoard groupBoard = mGroupBoardList.get(position);
        groupBoard.setSelected(true);
        mBoardList.clear();
        mBoardList.addAll(DatabaseManager.getInstance(getContext())
            .getBoardAllByIdGroupBoard(groupBoard.getId()));
        getView().showBoardList(mBoardList);
    }

    public void updateBoard(int boardId) {
        for (Board board : mBoardList) {
            if (board.getId() == boardId) {
                board.setIsSave(Constant.TRUE);
            }
        }
    }
}
