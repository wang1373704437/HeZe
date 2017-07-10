package com.linkct.hzdlzcgl.utils;

import android.app.Activity;
import android.content.Context;
import android.util.TypedValue;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;

/**
 * Created by wlh on 2017/7/1.
 */

public class ViewUtils {
    public static int dp2px(int dp, Context context) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp,
                context.getResources().getDisplayMetrics());
    }

    public static void hideSoftInputView(Context context) {
        InputMethodManager manager = ((InputMethodManager) context
                .getSystemService(Activity.INPUT_METHOD_SERVICE));
        if (((Activity)context).getWindow().getAttributes().softInputMode != WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN) {
            if (((Activity)context).getCurrentFocus() != null)
                manager.hideSoftInputFromWindow(((Activity)context).getCurrentFocus()
                        .getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }
}
