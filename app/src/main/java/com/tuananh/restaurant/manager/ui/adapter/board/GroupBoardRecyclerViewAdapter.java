package com.tuananh.restaurant.manager.ui.adapter.board;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.tuananh.restaurant.manager.R;
import com.tuananh.restaurant.manager.model.board.GroupBoard;
import com.tuananh.restaurant.manager.ui.listener.OnClickGroupBoardItemListener;

import java.util.List;

/**
 * Created by framgia on 16/09/2016.
 */
public class GroupBoardRecyclerViewAdapter
    extends RecyclerView.Adapter<GroupBoardRecyclerViewAdapter.GroupBoardViewHolder> {
    private List<GroupBoard> mGroupBoardList;
    private OnClickGroupBoardItemListener mOnClickGroupBoardItemListener;
    private Context mContext;
    private LayoutInflater mLayoutInflater;

    public GroupBoardRecyclerViewAdapter(Context context,
                                         List<GroupBoard> groupBoardList,
                                         OnClickGroupBoardItemListener onClickGroupBoardItemListener) {
        mContext = context;
        mGroupBoardList = groupBoardList;
        mOnClickGroupBoardItemListener = onClickGroupBoardItemListener;
        mLayoutInflater = LayoutInflater.from(context);
    }

    @Override
    public GroupBoardViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new GroupBoardViewHolder(
            mLayoutInflater.inflate(R.layout.item_group_board, parent, false));
    }

    @Override
    public void onBindViewHolder(final GroupBoardViewHolder holder, final int position) {
        final GroupBoard groupBoard = mGroupBoardList.get(position);
        holder.mTextViewNameGroupBoard.setText(groupBoard.getNameGroupBoard());
        holder.itemView.setBackground(ContextCompat.getDrawable(mContext, groupBoard.isSelected()
            ? R.drawable.surround_item_selected : R.drawable.surround_item));
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mOnClickGroupBoardItemListener != null) {
                    mOnClickGroupBoardItemListener.onClickItemBoard(holder, position);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mGroupBoardList.isEmpty() ? 0 : mGroupBoardList.size();
    }

    public class GroupBoardViewHolder extends RecyclerView.ViewHolder {
        private TextView mTextViewNameGroupBoard;

        GroupBoardViewHolder(View itemView) {
            super(itemView);
            mTextViewNameGroupBoard = (TextView) itemView.findViewById(
                R.id.text_number_group_board);
        }
    }
}
