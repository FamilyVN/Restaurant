package com.tuananh.restaurant.manager.ui.adapter.commodity;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.tuananh.restaurant.manager.R;
import com.tuananh.restaurant.manager.data.Constants;
import com.tuananh.restaurant.manager.data.model.board.Board;
import com.tuananh.restaurant.manager.data.model.commodity.Commodity;
import com.tuananh.restaurant.manager.ui.listener.OnClickCommoditySelectedItemListener;

import java.util.List;

/**
 * Created by framgia on 09/10/2016.
 */
public class CommoditySelectedRecyclerViewAdapter extends
    RecyclerView.Adapter<CommoditySelectedRecyclerViewAdapter.CommoditySelectedViewHolder> {
    private List<Commodity> mCommoditySelectedList;
    private OnClickCommoditySelectedItemListener mOnClickCommoditySelectedItemListener;
    private Board mBoard;
    private LayoutInflater mLayoutInflater;

    public CommoditySelectedRecyclerViewAdapter(Context context,
                                                List<Commodity> commoditySelectedList,
                                                OnClickCommoditySelectedItemListener
                                                    onClickCommoditySelectedItemListener,
                                                Board board) {
        mCommoditySelectedList = commoditySelectedList;
        mOnClickCommoditySelectedItemListener = onClickCommoditySelectedItemListener;
        mBoard = board;
        mLayoutInflater = LayoutInflater.from(context);
    }

    @Override
    public CommoditySelectedViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new CommoditySelectedViewHolder(
            mLayoutInflater.inflate(R.layout.item_selected_commodity, parent, false));
    }

    @Override
    public void onBindViewHolder(final CommoditySelectedViewHolder holder, final int position) {
        Commodity commodity = mCommoditySelectedList.get(position);
        holder.mTextViewNumber.setText(String.valueOf(commodity.getNumber()));
        holder.mTextViewName.setText(commodity.getName());
        holder.mTextViewTotalCost.setText(
            new StringBuilder().append(commodity.getTotalCost()).append(" đ").toString());
        final boolean isSave = mBoard.getIsSave() == Constants.TRUE;
        holder.mImageViewReduce.setVisibility(isSave ? View.GONE : View.VISIBLE);
        holder.mImageViewUpAmount.setVisibility(isSave ? View.GONE : View.VISIBLE);
        if (mOnClickCommoditySelectedItemListener != null) {
            holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    return isSave &&
                        mOnClickCommoditySelectedItemListener.onLongClick(holder, position);
                }
            });
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
        }
    }

    @Override
    public int getItemCount() {
        return mCommoditySelectedList == null ? 0 : mCommoditySelectedList.size();
    }

    public class CommoditySelectedViewHolder extends RecyclerView.ViewHolder {
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
