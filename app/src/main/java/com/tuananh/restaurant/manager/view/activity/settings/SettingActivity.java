package com.tuananh.restaurant.manager.view.activity.settings;

import com.tuananh.restaurant.manager.R;
import com.tuananh.restaurant.manager.databinding.ActivitySettingBinding;
import com.tuananh.restaurant.manager.view.activity.BaseActivityRestaurant;

/**
 * Created by framgia on 19/05/2017.
 */
public class SettingActivity
    extends BaseActivityRestaurant<ActivitySettingBinding, SettingActivityViewModel> {
    @Override
    public int getLayoutId() {
        return R.layout.activity_setting;
    }
}
