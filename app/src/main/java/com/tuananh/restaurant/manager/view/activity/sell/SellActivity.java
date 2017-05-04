package com.tuananh.restaurant.manager.view.activity.sell;

import android.support.v7.widget.LinearLayoutManager;

import com.restaurant.tuananh.mvvm.recycler.SingleTypeAdapter;
import com.tuananh.restaurant.manager.R;
import com.tuananh.restaurant.manager.databinding.ActivitySellBinding;
import com.tuananh.restaurant.manager.model.board.Board;
import com.tuananh.restaurant.manager.view.activity.BaseActivityRestaurant;

import java.util.List;

/**
 * Created by framgia on 04/05/2017.
 */
public class SellActivity
    extends BaseActivityRestaurant<ActivitySellBinding, SellActivityViewModel> {
    private SingleTypeAdapter<Board> mBoardAdapter;

    @Override
    public int getLayoutId() {
        return R.layout.activity_sell;
    }

    public void showBoardList(List<Board> boardList) {
        if (mBoardAdapter != null) {
            mBoardAdapter.clear();
            mBoardAdapter.addAll(boardList);
            return;
        }
        mBoardAdapter = new SingleTypeAdapter<>(this, R.layout.item_group_board);
        getBinding().recyclerViewSellGroup
            .setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        getBinding().setAdapter(mBoardAdapter);
        mBoardAdapter.addAll(boardList);
    }
}
