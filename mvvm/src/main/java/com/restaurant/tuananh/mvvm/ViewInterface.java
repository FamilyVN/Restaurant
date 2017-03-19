package com.restaurant.tuananh.mvvm;

import android.app.Activity;
import android.databinding.ViewDataBinding;

/**
 * Created by TuanAnh on 3/19/2017.
 */
public interface ViewInterface<TB extends ViewDataBinding, TM extends BaseViewModel> {
    TM getViewModel();
    TB getBinding();
    Activity getActivity();
    int getLayoutId();
}
