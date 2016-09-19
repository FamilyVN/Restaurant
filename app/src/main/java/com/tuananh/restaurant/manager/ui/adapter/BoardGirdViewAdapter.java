package com.tuananh.restaurant.manager.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.tuananh.restaurant.manager.R;
import com.tuananh.restaurant.manager.data.model.Board;

import java.util.List;

/**
 * Created by framgia on 19/09/2016.
 */
public class BoardGirdViewAdapter extends BaseAdapter {
    private List<Board> mBoardList;
    private Context mContext;

    public BoardGirdViewAdapter(Context context, List<Board> boardList) {
        mBoardList = boardList;
        mContext = context;
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
        View grid;
        TextView textViewName;
        Board board = mBoardList.get(position);
        LayoutInflater inflater =
            (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (view == null) {
            grid = inflater.inflate(R.layout.item_board, null);
            textViewName = (TextView) grid.findViewById(R.id.text_number_board);
        } else {
            grid = view;
            textViewName = (TextView) view.findViewById(R.id.text_number_board);
        }
        textViewName.setText(board.getNameBoard());
        return grid;
    }
}
