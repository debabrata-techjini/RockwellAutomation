package com.techjini.rockwellautomation.product.browse;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.techjini.rockwellautomation.R;
import com.techjini.rockwellautomation.base.BaseFragment;

/**
 * Created by Debu
 */
public class ProductsBrowseFragment extends BaseFragment implements ViewPager.OnPageChangeListener {

  private TabLayout tabLayoutProductsBrowse;
  private ViewPager viewPagerProductsBrowse;
  private ProductsBrowsePagerAdapter productsBrowsePagerAdapter;
  private int pageCount;
  // Current page position starts from 0
  private int currentPagePosition;

  public ProductsBrowseFragment() {
    // Required empty public constructor
  }

  public static ProductsBrowseFragment newInstance() {
    return new ProductsBrowseFragment();
  }

  @Override public View onCreateView(LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {
    // Inflate the layout for this fragment
    View view = inflater.inflate(R.layout.fragment_products_browse, container, false);

    tabLayoutProductsBrowse = (TabLayout) view.findViewById(R.id.tabLayoutProductsBrowse);
    viewPagerProductsBrowse = (ViewPager) view.findViewById(R.id.viewPagerProductsBrowse);

    pageCount = 2;
    currentPagePosition = 0;

    setUpProductsBrowseViewPager();

    return view;
  }

  @Override public void onResume() {
    super.onResume();

    currentPagePosition = viewPagerProductsBrowse.getCurrentItem();
  }

  @Override public void onPause() {
    super.onPause();

    viewPagerProductsBrowse.setCurrentItem(currentPagePosition);
  }

  private void setUpProductsBrowseViewPager() {
    productsBrowsePagerAdapter =
        new ProductsBrowsePagerAdapter(baseActivity.getSupportFragmentManager(), context,
            pageCount);
    viewPagerProductsBrowse.setAdapter(productsBrowsePagerAdapter);
    viewPagerProductsBrowse.setCurrentItem(currentPagePosition);
    viewPagerProductsBrowse.addOnPageChangeListener(this);
    tabLayoutProductsBrowse.setupWithViewPager(viewPagerProductsBrowse);
  }

  @Override
  public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

  }

  @Override public void onPageSelected(int position) {

  }

  @Override public void onPageScrollStateChanged(int state) {

  }
}
