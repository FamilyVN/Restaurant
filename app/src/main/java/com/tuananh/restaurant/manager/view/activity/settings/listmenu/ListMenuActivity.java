package com.tuananh.restaurant.manager.view.activity.settings.listmenu;

import com.tuananh.restaurant.manager.R;
import com.tuananh.restaurant.manager.databinding.ActivityListMenuBinding;
import com.tuananh.restaurant.manager.view.activity.BaseActivityRestaurant;

/**
 * Created by TuanAnh on 9/13/2017.
 */
public class ListMenuActivity
    extends BaseActivityRestaurant<ActivityListMenuBinding, ListMenuActivityViewModel> {
    @Override
    public int getLayoutId() {
        return R.layout.activity_list_menu;
    }
}
