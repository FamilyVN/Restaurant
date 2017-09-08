package com.tuananh.restaurant.manager.view.activity.settings;

import com.restaurant.tuananh.mvvm.BaseViewModel;
import com.tuananh.restaurant.manager.R;
import com.tuananh.restaurant.manager.databinding.ActivitySettingBinding;
import com.tuananh.restaurant.manager.model.Constant;
import com.tuananh.restaurant.manager.model.setting.Setting;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by framgia on 19/05/2017.
 */
public class SettingActivityViewModel
    extends BaseViewModel<ActivitySettingBinding, SettingActivity> {
    @Override
    public void onAttached(boolean isFirst) {
        super.onAttached(isFirst);
        if (isFirst) {
            loadData();
        }
    }

    private void loadData() {
        List<Setting> settingList = new ArrayList<>();
        settingList.add(new Setting(Constant.TYPE_SETTING_DRINK_AND_FOOD,
            getString(R.string.text_drink_and_food), R.drawable.ic_food));
        settingList.add(new Setting(Constant.TYPE_SETTING_ROOM_BOARD,
            getString(R.string.text_map_room_board), R.drawable.ic_map_table));
        getView().showSettingList(settingList);
    }
}
