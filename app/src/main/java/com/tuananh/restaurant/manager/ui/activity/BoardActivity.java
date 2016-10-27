package com.tuananh.restaurant.manager.ui.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.GridView;
import android.widget.TextView;

import com.tuananh.restaurant.manager.R;
import com.tuananh.restaurant.manager.data.Constants;
import com.tuananh.restaurant.manager.data.database.DBHelper;
import com.tuananh.restaurant.manager.data.model.board.Board;
import com.tuananh.restaurant.manager.data.model.commodity.Commodity;
import com.tuananh.restaurant.manager.data.model.commodity.GroupCommodity;
import com.tuananh.restaurant.manager.ui.adapter.commodity.CommodityGirdViewAdapter;
import com.tuananh.restaurant.manager.ui.adapter.commodity.CommoditySelectedRecyclerViewAdapter;
import com.tuananh.restaurant.manager.ui.adapter.commodity.GroupCommodityRecyclerViewAdapter;
import com.tuananh.restaurant.manager.ui.listener.OnClickCommodityItemListener;
import com.tuananh.restaurant.manager.ui.listener.OnClickCommoditySelectedItemListener;
import com.tuananh.restaurant.manager.ui.listener.OnClickGroupCommodityItemListener;

import java.util.ArrayList;
import java.util.List;

public class BoardActivity extends BaseActivity
    implements View.OnClickListener, OnClickGroupCommodityItemListener,
    OnClickCommodityItemListener, OnClickCommoditySelectedItemListener {
    private Button mButtonSave, mButtonPay;
    private CommoditySelectedRecyclerViewAdapter mCommoditySelectedRecyclerViewAdapter;
    private GroupCommodityRecyclerViewAdapter mGroupCommodityRecyclerViewAdapter;
    private List<Commodity> mCommoditySelectedList;
    private List<Commodity> mCommodityList;
    private List<GroupCommodity> mGroupCommodityList;
    private RecyclerView mRecyclerViewCommoditySelected;
    private RecyclerView mRecyclerViewGroupCommodity;
    private DBHelper mDBHelper;
    private CommodityGirdViewAdapter mCommodityGirdViewAdapter;
    private GridView mGridViewCommodity;
    private TextView mTextViewNameBoard;
    private Board mBoard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_board);
        loadData();
        initViews();
        initOnListener();
    }

    private void loadData() {
        mDBHelper = new DBHelper(this);
        mDBHelper.createDBBoard();
        mDBHelper.createDBGroupCommodity();
        mDBHelper.createDBCommodity();
        //
        mGroupCommodityList = mDBHelper.getDBGroupCommodity().getGroupCommodityAll();
        mCommodityList = mDBHelper.getDBCommodity().getCommodityAllCommon();
        mCommoditySelectedList = new ArrayList<>();
        //
        int id = getIntent().getIntExtra(Constants.ID_BOARD_SELECTED, Constants.ID_BOARD_DEFAULT);
        if (id != Constants.ID_BOARD_DEFAULT) {
            mBoard = mDBHelper.getDBBoard().getBoardById(id);
        }
    }

    private void initOnListener() {
        mButtonSave.setOnClickListener(this);
        mButtonPay.setOnClickListener(this);
    }

    private void initViews() {
        mButtonSave = (Button) findViewById(R.id.button_save);
        mButtonPay = (Button) findViewById(R.id.button_pay);
        // list commodity selected
        mRecyclerViewCommoditySelected =
            (RecyclerView) findViewById(R.id.recycler_view_board_activity_list_commodity_selected);
        mCommoditySelectedRecyclerViewAdapter = new CommoditySelectedRecyclerViewAdapter(this,
            mCommoditySelectedList, this);
        mRecyclerViewCommoditySelected.setLayoutManager(
            new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        mRecyclerViewCommoditySelected.setAdapter(mCommoditySelectedRecyclerViewAdapter);
        // group commodity
        mRecyclerViewGroupCommodity =
            (RecyclerView) findViewById(R.id.recycler_view_board_activity_group_commodity);
        mGroupCommodityRecyclerViewAdapter = new GroupCommodityRecyclerViewAdapter(
            this, mGroupCommodityList, this);
        mRecyclerViewGroupCommodity.setLayoutManager(new LinearLayoutManager(this,
            LinearLayoutManager.HORIZONTAL, false));
        mRecyclerViewGroupCommodity.setAdapter(mGroupCommodityRecyclerViewAdapter);
        mRecyclerViewGroupCommodity.setHasFixedSize(true);
        if (!mGroupCommodityList.isEmpty()) {
            mGroupCommodityList.get(0).setSelected(true);
            mGroupCommodityRecyclerViewAdapter.notifyItemChanged(Constants.ID_GROUP_DEFAULT);
        }
        // grid view commodity
        mGridViewCommodity = (GridView) findViewById(R.id.grid_view_commodity_board_activity);
        mCommodityGirdViewAdapter = new CommodityGirdViewAdapter(this, mCommodityList, this);
        mGridViewCommodity.setAdapter(mCommodityGirdViewAdapter);
        //
        mTextViewNameBoard = (TextView) findViewById(R.id.text_board_activity_board);
        if (mBoard != null) {
            mTextViewNameBoard.setText(new StringBuilder().append(
                getString(R.string.board_activity_board)).append("     ").append(
                mBoard.getNameBoard()).toString());
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.button_save:
                break;
            case R.id.button_pay:
                break;
        }
    }

    @Override
    public void onClickItemGroupCommodity(
        GroupCommodityRecyclerViewAdapter.GroupCommodityViewHolder groupCommodityViewHolder,
        int position) {
        unSelected();
        GroupCommodity groupCommodity = mGroupCommodityList.get(position);
        groupCommodity.setSelected(true);
        mGroupCommodityRecyclerViewAdapter.notifyDataSetChanged();
        //
        int size = mCommodityList.size();
        switch (position) {
            case 0:
                mCommodityList.addAll(mDBHelper.getDBCommodity().getCommodityAllCommon());
                break;
            default:
                mCommodityList.addAll(mDBHelper.getDBCommodity()
                    .getCommodityAllByIdGroupCommodity(position + 1));
                break;
        }
        mCommodityGirdViewAdapter.notifyDataSetChanged();
        int i = 0;
        while (i < size) {
            mCommodityList.remove(0);
            mCommodityGirdViewAdapter.notifyDataSetChanged();
            i++;
        }
    }

    private void unSelected() {
        for (GroupCommodity groupCommodity : mGroupCommodityList) {
            groupCommodity.setSelected(false);
        }
    }

    @Override
    public void onClickItemCommodity(
        CommodityGirdViewAdapter.CommodityViewHolder commodityViewHolder, int position) {
        mCommoditySelectedList.add(mCommodityList.get(position));
        mCommoditySelectedRecyclerViewAdapter.notifyDataSetChanged();
    }

    @Override
    public void onClickReduce(
        CommoditySelectedRecyclerViewAdapter.CommoditySelectedViewHolder commoditySelectedViewHolder,
        int position) {
    }

    @Override
    public void onClickUpAmount(
        CommoditySelectedRecyclerViewAdapter.CommoditySelectedViewHolder commoditySelectedViewHolder,
        int position) {
    }

    @Override
    public boolean onLongClick(
        CommoditySelectedRecyclerViewAdapter.CommoditySelectedViewHolder commoditySelectedViewHolder,
        int position) {
        return false;
    }
}
