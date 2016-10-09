package com.tuananh.restaurant.manager.ui.listener;

import com.tuananh.restaurant.manager.ui.adapter.commodity.GroupCommodityRecyclerViewAdapter;

/**
 * Created by framgia on 09/10/2016.
 */
public interface OnClickGroupCommodityItemListener {
    void onClickItemGroupCommodity(GroupCommodityRecyclerViewAdapter
                                       .GroupCommodityViewHolder groupCommodityViewHolder,
                                   int position);
}
