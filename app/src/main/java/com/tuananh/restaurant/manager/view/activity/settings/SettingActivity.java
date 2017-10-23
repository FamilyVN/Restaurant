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
import com.tuananh.restaurant.manager.view.activity.settings.drinkfood.ListDrinkFoodActivity;
import com.tuananh.restaurant.manager.view.activity.settings.listmenu.ListMenuActivity;
import com.tuananh.restaurant.manager.view.activity.settings.roomboard.MapRoomBoardActivity;

import java.util.List;

/**
 * Created by framgia on 19/05/2017.
 */
public class SettingActivity
    extends BaseActivityRestaurant<ActivitySettingBinding, SettingActivityViewModel> {
    private SingleTypeAdapter<Setting> mSettingAdapter;

    @Override
    protected void onViewCreated() {
        super.onViewCreated();
        getBinding().setListener(this);
    }

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
            Intent intent;
            switch (setting.getIdSettings()) {
                case Constant.TYPE_SETTING_LIST_MENU:
                    intent = new Intent(SettingActivity.this, ListMenuActivity.class);
                    break;
                case Constant.TYPE_SETTING_DRINK_AND_FOOD:
                    intent = new Intent(SettingActivity.this, ListDrinkFoodActivity.class);
                    break;
                default:
                    intent = new Intent(SettingActivity.this, MapRoomBoardActivity.class);
                    break;
            }
            startActivity(intent);
            overridePendingTransition(R.anim.slide_in_from_right, R.anim.slide_out_to_left);
        }
    }
}
