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
    private List<String> mCustomersPayList = new ArrayList<>();

    public void updatePay(String values, int type) {
        switch (type) {
            case Constant.TYPE_ADD:
                mCustomersPayList.add(values);
                break;
            case Constant.TYPE_REMOVE:
                int size = mCustomersPayList.size();
                if (size >= 1) {
                    mCustomersPayList.remove(size - 1);
                }
                break;
            case Constant.TYPE_CLEAR_ALL:
                mCustomersPayList.clear();
                break;
        }
        getView().updateTextCustomersPay(CommonUtils.convertListToString(mCustomersPayList));
    }
}
