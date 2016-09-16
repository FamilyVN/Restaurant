package com.tuananh.restaurant.manager.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.tuananh.restaurant.manager.R;
import com.tuananh.restaurant.manager.data.listener.OnClickBoardItemListener;
import com.tuananh.restaurant.manager.data.model.Board;

import java.util.List;

/**
 * Created by framgia on 16/09/2016.
 */
public class BoardRecyclerViewAdapter
    extends RecyclerView.Adapter<BoardRecyclerViewAdapter.BoardViewHolder> {
    private List<Board> mBoardList;
    private OnClickBoardItemListener mOnClickBoardItemListener;
    private Context mContext;

    public BoardRecyclerViewAdapter(Context context, List<Board> boardList,
                                    OnClickBoardItemListener onClickBoardItemListener) {
        mContext = context;
        mBoardList = boardList;
        mOnClickBoardItemListener = onClickBoardItemListener;
    }

    @Override
    public BoardViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new BoardViewHolder(LayoutInflater.from(mContext).inflate(
            R.layout.item_board, parent, false));
    }

    @Override
    public void onBindViewHolder(final BoardViewHolder holder, final int position) {
        Board board = mBoardList.get(position);
        holder.mTextViewNumberBoard.setText(board.getNumberBoard());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mOnClickBoardItemListener.onClickItem(holder, position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mBoardList.isEmpty() ? 0 : mBoardList.size();
    }

    public static class BoardViewHolder extends RecyclerView.ViewHolder {
        private TextView mTextViewNumberBoard;

        public BoardViewHolder(View itemView) {
            super(itemView);
            mTextViewNumberBoard = (TextView) itemView.findViewById(R.id.text_number_board);
        }
    }
}
