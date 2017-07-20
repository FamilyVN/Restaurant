package com.tuananh.restaurant.manager.view.activity.settings.drinkfood;

import com.tuananh.restaurant.manager.R;
import com.tuananh.restaurant.manager.databinding.ActivityListDrinkFoodBinding;
import com.tuananh.restaurant.manager.view.activity.BaseActivityRestaurant;

/**
 * Created by FRAMGIA\vu.tuan.anh on 19/07/2017.
 */
public class ListDrinkFoodActivity
    extends BaseActivityRestaurant<ActivityListDrinkFoodBinding, ListDrinkFoodActivityViewModel> {
    @Override
    public int getLayoutId() {
        return R.layout.activity_list_drink_food;
    }
}
