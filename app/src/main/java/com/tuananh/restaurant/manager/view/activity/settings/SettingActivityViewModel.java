package com.tuananh.restaurant.manager.view.activity.settings;

import com.restaurant.tuananh.mvvm.BaseViewModel;
import com.tuananh.restaurant.manager.R;
import com.tuananh.restaurant.manager.databinding.ActivitySettingBinding;
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
        loadData();
    }

    private void loadData() {
        List<Setting> settingList = new ArrayList<>();
        settingList
            .add(new Setting(1, getString(R.string.text_drink_and_food), R.drawable.ic_food));
        getView().showSettingList(settingList);
    }
}
