package com.tuananh.restaurant.manager.view.activity.settings.adddrinkfood;

import android.app.Activity;
import android.content.Intent;
import android.widget.Toast;

import com.tuananh.restaurant.manager.R;
import com.tuananh.restaurant.manager.database.DatabaseManager;
import com.tuananh.restaurant.manager.databinding.ActivityAddDrinkFoodBinding;
import com.tuananh.restaurant.manager.model.Constant;
import com.tuananh.restaurant.manager.model.commodity.Commodity;
import com.tuananh.restaurant.manager.view.activity.BaseActivityRestaurant;

public class AddDrinkFoodActivity
    extends BaseActivityRestaurant<ActivityAddDrinkFoodBinding, AddDrinkFoodActivityViewModel> {
    @Override
    protected void onViewCreated() {
        super.onViewCreated();
        getBinding().setListener(this);
    }

    private int getIsCommonCommodity() {
        int isCommonCommodity;
        switch (getBinding().radioGroupCommonCommodity.getCheckedRadioButtonId()) {
            case R.id.radio_button_yes:
                isCommonCommodity = Constant.TRUE;
                break;
            default:
                isCommonCommodity = Constant.FALSE;
                break;
        }
        return isCommonCommodity;
    }

    public void onSave() {
        Commodity commodity = null;
        String nameCommodity = getBinding().editNameCommodity.getText().toString();
        int costCommodity;
        try {
            costCommodity = Integer.parseInt(getBinding().editCostCommodity.getText().toString());
            // test
            int idForGroupCommodity = 2;
            commodity = new Commodity(nameCommodity, costCommodity, idForGroupCommodity,
                getIsCommonCommodity());
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (commodity == null) return;
        if (DatabaseManager.getInstance(this).insertCommodity(commodity)) {
            Toast.makeText(this, R.string.msg_add_drink_food_success, Toast.LENGTH_SHORT).show();
            Intent intent = new Intent();
            setResult(Activity.RESULT_OK, intent);
            finish();
        }
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_add_drink_food;
    }
}
