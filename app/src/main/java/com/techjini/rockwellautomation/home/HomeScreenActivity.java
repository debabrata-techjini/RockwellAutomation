package com.techjini.rockwellautomation.home;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.FrameLayout;
import com.techjini.rockwellautomation.R;
import com.techjini.rockwellautomation.base.BaseActivity;

/**
 * Created by Rupak, Debu
 */
public class HomeScreenActivity extends BaseActivity {

  private static final String TAG = HomeScreenActivity.class.getSimpleName();
  private FrameLayout frameLayoutLeftPanel;
  private FrameLayout frameLayoutRightPanel;

  @Override protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_home_screen);

    frameLayoutLeftPanel = (FrameLayout) rootView.findViewById(R.id.frameLayoutLeftPanel);
    frameLayoutRightPanel = (FrameLayout) rootView.findViewById(R.id.frameLayoutRightPanel);
  }

  @Override protected int getLayoutResourceId() {
    return R.layout.activity_home_screen;
  }
}
