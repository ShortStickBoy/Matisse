/*
 * Copyright 2017 Zhihu Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.sunzn.matisse.library.internal.utils;

import android.content.Context;
import android.util.DisplayMetrics;
import android.view.WindowManager;

public class UIUtils {

    public static int spanCount(Context context, int gridExpectedSize) {
        int screenWidth = context.getResources().getDisplayMetrics().widthPixels;
        float expected = (float) screenWidth / (float) gridExpectedSize;
        int spanCount = Math.round(expected);
        if (spanCount == 0) {
            spanCount = 1;
        }
        return spanCount;
    }

    public static int getScreenWidth(Context context) {
        context = context.getApplicationContext();
        WindowManager manager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics dm = new DisplayMetrics();     // 创建了一张白纸
        manager.getDefaultDisplay().getMetrics(dm);   // 给白纸设置宽高
        return dm.widthPixels;
    }

    public static int getScreenHeight(Context context) {
        context = context.getApplicationContext();
        WindowManager manager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics dm = new DisplayMetrics();     // 创建了一张白纸
        manager.getDefaultDisplay().getMetrics(dm);   // 给白纸设置宽高
        return dm.heightPixels;
    }

}
