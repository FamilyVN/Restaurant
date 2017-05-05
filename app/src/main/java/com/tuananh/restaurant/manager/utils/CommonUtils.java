package com.tuananh.restaurant.manager.utils;

import android.app.Activity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import java.util.List;

/**
 * Created by framgia on 05/05/2017.
 */
public class CommonUtils {
    public static void setupHideKeyboardUI(final View view) {
        // Set up touch listener for non-text box views to hide keyboard.
        if (!(view instanceof EditText)) {
            view.setOnTouchListener(new View.OnTouchListener() {
                public boolean onTouch(View v, MotionEvent event) {
                    hideSoftKeyboard(v);
                    clearFocus(v);
                    return false;
                }
            });
        } else {
            view.setOnFocusChangeListener(new View.OnFocusChangeListener() {
                @Override
                public void onFocusChange(View view, boolean hasFocus) {
                    if (!hasFocus) {
                        hideSoftKeyboard(view);
                        view.clearFocus();
                    }
                }
            });
        }
        //If a layout container, iterate over children and seed recursion.
        if (view instanceof ViewGroup) {
            view.setFocusableInTouchMode(true);
            view.setClickable(true);
            for (int i = 0; i < ((ViewGroup) view).getChildCount(); i++) {
                View innerView = ((ViewGroup) view).getChildAt(i);
                setupHideKeyboardUI(innerView);
            }
        }
    }

    public static void hideSoftKeyboard(View view) {
        InputMethodManager inputMethodManager =
            (InputMethodManager) view.getContext().getSystemService(Activity.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    public static void clearFocus(View view) {
        if (view.getRootView() == null) return;
        View viewFocus = view.getRootView().findFocus();
        if (viewFocus != null && viewFocus instanceof EditText) {
            viewFocus.clearFocus();
        }
    }

    public static boolean isEmptyList(List objectList) {
        return !(objectList != null && objectList.size() > 0);
    }
}
