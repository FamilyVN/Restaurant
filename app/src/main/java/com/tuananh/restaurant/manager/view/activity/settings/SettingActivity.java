package com.tuananh.restaurant.manager.view.activity.settings;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;

import com.restaurant.tuananh.mvvm.recycler.BaseViewAdapter;
import com.restaurant.tuananh.mvvm.recycler.SingleTypeAdapter;
import com.tuananh.restaurant.manager.R;
import com.tuananh.restaurant.manager.databinding.ActivitySettingBinding;
import com.tuananh.restaurant.manager.model.Constant;
import com.tuananh.restaurant.manager.model.setting.Setting;
import com.tuananh.restaurant.manager.view.activity.BaseActivityRestaurant;

import java.util.List;

/**
 * Created by framgia on 19/05/2017.
 */
public class SettingActivity
    extends BaseActivityRestaurant<ActivitySettingBinding, SettingActivityViewModel> {
    private SingleTypeAdapter<Setting> mSettingAdapter;

    public void showSettingList(List<Setting> settingList) {
        if (mSettingAdapter != null) {
            mSettingAdapter.clear();
            mSettingAdapter.addAll(settingList);
            return;
        }
        mSettingAdapter = new SingleTypeAdapter<>(this, R.layout.item_settings);
        mSettingAdapter.addAll(settingList);
        mSettingAdapter.setPresenter(new SettingClickListener());
        getBinding().recyclerViewSettingActivity.setAdapter(mSettingAdapter);
        getBinding().recyclerViewSettingActivity.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_setting;
    }

    public class SettingClickListener implements BaseViewAdapter.Presenter {
        public void showItemSetting(Setting setting) {
            switch (setting.getIdSettings()) {
                case Constant.TYPE_SETTING_DRINK_AND_FOOD:
                    startActivity(new Intent(SettingActivity.this, ListDrinkFoodActivity.class));
                    break;
                default:
                    break;
            }
        }
    }
}
