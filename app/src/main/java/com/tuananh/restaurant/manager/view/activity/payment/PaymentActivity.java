package com.tuananh.restaurant.manager.view.activity.payment;

import android.content.Intent;
import android.text.TextUtils;

import com.tuananh.restaurant.manager.R;
import com.tuananh.restaurant.manager.databinding.ActivityPaymentBinding;
import com.tuananh.restaurant.manager.model.Constant;
import com.tuananh.restaurant.manager.view.activity.BaseActivityRestaurant;

/**
 * Created by TuanAnh on 5/11/2017.
 */
public class PaymentActivity
    extends BaseActivityRestaurant<ActivityPaymentBinding, PaymentActivityViewModel> {
    private int mTotalMoney;

    @Override
    protected void onViewCreated() {
        super.onViewCreated();
        getBinding().setListener(this);
        setupBoardInformation();
    }

    private void setupBoardInformation() {
        Intent intent = getIntent();
        if (intent == null) return;
        getBinding().textNameBoardPaymentActivity
            .setText(intent.getStringExtra(Constant.KEY_NAME_BOARD));
        mTotalMoney = intent.getIntExtra(Constant.KEY_TOTAL_MONEY, 0);
        getBinding().textTotalMoneyPaymentActivity.setText(String.format("%s đ", mTotalMoney));
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_payment;
    }

    public void onPay(String values) {
        if (TextUtils.equals(values, getString(R.string.text_button_pay))) {
        } else if (TextUtils.equals(values, getString(R.string.text_button_pay_exactly))) {
        } else if (TextUtils.equals(values, getString(R.string.text_button_pay_clear_all))) {
            getViewModel().updatePay(values, Constant.TYPE_CLEAR_ALL);
        } else if (TextUtils.equals(values, getString(R.string.text_button_pay_back))) {
            getViewModel().updatePay(values, Constant.TYPE_REMOVE);
        } else {
            getViewModel().updatePay(values, Constant.TYPE_ADD);
        }
    }

    public void updateTextCustomersPay(String customersPay) {
        getBinding().textCustomersPayPaymentActivity.setText(customersPay);
    }
}
