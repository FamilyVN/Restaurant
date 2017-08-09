package com.tuananh.restaurant.manager.view.activity.board;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.restaurant.tuananh.mvvm.recycler.BaseViewAdapter;
import com.restaurant.tuananh.mvvm.recycler.SingleTypeAdapter;
import com.tuananh.restaurant.manager.R;
import com.tuananh.restaurant.manager.databinding.ActivityBoardBinding;
import com.tuananh.restaurant.manager.model.Constant;
import com.tuananh.restaurant.manager.model.commodity.Commodity;
import com.tuananh.restaurant.manager.model.commodity.GroupCommodity;
import com.tuananh.restaurant.manager.utils.CommonUtils;
import com.tuananh.restaurant.manager.utils.ToastUtils;
import com.tuananh.restaurant.manager.view.activity.BaseActivityRestaurant;
import com.tuananh.restaurant.manager.view.activity.payment.PaymentActivity;
import com.tuananh.restaurant.manager.view.activity.sell.SellActivity;
import com.tuananh.restaurant.manager.view.custom.BottomSheetBehaviorRecyclerManager;
import com.tuananh.restaurant.manager.view.custom.BottomSheetBehaviorV2;

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
    private BottomSheetBehaviorV2 mBottomSheetBehavior;

    @Override
    protected void onViewCreated() {
        super.onViewCreated();
        getBinding().setListener(new BoardListener());
        mBottomSheetBehavior = BottomSheetBehaviorV2.from(getBinding().bottomSheet);
        mBottomSheetBehavior.setState(BottomSheetBehaviorV2.STATE_HIDDEN);
        BottomSheetBehaviorRecyclerManager manager =
            new BottomSheetBehaviorRecyclerManager(getBinding().parentContainer,
                mBottomSheetBehavior, getBinding().bottomSheet);
        manager.addControl(getBinding().recyclerGroupCommodity);
        manager.addControl(getBinding().recyclerCommodity);
        manager.create();
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_board;
    }

    public void showCommoditySelectedList(List<Commodity> commoditySelectedList) {
        if (mCommoditySelectedAdapter != null) {
            mCommoditySelectedAdapter.clear();
            mCommoditySelectedAdapter.addAll(commoditySelectedList);
            return;
        }
        mCommoditySelectedAdapter = new SingleTypeAdapter<>(this, R.layout.item_selected_commodity);
        mCommoditySelectedAdapter.addAll(commoditySelectedList);
        mCommoditySelectedAdapter.setPresenter(new SelectedCommodityListener());
        getBinding().setCommoditySelectedAdapter(mCommoditySelectedAdapter);
        getBinding().setCommoditySelectedLayoutManager(new LinearLayoutManager(this));
    }

    public void showTotalCost(Long totalCost) {
        getBinding().textBoardActivityTotalMoney.setText(String
            .format(Constant.FORMAT_MONEY, CommonUtils.convertToMoney(totalCost.toString())));
    }

    public void showGroupCommodityList(List<GroupCommodity> groupCommodityList) {
        if (mGroupCommodityAdapter != null) {
            mGroupCommodityAdapter.clear();
            mGroupCommodityAdapter.addAll(groupCommodityList);
            return;
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
            return;
        }
        mCommodityAdapter = new SingleTypeAdapter<>(this, R.layout.item_commodity);
        mCommodityAdapter.addAll(commodityList);
        mCommodityAdapter.setPresenter(new CommodityListener());
        getBinding().setCommodityAdapter(mCommodityAdapter);
        getBinding().setCommodityLayoutManager(new GridLayoutManager(this, NUMBER_ROW));
    }

    public void updateButton(boolean isSave) {
        getBinding().buttonPay.setVisibility(isSave ? View.GONE : View.VISIBLE);
        getBinding().buttonSave.setVisibility(isSave ? View.VISIBLE : View.GONE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == Constant.REQUEST_CODE_PAYMENT && resultCode == RESULT_OK) {
            startActivity(new Intent(this, SellActivity.class));
            finish();
        }
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
                updateButton(false);
                getViewModel().saveData();
                mCommoditySelectedAdapter.notifyDataSetChanged();
                ToastUtils.showMessages(BoardActivity.this, R.string.text_save_data_ok);
                Intent intent = new Intent();
                setResult(Activity.RESULT_OK, intent);
                finish();
            } else {
                ToastUtils.showMessages(BoardActivity.this, R.string.msg_not_commodity_selected);
            }
        }

        public void onPay() {
            if (getViewModel().isSelectedCommodity()) {
                Intent intent = new Intent(BoardActivity.this, PaymentActivity.class);
                intent.putExtra(Constant.KEY_NAME_BOARD, getViewModel().getNameBoard());
                intent.putExtra(Constant.KEY_TOTAL_MONEY, getViewModel().getTotalCost());
                intent.putExtra(Constant.KEY_BOARD_ID, getViewModel().getBoard().getIdBoard());
                startActivityForResult(intent, Constant.REQUEST_CODE_PAYMENT);
            } else {
                ToastUtils.showMessages(BoardActivity.this, R.string.msg_not_commodity_selected);
            }
        }

        public void onOrder() {
            mBottomSheetBehavior.setState(BottomSheetBehaviorV2.STATE_EXPANDED);
        }

        public void onClose() {
            mBottomSheetBehavior.setState(BottomSheetBehaviorV2.STATE_HIDDEN);
        }
    }

    public class SelectedCommodityListener implements BaseViewAdapter.Presenter {
        public void onReduce(Commodity commodity, int position) {
            updateButton(!getViewModel().isQuickPay());
            int number = commodity.getNumberCommodity();
            if (number - DELTA_UP_REDUCE > 0) {
                commodity.setNumberCommodity(number - DELTA_UP_REDUCE);
            } else {
                getViewModel().removeSelectedList(position);
            }
            mCommoditySelectedAdapter.notifyDataSetChanged();
            getViewModel().updateTotalCost();
        }

        public void onUpAmount(Commodity commodity) {
            updateButton(!getViewModel().isQuickPay());
            commodity.setNumberCommodity(commodity.getNumberCommodity() + DELTA_UP_REDUCE);
            mCommoditySelectedAdapter.notifyDataSetChanged();
            getViewModel().updateTotalCost();
        }
    }

    public class CommodityListener implements BaseViewAdapter.Presenter {
        public void onSelected(int position) {
            updateButton(!getViewModel().isQuickPay());
            getViewModel().updateSelectedList(position);
            mCommoditySelectedAdapter.notifyDataSetChanged();
            getViewModel().updateTotalCost();
        }
    }
}
