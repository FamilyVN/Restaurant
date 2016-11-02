package com.tuananh.restaurant.manager.ui.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.tuananh.restaurant.manager.R;
import com.tuananh.restaurant.manager.controller.database.DBHelper;
import com.tuananh.restaurant.manager.data.model.setting.Setting;
import com.tuananh.restaurant.manager.ui.adapter.setting.SettingRecyclerAdapter;
import com.tuananh.restaurant.manager.ui.listener.OnClickSettingItemListener;

import java.util.List;

public class SettingActivity extends BaseActivity implements OnClickSettingItemListener {
    private RecyclerView mRecyclerViewSetting;
    private SettingRecyclerAdapter mSettingRecyclerAdapter;
    private List<Setting> mSettingList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        loadData();
        initViews();
    }

    private void loadData() {
        DBHelper dbHelper = new DBHelper(this);
        dbHelper.createDBSetting();
        mSettingList = dbHelper.getDBSetting().getSettingAll();
    }

    private void initViews() {
        mRecyclerViewSetting = (RecyclerView) findViewById(R.id.recycler_view_setting_activity);
        mSettingRecyclerAdapter = new SettingRecyclerAdapter(this, mSettingList, this);
        mRecyclerViewSetting.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerViewSetting.setAdapter(mSettingRecyclerAdapter);
    }

    @Override
    public void onClickItem(SettingRecyclerAdapter.SettingViewHolder settingViewHolder,
                            int position) {

    }
}
