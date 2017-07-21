package com.tuananh.restaurant.manager.view.activity.settings.roomboard;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;

import com.restaurant.tuananh.mvvm.recycler.BaseViewAdapter;
import com.restaurant.tuananh.mvvm.recycler.SingleTypeAdapter;
import com.tuananh.restaurant.manager.R;
import com.tuananh.restaurant.manager.databinding.ActivityRoomBoardBinding;
import com.tuananh.restaurant.manager.model.board.Board;
import com.tuananh.restaurant.manager.model.board.GroupBoard;
import com.tuananh.restaurant.manager.view.activity.BaseActivityRestaurant;

import java.util.List;

/**
 * Created by FRAMGIA\vu.tuan.anh on 20/07/2017.
 */
public class MapRoomBoardActivity
    extends BaseActivityRestaurant<ActivityRoomBoardBinding, MapRoomBoardActivityViewModel> {
    private static final int SPAN_COUNT = 2;
    private SingleTypeAdapter<GroupBoard> mGroupBoardAdapter;
    private SingleTypeAdapter<Board> mBoardAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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
        public void onClickItemBoard(Board board) {
        }
    }
}
