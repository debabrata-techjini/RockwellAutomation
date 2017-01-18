package com.techjini.rockwellautomation.home;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.widget.FrameLayout;
import com.techjini.rockwellautomation.R;
import com.techjini.rockwellautomation.base.BaseActivity;
import com.techjini.rockwellautomation.menu.MenuFragment;
import com.techjini.rockwellautomation.util.AppUtil;

/**
 * Created by Rupak, Debu
 */
public class HomeScreenActivity extends BaseActivity {

  private static final String TAG = HomeScreenActivity.class.getSimpleName();
  private FrameLayout frameLayoutLeftPanel;
  private FrameLayout frameLayoutRightPanel;
  private MenuFragment menuFragment;

  @Override protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_home_screen);

    frameLayoutLeftPanel = (FrameLayout) rootView.findViewById(R.id.frameLayoutLeftPanel);
    frameLayoutRightPanel = (FrameLayout) rootView.findViewById(R.id.frameLayoutRightPanel);

    Log.d(TAG, "Display width in dp: " + AppUtil.getDisplayWidthInDp(this));
    Log.d(TAG, "Display height in dp: " + AppUtil.getDisplayHeightInDp(this));
    Log.d(TAG, "Display density: " + AppUtil.getDisplayDensity(this));

    addMenuFragment();
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
}
