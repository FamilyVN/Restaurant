package com.tuananh.restaurant.manager.view.activity.payment;

import android.text.TextUtils;

import com.tuananh.restaurant.manager.R;
import com.tuananh.restaurant.manager.databinding.ActivityPaymentBinding;
import com.tuananh.restaurant.manager.view.activity.BaseActivityRestaurant;

/**
 * Created by TuanAnh on 5/11/2017.
 */
public class PaymentActivity
    extends BaseActivityRestaurant<ActivityPaymentBinding, PaymentActivityViewModel> {
    @Override
    public int getLayoutId() {
        return R.layout.activity_payment;
    }

    public void onPay(String aString) {
        String packageName = getPackageName();
        int resId = getResources().getIdentifier(aString, "string", packageName);
        String values = resId == 0 ? aString : getResources().getString(resId);
        if (TextUtils.equals(values, getString(R.string.text_button_pay))) {
        } else if (TextUtils.equals(values, getString(R.string.text_button_pay_exactly))) {
        } else if (TextUtils.equals(values, getString(R.string.text_button_pay_clear_all))) {
        }
    }
}
