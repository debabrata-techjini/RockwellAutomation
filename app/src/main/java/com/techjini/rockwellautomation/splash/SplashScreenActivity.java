package com.techjini.rockwellautomation.splash;

import android.os.Bundle;
import com.techjini.rockwellautomation.R;
import com.techjini.rockwellautomation.base.BaseActivity;
import com.techjini.rockwellautomation.setting.SettingsFragment;

public class SplashScreenActivity extends BaseActivity {

  private static final String SETTINGS_POPUP_TAG = "settingsPopup";
  private SettingsFragment settingsFragment;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    showSettingsPopup();
  }

  @Override protected int getLayoutResourceId() {
    return R.layout.activity_splash_screen;
  }

  private void showSettingsPopup() {
    settingsFragment = SettingsFragment.newInstance();
    settingsFragment.show(getSupportFragmentManager(), SETTINGS_POPUP_TAG);
  }
}
