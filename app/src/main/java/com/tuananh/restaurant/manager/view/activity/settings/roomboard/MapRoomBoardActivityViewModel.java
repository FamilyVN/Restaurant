package com.tuananh.restaurant.manager.view.activity.settings.roomboard;

import com.tuananh.restaurant.manager.database.DBTest;
import com.tuananh.restaurant.manager.database.DatabaseManager;
import com.tuananh.restaurant.manager.databinding.ActivityRoomBoardBinding;
import com.tuananh.restaurant.manager.model.BaseViewModelRestaurant;
import com.tuananh.restaurant.manager.model.Constant;
import com.tuananh.restaurant.manager.model.board.Board;
import com.tuananh.restaurant.manager.model.board.GroupBoard;
import com.tuananh.restaurant.manager.utils.CommonUtils;

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
    private int mIdGroupBoard;
    private int mPosition;

    @Override
    public void onAttached(boolean isFirst) {
        super.onAttached(isFirst);
        if (isFirst) {
            loadData();
            loadDataBoardList(Constant.ID_GROUP_DEFAULT);
        }
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
        mIdGroupBoard = groupBoard.getIdGroupBoard();
        mPosition = position;
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

    public Board getBoard() {
        return mBoard;
    }

    public void setBoard(Board board) {
        mBoard = board;
        notifyChange();
    }

    public void deleteBoard() {
        if (mBoard != null) {
            mBoardList.remove(mBoard);
            getView().showBoardList(mBoardList);
        }
    }

    public void addNewBoard() {
        Board board = new Board(mIdGroupBoard, getBinding().editNameBoard.getText().toString(),
            DBTest.NOT_SAVE, Constant.TRUE);
        DatabaseManager.getInstance(getContext()).insertBoard(board);
        getBinding().editNameBoard.setText("");
        loadDataBoardList(mPosition);
        CommonUtils.hideSoftKeyboard(getBinding().editNameBoard);
    }
}
