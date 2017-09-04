package com.tuananh.restaurant.manager.view.activity.settings.drinkfood;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.View;

import com.restaurant.tuananh.mvvm.recycler.BaseViewAdapter;
import com.restaurant.tuananh.mvvm.recycler.SingleTypeAdapter;
import com.tuananh.restaurant.manager.R;
import com.tuananh.restaurant.manager.database.DatabaseManager;
import com.tuananh.restaurant.manager.databinding.ActivityListDrinkFoodBinding;
import com.tuananh.restaurant.manager.model.Constant;
import com.tuananh.restaurant.manager.model.commodity.Commodity;
import com.tuananh.restaurant.manager.model.commodity.GroupCommodity;
import com.tuananh.restaurant.manager.view.activity.BaseActivityRestaurant;
import com.tuananh.restaurant.manager.view.activity.settings.adddrinkfood.AddDrinkFoodActivity;

import java.util.List;

/**
 * Created by FRAMGIA\vu.tuan.anh on 19/07/2017.
 */
public class ListDrinkFoodActivity
    extends BaseActivityRestaurant<ActivityListDrinkFoodBinding, ListDrinkFoodActivityViewModel> {
    protected SingleTypeAdapter<Commodity> mCommodityAdapter;
    private SingleTypeAdapter<GroupCommodity> mGroupCommodityAdapter;
    private Dialog mDialogDeleteCommodity;

    @Override
    protected void onViewCreated() {
        super.onViewCreated();
        getBinding().setListener(this);
    }

    public void showGroupCommodityList(List<GroupCommodity> groupCommodityList) {
        if (mGroupCommodityAdapter != null) {
            mGroupCommodityAdapter.clear();
            mGroupCommodityAdapter.addAll(groupCommodityList);
            return;
        }
        mGroupCommodityAdapter =
            new SingleTypeAdapter<>(this, R.layout.item_drink_food_setting_ground_commodity);
        getBinding().setGroupCommodityLayoutManager(
            new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        getBinding().setGroupCommodityAdapter(mGroupCommodityAdapter);
        mGroupCommodityAdapter.addAll(groupCommodityList);
        mGroupCommodityAdapter.setPresenter(new SelectedGroupCommodityListener());
    }

    public void showCommodityList(List<Commodity> commodityList) {
        if (mCommodityAdapter != null) {
            mCommodityAdapter.clear();
            mCommodityAdapter.addAll(commodityList);
            return;
        }
        mCommodityAdapter =
            new SingleTypeAdapter<>(this, R.layout.item_drink_food_setting_commodity);
        getBinding().setCommodityLayoutManager(new LinearLayoutManager(this));
        getBinding().setCommodityAdapter(mCommodityAdapter);
        mCommodityAdapter.addAll(commodityList);
        mCommodityAdapter.setPresenter(new SelectedCommodityListener());
    }

    public void onAddCommodity() {
        Log.d("TAG", "onAddCommodity : id = " + getViewModel().getGroupCommodityId());
        Intent intent = new Intent(this, AddDrinkFoodActivity.class);
        startActivityForResult(intent, Constant.REQUEST_CODE_ADD_DRINK_FOOD);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == Constant.REQUEST_CODE_ADD_DRINK_FOOD && resultCode == RESULT_OK) {
            int position = data.getIntExtra(Constant.KEY_POSITION_GROUP_COMMODITY, 1);
            getBinding().recyclerGroupCommodityAdd.scrollToPosition(position - 1);
            getViewModel().loadDataCommodityList(position - 1);
            mGroupCommodityAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_list_drink_food;
    }

    public class SelectedGroupCommodityListener implements BaseViewAdapter.Presenter {
        public void onClickItemGroupCommodity(int position) {
            getViewModel().loadDataCommodityList(position);
            mGroupCommodityAdapter.notifyDataSetChanged();
        }
    }

    public class SelectedCommodityListener implements BaseViewAdapter.Presenter {
        private AlertDialog.Builder createBuilder(final int idCommodity) {
            AlertDialog.Builder builder = new AlertDialog.Builder(ListDrinkFoodActivity.this)
                .setTitle(R.string.text_dialog_delete_commodity)
                .setMessage(R.string.text_dialog_msg_delete_commodity)
                .setPositiveButton(R.string.text_yes, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        DatabaseManager.getInstance(ListDrinkFoodActivity.this)
                            .deleteCommodity(idCommodity);
                    }
                })
                .setNegativeButton(R.string.text_no, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                });
            return builder;
        }

        public void onClickItemCommodity(View view, Commodity commodity) {
            Log.d("TAG", "onClickItemCommodity : id = " + commodity.getIdCommodity());
        }

        public void onDeleteCommodity(View view, Commodity commodity) {
            Log.d("TAG", "onDeleteCommodity : id = " + commodity.getIdCommodity());
            if (mDialogDeleteCommodity != null && mDialogDeleteCommodity.isShowing()) {
                mDialogDeleteCommodity.dismiss();
            }
            mDialogDeleteCommodity = createBuilder(commodity.getIdCommodity()).create();
            mDialogDeleteCommodity.show();
            mCommodityAdapter.notifyDataSetChanged();
        }
    }
}
