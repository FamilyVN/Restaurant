package com.tuananh.restaurant.manager.ui.adapter.board;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.tuananh.restaurant.manager.R;
import com.tuananh.restaurant.manager.data.model.board.Board;

import java.util.List;

/**
 * Created by framgia on 19/09/2016.
 */
public class BoardGirdViewAdapter extends BaseAdapter {
    private List<Board> mBoardList;
    private LayoutInflater mLayoutInflater;

    public BoardGirdViewAdapter(Context context, List<Board> boardList) {
        mBoardList = boardList;
        mLayoutInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return mBoardList != null ? mBoardList.size() : 0;
    }

    @Override
    public Object getItem(int position) {
        return mBoardList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return mBoardList.get(position).getId();
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        BoardViewHolder boardViewHolder;
        if (view == null) {
            view = mLayoutInflater.inflate(R.layout.item_board, null);
            boardViewHolder = new BoardViewHolder();
            boardViewHolder.mTextViewName = (TextView) view.findViewById(R.id.text_number_board);
            view.setTag(boardViewHolder);
        } else {
            boardViewHolder = (BoardViewHolder) view.getTag();
        }
        Board board = mBoardList.get(position);
        boardViewHolder.mTextViewName.setText(board.getNameBoard());
        return view;
    }

    private class BoardViewHolder {
        private TextView mTextViewName;
    }
}
