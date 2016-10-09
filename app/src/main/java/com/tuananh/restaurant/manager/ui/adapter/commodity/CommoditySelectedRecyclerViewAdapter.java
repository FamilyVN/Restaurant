package com.tuananh.restaurant.manager.ui.adapter.commodity;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.tuananh.restaurant.manager.data.model.commodity.CommoditySelected;

import java.util.List;

/**
 * Created by framgia on 09/10/2016.
 */
public class CommoditySelectedRecyclerViewAdapter extends
    RecyclerView.Adapter<CommoditySelectedRecyclerViewAdapter.CommoditySelectedViewHolder> {
    private List<CommoditySelected> mCommoditySelectedList;
    private Context mContext;

    public CommoditySelectedRecyclerViewAdapter(Context context,
                                                List<CommoditySelected> commoditySelectedList) {
        mContext = context;
        mCommoditySelectedList = commoditySelectedList;
    }

    @Override
    public CommoditySelectedViewHolder onCreateViewHolder(
        ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(CommoditySelectedViewHolder holder, int position) {
    }

    @Override
    public int getItemCount() {
        return mCommoditySelectedList == null ? 0 : mCommoditySelectedList.size();
    }

    static class CommoditySelectedViewHolder extends RecyclerView.ViewHolder {
        private ImageView mImageViewAdd, mImageViewRemove;
        private TextView mTextViewName, mTextViewNumber, mTextViewTotalMoney;

        public CommoditySelectedViewHolder(View itemView) {
            super(itemView);
        }
    }
}
