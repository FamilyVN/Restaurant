package com.tuananh.restaurant.manager.view.activity.settings.adddrinkfood;

import com.tuananh.restaurant.manager.R;
import com.tuananh.restaurant.manager.databinding.ActivityAddDrinkFoodBinding;
import com.tuananh.restaurant.manager.view.activity.BaseActivityRestaurant;

public class AddDrinkFoodActivity
    extends BaseActivityRestaurant<ActivityAddDrinkFoodBinding, AddDrinkFoodActivityViewModel> {
    @Override
    protected void onViewCreated() {
        super.onViewCreated();
        getBinding().setListener(this);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_add_drink_food;
    }
}
