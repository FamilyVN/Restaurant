package com.tuananh.restaurant.manager.view.activity.sell;

import com.restaurant.tuananh.mvvm.BaseViewModel;
import com.tuananh.restaurant.manager.R;
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
        mGroupBoardList = DatabaseManager.getInstance(getContext()).getGroupBoardAll();
        mGroupBoardList.add(0, new GroupBoard(getString(R.string.text_group_board_opening)));
        if (!mGroupBoardList.isEmpty()) {
            mGroupBoardList.get(Constant.ID_GROUP_BOARD_DEFAULT).setSelected(true);
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
        if (position == 0) {
            mBoardList.addAll(DatabaseManager.getInstance(getContext()).getBoardAllSave());
        } else {
            mBoardList.addAll(DatabaseManager.getInstance(getContext())
                .getBoardAllByIdGroupBoard(groupBoard.getIdGroupBoard()));
        }
        getView().showBoardList(mBoardList);
    }

    public void updateBoard(int idBoard) {
        for (Board board : mBoardList) {
            if (board.getIdBoard() == idBoard) {
                board.setIsSave(Constant.TRUE);
            }
        }
    }
}
