package com.tuananh.restaurant.manager.ui.adapter.commodity;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.tuananh.restaurant.manager.R;
import com.tuananh.restaurant.manager.data.Constants;
import com.tuananh.restaurant.manager.data.model.commodity.Commodity;
import com.tuananh.restaurant.manager.model.board.Board;
import com.tuananh.restaurant.manager.ui.listener.OnClickCommodityItemListener;

import java.util.List;

/**
 * Created by framgia on 17/10/2016.
 */
public class CommodityGirdViewAdapter extends BaseAdapter {
    private List<Commodity> mCommodityList;
    private LayoutInflater mLayoutInflater;
    private OnClickCommodityItemListener mOnClickCommodityItemListener;
    private Board mBoard;

    public CommodityGirdViewAdapter(Context context, List<Commodity> commodityList,
                                    OnClickCommodityItemListener onClickCommodityItemListener,
                                    Board board) {
        mCommodityList = commodityList;
        mLayoutInflater = LayoutInflater.from(context);
        mOnClickCommodityItemListener = onClickCommodityItemListener;
        mBoard = board;
    }

    @Override
    public int getCount() {
        return mCommodityList == null ? 0 : mCommodityList.size();
    }

    @Override
    public Object getItem(int position) {
        return mCommodityList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return mCommodityList.get(position).getId();
    }

    @Override
    public View getView(final int position, View view, ViewGroup viewGroup) {
        final CommodityViewHolder commodityViewHolder;
        if (view == null) {
            view = mLayoutInflater.inflate(R.layout.item_commodity, viewGroup, false);
            commodityViewHolder = new CommodityViewHolder();
            commodityViewHolder.mTextViewNameCommodity =
                (TextView) view.findViewById(R.id.text_name_commodity);
            commodityViewHolder.mTextViewCostCommodity =
                (TextView) view.findViewById(R.id.text_cost_commodity);
            view.setTag(commodityViewHolder);
        } else {
            commodityViewHolder = (CommodityViewHolder) view.getTag();
        }
        Commodity commodity = mCommodityList.get(position);
        commodityViewHolder.mTextViewNameCommodity.setText(commodity.getName());
        commodityViewHolder.mTextViewCostCommodity.setText(new StringBuilder()
            .append(String.valueOf(commodity.getCost())).append(" đ").toString());
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mOnClickCommodityItemListener != null &&
                    mBoard.getIsSave() == Constants.FALSE) {
                    mOnClickCommodityItemListener
                        .onClickItemCommodity(commodityViewHolder, position);
                }
            }
        });
        return view;
    }

    public class CommodityViewHolder {
        private TextView mTextViewNameCommodity;
        private TextView mTextViewCostCommodity;
    }
}
