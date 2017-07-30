package com.tuananh.restaurant.manager.view.activity.settings.roomboard;

import com.tuananh.restaurant.manager.database.DatabaseManager;
import com.tuananh.restaurant.manager.databinding.ActivityRoomBoardBinding;
import com.tuananh.restaurant.manager.model.BaseViewModelRestaurant;
import com.tuananh.restaurant.manager.model.Constant;
import com.tuananh.restaurant.manager.model.board.Board;
import com.tuananh.restaurant.manager.model.board.GroupBoard;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by FRAMGIA\vu.tuan.anh on 20/07/2017.
 */
public class MapRoomBoardActivityViewModel
    extends BaseViewModelRestaurant<ActivityRoomBoardBinding, MapRoomBoardActivity> {
    private List<GroupBoard> mGroupBoardList = new ArrayList<>();
    private List<Board> mBoardList = new ArrayList<>();
    private Board mBoard;

    @Override
    public void onAttached(boolean isFirst) {
        super.onAttached(isFirst);
        loadData();
        loadDataBoardList(Constant.ID_GROUP_DEFAULT);
    }

    private void loadData() {
        mGroupBoardList = DatabaseManager.getInstance(getContext()).getGroupBoardAll();
        if (!mGroupBoardList.isEmpty()) {
            mGroupBoardList.get(Constant.ID_GROUP_DEFAULT).setSelected(true);
        }
        getView().showGroupBoardList(mGroupBoardList);
    }

    public void loadDataBoardList(int position) {
        unSelectedGroupBoard();
        GroupBoard groupBoard = mGroupBoardList.get(position);
        groupBoard.setSelected(true);
        mBoardList.clear();
        mBoardList.addAll(DatabaseManager.getInstance(getContext())
            .getBoardAllByIdGroupBoard(groupBoard.getIdGroupBoard()));
        getView().showBoardList(mBoardList);
    }

    private void unSelectedGroupBoard() {
        for (GroupBoard groupBoard : mGroupBoardList) {
            groupBoard.setSelected(false);
        }
    }

    public void setBoard(Board board) {
        mBoard = board;
        notifyChange();
    }

    public Board getBoard() {
        return mBoard;
    }

    public void deleteBoard() {
        if (mBoard != null) {
            mBoardList.remove(mBoard);
            getView().showBoardList(mBoardList);
        }
    }
}
