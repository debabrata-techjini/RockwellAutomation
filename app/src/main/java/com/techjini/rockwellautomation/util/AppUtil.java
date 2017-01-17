package com.techjini.rockwellautomation.util;

import android.content.Context;
import android.content.res.Configuration;

/**
 * Created by Debu
 */
public class AppUtil {

  public static boolean isTablet(Context context) {
    return (context.getResources().getConfiguration().screenLayout
        & Configuration.SCREENLAYOUT_SIZE_MASK) >= Configuration.SCREENLAYOUT_SIZE_LARGE;
  }
}
