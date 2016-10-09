package com.tuananh.restaurant.manager.ui.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import com.tuananh.restaurant.manager.R;
import com.tuananh.restaurant.manager.data.Constants;
import com.tuananh.restaurant.manager.data.model.Commodity;
import com.tuananh.restaurant.manager.data.model.CommoditySelected;
import com.tuananh.restaurant.manager.ui.adapter.CommodityRecyclerViewAdapter;
import com.tuananh.restaurant.manager.ui.adapter.CommoditySelectedRecyclerViewAdapter;

import java.util.List;

public class BoardActivity extends AppCompatActivity implements View.OnClickListener {
    private Button mButtonSave, mButtonPay;
    private CommoditySelectedRecyclerViewAdapter mCommoditySelectedRecyclerViewAdapter;
    private CommodityRecyclerViewAdapter mCommodityRecyclerViewAdapter;
    private List<CommoditySelected> mCommoditySelectedList;
    private List<Commodity> mCommodityList;
    private RecyclerView mRecyclerViewCommoditySelected;
    private RecyclerView mRecyclerViewCommodity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_board);
        initViews();
        initOnListener();
        loadData();
    }

    private void loadData() {
        int id = getIntent().getIntExtra(Constants.ID_BOARD_SELECTED, Constants.ID_BOARD_DEFAULT);
        if (id != Constants.ID_BOARD_DEFAULT) {
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
            mCommoditySelectedList);
        mRecyclerViewCommoditySelected.setLayoutManager(
            new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        mRecyclerViewCommoditySelected.setAdapter(mCommoditySelectedRecyclerViewAdapter);
        // list commodity
        mRecyclerViewCommodity =
            (RecyclerView) findViewById(R.id.recycler_view_board_activity_list_commodity);
        mCommodityRecyclerViewAdapter = new CommodityRecyclerViewAdapter(this, mCommodityList);
        mRecyclerViewCommodity.setLayoutManager(
            new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        mRecyclerViewCommodity.setAdapter(mCommodityRecyclerViewAdapter);
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
}
