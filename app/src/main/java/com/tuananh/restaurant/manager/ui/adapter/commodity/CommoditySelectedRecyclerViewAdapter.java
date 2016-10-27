package com.tuananh.restaurant.manager.ui.adapter.commodity;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.tuananh.restaurant.manager.R;
import com.tuananh.restaurant.manager.data.model.commodity.Commodity;
import com.tuananh.restaurant.manager.ui.listener.OnClickCommoditySelectedItemListener;

import java.util.List;

/**
 * Created by framgia on 09/10/2016.
 */
public class CommoditySelectedRecyclerViewAdapter extends
    RecyclerView.Adapter<CommoditySelectedRecyclerViewAdapter.CommoditySelectedViewHolder> {
    private List<Commodity> mCommoditySelectedList;
    private Context mContext;
    private OnClickCommoditySelectedItemListener mOnClickCommoditySelectedItemListener;

    public CommoditySelectedRecyclerViewAdapter(Context context,
                                                List<Commodity> commoditySelectedList) {
        mContext = context;
        mCommoditySelectedList = commoditySelectedList;
    }

    @Override
    public CommoditySelectedViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new CommoditySelectedViewHolder(LayoutInflater.from(mContext)
            .inflate(R.layout.item_group_commodity, parent, false));
    }

    @Override
    public void onBindViewHolder(final CommoditySelectedViewHolder holder, final int position) {
        Commodity commodity = mCommoditySelectedList.get(position);
        holder.mImageViewReduce.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mOnClickCommoditySelectedItemListener.onClickReduce(holder, position);
            }
        });
        holder.mImageViewUpAmount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mOnClickCommoditySelectedItemListener.onClickUpAmount(holder, position);
            }
        });
        holder.mTextViewNumber.setText(commodity.getNumber());
        holder.mTextViewName.setText(commodity.getName());
        holder.mTextViewTotalCost.setText(commodity.getTotalCost());
        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                return mOnClickCommoditySelectedItemListener.onLongClick(holder, position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mCommoditySelectedList == null ? 0 : mCommoditySelectedList.size();
    }

    public static class CommoditySelectedViewHolder extends RecyclerView.ViewHolder {
        private ImageView mImageViewUpAmount, mImageViewReduce;
        private TextView mTextViewName, mTextViewNumber, mTextViewTotalCost;

        CommoditySelectedViewHolder(View itemView) {
            super(itemView);
            mImageViewReduce = (ImageView) itemView.findViewById(R.id.image_view_reduce);
            mImageViewUpAmount = (ImageView) itemView.findViewById(R.id.image_view_up_amount);
            mTextViewNumber =
                (TextView) itemView.findViewById(R.id.text_view_number_commodity_selected);
            mTextViewName =
                (TextView) itemView.findViewById(R.id.text_view_name_commodity_selected);
            mTextViewTotalCost =
                (TextView) itemView.findViewById(R.id.text_view_total_cost_commodity_selected);
        }
    }
}
