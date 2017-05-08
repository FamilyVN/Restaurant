package com.tuananh.restaurant.manager.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;

import com.tuananh.restaurant.manager.R;
import com.tuananh.restaurant.manager.controller.database.DBHelper;
import com.tuananh.restaurant.manager.data.Constants;
import com.tuananh.restaurant.manager.model.Constant;
import com.tuananh.restaurant.manager.model.board.Board;
import com.tuananh.restaurant.manager.model.commodity.Commodity;
import com.tuananh.restaurant.manager.model.commodity.GroupCommodity;
import com.tuananh.restaurant.manager.ui.adapter.commodity.CommodityGirdViewAdapter;
import com.tuananh.restaurant.manager.ui.adapter.commodity.CommoditySelectedRecyclerViewAdapter;
import com.tuananh.restaurant.manager.ui.adapter.commodity.GroupCommodityRecyclerViewAdapter;
import com.tuananh.restaurant.manager.ui.listener.OnClickCommodityItemListener;
import com.tuananh.restaurant.manager.ui.listener.OnClickCommoditySelectedItemListener;
import com.tuananh.restaurant.manager.ui.listener.OnClickGroupCommodityItemListener;
import com.tuananh.restaurant.manager.utility.ToastUtils;

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
    private TextView mTextViewNameBoard, mTextViewTotalCost;
    private Board mBoard;
    private ImageView mImageViewUpDown;
    private ScrollView mScrollViewSelected;

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
        mDBHelper.createDBBoardCommodity();
        //
        mGroupCommodityList = mDBHelper.getDBGroupCommodity().getGroupCommodityAll();
        mCommodityList = mDBHelper.getDBCommodity().getCommodityAllCommon();
        mCommoditySelectedList = new ArrayList<>();
        //
        int id = getIntent().getIntExtra(Constants.ID_BOARD_SELECTED, Constant.ID_BOARD_DEFAULT);
        if (id != Constant.ID_BOARD_DEFAULT) {
            mBoard = mDBHelper.getDBBoard().getBoardById(id);
        } else {
            mBoard = new Board();
        }
    }

    private void initOnListener() {
        mButtonSave.setOnClickListener(this);
        mButtonPay.setOnClickListener(this);
    }

    private void initViews() {
//        mButtonSave = (Button) findViewById(R.id.button_save);
//        mButtonPay = (Button) findViewById(R.id.button_pay);
//        // list commodity selected
//        mRecyclerViewCommoditySelected =
//            (RecyclerView) findViewById(R.id.recycler_view_board_activity_list_commodity_selected);
//        mCommoditySelectedRecyclerViewAdapter = new CommoditySelectedRecyclerViewAdapter(this,
//            mCommoditySelectedList, this, mBoard);
//        mRecyclerViewCommoditySelected.setLayoutManager(
//            new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
//        mRecyclerViewCommoditySelected.setAdapter(mCommoditySelectedRecyclerViewAdapter);
//        // group commodity
//        mRecyclerViewGroupCommodity =
//            (RecyclerView) findViewById(R.id.recycler_view_board_activity_group_commodity);
//        mGroupCommodityRecyclerViewAdapter = new GroupCommodityRecyclerViewAdapter(
//            this, mGroupCommodityList, this);
//        mRecyclerViewGroupCommodity.setLayoutManager(new LinearLayoutManager(this,
//            LinearLayoutManager.HORIZONTAL, false));
//        mRecyclerViewGroupCommodity.setAdapter(mGroupCommodityRecyclerViewAdapter);
//        mRecyclerViewGroupCommodity.setHasFixedSize(true);
//        if (!mGroupCommodityList.isEmpty()) {
//            mGroupCommodityList.get(0).setSelected(true);
//            mGroupCommodityRecyclerViewAdapter.notifyItemChanged(Constant.ID_GROUP_DEFAULT);
//        }
//        // grid view commodity
//        mGridViewCommodity = (GridView) findViewById(R.id.grid_view_commodity_board_activity);
//        mCommodityGirdViewAdapter =
//            new CommodityGirdViewAdapter(this, mCommodityList, this, mBoard);
//        mGridViewCommodity.setAdapter(mCommodityGirdViewAdapter);
//        //
//        mTextViewNameBoard = (TextView) findViewById(R.id.text_board_activity_board);
//        if (mBoard != null) {
//            mTextViewNameBoard.setText(mBoard.getNameBoard());
//        }
//        mTextViewTotalCost = (TextView) findViewById(R.id.text_board_activity_total_money);
//        //
//        mImageViewUpDown = (ImageView) findViewById(R.id.image_up_down_board_activity);
//        mImageViewUpDown.setOnClickListener(this);
//        mScrollViewSelected = (ScrollView) findViewById(R.id.scroll_view_selected_board_activity);
//        mScrollViewSelected.setVisibility(View.GONE);
//        updateTotalCost();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.button_save:
                if (mCommoditySelectedList.size() > 0) {
                    mButtonSave.setVisibility(View.GONE);
                    mButtonPay.setVisibility(View.VISIBLE);
                    mBoard.setIsSave(Constant.TRUE);
                    mCommoditySelectedRecyclerViewAdapter.notifyDataSetChanged();
                    saveData();
                } else {
                    ToastUtils.showMessages(this, R.string.not_commodity_selected);
                }
                break;
            case R.id.button_pay:
                startActivity(new Intent(this, PaymentActivity.class));
                break;
            default:
                if (mScrollViewSelected.getVisibility() == View.VISIBLE) {
                    mImageViewUpDown.setImageResource(R.drawable.ic_double_arrow_down);
                    mScrollViewSelected.setVisibility(View.GONE);
                } else {
                    mImageViewUpDown.setImageResource(R.drawable.ic_double_arrow_up);
                    mScrollViewSelected.setVisibility(View.VISIBLE);
                }
                break;
        }
    }

    private void saveData() {
        if (mBoard.getId() != Board.ID_DEFAULT) {
            for (Commodity commodity : mCommoditySelectedList) {
                mDBHelper.getDBBoardCommodity().addBoardCommodity(mBoard.getId(), commodity.getId(),
                    commodity.getNumber());
            }
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
        if (mScrollViewSelected.getVisibility() == View.GONE) {
            mScrollViewSelected.setVisibility(View.VISIBLE);
            mImageViewUpDown.setImageResource(R.drawable.ic_double_arrow_up);
        }
        Commodity commoditySelectedNew = mCommodityList.get(position);
        boolean isExit = false;
        Commodity commodity = null;
        int size = mCommoditySelectedList.size();
        if (size == 0) {
            mCommoditySelectedList.add(commoditySelectedNew);
        } else {
            for (int i = 0; i < size; i++) {
                if (mCommoditySelectedList.get(i).getId() == commoditySelectedNew.getId()) {
                    isExit = true;
                    commodity = mCommoditySelectedList.get(i);
                }
            }
            if (isExit) {
                commodity.setNumber(commodity.getNumber() + 1);
            } else {
                mCommoditySelectedList.add(commoditySelectedNew);
            }
        }
        mCommoditySelectedRecyclerViewAdapter.notifyDataSetChanged();
        updateTotalCost();
    }

    private void updateTotalCost() {
        int totalCost = 0;
        for (Commodity commodity : mCommoditySelectedList) {
            totalCost += commodity.getTotalCost();
        }
        mTextViewTotalCost.setText(new StringBuilder().append(totalCost).append(" đ").toString());
    }

    @Override
    public void onClickReduce(
        CommoditySelectedRecyclerViewAdapter.CommoditySelectedViewHolder commoditySelectedViewHolder,
        int position) {
        Commodity commodity = mCommoditySelectedList.get(position);
        int number = commodity.getNumber();
        if (number - 1 > 0) {
            commodity.setNumber(number - 1);
        } else {
            mCommoditySelectedList.remove(position);
        }
        mCommoditySelectedRecyclerViewAdapter.notifyDataSetChanged();
        updateTotalCost();
    }

    @Override
    public void onClickUpAmount(
        CommoditySelectedRecyclerViewAdapter.CommoditySelectedViewHolder commoditySelectedViewHolder,
        int position) {
        Commodity commodity = mCommoditySelectedList.get(position);
        commodity.setNumber(commodity.getNumber() + 1);
        mCommoditySelectedRecyclerViewAdapter.notifyDataSetChanged();
        updateTotalCost();
    }

    @Override
    public boolean onLongClick(
        CommoditySelectedRecyclerViewAdapter.CommoditySelectedViewHolder commoditySelectedViewHolder,
        int position) {
        mBoard.setIsSave(Constant.FALSE);
        mCommoditySelectedRecyclerViewAdapter.notifyDataSetChanged();
        mButtonSave.setVisibility(View.VISIBLE);
        mButtonPay.setVisibility(View.GONE);
        return false;
    }
}
