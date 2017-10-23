package com.tuananh.restaurant.manager.view.activity;

import android.databinding.ViewDataBinding;

import com.restaurant.tuananh.mvvm.BaseActivityHeaderFooter;
import com.restaurant.tuananh.mvvm.BaseViewModel;
import com.restaurant.tuananh.mvvm.ViewInterface;
import com.tuananh.restaurant.manager.R;

/**
 * Created by framgia on 03/05/2017.
 */
public abstract class BaseActivityRestaurant<TB extends ViewDataBinding, TM extends BaseViewModel>
    extends BaseActivityHeaderFooter<TB, TM> implements ViewInterface {
    @Override
    protected void onViewCreated() {
        super.onViewCreated();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.slide_in_from_left, R.anim.slide_out_to_right);
    }
}
