package com.techjini.rockwellautomation.product.browse;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import com.techjini.rockwellautomation.R;
import com.techjini.rockwellautomation.product.browse.encompasspartners.EncompassPartnersFragment;
import com.techjini.rockwellautomation.product.browse.rockwellautomation.RockwellAutomationFragment;

/**
 * Created by Debu
 */
public class ProductsBrowsePagerAdapter extends FragmentStatePagerAdapter {

  private Context context;
  // It's assumed that the tabCount is 2
  private int tabCount;

  public ProductsBrowsePagerAdapter(FragmentManager fragmentManager, Context context,
      int tabCount) {
    super(fragmentManager);

    this.context = context;
    this.tabCount = tabCount;
  }

  @Override public Fragment getItem(int position) {
    switch (position) {
      case 0:
        return RockwellAutomationFragment.newInstance();
      case 1:
        return EncompassPartnersFragment.newInstance();
      default:
        return null;
    }
  }

  @Override public int getCount() {
    return tabCount;
  }

  @Override public CharSequence getPageTitle(int position) {
    switch (position) {
      case 0:
        return context.getText(R.string.page_title_rockwell_automation);
      case 1:
        return context.getText(R.string.page_title_encompass_partners);
      default:
        return null;
    }
  }
}
