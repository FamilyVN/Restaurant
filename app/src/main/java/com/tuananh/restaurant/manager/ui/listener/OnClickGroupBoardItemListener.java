package com.tuananh.restaurant.manager.ui.listener;

import com.tuananh.restaurant.manager.ui.adapter.board.GroupBoardRecyclerViewAdapter;

/**
 * Created by framgia on 16/09/2016.
 */
public interface OnClickGroupBoardItemListener {
    void onClickItem(GroupBoardRecyclerViewAdapter.GroupBoardViewHolder groupBoardViewHolder,
                     int position);
}
