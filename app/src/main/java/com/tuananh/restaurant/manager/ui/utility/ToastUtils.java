package com.tuananh.restaurant.manager.ui.utility;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by framgia on 09/10/2016.
 */
public class ToastUtils {
    private static Context sContext;

    public static void ToastUtils(Context context) {
        sContext = context;
    }

    public static void showMessages(int resId) {
        Toast.makeText(sContext, resId, Toast.LENGTH_SHORT);
    }

    public static void showMessages(String messages) {
    }
}
