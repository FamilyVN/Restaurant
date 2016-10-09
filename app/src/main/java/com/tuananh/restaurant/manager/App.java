package com.tuananh.restaurant.manager;

import android.app.Application;

import com.tuananh.restaurant.manager.ui.utility.ToastUtils;

/**
 * Created by framgia on 09/10/2016.
 */
public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        ToastUtils.ToastUtils(this);
    }
}
