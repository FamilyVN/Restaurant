package com.tuananh.restaurant.manager.view.activity.payment;

import android.content.Intent;
import android.text.TextUtils;

import com.tuananh.restaurant.manager.R;
import com.tuananh.restaurant.manager.databinding.ActivityPaymentBinding;
import com.tuananh.restaurant.manager.model.Constant;
import com.tuananh.restaurant.manager.utils.CommonUtils;
import com.tuananh.restaurant.manager.view.activity.BaseActivityRestaurant;

/**
 * Created by TuanAnh on 5/11/2017.
 */
public class PaymentActivity
    extends BaseActivityRestaurant<ActivityPaymentBinding, PaymentActivityViewModel> {
    private Long mTotalMoney;

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
        mTotalMoney = intent.getLongExtra(Constant.KEY_TOTAL_MONEY, 0L);
        getBinding().textTotalMoneyPaymentActivity.setText(String.format(Constant.FORMAT_MONEY,
            CommonUtils.convertToMoney(mTotalMoney.toString())));
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_payment;
    }

    public void onPay(String values) {
        int type;
        if (TextUtils.equals(values, getString(R.string.text_button_pay))) {
            type = Constant.TYPE_PAY;
        } else if (TextUtils.equals(values, getString(R.string.text_button_pay_exactly))) {
            type = Constant.TYPE_PAY_EXACTLY;
        } else if (TextUtils.equals(values, getString(R.string.text_button_pay_clear_all))) {
            type = Constant.TYPE_CLEAR_ALL;
        } else if (TextUtils.equals(values, getString(R.string.text_button_pay_back))) {
            type = Constant.TYPE_REMOVE;
        } else {
            type = Constant.TYPE_ADD;
        }
        getViewModel().updatePay(values, type);
    }

    public void updateTextCustomersPay(String customersPay, boolean isPayExactly) {
        getBinding().textCustomersPayPaymentActivity.setText(
            String.format(Constant.FORMAT_MONEY, CommonUtils.convertToMoney(
                isPayExactly ? mTotalMoney.toString() : customersPay)));
    }

    public void updateTextRefundsToCustomer(String customersPay, boolean isPayExactly) {
        Long refundsToCustomer = 0L;
        try {
            refundsToCustomer =
                (TextUtils.isEmpty(customersPay) ? 0L : Long.parseLong(customersPay)) - mTotalMoney;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        getBinding().textRefundsToCustomersPaymentActivity.setText(
            String.format(Constant.FORMAT_MONEY, isPayExactly ? "0" :
                CommonUtils.convertToMoney(refundsToCustomer.toString())));
    }
}
