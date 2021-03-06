package com.tuananh.restaurant.manager.view.activity.sell;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;

import com.restaurant.tuananh.mvvm.recycler.BaseViewAdapter;
import com.restaurant.tuananh.mvvm.recycler.SingleTypeAdapter;
import com.tuananh.restaurant.manager.R;
import com.tuananh.restaurant.manager.databinding.ActivitySellBinding;
import com.tuananh.restaurant.manager.model.Constant;
import com.tuananh.restaurant.manager.model.board.Board;
import com.tuananh.restaurant.manager.model.board.GroupBoard;
import com.tuananh.restaurant.manager.view.activity.BaseActivityRestaurant;
import com.tuananh.restaurant.manager.view.activity.board.BoardActivity;

import java.util.List;

/**
 * Created by framgia on 04/05/2017.
 */
public class SellActivity
    extends BaseActivityRestaurant<ActivitySellBinding, SellActivityViewModel> {
    private static final int SPAN_COUNT = 2;
    private SingleTypeAdapter<GroupBoard> mGroupBoardAdapter;
    private SingleTypeAdapter<Board> mBoardAdapter;

    @Override
    protected void onViewCreated() {
        super.onViewCreated();
        getBinding().setListener(this);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_sell;
    }

    public void showGroupBoardList(List<GroupBoard> groupBoardList) {
        if (mGroupBoardAdapter != null) {
            mGroupBoardAdapter.clear();
            mGroupBoardAdapter.addAll(groupBoardList);
            return;
        }
        mGroupBoardAdapter = new SingleTypeAdapter<>(this, R.layout.item_group_board);
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
        mBoardAdapter = new SingleTypeAdapter<>(this, R.layout.item_board);
        mBoardAdapter.addAll(boardList);
        getBinding().setBoardAdapter(mBoardAdapter);
        getBinding().setBoardLayoutManager(new GridLayoutManager(this, SPAN_COUNT));
        mBoardAdapter.setPresenter(new SelectedBoardListener());
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == Constant.FLAG_SELECTED_BOARD && resultCode == Activity.RESULT_OK) {
            getBinding().recyclerGroupBoard.scrollToPosition(0);
            getViewModel().loadData();
            getViewModel().loadDataBoardList(Constant.ID_GROUP_DEFAULT);
        }
    }

    public class SelectedGroupBoardListener implements BaseViewAdapter.Presenter {
        public void onClickItemGroupBoard(int position) {
            getViewModel().loadDataBoardList(position);
            mGroupBoardAdapter.notifyDataSetChanged();
        }
    }

    public class SelectedBoardListener implements BaseViewAdapter.Presenter {
        public void onClickItemBoard(Board board) {
            Intent intent = new Intent(SellActivity.this, BoardActivity.class);
            intent.putExtra(Constant.KEY_ID_BOARD, board.getIdBoard());
            startActivityForResult(intent, Constant.FLAG_SELECTED_BOARD);
            overridePendingTransition(R.anim.slide_in_from_right, R.anim.slide_out_to_left);
        }
    }
}
