package com.tuananh.restaurant.manager.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.tuananh.restaurant.manager.data.model.Commodity;

import java.util.List;

/**
 * Created by framgia on 09/10/2016.
 */
public class CommodityRecyclerViewAdapter extends
    RecyclerView.Adapter<CommodityRecyclerViewAdapter.CommodityViewHolder> {
    private List<Commodity> mCommodityList;
    private Context mContext;

    public CommodityRecyclerViewAdapter(Context context, List<Commodity> commodityList) {
        mCommodityList = commodityList;
        mContext = context;
    }

    @Override
    public CommodityViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(CommodityViewHolder holder,
                                 int position) {
    }

    @Override
    public int getItemCount() {
        return mCommodityList == null ? 0 : mCommodityList.size();
    }

    static class CommodityViewHolder extends RecyclerView.ViewHolder {
        private TextView mTextViewName, mTextViewMoney;

        public CommodityViewHolder(View itemView) {
            super(itemView);
        }
    }
}
