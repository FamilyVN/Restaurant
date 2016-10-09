package com.tuananh.restaurant.manager.ui.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import com.tuananh.restaurant.manager.R;
import com.tuananh.restaurant.manager.data.Constants;
import com.tuananh.restaurant.manager.data.database.DBHelper;
import com.tuananh.restaurant.manager.data.model.commodity.Commodity;
import com.tuananh.restaurant.manager.data.model.commodity.CommoditySelected;
import com.tuananh.restaurant.manager.data.model.commodity.GroupCommodity;
import com.tuananh.restaurant.manager.ui.adapter.commodity.CommoditySelectedRecyclerViewAdapter;
import com.tuananh.restaurant.manager.ui.adapter.commodity.GroupCommodityRecyclerViewAdapter;
import com.tuananh.restaurant.manager.ui.listener.OnClickGroupCommodityItemListener;

import java.util.List;

public class BoardActivity extends AppCompatActivity
    implements View.OnClickListener, OnClickGroupCommodityItemListener {
    private Button mButtonSave, mButtonPay;
    private CommoditySelectedRecyclerViewAdapter mCommoditySelectedRecyclerViewAdapter;
    private GroupCommodityRecyclerViewAdapter mGroupCommodityRecyclerViewAdapter;
    private List<CommoditySelected> mCommoditySelectedList;
    private List<Commodity> mCommodityList;
    private List<GroupCommodity> mGroupCommodityList;
    private RecyclerView mRecyclerViewCommoditySelected;
    private RecyclerView mRecyclerViewGroupCommodity;
    private DBHelper mDBHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_board);
        loadData();
        initViews();
        initOnListener();
    }

    private void loadData() {
        int id = getIntent().getIntExtra(Constants.ID_BOARD_SELECTED, Constants.ID_BOARD_DEFAULT);
        if (id != Constants.ID_BOARD_DEFAULT) {
        }
        mDBHelper = new DBHelper(this);
        mDBHelper.createDBGroupCommodity();
        mGroupCommodityList = mDBHelper.getDBGroupCommodity().getGroupCommodityAll();
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
            mCommoditySelectedList);
        mRecyclerViewCommoditySelected.setLayoutManager(
            new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        mRecyclerViewCommoditySelected.setAdapter(mCommoditySelectedRecyclerViewAdapter);
        // grid view commodity
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
        //
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
//        int size = mCommodityList.size();
//        mGroupCommodityList.addAll(mDBHelper.getDBGroupCommodity()
//            .getCommodityAllByIdGroupCommodity(groupCommodity.getId()));
//        mCommodityRecyclerViewAdapter.notifyDataSetChanged();
//        int i = 0;
//        while (i < size) {
//            mCommodityList.remove(0);
//            mCommodityRecyclerViewAdapter.notifyDataSetChanged();
//            i++;
//        }
    }

    private void unSelected() {
        for (GroupCommodity groupCommodity : mGroupCommodityList) {
            groupCommodity.setSelected(false);
        }
    }
}
