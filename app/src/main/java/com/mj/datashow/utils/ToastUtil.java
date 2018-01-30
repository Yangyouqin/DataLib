package com.mj.datashow.utils;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by YQ on 2017/12/14.
 */

public class ToastUtil {
    public static void showToast(Context context, String s){
        Toast.makeText(context,s,Toast.LENGTH_LONG).show();
    }
}
