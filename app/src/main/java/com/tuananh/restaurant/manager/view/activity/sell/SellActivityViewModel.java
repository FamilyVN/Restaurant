package com.tuananh.restaurant.manager.view.activity.sell;

import com.restaurant.tuananh.mvvm.BaseViewModel;
import com.tuananh.restaurant.manager.data.Constants;
import com.tuananh.restaurant.manager.database.DatabaseManager;
import com.tuananh.restaurant.manager.databinding.ActivitySellBinding;
import com.tuananh.restaurant.manager.model.board.Board;

import java.util.List;

/**
 * Created by framgia on 04/05/2017.
 */
public class SellActivityViewModel extends BaseViewModel<ActivitySellBinding, SellActivity> {
    @Override
    public void onAttached(boolean isFirst) {
        super.onAttached(isFirst);
        loadData();
    }

    private void loadData() {
        List<Board> boardList = DatabaseManager.getInstance(getContext())
            .getBoardAllByIdGroupBoard(Constants.ID_GROUP_DEFAULT);
        getView().showBoardList(boardList);
    }
}
