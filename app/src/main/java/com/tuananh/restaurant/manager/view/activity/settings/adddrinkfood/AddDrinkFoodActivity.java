package com.tuananh.restaurant.manager.view.activity.settings.adddrinkfood;

import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.tuananh.restaurant.manager.R;
import com.tuananh.restaurant.manager.database.DatabaseManager;
import com.tuananh.restaurant.manager.databinding.ActivityAddDrinkFoodBinding;
import com.tuananh.restaurant.manager.model.Constant;
import com.tuananh.restaurant.manager.model.commodity.Commodity;
import com.tuananh.restaurant.manager.model.commodity.GroupCommodity;
import com.tuananh.restaurant.manager.view.activity.BaseActivityRestaurant;

import java.util.ArrayList;
import java.util.List;

public class AddDrinkFoodActivity
    extends BaseActivityRestaurant<ActivityAddDrinkFoodBinding, AddDrinkFoodActivityViewModel> {
    private int mIdForGroupCommodity;
    private int mPositionDefault;
    private int mIdCommodity;
    private Commodity mCommodity;

    @Override
    protected void onViewCreated() {
        super.onViewCreated();
        updateData();
        getBinding().setListener(this);
        initSpinner();
    }

    private void updateData() {
        Intent intent = getIntent();
        if (intent != null) {
            mCommodity = (Commodity) intent.getSerializableExtra(Constant.KEY_COMMODITY);
            boolean isCommonCommodity = false;
            if (mCommodity != null) {
                mIdCommodity = mCommodity.getIdCommodity();
                getBinding().editNameCommodity.setText(mCommodity.getNameCommodity());
                getBinding().editCostCommodity
                    .setText(String.valueOf(mCommodity.getCostCommodity()));
                isCommonCommodity = mCommodity.isCommonCommodity();
            } else {
                mIdCommodity = -1;
            }
            getBinding().radioButtonYes.setChecked(isCommonCommodity);
            getBinding().radioButtonNo.setChecked(!isCommonCommodity);
        }
    }

    private void initSpinner() {
        final List<GroupCommodity> groupCommodityList =
            DatabaseManager.getInstance(this).getGroupCommodityAll();
        final List<String> dataList = new ArrayList<>();
        for (GroupCommodity groupCommodity : groupCommodityList) {
            dataList.add(groupCommodity.getNameGroupCommodity());
        }
        // note
        dataList.remove(0);
        groupCommodityList.remove(0);
        if (mCommodity != null) {
            for (GroupCommodity groupCommodity : groupCommodityList) {
                if (groupCommodity.getIdGroupCommodity() == mCommodity.getIdForGroupCommodity()) {
                    mPositionDefault = groupCommodityList.indexOf(groupCommodity);
                }
            }
        }
        //
        ArrayAdapter<String> adapter =
            new ArrayAdapter<>(this, R.layout.item_spinner_group_commodity, dataList);
        adapter.setDropDownViewResource(R.layout.item_spinner_list_single_choice);
        getBinding().spinnerGroupCommodity.setAdapter(adapter);
        getBinding().spinnerGroupCommodity.setSelection(mPositionDefault);
        getBinding().spinnerGroupCommodity.setOnItemSelectedListener(
            new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> adapterView, View view, int position,
                                           long l) {
                    mIdForGroupCommodity =
                        groupCommodityList.get(position).getIdGroupCommodity();
                }

                @Override
                public void onNothingSelected(AdapterView<?> adapterView) {
                    if (groupCommodityList.size() > 0) {
                        groupCommodityList.get(0).getIdGroupCommodity();
                    }
                }
            });
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
            commodity = new Commodity(nameCommodity, costCommodity, mIdForGroupCommodity,
                getIsCommonCommodity());
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (commodity == null) return;
        if (mIdCommodity == -1) {
            if (DatabaseManager.getInstance(this).insertCommodity(commodity)) {
                onSaveSuccess();
            }
        } else {
            if (DatabaseManager.getInstance(this).updateCommodity(mIdCommodity, commodity)) {
                onSaveSuccess();
            }
        }
    }

    private void onSaveSuccess() {
        Toast.makeText(this, R.string.msg_add_drink_food_success, Toast.LENGTH_SHORT).show();
        Intent intent = new Intent();
        intent.putExtra(Constant.KEY_POSITION_GROUP_COMMODITY, mIdForGroupCommodity);
        setResult(Activity.RESULT_OK, intent);
        finish();
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_add_drink_food;
    }
}
