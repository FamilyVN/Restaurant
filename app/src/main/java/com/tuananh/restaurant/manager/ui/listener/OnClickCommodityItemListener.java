package com.tuananh.restaurant.manager.ui.listener;

import com.tuananh.restaurant.manager.ui.adapter.commodity.CommodityGirdViewAdapter;

/**
 * Created by framgia on 18/10/2016.
 */
public interface OnClickCommodityItemListener {
    void onClickItemCommodity(CommodityGirdViewAdapter.CommodityViewHolder commodityViewHolder,
                              int position);
}
