package com.techjini.rockwellautomation.home;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import com.techjini.rockwellautomation.R;
import com.techjini.rockwellautomation.base.BaseActivity;
import com.techjini.rockwellautomation.menu.MenuFragment;
import com.techjini.rockwellautomation.product.browse.ProductsBrowseFragment;
import com.techjini.rockwellautomation.product.search.ProductsSearchFragment;
import com.techjini.rockwellautomation.util.AppUtil;

/**
 * Created by Rupak, Debu
 */
public class HomeScreenActivity extends BaseActivity
    implements ProductsBrowseFragment.OnSearchClickListener,
    ProductsSearchFragment.OnBrowseClickListener {

  private static final String TAG = HomeScreenActivity.class.getSimpleName();
  private MenuFragment menuFragment;
  private ProductsBrowseFragment productsBrowseFragment;
  private ProductsSearchFragment productsSearchFragment;

  @Override protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_home_screen);

    Log.d(TAG, "Display width in dp: " + AppUtil.getDisplayWidthInDp(this));
    Log.d(TAG, "Display height in dp: " + AppUtil.getDisplayHeightInDp(this));
    Log.d(TAG, "Display density: " + AppUtil.getDisplayDensity(this));

    addMenuFragment();
    addProductsBrowseFragment();
  }

  @Override protected int getLayoutResourceId() {
    return R.layout.activity_home_screen;
  }

  private void addMenuFragment() {
    FragmentManager fragmentManager = getSupportFragmentManager();
    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
    menuFragment = MenuFragment.newInstance();
    fragmentTransaction.add(R.id.frameLayoutLeftPanel, menuFragment);
    fragmentTransaction.commit();
  }

  private void addProductsBrowseFragment() {
    FragmentManager fragmentManager = getSupportFragmentManager();
    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
    productsBrowseFragment = ProductsBrowseFragment.newInstance();
    fragmentTransaction.replace(R.id.frameLayoutRightPanel, productsBrowseFragment);
    fragmentTransaction.commit();
  }

  private void addProductsSearchFragment() {
    FragmentManager fragmentManager = getSupportFragmentManager();
    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
    productsSearchFragment = ProductsSearchFragment.newInstance();
    fragmentTransaction.replace(R.id.frameLayoutRightPanel, productsSearchFragment);
    fragmentTransaction.commit();
  }

  @Override public void onSearchClick() {
    addProductsSearchFragment();
  }

  @Override public void onBrowseClick() {
    addProductsBrowseFragment();
  }
}
