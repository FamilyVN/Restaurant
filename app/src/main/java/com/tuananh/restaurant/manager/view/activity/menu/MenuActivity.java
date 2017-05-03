package com.tuananh.restaurant.manager.view.activity.menu;

import com.restaurant.tuananh.mvvm.BaseActivityHeaderFooter;
import com.tuananh.restaurant.manager.R;
import com.tuananh.restaurant.manager.databinding.ActivityMenuBinding;

public class MenuActivity
    extends BaseActivityHeaderFooter<ActivityMenuBinding, MenuActivityViewModel> {
    @Override
    public int getLayoutId() {
        return R.layout.activity_menu;
    }
}
