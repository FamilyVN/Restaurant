package com.tuananh.restaurant.manager.ui.listener;

import com.tuananh.restaurant.manager.ui.adapter.commodity.CommoditySelectedRecyclerViewAdapter.CommoditySelectedViewHolder;

/**
 * Created by framgia on 27/10/2016.
 */
public interface OnClickCommoditySelectedItemListener {
    void onClickReduce(CommoditySelectedViewHolder commoditySelectedViewHolder, int position);
    void onClickUpAmount(CommoditySelectedViewHolder commoditySelectedViewHolder, int position);
    boolean onLongClick(CommoditySelectedViewHolder commoditySelectedViewHolder, int position);
}
