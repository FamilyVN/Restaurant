package com.tuananh.restaurant.manager.view.activity.settings.roomboard;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.PopupWindow;

import com.restaurant.tuananh.mvvm.recycler.BaseViewAdapter;
import com.restaurant.tuananh.mvvm.recycler.SingleTypeAdapter;
import com.tuananh.restaurant.manager.R;
import com.tuananh.restaurant.manager.database.DatabaseManager;
import com.tuananh.restaurant.manager.databinding.ActivityRoomBoardBinding;
import com.tuananh.restaurant.manager.databinding.PopupWindowEditRoomBoardBinding;
import com.tuananh.restaurant.manager.model.Constant;
import com.tuananh.restaurant.manager.model.board.Board;
import com.tuananh.restaurant.manager.model.board.GroupBoard;
import com.tuananh.restaurant.manager.model.editboard.EditRoomBoard;
import com.tuananh.restaurant.manager.view.activity.BaseActivityRestaurant;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by FRAMGIA\vu.tuan.anh on 20/07/2017.
 */
public class MapRoomBoardActivity
    extends BaseActivityRestaurant<ActivityRoomBoardBinding, MapRoomBoardActivityViewModel> {
    private static final int SPAN_COUNT = 2;
    private SingleTypeAdapter<GroupBoard> mGroupBoardAdapter;
    private SingleTypeAdapter<Board> mBoardAdapter;
    private List<EditRoomBoard> mEditRoomBoardList = new ArrayList<>();
    private PopupWindowEditRoomBoardBinding mEditRoomBoardBinding;
    private PopupWindow mPopupEditRoomBoard;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mEditRoomBoardList.add(new EditRoomBoard(Constant.TYPE_EDIT_ROOM_BOARD_RENAME, "Đổi tên"));
        mEditRoomBoardList.add(new EditRoomBoard(Constant.TYPE_EDIT_ROOM_BOARD_DELETE, "Xóa"));
        getBinding().setListener(this);
        createPopupEditRoomBoard();
    }

    private void createPopupEditRoomBoard() {
        LayoutInflater layoutInflater = LayoutInflater.from(MapRoomBoardActivity.this);
        mEditRoomBoardBinding = DataBindingUtil
            .inflate(layoutInflater, R.layout.popup_window_edit_room_board, null, true);
        SingleTypeAdapter<EditRoomBoard> editRoomBoardAdapter =
            new SingleTypeAdapter<>(MapRoomBoardActivity.this, R.layout.item_edit_room_board);
        editRoomBoardAdapter.set(mEditRoomBoardList);
        editRoomBoardAdapter.setPresenter(new SelectedEditRoomBoardListener());
        mEditRoomBoardBinding.setAdapter(editRoomBoardAdapter);
        mEditRoomBoardBinding.setLayoutManager(new LinearLayoutManager(MapRoomBoardActivity.this));
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_room_board;
    }

    public void showGroupBoardList(List<GroupBoard> groupBoardList) {
        if (mGroupBoardAdapter != null) {
            mGroupBoardAdapter.clear();
            mGroupBoardAdapter.addAll(groupBoardList);
            return;
        }
        mGroupBoardAdapter =
            new SingleTypeAdapter<>(this, R.layout.item_room_board_setting_group_board);
        getBinding().setGroupBoardLayoutManager(
            new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        getBinding().setGroupBoardAdapter(mGroupBoardAdapter);
        mGroupBoardAdapter.addAll(groupBoardList);
        mGroupBoardAdapter.setPresenter(new SelectedGroupBoardListener());
    }

    public void showBoardList(List<Board> boardList) {
        if (mBoardAdapter != null) {
            mBoardAdapter.clear();
            mBoardAdapter.addAll(boardList);
        }
        mBoardAdapter = new SingleTypeAdapter<>(this, R.layout.item_room_board_setting_board);
        mBoardAdapter.addAll(boardList);
        getBinding().setBoardAdapter(mBoardAdapter);
        getBinding().setBoardLayoutManager(new GridLayoutManager(this, SPAN_COUNT));
        mBoardAdapter.setPresenter(new SelectedBoardListener());
    }

    public class SelectedGroupBoardListener implements BaseViewAdapter.Presenter {
        public void onClickItemGroupBoard(int position) {
            getViewModel().loadDataBoardList(position);
            mGroupBoardAdapter.notifyDataSetChanged();
        }
    }

    public class SelectedBoardListener implements BaseViewAdapter.Presenter {
        public void onClickItemBoard(View view, Board board) {
            if (mPopupEditRoomBoard != null) {
                mPopupEditRoomBoard.dismiss();
            }
            mPopupEditRoomBoard = new PopupWindow(mEditRoomBoardBinding.getRoot(),
                view.getWidth(), view.getHeight() + 20, true);
            mPopupEditRoomBoard.showAsDropDown(view, 0, 20);
            getViewModel().setBoard(board);
        }
    }

    public class SelectedEditRoomBoardListener implements BaseViewAdapter.Presenter {
        public void onEditRoomBoard(View view, EditRoomBoard editRoomBoard) {
            Board board = getViewModel().getBoard();
            if (board != null) {
                switch (editRoomBoard.getId()) {
                    case Constant.TYPE_EDIT_ROOM_BOARD_RENAME:
                        break;
                    case Constant.TYPE_EDIT_ROOM_BOARD_DELETE:
                        getViewModel().deleteBoard();
                        board.setUsed(Constant.FALSE);
                        DatabaseManager.getInstance(MapRoomBoardActivity.this).updateBoard(board);
                        if (mPopupEditRoomBoard != null) {
                            mPopupEditRoomBoard.dismiss();
                        }
                        break;
                }
            }
        }
    }
}
