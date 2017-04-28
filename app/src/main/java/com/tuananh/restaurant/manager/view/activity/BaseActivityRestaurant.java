package com.tuananh.restaurant.manager.view.activity;

import android.databinding.ViewDataBinding;

import com.restaurant.tuananh.mvvm.BaseActivityHeaderFooter;
import com.restaurant.tuananh.mvvm.BaseViewModel;
import com.restaurant.tuananh.mvvm.ViewInterface;

/**
 * Created by framgia on 03/05/2017.
 */
public abstract class BaseActivityRestaurant<TB extends ViewDataBinding, TM extends BaseViewModel>
    extends BaseActivityHeaderFooter<TB, TM> implements ViewInterface {
    @Override
    protected void onViewCreated() {
        super.onViewCreated();
    }
}
