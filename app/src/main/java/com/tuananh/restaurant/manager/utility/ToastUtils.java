package com.tuananh.restaurant.manager.utility;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by framgia on 09/10/2016.
 */
public class ToastUtils {
    public static void showMessages(Context context, int resId) {
        Toast.makeText(context, resId, Toast.LENGTH_SHORT).show();
    }

    public static void showMessages(Context context, String messages) {
        Toast.makeText(context, messages, Toast.LENGTH_SHORT).show();
        ;
    }
}
