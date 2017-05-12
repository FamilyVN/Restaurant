package com.tuananh.restaurant.manager.view.activity.board;

import android.support.design.widget.BottomSheetBehavior;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.restaurant.tuananh.mvvm.recycler.BaseViewAdapter;
import com.restaurant.tuananh.mvvm.recycler.SingleTypeAdapter;
import com.tuananh.restaurant.manager.R;
import com.tuananh.restaurant.manager.databinding.ActivityBoardBinding;
import com.tuananh.restaurant.manager.model.commodity.Commodity;
import com.tuananh.restaurant.manager.model.commodity.GroupCommodity;
import com.tuananh.restaurant.manager.utility.ToastUtils;
import com.tuananh.restaurant.manager.view.activity.BaseActivityRestaurant;

import java.util.List;

/**
 * Created by framgia on 05/05/2017.
 */
public class BoardActivity
    extends BaseActivityRestaurant<ActivityBoardBinding, BoardActivityViewModel> {
    private static final int DELTA_UP_REDUCE = 1;
    private static final int NUMBER_ROW = 3;
    private SingleTypeAdapter<Commodity> mCommoditySelectedAdapter;
    private SingleTypeAdapter<GroupCommodity> mGroupCommodityAdapter;
    private SingleTypeAdapter<Commodity> mCommodityAdapter;
    private BottomSheetBehavior mBottomSheetBehavior;

    @Override
    protected void onViewCreated() {
        super.onViewCreated();
        getBinding().setListener(new BoardListener());
        mBottomSheetBehavior = BottomSheetBehavior.from(getBinding().bottomSheet);
        mBottomSheetBehavior.setState(BottomSheetBehavior.STATE_HIDDEN);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_board;
    }

    public void showCommoditySelectedList(List<Commodity> commoditySelectedList) {
        if (mCommoditySelectedAdapter != null) {
            mCommoditySelectedAdapter.clear();
            mCommoditySelectedAdapter.addAll(commoditySelectedList);
        }
        mCommoditySelectedAdapter = new SingleTypeAdapter<>(this, R.layout.item_selected_commodity);
        mCommoditySelectedAdapter.addAll(commoditySelectedList);
        mCommoditySelectedAdapter.setPresenter(new SelectedCommodityListener());
        getBinding().setCommoditySelectedAdapter(mCommoditySelectedAdapter);
        getBinding().setCommoditySelectedLayoutManager(new LinearLayoutManager(this));
    }

    public void showTotalCost(int totalCost) {
        getBinding().textBoardActivityTotalMoney.setText(String.format("%d đ", totalCost));
    }

    public void showGroupCommodityList(List<GroupCommodity> groupCommodityList) {
        if (mGroupCommodityAdapter != null) {
            mGroupCommodityAdapter.clear();
            mGroupCommodityAdapter.addAll(groupCommodityList);
        }
        mGroupCommodityAdapter = new SingleTypeAdapter<>(this, R.layout.item_group_commodity);
        mGroupCommodityAdapter.addAll(groupCommodityList);
        mGroupCommodityAdapter.setPresenter(new GroupCommodityListener());
        getBinding().setGroupCommodityAdapter(mGroupCommodityAdapter);
        getBinding().setGroupCommodityLayoutManager(
            new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
    }

    public void showCommodityList(List<Commodity> commodityList) {
        if (mCommodityAdapter != null) {
            mCommodityAdapter.clear();
            mCommodityAdapter.addAll(commodityList);
        }
        mCommodityAdapter = new SingleTypeAdapter<>(this, R.layout.item_commodity);
        mCommodityAdapter.addAll(commodityList);
        mCommodityAdapter.setPresenter(new GroupCommodityListener());
        getBinding().setCommodityAdapter(mCommodityAdapter);
        getBinding().setCommodityLayoutManager(new GridLayoutManager(this, NUMBER_ROW));
    }

    public class GroupCommodityListener implements BaseViewAdapter.Presenter {
        public void onSelected(int position) {
            getViewModel().loadDataGroupCommodityList(position);
            mGroupCommodityAdapter.notifyDataSetChanged();
        }
    }

    public class BoardListener {
        public void onSave() {
            if (getViewModel().isSelectedCommodity()) {
                getBinding().buttonSave.setVisibility(View.GONE);
                getBinding().buttonPay.setVisibility(View.VISIBLE);
                getViewModel().saveData();
                mCommoditySelectedAdapter.notifyDataSetChanged();
            } else {
                ToastUtils.showMessages(BoardActivity.this, R.string.not_commodity_selected);
            }
        }

        public void onPay() {
        }

        public void onOrder() {
            mBottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
        }
    }

    public class SelectedCommodityListener implements BaseViewAdapter.Presenter {
        public void onReduce(Commodity commodity, int position) {
            int number = commodity.getNumber();
            if (number - DELTA_UP_REDUCE > 0) {
                commodity.setNumber(number - DELTA_UP_REDUCE);
            } else {
                mCommoditySelectedAdapter.remove(position);
            }
        }

        public void onUpAmount(Commodity commodity) {
            commodity.setNumber(commodity.getNumber() + DELTA_UP_REDUCE);
            mCommoditySelectedAdapter.notifyDataSetChanged();
            getViewModel().updateTotalCost();
        }
    }
}
