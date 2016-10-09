package com.tuananh.restaurant.manager.ui.adapter.commodity;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.tuananh.restaurant.manager.R;
import com.tuananh.restaurant.manager.data.model.commodity.GroupCommodity;
import com.tuananh.restaurant.manager.ui.listener.OnClickGroupCommodityItemListener;

import java.util.List;

/**
 * Created by framgia on 09/10/2016.
 */
public class GroupCommodityRecyclerViewAdapter extends
    RecyclerView.Adapter<GroupCommodityRecyclerViewAdapter.GroupCommodityViewHolder> {
    private List<GroupCommodity> mGroupCommodityList;
    private OnClickGroupCommodityItemListener mOnClickGroupCommodityItemListener;
    private Context mContext;

    public GroupCommodityRecyclerViewAdapter(Context context,
                                             List<GroupCommodity> groupCommodityList,
                                             OnClickGroupCommodityItemListener onClickGroupCommodityItemListener) {
        mContext = context;
        mGroupCommodityList = groupCommodityList;
        mOnClickGroupCommodityItemListener = onClickGroupCommodityItemListener;
    }

    @Override
    public GroupCommodityViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new GroupCommodityViewHolder(LayoutInflater.from(mContext)
            .inflate(R.layout.item_group_commodity, parent, false));
    }

    @Override
    public void onBindViewHolder(final GroupCommodityViewHolder holder, final int position) {
        final GroupCommodity groupCommodity = mGroupCommodityList.get(position);
        holder.mTextViewNameGroupCommodity.setText(groupCommodity.getNameGroupCommodity());
        holder.itemView
            .setBackground(ContextCompat.getDrawable(mContext, groupCommodity.isSelected()
                ? R.drawable.surround_item_selected : R.drawable.surround_item));
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mOnClickGroupCommodityItemListener.onClickItemGroupCommodity(holder, position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mGroupCommodityList.isEmpty() ? 0 : mGroupCommodityList.size();
    }

    public static class GroupCommodityViewHolder extends RecyclerView.ViewHolder {
        private TextView mTextViewNameGroupCommodity;

        GroupCommodityViewHolder(View itemView) {
            super(itemView);
            mTextViewNameGroupCommodity = (TextView) itemView.findViewById(
                R.id.text_number_group_commodity);
        }
    }
}
