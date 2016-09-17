package com.tuananh.restaurant.manager.ui.activity;

import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.tuananh.restaurant.manager.R;
import com.tuananh.restaurant.manager.data.database.DBHelper;
import com.tuananh.restaurant.manager.data.enums.Status;
import com.tuananh.restaurant.manager.data.listener.OnClickGroupBoardItemListener;
import com.tuananh.restaurant.manager.data.model.GroupBoard;
import com.tuananh.restaurant.manager.ui.adapter.GroupBoardRecyclerViewAdapter;

import java.util.List;

public class SellActivity extends BaseActivity implements OnClickGroupBoardItemListener {
    private RecyclerView mRecyclerViewGroupBoard;
    private GroupBoardRecyclerViewAdapter mGroupBoardRecyclerViewAdapter;
    private List<GroupBoard> mGroupBoardList;
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
        mDBHelper.create(Status.NO_CREATE, Status.CREATE);
        mGroupBoardList = mDBHelper.getDBGroupBoard().getGroupBoardAll();
    }

    private void initViews() {
        mRecyclerViewGroupBoard = (RecyclerView) findViewById(R.id.recycler_view_sell_group);
        mGroupBoardRecyclerViewAdapter =
            new GroupBoardRecyclerViewAdapter(this, mGroupBoardList, this);
        mRecyclerViewGroupBoard.setLayoutManager(new LinearLayoutManager(this,
            LinearLayoutManager.HORIZONTAL, false));
        mRecyclerViewGroupBoard.setAdapter(mGroupBoardRecyclerViewAdapter);
//        SnapHelper snapHelper = new LinearSnapHelper();
//        snapHelper.attachToRecyclerView(mRecyclerViewGroupBoard);
    }

    @Override
    public void onClickItem(GroupBoardRecyclerViewAdapter.BoardViewHolder boardViewHolder, int id) {
        for (GroupBoard groupBoard : mGroupBoardList) {
            boardViewHolder.itemView.setBackground(groupBoard.getId() == id ?
                ContextCompat.getDrawable(this, R.drawable.surround_item_board_selected) :
                ContextCompat.getDrawable(this, R.drawable.surround_item_board));
        }
    }
}
