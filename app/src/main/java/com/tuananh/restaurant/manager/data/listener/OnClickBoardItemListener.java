package com.tuananh.restaurant.manager.data.listener;

import com.tuananh.restaurant.manager.ui.adapter.BoardRecyclerViewAdapter;

/**
 * Created by framgia on 16/09/2016.
 */
public interface OnClickBoardItemListener {
    void onClickItem(BoardRecyclerViewAdapter.BoardViewHolder boardViewHolder, int position);
}
