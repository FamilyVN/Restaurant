package com.tuananh.restaurant.manager.view.activity.board;

import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.restaurant.tuananh.mvvm.recycler.SingleTypeAdapter;
import com.tuananh.restaurant.manager.R;
import com.tuananh.restaurant.manager.data.model.commodity.Commodity;
import com.tuananh.restaurant.manager.databinding.ActivityBoardBinding;
import com.tuananh.restaurant.manager.utility.ToastUtils;
import com.tuananh.restaurant.manager.view.activity.BaseActivityRestaurant;

import java.util.List;

/**
 * Created by framgia on 05/05/2017.
 */
public class BoardActivity
    extends BaseActivityRestaurant<ActivityBoardBinding, BoardActivityViewModel> {
    private SingleTypeAdapter<Commodity> mCommoditySelectedAdapter;

    @Override
    protected void onViewCreated() {
        super.onViewCreated();
        getBinding().setListener(new BoardListener());
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
        mCommoditySelectedAdapter = new SingleTypeAdapter<>(this, R.layout.item_commodity);
        mCommoditySelectedAdapter.addAll(commoditySelectedList);
        getBinding().setCommoditySelectedAdapter(mCommoditySelectedAdapter);
        getBinding().setCommoditySelectedLayoutManager(new LinearLayoutManager(this));
    }

    public void showTotalCost(int totalCost) {
        getBinding().textBoardActivityTotalMoney.setText(String.format("%d đ", totalCost));
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
    }
}
