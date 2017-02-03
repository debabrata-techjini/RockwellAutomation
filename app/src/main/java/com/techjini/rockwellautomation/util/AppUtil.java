package com.techjini.rockwellautomation.util;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import com.techjini.rockwellautomation.product.browse.ProductGroup;
import com.techjini.rockwellautomation.product.browse.category.Category;
import com.techjini.rockwellautomation.product.browse.subcategory.SubCategory;
import java.util.ArrayList;
import java.util.List;

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

  public static void hideKeyboard(Activity activity) {
    InputMethodManager inputMethodManager =
        (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
    View view = activity.getCurrentFocus();

    if (inputMethodManager.isAcceptingText() && view != null) {
      inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }
  }

  // TODO: Need to implement; currently, it returns a dummy list of product groups
  public static List<ProductGroup> getListOfProductGroups() {
    List<ProductGroup> listOfProductGroups = new ArrayList<>();

    ProductGroup productGroup1 =
        new ProductGroup(1, "D product group name", "Product group details goes here.",
            "Description about the product group goes here.", null, getListOfCategories(1));
    ProductGroup productGroup2 =
        new ProductGroup(2, "N product group name", "Product group details goes here.",
            "Description about the product group goes here.", null, getListOfCategories(2));
    ProductGroup productGroup3 =
        new ProductGroup(3, "S product group name", "Product group details goes here.",
            "Description about the product group goes here.", null, getListOfCategories(3));
    ProductGroup productGroup4 =
        new ProductGroup(4, "T product group name", "Product group details goes here.",
            "Description about the product group goes here.", null, getListOfCategories(4));
    ProductGroup productGroup5 =
        new ProductGroup(5, "P product group name", "Product group details goes here.",
            "Description about the product group goes here.", null, getListOfCategories(5));

    listOfProductGroups.add(productGroup1);
    listOfProductGroups.add(productGroup2);
    listOfProductGroups.add(productGroup3);
    listOfProductGroups.add(productGroup4);
    listOfProductGroups.add(productGroup5);

    return listOfProductGroups;
  }

  // TODO: Need to implement; currently, it returns a dummy list of categories
  private static List<Category> getListOfCategories(int productGroupId) {
    List<Category> listOfCategories = new ArrayList<>();

    Category category1 =
        new Category(1, "Category 1" + productGroupId, getListOfSubCategories(productGroupId, 1));
    Category category2 =
        new Category(2, "Category 2" + productGroupId, getListOfSubCategories(productGroupId, 2));
    Category category3 =
        new Category(3, "Category 3" + productGroupId, getListOfSubCategories(productGroupId, 3));
    Category category4 =
        new Category(4, "Category 4" + productGroupId, getListOfSubCategories(productGroupId, 4));
    Category category5 =
        new Category(5, "Category 5" + productGroupId, getListOfSubCategories(productGroupId, 5));

    listOfCategories.add(category1);
    listOfCategories.add(category2);
    listOfCategories.add(category3);
    listOfCategories.add(category4);
    listOfCategories.add(category5);

    return listOfCategories;
  }

  // TODO: Need to implement; currently, it returns a dummy list of sub categories
  private static List<SubCategory> getListOfSubCategories(int productGroupId, int categoryId) {
    List<SubCategory> listOfSubCategories = new ArrayList<>();

    SubCategory subCategory1 =
        new SubCategory(1, "Sub category 1" + productGroupId + categoryId, null);
    SubCategory subCategory2 =
        new SubCategory(2, "Sub category 2" + productGroupId + categoryId, null);
    SubCategory subCategory3 =
        new SubCategory(3, "Sub category 3" + productGroupId + categoryId, null);
    SubCategory subCategory4 =
        new SubCategory(4, "Sub category 4" + productGroupId + categoryId, null);
    SubCategory subCategory5 =
        new SubCategory(5, "Sub category 5" + productGroupId + categoryId, null);

    listOfSubCategories.add(subCategory1);
    listOfSubCategories.add(subCategory2);
    listOfSubCategories.add(subCategory3);
    listOfSubCategories.add(subCategory4);
    listOfSubCategories.add(subCategory5);

    return listOfSubCategories;
  }
}
