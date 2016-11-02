package com.tuananh.restaurant.manager.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import com.tuananh.restaurant.manager.R;
import com.tuananh.restaurant.manager.data.Constants;
import com.tuananh.restaurant.manager.controller.database.DBHelper;
import com.tuananh.restaurant.manager.data.model.board.Board;
import com.tuananh.restaurant.manager.data.model.board.GroupBoard;
import com.tuananh.restaurant.manager.ui.adapter.board.BoardGirdViewAdapter;
import com.tuananh.restaurant.manager.ui.adapter.board.GroupBoardRecyclerViewAdapter;
import com.tuananh.restaurant.manager.ui.listener.OnClickGroupBoardItemListener;

import java.util.List;

public class SellActivity extends BaseActivity
    implements OnClickGroupBoardItemListener, AdapterView.OnItemClickListener {
    private RecyclerView mRecyclerViewGroupBoard;
    private GroupBoardRecyclerViewAdapter mGroupBoardRecyclerViewAdapter;
    private BoardGirdViewAdapter mBoardGirdViewAdapter;
    private List<GroupBoard> mGroupBoardList;
    private List<Board> mBoardList;
    private DBHelper mDBHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sell);
        initData();
        initViews();
    }

    private void initData() {
        mDBHelper = new DBHelper(this);
        mDBHelper.createDBBoard();
        mDBHelper.createDBGroupBoard();
        mGroupBoardList = mDBHelper.getDBGroupBoard().getGroupBoardAll();
        mBoardList = mDBHelper.getDBBoard().getBoardAllByIdGroupBoard(Constants.ID_GROUP_DEFAULT);
    }

    private void initViews() {
        mRecyclerViewGroupBoard = (RecyclerView) findViewById(R.id.recycler_view_sell_group);
        mGroupBoardRecyclerViewAdapter =
            new GroupBoardRecyclerViewAdapter(this, mGroupBoardList, this);
        mRecyclerViewGroupBoard.setLayoutManager(new LinearLayoutManager(this,
            LinearLayoutManager.HORIZONTAL, false));
        mRecyclerViewGroupBoard.setAdapter(mGroupBoardRecyclerViewAdapter);
        if (!mGroupBoardList.isEmpty()) {
            mGroupBoardList.get(0).setSelected(true);
            mGroupBoardRecyclerViewAdapter.notifyItemChanged(Constants.ID_GROUP_DEFAULT);
        }
        mBoardGirdViewAdapter = new BoardGirdViewAdapter(this, mBoardList);
        GridView gridView = (GridView) findViewById(R.id.grid_view_sell_board);
        gridView.setAdapter(mBoardGirdViewAdapter);
        gridView.setOnItemClickListener(this);
    }

    @Override
    public void onClickItemBoard(GroupBoardRecyclerViewAdapter.GroupBoardViewHolder
                                     groupBoardViewHolder, int position) {
        unSelected();
        GroupBoard groupBoard = mGroupBoardList.get(position);
        groupBoard.setSelected(true);
        mGroupBoardRecyclerViewAdapter.notifyDataSetChanged();
        int size = mBoardList.size();
        mBoardList.addAll(mDBHelper.getDBBoard().getBoardAllByIdGroupBoard(groupBoard.getId()));
        mBoardGirdViewAdapter.notifyDataSetChanged();
        int i = 0;
        while (i < size) {
            mBoardList.remove(0);
            mBoardGirdViewAdapter.notifyDataSetChanged();
            i++;
        }
    }

    public void unSelected() {
        for (GroupBoard groupBoard : mGroupBoardList) {
            groupBoard.setSelected(false);
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent = new Intent(this, BoardActivity.class);
        intent.putExtra(Constants.ID_BOARD_SELECTED, mBoardList.get(position).getId());
        startActivity(intent);
    }
}
