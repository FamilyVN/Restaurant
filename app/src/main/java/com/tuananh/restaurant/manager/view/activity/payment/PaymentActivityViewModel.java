package com.tuananh.restaurant.manager.view.activity.payment;

import com.restaurant.tuananh.mvvm.BaseViewModel;
import com.tuananh.restaurant.manager.databinding.ActivityPaymentBinding;
import com.tuananh.restaurant.manager.model.Constant;
import com.tuananh.restaurant.manager.utils.CommonUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by TuanAnh on 5/11/2017.
 */
public class PaymentActivityViewModel
    extends BaseViewModel<ActivityPaymentBinding, PaymentActivity> {
    private static final int MAX_LENGTH = 10;
    private List<String> mCustomersPayList = new ArrayList<>();

    public void updatePay(String values, int type) {
        switch (type) {
            case Constant.TYPE_ADD:
                int length = mCustomersPayList.size();
                if (length < MAX_LENGTH &&
                    CommonUtils.convertListToString(mCustomersPayList).length() < MAX_LENGTH) {
                    mCustomersPayList.add(values);
                }
                updateCost(false);
                break;
            case Constant.TYPE_REMOVE:
                int size = mCustomersPayList.size();
                if (size >= 1) {
                    mCustomersPayList.remove(size - 1);
                }
                updateCost(false);
                break;
            case Constant.TYPE_CLEAR_ALL:
                mCustomersPayList.clear();
                updateCost(false);
                break;
            case Constant.TYPE_PAY_EXACTLY:
                mCustomersPayList.clear();
                updateCost(true);
                break;
            case Constant.TYPE_PAY:
                onPay();
                break;
        }
    }

    private void onPay() {
    }

    public void updateCost(boolean isPayExactly) {
        String customersPay = CommonUtils.convertListToString(mCustomersPayList);
        getView().updateTextCustomersPay(
            CommonUtils.isEmptyList(mCustomersPayList) ? "0" : customersPay, isPayExactly);
        getView().updateTextRefundsToCustomer(customersPay, isPayExactly);
    }
}
