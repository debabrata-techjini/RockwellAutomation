package com.techjini.rockwellautomation.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.WindowManager;

/**
 * Created by Rupak, Debu
 */
public class AppUtil {

  private static int displayWidthInPixel;
  private static int displayHeightInPixel;
  private static float displayDensity;

  public static boolean isTablet(Context context) {
    return (context.getResources().getConfiguration().screenLayout
        & Configuration.SCREENLAYOUT_SIZE_MASK) >= Configuration.SCREENLAYOUT_SIZE_LARGE;
  }

  public static boolean getIsSettingsSavedFromPref(Context context) {
    boolean isSettingsSaved;
    SharedPreferences sharedPref =
        context.getSharedPreferences(Constants.PREF_FILE_NAME, Context.MODE_PRIVATE);
    isSettingsSaved = sharedPref.getBoolean(Constants.PREF_KEY_IS_SETTINGS_SAVED,
        Constants.DEFAULT_IS_SETTINGS_SAVED_VALUE);
    return isSettingsSaved;
  }

  public static void setIsSettingsSavedToPref(Context context, boolean isSettingsSaved) {
    SharedPreferences sharedPref =
        context.getSharedPreferences(Constants.PREF_FILE_NAME, Context.MODE_PRIVATE);
    SharedPreferences.Editor editor = sharedPref.edit();
    editor.putBoolean(Constants.PREF_KEY_IS_SETTINGS_SAVED, isSettingsSaved);
    editor.commit();
  }

  private static void setDisplayMetrics(Context context) {
    WindowManager windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
    Display display = windowManager.getDefaultDisplay();
    DisplayMetrics displayMetrics = new android.util.DisplayMetrics();
    display.getMetrics(displayMetrics);
    displayWidthInPixel = displayMetrics.widthPixels;
    displayHeightInPixel = displayMetrics.heightPixels;
    displayDensity = displayMetrics.density;
  }

  public static int getDisplayWidthInDp(Context context) {
    setDisplayMetrics(context);
    return convertPixelToDp(context, displayWidthInPixel);
  }

  public static int getDisplayHeightInDp(Context context) {
    setDisplayMetrics(context);
    return convertPixelToDp(context, displayHeightInPixel);
  }

  public static float getDisplayDensity(Context context) {
    setDisplayMetrics(context);
    return displayDensity;
  }

  public static int convertPixelToDp(Context context, int pixel) {
    DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
    return Math.round(pixel / displayMetrics.density);
  }
}
